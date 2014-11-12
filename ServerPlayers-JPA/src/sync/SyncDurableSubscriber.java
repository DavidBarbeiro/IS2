package sync;

import javax.jms.JMSException;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSubscriber;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *  A JMS client example program that synchronously receives a message a Topic
 *     
 *  @author Scott.Stark@jboss.org
 *  @version $Revision: 1.9 $
 */
public class SyncDurableSubscriber
{
    TopicConnection conn = null;
    TopicSession session = null;
    Topic topic = null;
    
    public TopicSubscriber recv;
    // Setup the pub/sub connection, session
    public void setupPubSub(String id)
        throws JMSException, NamingException
    {
        InitialContext iniCtx = new InitialContext();
        Object tmp = iniCtx.lookup("jms/RemoteConnectionFactory");

        TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        conn = tcf.createTopicConnection("joao", "pedro");
        conn.setClientID(id);
        topic = (Topic) iniCtx.lookup("jms/topic/test");

        session = conn.createTopicSession(false,
                                          TopicSession.AUTO_ACKNOWLEDGE);
        conn.start();
        recv = session.createDurableSubscriber(topic, "jms-ex1dtps");
    }
   
    public void stop() 
        throws JMSException
    {   
        session.close();
        conn.close();
    }    

}