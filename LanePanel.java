import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class LanePanel extends JPanel {
	private LanesClient myClient;
	private JButton lanes;
	private JButton change;
	private JPanel buttonLayout;
	private JPanel panelLayout;
	private CardLayout cLayout;
	private LaneGraphics graphics;
	
	public LanePanel( LanesClient lc ) {
		myClient = lc;
		lanes = new JButton( "View Lanes" );
		change = new JButton( "Break Panel" );
		graphics = new LaneGraphics();
		
		panelLayout = new JPanel();
		cLayout = new CardLayout();
		panelLayout.setLayout( cLayout );
		
		buttonLayout = new JPanel();
		buttonLayout.setLayout( new GridBagLayout() );
		
		//layout buttons on bottom of panel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = c.VERTICAL;
		c.gridx = 0;
		buttonLayout.add( lanes, c );
		
		c.gridx = 1;
		buttonLayout.add( change, c );
		
		//layout graphics and break panel in center
		panelLayout.add(graphics, "gfx" );
		
		//layout panels
		setLayout( new BorderLayout() );
		add( buttonLayout, BorderLayout.SOUTH );
		add( panelLayout, BorderLayout.CENTER );
	}
	
	public NetComm getCom(){
		return myClient.getCom();
	}
	
	public void setFactoryState(FactoryStateMsg factoryState)
	{
		graphics.setFactoryState(factoryState);
	}

	public void update(FactoryUpdateMsg updateMsg)
	{
		graphics.update(updateMsg);
	}
}
