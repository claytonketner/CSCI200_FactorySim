	import java.net.*;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import java.util.*;

public class KitsClient extends JFrame implements ActionListener, Networked 
{
	private NetComm netComm;

	private CardLayout layout;
	private ConnectPanel cPanel;
	private KitManager mPanel;
	
	private ArrayList<Kit> allKits;
	private ArrayList<Part> allParts;
	
	public KitsClient(){
		allKits = new ArrayList<Kit>();
		allParts = new ArrayList<Part>();
		cPanel = new ConnectPanel(this);
		mPanel = new KitManager(this);
		
		layout = new CardLayout();
		setLayout(layout);
		add(cPanel, "connect");
		add(mPanel, "manage");
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		KitsClient kClient = new KitsClient();
	}
	
	public void msgReceived(Object msgObj, NetComm sender) {
		if (msgObj instanceof CloseConnectionMsg)  //handles CloseConnectionMsg
		{ 
			netComm.close();
			netComm = null;
			cPanel.reset();
			cPanel.setActionMsg("Unexpectedly disconnected from server");
			layout.show(this.getContentPane(), "connect");
		}
		else if (msgObj instanceof StringMsg) //handles messages of kits being added, deleted, changed
		{ 
			StringMsg temp = (StringMsg)msgObj;
			mPanel.setMsg( temp.message );
		}
		else if (msgObj instanceof PartListMsg)  //handles request for a list of parts
		{ 
			PartListMsg temp = (PartListMsg)msgObj;
			allParts = temp.parts;
		}
		else if(msgObj instanceof KitListMsg) //handles request for a list of kits
		{
			KitListMsg temp = (KitListMsg)msgObj;
			allKits = temp.kits;
			mPanel.displayKits();
		}
		else {
			System.out.println("Warning: received unknown message " + msgObj);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cPanel) { //connect to server
			try {
				netComm = new NetComm(new Socket(ae.getActionCommand(), Server.PORT), this);
				layout.show(this.getContentPane(), "manage");
				netComm.write( new KitListMsg() );
				netComm.write( new PartListMsg());
				mPanel.requestParts();
				mPanel.generatePartList();
			}
			catch (Exception ex) {
				netComm = null;
				cPanel.setActionError("Could not connect to server; check that it was entered correctly");
			}
		}
	}
	
	public NetComm getCom()
	{
		return netComm;
	}
	
	public ArrayList<Kit> getKits()
	{
		System.out.println(allKits.size());
		return allKits;
	}
	
	public ArrayList<Part> getParts()
	{
		return allParts;
	}

}
