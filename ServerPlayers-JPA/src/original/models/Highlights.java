package original.models;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "highlights")
@XmlAccessorType(XmlAccessType.FIELD)
public class Highlights implements Serializable{
    private static final long serialVersionUID = 9125277018717732648L;

    @XmlElement(name = "highlight")
    private List<String> highlights;

    public Highlights() {
    	highlights=new ArrayList<String>();
    }
    
    public List<String> getHighlights() {
        return highlights;
    }

    public void setMedia_url(List<String> media_url) {
        this.highlights = media_url;
    }
    
    public void addHighlight(String highlight) {
        if(this.highlights == null)
            this.highlights = new ArrayList<String>();
        this.highlights.add(highlight);
    }
}
