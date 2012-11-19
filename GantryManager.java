import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

//code adapted from LanePanel for consistency

public class GantryManager extends JPanel {
	private GantryClient myClient;
	private JButton gantry;
	private JButton change;
	private JPanel buttonLayout;
	private JPanel panelLayout;
	private CardLayout cLayout;
	private GantryGraphics graphics;
	
	public GantryManager( GantryClient client ) {
		myClient = client;
		gantry = new JButton( "View Gantry" );
		change = new JButton( "Break Panel" );
		graphics = new GantryGraphics();
		
		panelLayout = new JPanel();
		cLayout = new CardLayout();
		panelLayout.setLayout( cLayout );
		
		buttonLayout = new JPanel();
		buttonLayout.setLayout( new GridBagLayout() );
		
		//layout buttons on bottom of panel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = c.VERTICAL;
		c.gridx = 0;
		buttonLayout.add( gantry, c );
		
		c.gridx = 1;
		buttonLayout.add( change, c );
		
		//layout graphics and break panel in center
		panelLayout.add(graphics, "Graphics" );
		
		//add break panel later
		
		//layout panels
		setLayout( new BorderLayout() );
		add( buttonLayout, BorderLayout.SOUTH );
		add( panelLayout, BorderLayout.CENTER );
	}
	
	public void setFactoryState(FactoryStateMsg factoryState)
	{
		graphics.setFactoryState(factoryState);
	}

	public void update(FactoryUpdateMsg updateMsg)
	{
		graphics.update(updateMsg);
	}
	
	public NetComm getCom(){
		return myClient.getCom();
	}
}