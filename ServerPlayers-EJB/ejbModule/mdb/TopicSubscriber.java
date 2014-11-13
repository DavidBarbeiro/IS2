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
        // TODO Auto-generated constructor stub
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

        
        ArrayList<original.models.NewsSection> sections = globalNewsList.getSections();
        ArrayList<models.News> DBnewsList = new ArrayList<models.News>(); 
        for (original.models.NewsSection newsSection : sections) {
			List<original.models.News> newsList = newsSection.getNews();
			for (original.models.News news : newsList) {
				models.News tempNews = new models.News();
				DBnewsList.add(tempNews);
				tempNews.setTitle(news.getTitle());
				//tempNews.setAuthor(news.getAuthor());
				tempNews.setHighlights(news.getHighlights());
				tempNews.setLink(news.getLink());
				tempNews.setText(news.getText());
				tempNews.setDate(news.getDate());
				tempNews.setMediaURL(news.getMedia_Url());
				tempNews.setSection(newsSection.getName());
				em.persist(tempNews);
			}
		}
        
        System.out.println("Database populated");
        
        

    }

}
