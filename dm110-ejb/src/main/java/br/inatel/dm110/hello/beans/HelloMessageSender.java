package br.inatel.dm110.hello.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@Stateless
public class HelloMessageSender {
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connFactory;
	
	@Resource(lookup = "java:/jms/queue/dm110queue")
	private Queue queue;
	
	public void sendHelloMessage(String textMessage) throws JMSException {
		
		try {
			Connection conn = connFactory.createConnection();
			
			Session session = conn.createSession();
			
			MessageProducer producer = session.createProducer(queue);
			
			TextMessage msg = session.createTextMessage(textMessage);
			
			producer.send(msg);
			
		}catch(JMSException e) {
			e.printStackTrace();
		}
		
		
	}

}
