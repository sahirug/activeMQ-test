package com.home.test.sender;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageSender {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String subject = "Test";
	
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection conn = connectionFactory.createConnection();
		conn.start();
		
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination dest = session.createQueue(subject);
		
		MessageProducer producer = session.createProducer(dest);
		
		TextMessage msg = session.createTextMessage("Hello world3!");
		
		producer.send(msg);
		
		System.out.println("@@ Sending... @@ " + msg.getText());
		conn.close();
	}
}
