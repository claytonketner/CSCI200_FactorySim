import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PartManager extends JPanel {
	private PartsClient myClient;
	private JLabel pName;
	private JLabel pNumber;
	private JLabel pInfo;
	private JLabel pEdit;
	private JTextField tName;
	private JTextField tNumber;
	private JTextField tInfo;
	private JTextField tEdit;
	private JButton create;
	private JButton change;
	private JButton delete;
	private JScrollPane scroll;
	private JPanel parts;
	private JLabel msg;
	
	public PartManager( PartsClient pc ){
		myClient = pc;
		
		pName = new JLabel("Part Name: ");
		pNumber = new JLabel("Part Number: ");
		pInfo = new JLabel("Part Info: ");
		pEdit = new JLabel("Number of part to be changed/deleted: ");
		tName = new JTextField(10);
		tNumber = new JTextField(10);
		tInfo = new JTextField(10);
		tEdit = new JTextField(10);
		create = new JButton("Create");
		change = new JButton("Change");
		delete = new JButton("Delete");
		msg = new JLabel("");
		
		//jscrollpane for list of parts
		parts = new JPanel();
		scroll = new JScrollPane(parts);
		
//		labels = new JPanel();
//		textfields = new JPanel();
//		buttons = new JPanel();
//		parts = new JPanel();
//		blank = new JPanel();
		
		setLayout( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		
		//parts scroll pane
		c.fill = c.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 10;
		add( scroll, c );
		
		
		//adding parts
		c.fill = c.HORIZONTAL;
		c.insets = new Insets(20,10,0,0);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		add( pName, c );
		
		c.gridx = 1;
		c.gridy = 1;
		add( pNumber, c );
		
		c.gridx = 1;
		c.gridy = 2;
		add( pInfo, c );
		
		c.gridx = 2;
		c.gridy = 0;
		add( tName, c );
		
		c.gridx = 2;
		c.gridy = 1;
		add( tNumber, c );
		
		c.gridx = 2;
		c.gridy = 2;
		add( tInfo, c );
		
		c.gridx = 3;
		c.gridy = 1;
		add( create, c );
		
		
		//changing/deleting parts
		c.gridheight = 2;
		c.insets = new Insets(50,10,0,0);
		c.gridx = 1;
		c.gridy = 3;
		add( pEdit, c );
		
		c.gridx = 2;
		c.gridy = 3;
		add( tEdit, c );
		
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 3;
		add( change, c );
	
		c.insets = new Insets(10,10,0,0);
		c.gridx = 3;
		c.gridy = 4;
		add( delete, c );
		
		//messages
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 3;
		add( msg, c );
		
		//action listeners for buttons
	}
	
}
