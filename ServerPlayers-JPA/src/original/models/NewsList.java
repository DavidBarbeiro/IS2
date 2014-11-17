/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package original.models;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author joao
 */
@XmlRootElement(name = "news_list")
public class NewsList implements Serializable{
    private static final long serialVersionUID = 9125277018717732648L;
    
    @XmlElement(name = "count")
    private int news_number;
    
    @XmlElement(name = "section")
    private ArrayList<NewsSection> news_list;
    
    public NewsList() {
        news_list = new ArrayList<NewsSection>();
    }
    
    public int getNewsCount() {
        int count = 0;
        for( NewsSection section : news_list) {
            count += section.getNews().size();
        }
        news_number = count;
        return count;
    }

    public ArrayList<NewsSection> getSections() {
        return news_list;
    }

    public void addSection(NewsSection s) {
        if(s.getNews().size() == 0)
            return;
        news_list.add(s);
    }
    
    static public NewsList unmarshalNewsList(String text){
        JAXBContext jaxbContext=null;
        Unmarshaller unmarshaller = null;
        NewsList newsList = null;
        try { 
            // Source
            jaxbContext = JAXBContext.newInstance(NewsList.class);
            unmarshaller = jaxbContext.createUnmarshaller();
        
            ByteArrayInputStream input = new ByteArrayInputStream (text.getBytes());
            newsList = (NewsList) unmarshaller.unmarshal(input);
        } 
        catch (JAXBException ex) {
            System.out.println("There was a problem with the received XML");
        }
        return newsList;
    }
}
