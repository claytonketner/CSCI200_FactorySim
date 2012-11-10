import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PartsClient extends JFrame implements ActionListener, Networked {
	private NetComm netComm;

	private CardLayout layout;
	private ConnectPanel cPanel;
	
	public PartsClient(){
		cPanel = new ConnectPanel(this);
		add(cPanel, "connect");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		PartsClient pClient = new PartsClient();
	}
	
	public void msgReceived(Object msgObj, NetComm sender) {
		if (msgObj instanceof CloseConnectionMsg) { //handles CloseConnectionMsg
			netComm.close();
			netComm = null;
			cPanel.reset();
			cPanel.setActionMsg("Unexpectedly disconnected from server");
			layout.show(this.getContentPane(), "connect");
		}
		else {
			System.out.println("Warning: received unknown message " + msgObj);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cPanel) { //connect to server
			try {
				netComm = new NetComm(new Socket(ae.getActionCommand(), Server.PORT), this);
				layout.show(this.getContentPane(), "chat");//change this to part manager
			}
			catch (Exception ex) {
				netComm = null;
				cPanel.setActionError("Could not connect to server; check that it was entered correctly");
			}
		}
	}

}
