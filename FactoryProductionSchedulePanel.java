import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FactoryProductionSchedulePanel extends JPanel implements ActionListener{
//	To display the schedule
//	I added a temporary image to images/kit to test whether the image-display function works
	
	private ArrayList<JLabel>  lblKitsNames;
	private ArrayList<JLabel>  lblKitsNumbers;
	private ArrayList<JLabel>  lblKitsStatus;
	private JLabel lblDisplayName;
	private JLabel lblDisplayNumber;
	private JLabel lblDisplayStatus;
	private JButton btnProduce;
	private JLabel lblSelectKit;
	private String[] jcbKitStrings = {"Select a kit", "DefaultKit"}; // kit names in the combo box
	private JComboBox jcbSelectKit;
	private JTextField txtKitQuantity;
	private JLabel picture = new JLabel();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public FactoryProductionSchedulePanel(){
		initialize();
		makeSchedule();
	}
	public void initialize(){
		
		lblKitsNames = new ArrayList<JLabel>();
		lblKitsNumbers = new ArrayList<JLabel>();
		lblKitsStatus = new ArrayList<JLabel>();
		lblDisplayName = new JLabel("Kit Name: ");
		lblDisplayNumber = new JLabel("Quantity");
		lblDisplayStatus = new JLabel("Status");
		jcbSelectKit = new JComboBox(jcbKitStrings);
		btnProduce = new JButton("Produce");
		txtKitQuantity = new JTextField("Enter the amount");
		btnProduce.addActionListener(this);
		jcbSelectKit.addActionListener(this);
	}
	public void makeSchedule(){
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(20,10,0,0);
		c.gridx = 0;
		c.gridy = 0;
		add( lblDisplayName, c );
		c.gridx = 1;
		c.gridy = 0;
		add( lblDisplayNumber, c );
		c.gridx = 2;
		c.gridy = 0;
		add( lblDisplayStatus, c );
		//layout for combobox
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 1;
		add( jcbSelectKit, c );
		c.gridx = 1;
		c.gridy = 1;
		add( picture, c );
		c.gridx = 2;
		c.gridy = 1;
		add( txtKitQuantity, c );
	}
	
    
    protected void updateLabel(String name) {
        ImageIcon icon = new ImageIcon("images/kit/" + name + ".jpg");
        picture.setIcon(icon);
        picture.setToolTipText("A drawing of a " + name.toLowerCase());
        if (icon != null) {
            picture.setText(null);
        } 
        else {
            picture.setText("Image not found");
        }     
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox cb = (JComboBox)e.getSource();
        String kitName = (String)cb.getSelectedItem();
        updateLabel(kitName);
	}
}
