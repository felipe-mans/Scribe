package sockets;

import java.io.IOException;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import backend.JDBCQuery;

@ServerEndpoint(value = "/discussion")
public class ServerSocket {

	private static Vector<Session> sessionVector = new Vector<Session>();
	
	@OnOpen
	public void open(Session session) {
		System.out.println("Connection made!");
		sessionVector.add(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		if(message.startsWith("ACCEPT")) {
			String[] request = message.split(" ");
			String uname = request[1];
			String classId = request[2];
			String userId = request[3];
			JDBCQuery.addEnrollment(Integer.parseInt(classId), Integer.parseInt(userId));
			JDBCQuery.updateRequest(Integer.parseInt(classId), Integer.parseInt(userId));
			message = uname + " has joined the class!";
		} else if(message.startsWith("REQUEST")) {
			String[] request = message.split(" ", 5);
			String classId = request[1];
			String userId = request[2];
			JDBCQuery.addRequest(Integer.parseInt(userId), Integer.parseInt(classId));
			message = request[3] + " " + request[4];
		}
		System.out.println("Sending message: " + message);
		try {
			for(Session s : sessionVector) {
				s.getBasicRemote().sendText(message);
			}
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
			close(session);
		}
	}
	
	@OnClose
	public void close(Session session) {
		System.out.println("Disconnecting!");
		sessionVector.remove(session);
	}
	
	@OnError
	public void error(Throwable error) {
		System.out.println("Error!");
	}
}