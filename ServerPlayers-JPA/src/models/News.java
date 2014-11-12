package models;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: News
 *
 */
@Entity
@Table(name="News",uniqueConstraints=
@UniqueConstraint(columnNames = {"Title","Date"})) 
public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="Title", columnDefinition="TEXT")
	private String title;
	@Column(name="Link",columnDefinition="TEXT")
	private String link;
	@Column(name="Highlights",columnDefinition="TEXT")
	private String highlights;
	@Column(name="Text_content",columnDefinition="TEXT")
	private String text;
	@Temporal(TemporalType.TIMESTAMP)
    private Date date;
	@Column(name="MediaURL",columnDefinition="TEXT")
	private String mediaURL;
	@Column(name="Section")
	private String section;
	

    @ManyToMany
    @JoinTable(name="news_has_authors", joinColumns=
    {@JoinColumn(name="news_id")}, inverseJoinColumns=
      {@JoinColumn(name="authors_id")})
	private List<Author> authors;
	
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public News() {
		super();
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}   
	public String getHighlights() {
		return this.highlights;
	}

	public void setHighlights(String highlights) {
		this.highlights = highlights;
	}   
  
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date text) {
		this.date = text;
	}
	public String getMediaURL() {
		return mediaURL;
	}
	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}
	public String getSection(){
		return this.section;
	}
	public void setSection(String section){
		this.section=section;
	}
   
}
