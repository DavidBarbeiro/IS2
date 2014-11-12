/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package original.models;

import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author joao
 */
@XmlRootElement(name = "news")
@XmlAccessorType(XmlAccessType.FIELD)
public class News implements Serializable {
    
    private static final long serialVersionUID = 9125277018717732648L;
    
    @XmlElement(name = "title", required = true)
    private String title;
    
    @XmlElement(name = "link")
    private String link;
    
    @XmlElement(name = "highlights")
    private String highlights;
    
    @XmlSchemaType(name = "dateTime")
    protected Date date;
    
    @XmlElement(name = "author")
    private String author;
    
    @XmlElement(name = "text", required = true)
    private String text;
    
    @XmlElement(name = "media_urls")
    private String media_url;
    
    public String getText() {
        return text;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public Date getDate() {
        return date;
    }


    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(String value) throws ParseException, DatatypeConfigurationException {
        this.date = stringToXMLGregorianCalendar(value).toGregorianCalendar().getTime();
    }
    
     public XMLGregorianCalendar stringToXMLGregorianCalendar(String s) 
     throws ParseException, 
            DatatypeConfigurationException
 {
     
    XMLGregorianCalendar result = null;
    Date date;
    SimpleDateFormat simpleDateFormat;
    GregorianCalendar gregorianCalendar;

    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                   date = simpleDateFormat.parse(s);        
                   gregorianCalendar = 
                       (GregorianCalendar)GregorianCalendar.getInstance();
                   gregorianCalendar.setTime(date);
                   result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
                   return result;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setMediaUrl(String media_url) {
        this.media_url=media_url;
    }
    
    public String getMedia_Url(){
        return this.media_url;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String root, String link) {
        //Validar se o link é correcto
        try {
            URL url = new URL(link);
            this.link = link;
        } catch ( Exception e) {
            try {
                URL url = new URL(root+link);
                this.link = root + link;
            } catch (Exception ex) {
                System.out.println("This link is weird... ("+ root + link +")");
            }
        }   
    }
    
    
}
