import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FactoryProductionClient extends JFrame implements ActionListener,
		Networked {

	private NetComm netComm;
	private ConnectPanel conp;
	private CardLayout cardlayout;
	private FactoryProductionManager fpm;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FactoryProductionClient();
	}

	public FactoryProductionClient() {
		initialize();
	}

	public void initialize() {
		
		cardlayout = new CardLayout();
		conp = new ConnectPanel(this);
		fpm = new FactoryProductionManager();
		setLayout(cardlayout);
		add(conp, "connect");
		add(fpm, "fpm");
		setTitle("Factory Production Manager");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == conp) { //connect to server
			try {
				netComm = new NetComm(new Socket(e.getActionCommand(), Server.PORT), this);
				cardlayout.last(this.getContentPane());
			}
			catch (Exception ex) {
				netComm = null;
				conp.setActionError("Could not connect to server; check that it was entered correctly");
			}
		}
	}

	@Override
	public void msgReceived(Object msgObj, NetComm sender) {
		
		if (msgObj instanceof CloseConnectionMsg) { //handles CloseConnectionMsg
			netComm.close();
			netComm = null;
			conp.reset();
			conp.setActionMsg("Unexpectedly disconnected from server");
			cardlayout.first(this.getContentPane());
		}
		else {
			System.out.println("Warning: received unknown message " + msgObj);
		}
	}
}
