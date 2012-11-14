import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FactoryProductionSchedulePanel extends JPanel implements
		ActionListener {
	// To display the schedule
	// I added a temporary image to images/kit to test whether the image-display
	// function works

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
	public JComboBox<String> jcbSelectKit;
	public JTextField txtKitQuantity;
	// work on this feature when we want enhance the Factory Production GUI
	private JLabel picture = new JLabel();
	private int row = 0;
	TreeMap<Integer, Integer> schedule = new TreeMap<Integer, Integer>();
	private ProduceStatusMsg status;
	GridBagConstraints c = new GridBagConstraints();

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
		status = new ProduceStatusMsg();
		lblKitsNames = new ArrayList<JLabel>();
		lblKitsNumbers = new ArrayList<JLabel>();
		lblKitsStatus = new ArrayList<JLabel>();
		lblDisplayName = new JLabel();
		lblDisplayName.setIcon(new ImageIcon(
				"images/cooltext/lblDisplayKitName.gif"));
		lblDisplayNumber = new JLabel();
		lblDisplayNumber.setIcon(new ImageIcon(
				"images/cooltext/lblDisplayKitQuantity.gif"));
		lblDisplayStatus = new JLabel();
		lblDisplayStatus.setIcon(new ImageIcon(
				"images/cooltext/lblKitStatus.gif"));
		lblSelectKit = new JLabel();
		lblSelectKit
				.setIcon(new ImageIcon("images/cooltext/lblSelectAKit.png"));
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

	public void makeSchedule() {

		setLayout(new GridBagLayout());

		// layout for combobox

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 0;
		add(lblSelectKit, c);
		c.gridx = 1;
		c.gridy = 0;
		add(jcbSelectKit, c);
		c.gridx = 2;
		c.gridy = 0;
		picture.setText(""/* "here is the image" */);
		add(picture, c);
		c.weightx = 0.5;
		c.weighty = 1;
		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 1;
		add(txtKitQuantity, c);
		c.gridx = 1;
		c.gridy = 1;
		add(btnProduce, c);

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		// c.insets = new Insets(20, 10, 0, 0);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		add(lblDisplayName, c);
		c.gridx = 1;
		c.gridy = 2;
		add(lblDisplayNumber, c);
		c.gridx = 2;
		c.gridy = 2;
		add(lblDisplayStatus, c);

	}

	public void updateSchedule(ProduceStatusMsg msg) {
		status = msg;
		schedule.clear();
		merge(status);
		if (status.cmds.size() > 0) {

			for (int i = 0; i < lblKitsNames.size(); i++) {
				c.fill = GridBagConstraints.NONE;
				c.anchor = GridBagConstraints.NORTHWEST;
				c.weightx = 0.5;
				c.weighty = 1;
				c.gridx = 0;
				c.gridy = i + 3;
				add(lblKitsNames.get(i), c);
				c.gridx = 1;
				c.gridy = i + 3;
				lblKitsNumbers.get(i).setBackground(Color.YELLOW);
				add(lblKitsNumbers.get(i), c);
				c.gridx = 2;
				c.gridy = i + 3;
				add(lblKitsStatus.get(i), c);

			}
		}
		validate();
		repaint();
	}

	public void merge(ProduceStatusMsg statusmsg) {
		ProduceStatusMsg status = statusmsg;
		ArrayList<Integer> index = new ArrayList<Integer>();
		boolean merged = false;
		int temp = 0;

		int quantity = 0;
		if (status.cmds.size() == 1) {

			quantity = status.cmds.get(0).howMany;
		}

		for (int i = 0; i < status.cmds.size() - 1; i++) {

			for (int j = i + 1; j < status.cmds.size(); j++) {
				if (status.cmds.get(i).kitNumber == status.cmds.get(j).kitNumber
						&& status.status.get(i) == status.status.get(j)) {
					index.add(j);
					index.add(i);
					merged = true;

				} else {

				}

			}

		}
		if (!merged) {

			if (lblKitsNames.size() > 0) {
				lblKitsNames.add(new JLabel((String) jcbSelectKit
						.getSelectedItem()));
				JLabel lblquantity = new JLabel(""
						+ status.cmds.get(status.cmds.size() - 1).howMany);
				lblKitsNumbers.add(lblquantity);
				lblKitsStatus.add(new JLabel(""
						+ status.status.get(status.status.size() - 1)));
			}
		}
		merged = true;
		if (status.cmds.size() == 1) {
			schedule.put(status.cmds.get(0).kitNumber, quantity);
			lblKitsNames
					.add(new JLabel((String) jcbSelectKit.getSelectedItem()));
			JLabel lblquantity = new JLabel(""
					+ schedule.get(status.cmds.get(0).kitNumber));
			lblKitsNumbers.add(lblquantity);
			lblKitsStatus.add(new JLabel("" + status.status.get(0)));

		}
		
		for (int i = 0; i < index.size(); i+=2) {
			quantity = status.cmds.get(index.get(i)).howMany;
			status.cmds.get(index.get(i+1)).howMany += quantity;
			status.cmds.remove(status.cmds.get(index.get(i)));
			status.status.remove(status.status.get(index.get(i)));

			merged = true;
			for (int j = 0; j < lblKitsNames.size(); j++) {
				if (lblKitsNames.get(j).getText() == (String) jcbSelectKit
						.getSelectedItem()) {

					lblKitsNumbers.get(j).setText(
							"" + status.cmds.get(index.get(i+1)).howMany);
				}
			}

		}
		
		if (status.cmds.size() > 0 && merged) {

			client.echoProduceUpdateMsg(status);
			index.clear();
			merged = false;
		}

		validate();
		repaint();

	}

	public void updateKitList(KitListMsg msgObj) {
		vectorjcbKitStrings.clear();
		for (int i = 0; i < msgObj.kits.size(); i++) {

			vectorjcbKitStrings.add(msgObj.kits.get(i).getName());
		}
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
}
