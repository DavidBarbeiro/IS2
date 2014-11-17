/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package original.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author joao
 */
@XmlRootElement(name = "section")
@XmlType(propOrder = { "name", "link", "news_list" })
public class NewsSection implements Serializable{
    private static final long serialVersionUID = 9125277018717732648L;
    
    private String name;
    private String link;
    
    @XmlElement(name = "news")
    private List<News> news_list;

    public NewsSection() {
        news_list = new ArrayList<News>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<News> getNews() {
        return news_list;
    }

    public void addNews(News news) {
        //if( news.getText().isEmpty() )
            //return;
        news_list.add(news);
    }
    
    
}
