package com.jersoe.messengerserver;

import java.io.DataInputStream;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Client implements Runnable{


	private Socket _server;
	private String _line;
	private String _input;
	private Model _model;
	private PrintStream _out;
	private boolean _loggedOn;
	private User _user;

	Client(Socket s, Model m) {
		_server=s;
		_model = m;
	}	

	@Override
	public void run() {
		_model.addClient(this);

		_input="";

		try {
			// Get input from the client
			DataInputStream in = new DataInputStream (_server.getInputStream());
			_out = new PrintStream(_server.getOutputStream());


			//Start while loop to catch new messages and process them
			while((_line = in.readLine()) != null) {
				//Server receives something from client. Parse data as JSONObject
				
				JSONParser parser = new JSONParser();
				try{
					Object obj = parser.parse(_line);
					JSONObject json = (JSONObject) obj;
					
					//Send json to model to process
					_model.processMessage(json, this);
					
				}catch(ParseException pe){
					System.out.println("position: " + pe.getPosition());
					System.out.println(pe);
				}
			}

			// Connection closed, remove user

			_model.removeClient(this);

			_server.close();
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}
	}	

	public String getNickName(){
		return _user.getNickName();
	}

	public String getIpAdress(){
		return _server.getInetAddress().toString();
	}

	public void disconnect(){
		_out.println("ServerMessage: This server is going offline. Goodbye!");
		try {
			_server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message){
		_out.println(message.getJSONString());
	}
	
	public long getID(){
		return _user.getID();
	}
	
	public boolean isLoggedOn() {
		return _loggedOn;
	}
	
	public void setLoggedOn(long userid) {
		_user = new User(userid);
		_loggedOn = true;
	}
}
