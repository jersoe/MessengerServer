package com.jersoe.messengerserver;

public class User {

	private long _id;
	
	User(long userid){
		_id=userid;
	}
	
	public String getFirstName() {
		//TODO: retrieve name from database
		return "TestFirst";
	}
	
	public String getLastName() {
		//TODO: retrieve name from database
		return "TestLast";
	}
	
	public String getEmail() {
		//TODO: retrieve email from database
		return "test@test.com";
	}
	
	public String getAvatar() {
		//TODO: retrieve avatar from database
		return "asdasd";
	}
	
	public String[] getFriends() {
		//TODO: retrieve friends from database
		return null;
	}
	
	public long getID() {
		return _id;
	}
	
	public String getNickName(){
		//TODO: retrieve nick from database 
		return "TestNick";
	}
}
