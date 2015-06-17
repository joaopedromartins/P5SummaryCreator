package pt.uc.dei.aor.paj.jms;

import java.io.IOException;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSubscriber;
import javax.jms.TopicSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class DurableTopicRecvClient implements MessageListener
{
    TopicConnection conn = null;
    TopicSession session = null;
    Topic topic = null;
    
    public void setupPubSub()
        throws JMSException, NamingException
    {
        InitialContext iniCtx = new InitialContext();
        Object tmp = iniCtx.lookup("jms/RemoteConnectionFactory");

        TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
        conn = tcf.createTopicConnection("user", "123");
        conn.setClientID("jms-summary");
        topic = (Topic) iniCtx.lookup("jms/topic/PlayTopic");

        session = conn.createTopicSession(false,
                                          TopicSession.AUTO_ACKNOWLEDGE); 
        conn.start();
    }
    
    @Override
    public void onMessage(Message msg) {
     TextMessage tmsg = (TextMessage) msg;
     SummaryCreator creator = new SummaryCreator();
     creator.gotNewMessage(tmsg);     
    }
    
    public void recvAsync()
        throws JMSException, NamingException
    {

        // Setup the pub/sub connection, session
        setupPubSub();

        TopicSubscriber recv = session.createDurableSubscriber(topic, "jms-summary");
        recv.setMessageListener(this);
    }
    
    public  void stop() 
        throws JMSException
    {
        conn.stop();
        session.close();
        conn.close();
    }
    
    public void listen() throws JMSException, NamingException, IOException{
    	System.out.println("À espera de mensagens...");
    	DurableTopicRecvClient client = new DurableTopicRecvClient();
    	client.recvAsync();
    	System.out.println("Press enter to finish...");
        System.in.read();
        client.stop();
        System.out.println("Cliente de leitura terminado!");
    	
    	

    }

    
}
