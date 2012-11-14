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
		add( pName, c );
		
		c.gridx = 2;
		c.gridy = 1;
		add( pNumber, c );
		
		c.gridx = 2;
		c.gridy = 2;
		add( pInfo, c );
		
		c.gridx = 3;
		c.gridy = 0;
		add( tName, c );
		
		c.gridx = 3;
		c.gridy = 1;
		add( tNumber, c );
		
		c.gridx = 3;
		c.gridy = 2;
		add( tInfo, c );
		
		c.gridx = 4;
		c.gridy = 1;
		add( create, c );
		
		
		//changing/deleting parts
		c.gridx = 2;
		c.gridy = 4;
		add( pEdit, c );
		
		c.gridx = 3;
		c.gridy = 3;
		add( pEdit2, c );
		
		c.gridx = 3;
		c.gridy = 4;
		add( tEdit, c );
		
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 3;
		add( change, c );
		
		c.gridx = 4;
		c.gridy = 4;
		add( delete, c );
		
		//messages
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 3;
		add( msg, c );
		
		//action listeners for buttons
		create.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				if( !tName.getText().equals("") && !tInfo.getText().equals("") && !tNumber.getText().equals("") ) {
					try{
						//add part to server
						myClient.getCom().write( new NewPartMsg( new Part( tName.getText(), tInfo.getText(), (int)Integer.parseInt( tNumber.getText() ) ) ) );
						
						//display parts list
						requestParts();
					} catch (NumberFormatException nfe) {
						msg.setText( "Please enter a number for Part Number" );
					}
				}
				else {
					msg.setText( "Please enter all part information" );
				}
			}
		});
		
		change.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				if( !tName.getText().equals("") && !tInfo.getText().equals("") && !tNumber.getText().equals("") && !tEdit.getText().equals("") ) {
					try{
						//replace part number X with new part
						myClient.getCom().write( new ChangePartMsg( (int)Integer.parseInt( tEdit.getText() ), new Part( tName.getText(), tInfo.getText(), Integer.parseInt( tNumber.getText() ) ) ) );

						//display parts list
						requestParts();
					} catch (NumberFormatException nfe) {
						msg.setText( "Please enter a number for part to be changed" );
					}
				}
				else if( tEdit.getText().equals("") ) {
					msg.setText( "Please enter part number of part to be changed." );
				}
				else {
					msg.setText( "Please enter all part information" );
				}
			}
		});
		
		delete.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				if( !tEdit.getText().equals("") ) {
					try {
						//delete the part on the server
						myClient.getCom().write( new DeletePartMsg( Integer.parseInt( tEdit.getText() ) ) );
	
						//display parts list
						requestParts();
					} catch (NumberFormatException nfe) {
						msg.setText( "Please enter a number for part to be deleted" );
					}
				}
				else {
					msg.setText( "Please enter part number of part to be deleted." );
				}
			}
		});
	}
	
	public void requestParts(){
		//get updated parts list
		myClient.getCom().write( new PartListMsg() );
	}

	public void displayParts(){
		//remove current list from the panel
		parts.removeAll();
				
		//add new list to panel
		ArrayList<Part> temp = myClient.getParts();
				
		for( Part p : temp ){ //maybe use string builder in future?
			parts.add( new JLabel( p.getNumber() + " - " + p.getName() + " - " + p.getDescription() ) );
		}
				
		validate();
		repaint();
	}
	
	public void setMsg( String s ){
		msg.setText(s);
	}


}
