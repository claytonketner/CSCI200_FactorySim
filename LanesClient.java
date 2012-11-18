import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class LanesClient extends JFrame implements ActionListener, Networked {
	private NetComm netComm;

	private CardLayout layout;
	private ConnectPanel cPanel;
	private LanePanel lPanel;
	
	public LanesClient(){
		Painter.loadImages();
		
		cPanel = new ConnectPanel(this);
		lPanel = new LanePanel(this);
		
		layout = new CardLayout();
		setLayout(layout);
		add(cPanel, "connect");
		add(lPanel, "lanes");
		
		setTitle("Lane Client");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		new Timer(LaneGraphics.UPDATE_RATE, this).start();
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
		} else if (msgObj instanceof FactoryUpdateMsg) { //handles update message
			lPanel.update( (FactoryUpdateMsg) msgObj );
		} else if (msgObj instanceof FactoryStateMsg) { //handles state message
			lPanel.setFactoryState( (FactoryStateMsg) msgObj );
		}
		else {
			System.out.println("Warning: received unknown message " + msgObj);
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cPanel) { //connect to server
			try {
				netComm = new NetComm(new Socket(ae.getActionCommand(), Server.PORT), this);
				layout.show(this.getContentPane(), "lanes");
				netComm.write( new FactoryStateMsg() );
			}
			catch (Exception ex) {
				netComm = null;
				cPanel.setActionError("Could not connect to server; check that it was entered correctly");
			}
		} else if (ae.getSource() instanceof Timer) {
			repaint();
		}
	}
	
	public NetComm getCom(){
		return netComm;
	}
}
