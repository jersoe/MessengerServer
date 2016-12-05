package simplemessenger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;

public class ViewUI {

	private JFrame frmSimpleMessenger;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUI window = new ViewUI();
					window.frmSimpleMessenger.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimpleMessenger = new JFrame();
		frmSimpleMessenger.setTitle("Simple Messenger");
		frmSimpleMessenger.setBounds(100, 100, 271, 470);
		frmSimpleMessenger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimpleMessenger.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JList list = new JList();
		
		
		frmSimpleMessenger.getContentPane().add(list, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmSimpleMessenger.setJMenuBar(menuBar);
		
		JMenu mnStatus = new JMenu("Status");
		menuBar.add(mnStatus);
		
		JMenuItem mntmGoOnline = new JMenuItem("Go online");
		mnStatus.add(mntmGoOnline);
		
		JMenuItem mntmSetAway = new JMenuItem("Set away");
		mnStatus.add(mntmSetAway);
		
		JMenuItem mntmDisconnect = new JMenuItem("Disconnect");
		mnStatus.add(mntmDisconnect);
		
		JMenu mnFriends = new JMenu("Friends");
		menuBar.add(mnFriends);
		
		JMenuItem mntmAddFriend = new JMenuItem("Add friend");
		mnFriends.add(mntmAddFriend);
		
		JMenuItem mntmRemoveFriend = new JMenuItem("Remove friend");
		mnFriends.add(mntmRemoveFriend);
		
		JMenuItem mntmBlockedUsers = new JMenuItem("Blocked users");
		mnFriends.add(mntmBlockedUsers);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmServerSettings = new JMenuItem("Server settings");
		mnSettings.add(mntmServerSettings);
		
		JMenuItem mntmUserSettings = new JMenuItem("User settings");
		mnSettings.add(mntmUserSettings);
	}

}
