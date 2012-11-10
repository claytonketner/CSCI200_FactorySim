import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PartManager extends JPanel {
	private PartsClient myClient;
	private JLabel pName;
	private JLabel pNumber;
	private JLabel pInfo;
	private JTextField tName;
	private JTextField tNumber;
	private JTextField tInfo;
	private JButton create;
	private JButton change;
	private JButton delete;
	private JScrollPane scroll;
	private JPanel parts;
	private JPanel labels;
	private JPanel textfields;
	private JPanel buttons;
	
	public PartManager( PartsClient pc ){
		myClient = pc;
		
		pName = new JLabel("Part Name: ");
		pNumber = new JLabel("Part Number: ");
		pInfo = new JLabel("Part Info: ");
		tName = new JTextField(10);
		tNumber = new JTextField(10);
		tInfo = new JTextField(10);
		create = new JButton("Create");
		change = new JButton("Change");
		delete = new JButton("Delete");
		
		labels = new JPanel();
		textfields = new JPanel();
		buttons = new JPanel();
		parts = new JPanel();
		
		//layout the labels
		labels.setLayout( new BoxLayout( labels, BoxLayout.Y_AXIS ) );
		labels.add(pName);
		labels.add(pNumber);
		labels.add(pInfo);
		
		//layout the textfields
		textfields.setLayout( new BoxLayout( textfields, BoxLayout.Y_AXIS ) );
		textfields.add(tName);
		textfields.add(tNumber);
		textfields.add(tInfo);
		
		//layout the buttons
		buttons.setLayout( new BorderLayout() );
		buttons.add( create, BorderLayout.WEST );
		buttons.add( change, BorderLayout.CENTER );
		buttons.add( delete, BorderLayout.EAST );
		
		//jscrollpane for list of parts
		scroll = new JScrollPane(parts);
		
		//layout all panels
		setLayout( new BorderLayout() );
		add( scroll, BorderLayout.WEST );
		add( labels, BorderLayout.CENTER );
		add( textfields, BorderLayout.EAST );
		add( buttons, BorderLayout.SOUTH );
		
		//action listeners for buttons
	}
	
}
