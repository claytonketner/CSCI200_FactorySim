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
		ActionListener, MouseListener {
	public ArrayList<Kit> kits;
	public ArrayList<JLabel> lblKitsNames;
	public ArrayList<JLabel> lblKitsNumbers;
	public ArrayList<JLabel> lblKitsStatus;
	private FactoryProductionClient client;
	private JLabel lblDisplayName;
	private JLabel lblDisplayNumber;
	private JLabel lblDisplayStatus;
	public JButton btnProduce;
	private JLabel lblSelectKit;
	private Vector<String> vectorjcbKitStrings = new Vector<String>();
	private String[] jcbKitStrings = {}; // kit
											// names
											// in
											// the
											// combo
											// box
	public JComboBox jcbSelectKit;
	public JTextField txtKitQuantity;
	// work on this feature when we want enhance the Factory Production GUI
	private JLabel picture = new JLabel();
	TreeMap<Integer, Integer> schedule = new TreeMap<Integer, Integer>();
	private ProduceStatusMsg status;
	GridBagConstraints c = new GridBagConstraints();
	private JScrollPane scroll;
	private JPanel pnlKits;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public FactoryProductionSchedulePanel(FactoryProductionClient c) {
		initialize();
		makeSchedule();
		client = c;
	}

	public void initialize() {
		pnlKits = new JPanel();
		pnlKits.setLayout(new BoxLayout(pnlKits, BoxLayout.Y_AXIS));
		pnlKits.add(new JLabel("Welcome to Factory Production Panel"));
		pnlKits.add(new JLabel("Welcome to Factory Production Panel"));
		pnlKits.setVisible(true);
		scroll = new JScrollPane(pnlKits);

		kits = new ArrayList<Kit>();
		status = new ProduceStatusMsg();
		lblKitsNames = new ArrayList<JLabel>();
		lblKitsNumbers = new ArrayList<JLabel>();
		lblKitsStatus = new ArrayList<JLabel>();
		lblDisplayName = new JLabel("Kit Name: ");

		lblDisplayNumber = new JLabel("Kit Quantity: ");

		lblDisplayStatus = new JLabel("Kit Status");

		lblSelectKit = new JLabel("Select a Kit: ");

		jcbSelectKit = new JComboBox(vectorjcbKitStrings);
		jcbSelectKit.setPreferredSize(new Dimension(100, 25));
		btnProduce = new JButton();
		btnProduce.setIcon(new ImageIcon("images/cooltext/btnProduce.png"));
		btnProduce.setPreferredSize(new Dimension(130, 50));
		// btnUpdate.addMouseListener(this);
		// addMouseListener(this);
		txtKitQuantity = new JTextField(20);
		txtKitQuantity.setText("Enter amount here");
		txtKitQuantity.setPreferredSize(new Dimension(100, 60));
		// work on this feature when we want enhance the Factory Production GUI
		picture.setPreferredSize(new Dimension(50, 50));
		jcbSelectKit.addActionListener(this);
	}

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
		
//		c.fill = c.BOTH;
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridx = 2;
//		c.gridy = 2;
//		c.gridwidth = 2;
//		c.gridheight = 2;
//		add(pnlKits, c);
		
		
	}

	public void updateSchedule(ProduceStatusMsg msg) {
		status = msg;
		schedule.clear();
		
		merge(status);

	}

	public void merge(ProduceStatusMsg statusmsg) {
		ProduceStatusMsg status = statusmsg;
		String kitname = "";
		if (status.cmds.size() > 0) {
			pnlKits.removeAll();
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

	public void btnProducePressed() {

	}

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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == jcbSelectKit) {
			JComboBox cb = (JComboBox) e.getSource();
			String kitName = (String) cb.getSelectedItem();
			updateLabel(kitName);
		}
		if (e.getSource() == btnProduce) {

		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
