package com.jersoe.messengerserver;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	private static int port=45678;
	private static int maxConnections=0;
	private Model _model;
	private ServerSocket listener;
	
	Server(Model m){
		_model=m;
		
	}

	@Override
	public void run() {
		int i=0;
		try{
			listener = new ServerSocket(port);
			Socket server;
			while((i++ < maxConnections) || (maxConnections == 0)){

				server = listener.accept();
				Client user= new Client(server, _model);
				Thread t = new Thread(user);
				t.start();
			}
		} catch (IOException ioe) {
			System.out.println("IOException on socket listen: " + ioe);
			ioe.printStackTrace();
		}	
		System.out.println("Server loop ended");
	}
	
	public void shutDown(){
		
		
		
		try {
			listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
	}
	
}
