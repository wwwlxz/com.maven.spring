package com.lxz.activemq.receiver;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.jms.support.JmsUtils;

import com.lxz.activemq.dao.UserMistake;

public class ReceiverListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;
		try{
			UserMistake mail = new UserMistake();
			mail.setUserID(mapMessage.getString("userID"));
			mail.setMistakeID(mapMessage.getString("mistakeID"));
			mail.setTypeID(mapMessage.getString("typeID"));
			mail.setTime(mapMessage.getString("time"));
			displayMail(mail);
		}catch(JMSException e){
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

	private void displayMail(UserMistake userMistake){
		System.out.println("Mail #" + userMistake.getUserID() + "received.");
		System.out.println("Mail #" + userMistake.getMistakeID() + "received.");
		System.out.println("Mail #" + userMistake.getTypeID() + "received.");
		System.out.println("Mail #" + userMistake.getTime() + "received.");
	}
}
