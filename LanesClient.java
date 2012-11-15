import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

@SuppressWarnings("serial")
public class LanesClient extends JFrame implements ActionListener, Networked {
	private NetComm netComm;

	private CardLayout layout;
	private ConnectPanel cPanel;
	
	public LanesClient(){
		cPanel = new ConnectPanel(this);
		
		layout = new CardLayout();
		setLayout(layout);
		add(cPanel, "connect");
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args){
		LanesClient LClient = new LanesClient();
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
				layout.show(this.getContentPane(), "manage"); //change this to lane panel
			}
			catch (Exception ex) {
				netComm = null;
				cPanel.setActionError("Could not connect to server; check that it was entered correctly");
			}
		}
	}
}
