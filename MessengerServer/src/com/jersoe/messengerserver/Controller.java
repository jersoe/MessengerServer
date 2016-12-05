
package com.jersoe.messengerserver;

public class Controller {

	private View _view;
	private Model _model;

	Controller(){
		_view=new View(this);
		_model=new Model(this);

		_view.writeToConsole("Starting server");
	}

	private void goOffline(){
		if (_model.getServerStatus()==ServerStatus.OFFLINE){
			_view.writeToConsole("Allready offline");
		} else {
			_model.goOffline();
			_view.writeToConsole("Server is now offline");
			UpdateNumberOfUsers();
		}
	}

	private void goOnline(){
		if (_model.getServerStatus()==ServerStatus.ONLINE){
			_view.writeToConsole("Allready online");
		} else {
			_model.goOnline();
			_view.writeToConsole("Server is now online");
		}
	}

	protected void changeStatus(){
		if (_model.getServerStatus()==ServerStatus.ONLINE) {
			goOffline();
		} else {
			goOnline();
		}
	}

	protected ServerStatus getServerStatus(){
		return _model.getServerStatus();
	}

	protected void UpdateNumberOfUsers(){
		_view.setOnlineUsers(_model.getNumberOfOnlineUsers());
	}

	protected void newClientConnected(Client c){
		UpdateNumberOfUsers();
		_view.writeToConsole("New client connected from IP: "+c.getIpAdress());
	}

	protected void clientLeft(Client c){
		if (!c.isLoggedOn()) {
			_view.writeToConsole("Client disconnected before completing logon. IP: "+c.getIpAdress());
		} else {
			_view.writeToConsole("User left: "+c.getNickName()+" IP: "+c.getIpAdress());
			UpdateNumberOfUsers();
		}
	}

	protected void newUserAuthenticated(long id) {
		_view.writeToConsole("User authenticated: id "+id);	
		UpdateNumberOfUsers();
	}


}
