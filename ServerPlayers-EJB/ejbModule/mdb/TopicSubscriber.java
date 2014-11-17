package mdb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Message-Driven Bean implementation class for: TopicSubscriber
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "topic/test"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "topic/test")
public class TopicSubscriber implements MessageListener {
	@PersistenceContext(name="TestPersistence")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public TopicSubscriber() {
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	System.out.println("Message received from topic");
        ObjectMessage obj = (ObjectMessage)message;
        original.models.NewsList globalNewsList= null;
        try {
			globalNewsList = (original.models.NewsList)obj.getObject();
		} catch (JMSException e) {
			e.printStackTrace();
		}        
        //The received message contains a list of sections which contains a list of news
        ArrayList<original.models.NewsSection> sections = globalNewsList.getSections();
        for (original.models.NewsSection newsSection : sections) {
			List<original.models.News> newsList = newsSection.getNews();			
			for (original.models.News news : newsList) {
				//We only add news that are unique in their title&date
				Query query = em.createQuery("Select n FROM News n WHERE n.title = :title and n.date = :date");
				query.setParameter("title", news.getTitle());
				query.setParameter("date", news.getDate());
				models.News tempNews=null;
				try{
					tempNews = (models.News) query.getSingleResult();
				}catch(Exception e){
					//System.out.println( e.getMessage() );
				}
				if(tempNews==null){
					tempNews = new models.News();
					original.models.Authors authors1 = news.getAuthors();
					List<String> authors = authors1.getAuthors();
					ArrayList<models.Author> authorsDB=new ArrayList<models.Author>();
					for (String name : authors) {
						query = em.createQuery("Select a FROM Author a WHERE a.name = :name");
						query.setParameter("name", name);
						models.Author author=null;
						try{
							author = (models.Author)query.getSingleResult();
						}catch(Exception e){
							System.out.println( e.getMessage() );
						}
						if(author==null){
							author = new models.Author(name);
							em.persist(author);
						}
						authorsDB.add(author);
					}
					tempNews.setAuthors(authorsDB);
					tempNews.setTitle(news.getTitle());
					tempNews.setHighlights(news.getHighlights().getHighlights());
					tempNews.setLink(news.getLink());
					tempNews.setText(news.getText());
					tempNews.setDate(news.getDate());
					tempNews.setMediaURL(news.getMedia_Url());
					tempNews.setSection(newsSection.getName());
					em.persist(tempNews);
				}
			}
		}
        
        System.out.println("Database populated");
    }

}
