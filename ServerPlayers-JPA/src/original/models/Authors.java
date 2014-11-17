package original.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authors")
@XmlAccessorType(XmlAccessType.FIELD)
public class Authors implements Serializable{

    private static final long serialVersionUID = 9125277018717732648L;
    
    @XmlElement(name = "author")
    private List<String> authors;

    public Authors() {
    	authors=new ArrayList<String>();
    }
    
    public Authors(List<String> authors) {
		super();
		this.authors = authors;
	}

	public List<String> getAuthors() {
        return authors;
    }

    public void setMedia_url(List<String> authors) {
        this.authors = authors;
    }
    
    public void addAuthor(String author) {
        if(this.authors == null)
            this.authors = new ArrayList<String>();
        this.authors.add(author);
    }
}
