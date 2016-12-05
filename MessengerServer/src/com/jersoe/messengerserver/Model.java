package com.jersoe.messengerserver;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class Model {

	private ServerStatus _status;
	private Controller _controller;
	private ArrayList<Client> _clients = new ArrayList<Client>();
	private Server _server;

	Model(Controller c){
		_controller = c;

	}

	public void goOffline(){

		//First disconnect all users
		for(Client client:_clients){
			client.disconnect();
		}
		//The shut down the listener
		_server.shutDown();
		//And update the status.
		_status = ServerStatus.OFFLINE;
	}

	public void goOnline(){
		_server = new Server(this);
		Thread serverThread = new Thread(_server);
		serverThread.start();
		_status = ServerStatus.ONLINE;
	}

	public ServerStatus getServerStatus(){
		return _status;
	}

	/*Returns number of authenticated clients*/
	public int getNumberOfOnlineUsers(){
		int users = 0;
		for (Client c: _clients){
			if (c.isLoggedOn()) {
				users++;
			}
		}
		return users;		
	}

	protected void addClient(Client c){
		_clients.add(c);
		_controller.newClientConnected(c);
	}

	/*Removes a user. Protected since a user needs to be able to remove itself if
	 * the connection is lost or the user leaves.*/
	protected void removeClient(Client c){
		_clients.remove(c);
		_controller.clientLeft(c);
	}

	protected void processMessage(JSONObject json, Client c) {
		if ((long)json.get("type")==0) {	//System message
			if ((long)json.get("action")==0) { //login request with password
				if (isCorrectPassword((long)json.get("from"),(String)json.get("message"))){ //check if the password is correct
					c.setLoggedOn((long)json.get("from"));
					c.sendMessage(new Message(0,0,c.getID(),0,"Password accepted. Login complete."));
					_controller.newUserAuthenticated(c.getID());
				} else {
					c.sendMessage(new Message(0,0,c.getID(),0,"Password wrong."));
				}
			}

		} else if ((long)json.get("type")==1) { //User message
			if ((long)json.get("action")==0) { //Regular message for another user
				//Check if from client is logged on
				if (c.isLoggedOn()){
					//check if receiver is online
					ArrayList<Client> receivingClients = getClient((long)json.get("to"));
					if (receivingClients.size()>0){
						for(Client receivingClient: receivingClients){
							receivingClient.sendMessage(new Message(json));
						}
					} else {
						c.sendMessage(new Message(0,0,c.getID(),0,"User is offline."));
					}
				} else {
					c.sendMessage(new Message(0,0,c.getID(),0,"Not logged on"));
				}
			}
		}
	}

	/*Checks if the password is correct for the given userid. Needs to be private
	 * because only model can authenticate. Making this accessible by anyone else
	 * would allow for local brute-force attacking.*/
	private boolean isCorrectPassword(long userid, String password){
		//TODO: actually check the password
		return true;
	}


	/*Return an arraylist of all connected authenticated clients with an parameter
	 *  id. This needs to be an arraylist because a user can be logged on from
	 *  multiple devices at the same time.*/
	private ArrayList<Client> getClient(long id){
		ArrayList<Client> result = new ArrayList<Client>();

		for (Client c: _clients){
			if (c.getID()==id){
				result.add(c);
			}
		}

		return result;
	}
}
