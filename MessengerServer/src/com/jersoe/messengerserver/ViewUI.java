package com.jersoe.messengerserver;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

public class ViewUI {
	private View _v;
	private JFrame frame;
	private JButton _buttonChangeStatus;
	private JTextPane _statusLog;
	private JLabel lblOnlineUsers;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ViewUI(View v) {
		_v = v;
		
		frame = new JFrame("Simple Messenger Server");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));
		
		
		JPanel mainPanel = new JPanel();
				
		mainPanel.setLayout(new BorderLayout());
		
		_statusLog = new JTextPane();
		mainPanel.add(_statusLog, BorderLayout.CENTER);
		
		frame.getContentPane().add(mainPanel);
		
		JPanel buttonPanel = new JPanel();
		_buttonChangeStatus = new JButton("Go online");
		_buttonChangeStatus.addActionListener(_v);
		_buttonChangeStatus.setActionCommand("status");
		
		buttonPanel.add(_buttonChangeStatus);

		mainPanel.add(buttonPanel, BorderLayout.WEST);
		
		lblOnlineUsers = new JLabel("Online users: 0");
		mainPanel.add(lblOnlineUsers, BorderLayout.SOUTH);
		
		
		frame.pack();
		frame.setVisible(true);
	}

	public void updateStatusButton(String text) {
		_buttonChangeStatus.setText(text);
	}
	
	public void writeToConsole(String text){
		_statusLog.setText(_statusLog.getText()+text+"\n");
	}
	
	public void setOnlineUsers(int users){
		lblOnlineUsers.setText("Online users: "+users);
	}
}
