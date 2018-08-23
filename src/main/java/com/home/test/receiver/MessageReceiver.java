package com.home.test.receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	private static String subject = "Test";
	
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection conn = connectionFactory.createConnection();
		conn.start();
		
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination dest = session.createQueue(subject);
		
		MessageConsumer consumer = session.createConsumer(dest);
		
		Message msg = consumer.receive();
		
		if(msg instanceof TextMessage) {
			TextMessage tMsg = (TextMessage) msg;
			System.out.println("@@ Received: @@ " + tMsg.getText());
		}
	}
}
