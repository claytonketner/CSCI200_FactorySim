import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JFrame;

public class FactoryProductionClient extends JFrame implements ActionListener,
		Networked {

	private NetComm netComm;
	private ConnectPanel conp;
	private CardLayout cardlayout;
	private FactoryProductionManager fpm;
	

	public static void main(String[] args) {
		new FactoryProductionClient();
	}

	public FactoryProductionClient() {
		Painter.loadImages();
		cardlayout = new CardLayout();
		conp = new ConnectPanel(this);
		fpm = new FactoryProductionManager(this);
		fpm.fpsp.btnProduce.addActionListener(this);
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
				netComm.write(new FactoryStateMsg());
			}
			catch (Exception ex) {
				netComm = null;
				conp.setActionError("Could not connect to server; check that it was entered correctly");
			}
		}
		if(e.getSource() == fpm.fpsp.btnProduce) {
			if(isInteger(fpm.fpsp.txtKitQuantity.getText())){
				ProduceKitsMsg kitsMsg = new ProduceKitsMsg(0,Integer.parseInt(fpm.fpsp.txtKitQuantity.getText()));
				netComm.write(kitsMsg);
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
		else if (msgObj instanceof FactoryStateMsg) {
			fpm.getViewPanel().setFactoryState((FactoryStateMsg)msgObj);
			cardlayout.last(this.getContentPane());
		}
		
		else if (msgObj instanceof FactoryUpdateMsg) {
			fpm.getViewPanel().update((FactoryUpdateMsg)msgObj);
		}
		else if(msgObj instanceof StringMsg){
			System.out.println(((StringMsg) msgObj).message);
			
		}
		else {
			System.out.println("Warning: received unknown message " + msgObj);
		}
	}
	
	public boolean isInteger(String number){
		try{
			Integer.parseInt(number);
			return true;
		}catch(Exception e){
			return false;
			
		}
		
	}
	
	
}
