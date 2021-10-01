package com.springboot.app.Sockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.springboot.app.Databases.MessageDatabase;
import com.springboot.app.Databases.TenantDatabase;
import com.springboot.app.Entities.Message;
import com.springboot.app.Entities.Tenant;

@Controller      // this is needed for this to be an endpoint to springboot
@ServerEndpoint(value = "/chat/{username}/{recipient}")  // this is Websocket url
public class ChatSocket {

	
	@Autowired
	TenantDatabase tdb;
	
	
  // cannot autowire static directly (instead we do it by the below
  // method
	private static MessageDatabase msgRepo; 

	/*
   * Grabs the MessageRepository singleton from the Spring Application
   * Context.  This works because of the @Controller annotation on this
   * class and because the variable is declared as static.
   * There are other ways to set this. However, this approach is
   * easiest.
	 */
	@Autowired
	public void setMessageRepository(MessageDatabase repo) {
		msgRepo = repo;  // we are setting the static variable
	}

	// Store all socket session and their corresponding username.
	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(ChatSocket.class);

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username, @PathParam("recipient") String recipient) 
      throws IOException {

		logger.info("Testing stuff");
		
		
		

    // store connecting user information
		sessionUsernameMap.put(session, username);
		usernameSessionMap.put(username, session);
		
		
		
		
		
		//Send chat history to the newly connected user
		sendMessageToPArticularUser(username, getChatHistory(username,recipient));
		
    // broadcast that new user joined
		//String message = "User:" + username + " has Joined the Chat";
		//broadcast(message);
	}


	@OnMessage
	public void onMessage(Session session, String message, @PathParam("recipient") String recipient) throws IOException {

		String newMessage = "@" + recipient + " " + message;
		// Handle new messages
		logger.info("Entered into Message: Got Message:" + message);
		String username = sessionUsernameMap.get(session);

    // Direct message to a user using the format "@username <message>"
		if (newMessage.startsWith("@")) {

      // send the message to the sender and receiver
			sendMessageToPArticularUser(recipient, "[DM] " + username + ":" + message);
			sendMessageToPArticularUser(username, "[DM] " + username + ":" + message);

		} 
    else { // broadcast
			broadcast(username + ": " + message);
		}

		// Saving chat history to repository
		msgRepo.save(new Message(username, recipient, message));
	}


	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

    // remove the user connection information
		String username = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		usernameSessionMap.remove(username);

    // broadcase that the user disconnected
		//String message = username + " disconnected";
		//broadcast(message);
	}


	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}


	private void sendMessageToPArticularUser(String username, String message) {
		try {
			usernameSessionMap.get(username).getBasicRemote().sendText(message);
		} 
    catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}


	private void broadcast(String message) {
		sessionUsernameMap.forEach((session, username) -> {
			try {
				session.getBasicRemote().sendText(message);
			} 
      catch (IOException e) {
				logger.info("Exception: " + e.getMessage().toString());
				e.printStackTrace();
			}

		});

	}
	

  // Gets the Chat history from the repository
	private String getChatHistory(String username, String recipient) {
		List<Message> needToModify = msgRepo.findAll();
		List<Message> messages = new ArrayList();
		for(int x = 0; x < needToModify.size(); x++) {
			if((needToModify.get(x).getUserName().equals(username)&&needToModify.get(x).getRecipientName().equals(recipient))||(needToModify.get(x).getUserName().equals(recipient)&&needToModify.get(x).getRecipientName().equals(username))) {
				messages.add(needToModify.get(x));
			}
		}
    
    // convert the list to a string
		StringBuilder sb = new StringBuilder();
		if(messages != null && messages.size() != 0) {
			for (Message message : messages) {
				sb.append(message.getUserName() + ": " + message.getContent() + "\n");
			}
		}
		return sb.toString();
	}

} // end of Class
