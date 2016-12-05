package com.jersoe.messengerserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ActionListener{
	private Controller _controller;
	private ViewUI _UI;
	
	
	View(Controller c){
		_controller=c;
		_UI = new ViewUI(this);
	}
	
	
	public void writeToConsole(String output){
		_UI.writeToConsole(output);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="status"){
			_controller.changeStatus();
			
			if (_controller.getServerStatus()==ServerStatus.ONLINE) {
				_UI.updateStatusButton("Go offline");
			} else {
				_UI.updateStatusButton("Go online");
			}
		}	
	}
	
	public void setOnlineUsers(int users){
		_UI.setOnlineUsers(users);
	}
	
}
