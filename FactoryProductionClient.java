import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FactoryProductionClient extends JFrame implements ActionListener,
		Networked {

	private NetComm netComm;
	private ConnectPanel conp;
	private CardLayout cardlayout;
	private FactoryProductionManager fpm;
	private ArrayList<Kit> kitList;

	public static void main(String[] args) {
		new FactoryProductionClient();
	}

	public FactoryProductionClient() {
		kitList = new ArrayList<Kit>();
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
		new Timer(FactoryProductionViewPanel.UPDATE_RATE, this).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == conp) { // connect to server
			try {
				netComm = new NetComm(new Socket(e.getActionCommand(),
						Server.PORT), this);
				netComm.write(new KitListMsg());
				netComm.write(new ProduceStatusMsg());
				netComm.write(new FactoryStateMsg());

			} catch (Exception ex) {
				netComm = null;
				conp.setActionError("Could not connect to server; check that it was entered correctly");
			}
		}
		else if (e.getSource() == fpm.fpsp.btnProduce) {
			try {
				if (isInteger(fpm.fpsp.txtKitQuantity.getText())) {

					String kitName = (String) fpm.fpsp.jcbSelectKit
							.getSelectedItem();
					for (int i = 0; i < kitList.size(); i++) {
						if (kitName == kitList.get(i).getName()) {
							ProduceKitsMsg kitsMsg = new ProduceKitsMsg(kitList
									.get(i).getNumber(),
									Integer.parseInt(fpm.fpsp.txtKitQuantity
											.getText()));
							netComm.write(kitsMsg);
						}
					}

				}
			}

			catch (Exception ex) {
				netComm = null;
				conp.setActionError("Could not connect to server; check that it was entered correctly");
			}

		}
		else if (e.getSource() instanceof Timer) {
			repaint();
		}
	}


	@Override
	public void msgReceived(Object msgObj, NetComm sender) {

		if (msgObj instanceof CloseConnectionMsg) { // handles CloseConnectionMsg
			netComm.close();
			netComm = null;
			conp.reset();
			conp.setActionMsg("Unexpectedly disconnected from server");

		} else if (msgObj instanceof FactoryStateMsg) {
			fpm.getViewPanel().setFactoryState((FactoryStateMsg) msgObj);
			cardlayout.last(this.getContentPane());
		}

		else if (msgObj instanceof FactoryUpdateMsg) {
			fpm.getViewPanel().update((FactoryUpdateMsg) msgObj);
		} else if (msgObj instanceof StringMsg) {
			System.out.println(((StringMsg) msgObj).message);
		} else if (msgObj instanceof KitListMsg) {
			fpm.fpsp.updateKitList((KitListMsg) msgObj);
			kitList = ((KitListMsg) msgObj).kits;
			validate();
			repaint();
		} else if (msgObj instanceof ProduceStatusMsg) {
			fpm.fpsp.updateSchedule((ProduceStatusMsg) msgObj);
		} else {
			System.out.println("Warning: received unknown message " + msgObj);
		}
	}

	public boolean isInteger(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			return false;

		}

	}

}
