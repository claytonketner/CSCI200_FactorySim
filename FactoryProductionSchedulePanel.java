import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

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
	private JLabel lblDisplayName;
	private JLabel lblDisplayNumber;
	private JLabel lblDisplayStatus;
	public JButton btnProduce;
	private JLabel lblSelectKit;
	private String[] jcbKitStrings = { "Select a kit", "empty_kit" }; // kit
																		// names
																		// in
																		// the
																		// combo
																		// box
	private JComboBox jcbSelectKit;
	public JTextField txtKitQuantity;
	private JLabel picture = new JLabel();
	private int row,col; //row and col
	private TreeMap<String , String> schedule;
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

	public void initialize() {
		schedule = new TreeMap<String , String>();
		lblKitsNames = new ArrayList<JLabel>();
		lblKitsNumbers = new ArrayList<JLabel>();
		lblKitsStatus = new ArrayList<JLabel>();
		lblDisplayName = new JLabel("Kit Name: ");
		lblDisplayNumber = new JLabel("Quantity");
		lblDisplayStatus = new JLabel("Status");
		lblSelectKit = new JLabel("Select a kit");
		jcbSelectKit = new JComboBox(jcbKitStrings);
		btnProduce = new JButton("Produce");
		txtKitQuantity = new JTextField("Enter the amount");
		
		jcbSelectKit.addActionListener(this);
	}

	public void makeSchedule() {

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		//c.insets = new Insets(20, 10, 0, 0);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(lblDisplayName, c);
		c.gridx = 1;
		c.gridy = 0;
		add(lblDisplayNumber, c);
		c.gridx = 2;
		c.gridy = 0;
		add(lblDisplayStatus, c);
		
		// layout for combobox
		row = schedule.size();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = row + 1;
		add(lblSelectKit, c);
		c.gridx = 1;
		c.gridy = row + 1;
		add(jcbSelectKit, c);
		c.gridx = 2;
		c.gridy = row + 1;
		picture.setText("Here is the image");
		add(picture, c);
		c.weightx = 0.5;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = row + 2;
		add(txtKitQuantity, c);
		c.gridx = 1;
		c.gridy = row + 2;
		add(btnProduce, c);
	}

	protected void updateLabel(String name) {
		ImageIcon icon = new ImageIcon("images/kit/" + name + ".png");
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
