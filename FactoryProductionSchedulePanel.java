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

	public FactoryProductionSchedulePanel() {
		initialize();
		makeSchedule();
	}

	public void initialize() {
		status = new ProduceStatusMsg();
		lblKitsNames = new ArrayList<JLabel>();
		lblKitsNumbers = new ArrayList<JLabel>();
		lblKitsStatus = new ArrayList<JLabel>();
		lblDisplayName = new JLabel("Kit Name: ");
		lblDisplayNumber = new JLabel("Quantity");
		lblDisplayStatus = new JLabel("Status");
		lblSelectKit = new JLabel("Select a kit");
		jcbSelectKit = new JComboBox(jcbKitStrings);
		btnProduce = new JButton("Produce");
		txtKitQuantity = new JTextField(20);

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
		picture.setText("Here is the image");
		add(picture, c);
		c.weightx = 0.5;
		c.weighty = 1;
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

	public void merge(ProduceStatusMsg status) {
		int temp = 0;
		
		int quantity = 0;
		if(status.cmds.size()>0){
			
			quantity = status.cmds.get(0).howMany;
		}
		
		for (int i = 0; i < status.cmds.size(); i++) {

			if (i >= 1) {
				if (status.cmds.get(i).kitNumber == status.cmds.get(i - 1).kitNumber
						&& status.status.get(i) == status.status.get(i - 1)) {
					quantity += status.cmds.get(i).howMany;

					schedule.remove(status.cmds.get(i - 1).kitNumber);
					if (lblKitsNames.size() > 0) {
						// lblKitsNames.remove(i - 1);
						// lblKitsNumbers.remove(i - 1);
						// lblKitsStatus.remove(i - 1);
						validate();
						repaint();
					}
				}

			}
			schedule.put(status.cmds.get(i).kitNumber, quantity);
			temp = quantity;
		}

		for (int i = 0; i < status.cmds.size(); i++) {

			if (status.cmds.size() > 1) {
				System.out.println(status.cmds.size());
				if (i > 0) {
					if (status.cmds.get(i).kitNumber == status.cmds.get(i - 1).kitNumber
							&& status.status.get(i) == status.status.get(i - 1)) {
						
						// lblKitsNames.remove(i - 1);
						// lblKitsNumbers.remove(i - 1);
						// lblKitsStatus.remove(i - 1);
						// System.out.println("11");
						lblKitsNumbers.get(0).setText("" + temp);

					} else {
						// System.out.println("11");
						if (status.cmds.get(i).kitNumber == 0) {
							lblKitsNames.add(new JLabel("Empty Kit"));

						}
						JLabel lblquantity = new JLabel(""
								+ schedule.get(status.cmds.get(i).kitNumber));
						lblKitsNumbers.add(lblquantity);
						lblKitsStatus
								.add(new JLabel("" + status.status.get(i)));

					}
				}
			} else {
				System.out.println(status.cmds.size());
				if (status.cmds.get(i).kitNumber == 0) {
					lblKitsNames.add(new JLabel("Empty Kit"));

				}
				JLabel lblquantity = new JLabel(""
						+ schedule.get(status.cmds.get(i).kitNumber));
				lblKitsNumbers.add(lblquantity);
				lblKitsStatus.add(new JLabel("" + status.status.get(i)));
			}

			validate();
			repaint();
		}
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
