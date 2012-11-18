import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FactoryProductionSchedulePanel extends JPanel implements
		ActionListener{
	/** kits which are sent from server */
	public ArrayList<Kit> kits;
	/** produce button when user want to produce kits */
	public JButton btnProduce;
	/** print text "Select a Kit" */
	private JLabel lblSelectKit;
	/** strings that used in combobox for kits' names*/
	private Vector<String> vectorjcbKitStrings = new Vector<String>();
	/** combobox for selecting a kit to produce */
	public JComboBox jcbSelectKit;
	/** textfield to enter amount of kit user wnat to produce */
	public JTextField txtKitQuantity;
	/** work on this feature when we want enhance the Factory Production GUI */
	private JLabel picture = new JLabel();
	/** ProduceStatusMsg is sent from Server to info the kits' amount and status */
	private ProduceStatusMsg status;
	/** factory needed for GridBagLayout */
	GridBagConstraints c = new GridBagConstraints();
	/** scroll pane for displaying kits schedule */
	private JScrollPane scroll;
	/** put pnlKits inside JscrollPane */
	public JPanel pnlKits;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public FactoryProductionSchedulePanel() {
		initialize();
		makeSchedule();
		
	}
	/** initialize variables */
	public void initialize() {
		pnlKits = new JPanel();
		pnlKits.setLayout(new BoxLayout(pnlKits, BoxLayout.Y_AXIS));
		pnlKits.add(new JLabel("Welcome to Kit Queue List"));
		pnlKits.setVisible(true);
		scroll = new JScrollPane(pnlKits);

		kits = new ArrayList<Kit>();
		status = new ProduceStatusMsg();
		lblSelectKit = new JLabel("Select a Kit: ");
		jcbSelectKit = new JComboBox(vectorjcbKitStrings);
		jcbSelectKit.setPreferredSize(new Dimension(100, 25));
		btnProduce = new JButton();
		btnProduce.setIcon(new ImageIcon("images/cooltext/btnProduce.png"));
		btnProduce.setPreferredSize(new Dimension(130, 50));
		txtKitQuantity = new JTextField(20);
		txtKitQuantity.setText("Enter amount here");
		txtKitQuantity.setPreferredSize(new Dimension(100, 60));
		// work on this feature when we want enhance the Factory Production GUI
		picture.setPreferredSize(new Dimension(50, 50));
		jcbSelectKit.addActionListener(this);
	}
	/** manage the layout for schedule */
	public void makeSchedule() {

		setLayout(new GridBagLayout());

		// layout for combobox

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 3;
		c.gridy = 1;
		add(jcbSelectKit, c);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 3;
		c.gridy = 0;
		add(lblSelectKit, c);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 1;
		add(txtKitQuantity, c);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		// c.insets = new Insets(20, 10, 0, 0);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		add(btnProduce, c);

		c.fill = c.BOTH;
		c.weightx = 1;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 5;
		add(scroll, c);
		

		
		
	}
	/** when received ProduceStatusMsg from Server, regenerate all of the available kits */
	public void updateSchedule(ProduceStatusMsg msg) {
		status = msg;
		regenerateSchedule(status);

	}
	/** regenerate all of the available kits */
	public void regenerateSchedule(ProduceStatusMsg statusmsg) {
		ProduceStatusMsg status = statusmsg;
		String kitname = "";
		pnlKits.removeAll();
		if (status.cmds.size() > 0) {
			
			for (int i = 0; i < status.cmds.size(); i++) {
				for (int j = 0; j < kits.size(); j++) {
					kitname = kits.get(j).getName();

					if (kits.get(j).getNumber() == status.cmds.get(i).kitNumber) {
						System.out.println(kitname);
						
						pnlKits.add(new JLabel(kitname + " - "
								+ status.cmds.get(i).howMany + " - "
								+ status.status.get(i)));
						
						
					}
				}
			}
		} 
	
		validate();
		repaint();
	}
	/** regenerate all of the available kits */
	public void updateKitList(KitListMsg msgObj) {
		validate();
		repaint();
		vectorjcbKitStrings.clear();
		kits.clear();
		for (int i = 0; i < msgObj.kits.size(); i++) {
			kits.add(msgObj.kits.get(i));
			vectorjcbKitStrings.add(msgObj.kits.get(i).getName());
		}

		validate();
		repaint();
	}
	/**this method never run since we didn't enable this feature until we decide what is our factory going to produce  */
	protected void updateLabel(String name) {
		ImageIcon icon = new ImageIcon(/* type image address here */);
		picture.setIcon(icon);
		picture.setPreferredSize(new Dimension(50, 50));
		picture.setToolTipText("A drawing of a " + name.toLowerCase());
		if (icon != null) {
			picture.setText(null);
		}

	}

	@Override
	/** handle user's input (mostly combobox selection) */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == jcbSelectKit) {
			JComboBox cb = (JComboBox) e.getSource();
			String kitName = (String) cb.getSelectedItem();
			updateLabel(kitName);
		}
		

	}
}
