package com.jersoe.messengerserver;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Message {

	private JSONObject _json;

	Message(JSONObject json){
		_json = json;
	}
	
	Message(long type, long from, long to, long action, String message){
		_json = new JSONObject();
		_json.put("type", type);
		_json.put("from", from);
		_json.put("to", to);
		_json.put("action", action);
		_json.put("message", message);		
	}
	
	public long getType(){
		return (long) _json.get("type");
	}
	
	public long getFrom(){
		return (long) _json.get("from");
	}
	
	public long getTo(){
		return (long) _json.get("to");
	}
	
	public long getAction(){
		return (long) _json.get("action");
	}
	
	public String getMessage(){
		return (String) _json.get("message");
	}

	public String getJSONString() {
		return _json.toJSONString();
	}
}
