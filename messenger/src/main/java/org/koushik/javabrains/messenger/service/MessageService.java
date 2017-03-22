package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService()
	{
		messages.put(1L, new Message(1, "Hello Sonal!!", "Sonal"));
		messages.put(2L, new Message(2, "Hello Sachin!!", "Sachin"));
	}
	
	public List<Message> getAllMessages()
	{
		return new ArrayList<Message>(messages.values());
		
	}
	
	//             /messages?year=2015
	public List<Message> getAllMessagesForYear(int year)
	{
		List<Message> messagesYear = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for(Message message : messages.values()){
		    calendar.setTime(message.getCreated());	
		if(calendar.get(Calendar.YEAR) == year){
			messagesYear.add(message);
		}	
		}
		
		return messagesYear;
	}
	
	//            /messages?start=10&size=20
	public List<Message> getAllMessagesPaginated(int start, int size)
	{
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if(start+size > list.size()) return new ArrayList<Message>();	
		return list.subList(start, start+size);
	}
	
	public Message getMessage(long id)
	{
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with id "+ id +"not found");
		}	
		
		return message;
		 
	}
	
	public Message deleteMessage(long id)
	{
		return messages.remove(id);
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}		
	
	public Message updateMessage(Message message)
	{
		if(message.getId()<= 0)
		{
			return null;
		}	
		messages.put(message.getId(), message);
		return message;
	}
		
	/*public List<Message> getAllMessages()
	{
		Message m1 = new Message(1, "Hello World!", "Sonal");
		Message m2 = new Message(2, "Hello Jersey!", "Sonal");
		
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		
		return list;
		
	}*/
	
}
