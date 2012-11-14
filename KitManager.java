import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class KitManager extends JPanel
{
	private KitsClient myClient;
	private JLabel lblName;
	private JLabel lblNumber;
	private JLabel lblInfo;
	private JLabel lblNumToProduce;
	private JLabel lblEdit;
	private JLabel lblEdit2;
	private JTextField txtName;
	private JTextField txtNumber;
	private JTextField txtInfo;
	private JTextField txtEdit;
	private JButton btnCreate;
	private JButton btnChange;
	private JButton btnDelete;
	private JScrollPane scroll;
	private JPanel pnlKits;
	private JLabel lblMsg;
	private JComboBox dropDown1;
	private JComboBox dropDown2;
	private JComboBox dropDown3;
	private JComboBox dropDown4;
	private JComboBox dropDown5;
	private JComboBox dropDown6;
	private JComboBox dropDown7;
	private JComboBox dropDown8;
	
	public KitManager ( KitsClient kc ){
		myClient = kc;
		
		lblName = new JLabel("Kit Name: ");
		lblNumber = new JLabel("Kit Number: ");
		lblInfo = new JLabel("Kit Info: ");
		lblNumToProduce = new JLabel("Number of specified kits to produce: ");
		lblEdit = new JLabel("Kit will be changed to new kit above");
		lblEdit2 = new JLabel("Kit will be changed to new kit above");
		txtName = new JTextField(10);
		txtNumber = new JTextField(10);
		txtInfo = new JTextField(10);
		txtEdit = new JTextField(10);
		btnCreate = new JButton("Create");
		btnChange = new JButton("Change");
		btnDelete = new JButton("Delete");
		lblMsg = new JLabel("");
		
		//jscrollpane for list of parts
		pnlKits = new JPanel();
		pnlKits.setLayout( new BoxLayout( pnlKits, BoxLayout.Y_AXIS ) );
		scroll = new JScrollPane(pnlKits);
		
		//layout GUI
		setLayout( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		
		//parts scroll pane
		c.fill = c.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 2;
		c.gridheight = 10;
		add( scroll, c );
		
		
		//adding parts
		c.fill = c.HORIZONTAL;
		c.insets = new Insets(20,10,0,0);
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add( lblName, c );
		
		c.gridx = 2;
		c.gridy = 1;
		add( lblNumber, c );
		
		c.gridx = 2;
		c.gridy = 2;
		add( lblInfo, c );
		
		c.gridx = 3;
		c.gridy = 0;
		add( txtName, c );
		
		c.gridx = 3;
		c.gridy = 1;
		add( txtNumber, c );
		
		c.gridx = 3;
		c.gridy = 2;
		add( txtInfo, c );
		
		c.gridx = 4;
		c.gridy = 1;
		add( btnCreate, c );
		
		
		//changing/deleting parts
		c.gridx = 2;
		c.gridy = 4;
		add( lblEdit, c );
		
		c.gridx = 3;
		c.gridy = 3;
		add( lblEdit2, c );
		
		c.gridx = 3;
		c.gridy = 4;
		add( txtEdit, c );
		
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 3;
		add( btnChange, c );
		
		c.gridx = 4;
		c.gridy = 4;
		add( btnDelete, c );
		
		//messages
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 3;
		add( lblMsg, c );
		
		//action listeners for buttons
		btnCreate.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				if( !txtName.getText().equals("") && !txtInfo.getText().equals("") && !txtNumber.getText().equals("") ) {
					try{
						//add part to server
						myClient.getCom().write( new NewPartMsg( new Part( txtName.getText(), txtInfo.getText(), (int)Integer.parseInt( txtNumber.getText() ) ) ) );
						
						//display parts list
						requestParts();
					} catch (NumberFormatException nfe) {
						lblMsg.setText( "Please enter a number for Part Number" );
					}
				}
				else {
					lblMsg.setText( "Please enter all part information" );
				}
			}
		});
		
		btnChange.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				if( !txtName.getText().equals("") && !txtInfo.getText().equals("") && !txtNumber.getText().equals("") && !txtEdit.getText().equals("") ) {
					try{
						//replace part number X with new part
						myClient.getCom().write( new ChangePartMsg( (int)Integer.parseInt( txtEdit.getText() ), new Part( txtName.getText(), txtInfo.getText(), Integer.parseInt( txtNumber.getText() ) ) ) );

						//display parts list
						requestParts();
					} catch (NumberFormatException nfe) {
						lblMsg.setText( "Please enter a number for part to be changed" );
					}
				}
				else if( txtEdit.getText().equals("") ) {
					lblMsg.setText( "Please enter part number of part to be changed." );
				}
				else {
					lblMsg.setText( "Please enter all part information" );
				}
			}
		});
		
		btnDelete.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				if( !txtEdit.getText().equals("") ) {
					try {
						//delete the part on the server
						myClient.getCom().write( new DeleteKitMsg( Integer.parseInt( txtEdit.getText() ) ) );
	
						//display parts list
						requestParts();
					} catch (NumberFormatException nfe) {
						lblMsg.setText( "Please enter a number for part to be deleted" );
					}
				}
				else {
					lblMsg.setText( "Please enter kit number to be deleted." );
				}
			}
		});
	}
	
	public void requestParts()
	{
		//get updated parts list
		myClient.getCom().write( new PartListMsg() );
	}

	public void requestKits()
	{
		//get updated kits list
		myClient.getCom().write(new KitListMsg());
	}
	
	public void displayKits(){
		//remove current list from the panel
		pnlKits.removeAll();
				
		//add new list to panel
		ArrayList<Kit> temp = myClient.getKits();
				
		for( Kit k : temp )
		{ 
			pnlKits.add( new JLabel( k.getNumber() + " - " + k.getName() + " - " + k.getDescription() ) );
		}
				
		validate();
		repaint();
	}
	
	public void setMsg( String s ){
		lblMsg.setText(s);
	}


}
