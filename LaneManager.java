import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class LaneManager extends JPanel {
	/** LanesClient variable for interacting with LaneClient */
	private LanesClient myClient;
	/** lanes button for viewing the lanes */
	private JButton lanes;
	/** change button for accessing break panel */
	private JButton change;
	/** panel for laying out the buttons */
	private JPanel buttonLayout;
	/** panel for laying out graphics panel and break panel */
	private JPanel panelLayout;
	/** cardlayout for switching between lane and break panels */
	private CardLayout cLayout;
	/** graphics panel */
	private LaneGraphics graphics;
	/** break panel*/
	private BreakLanePanel breaker;
	/** Initialize */
	public LaneManager( LanesClient lc ) {
		myClient = lc;
		lanes = new JButton( "View Lanes" );
		change = new JButton( "Break Panel" );
		graphics = new LaneGraphics();
		breaker = new BreakLanePanel( lc );
		
		panelLayout = new JPanel();
		cLayout = new CardLayout();
		panelLayout.setLayout( cLayout );
		
		buttonLayout = new JPanel();
		buttonLayout.setLayout( new GridBagLayout() );
		
		//layout buttons on bottom of panel
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		buttonLayout.add( lanes, c );
		
		c.gridx = 1;
		buttonLayout.add( change, c );
		
		//layout graphics and break panel in center
		panelLayout.add( graphics, "gfx" );
		panelLayout.add( breaker, "break" );
		
		//layout panels
		setLayout( new BorderLayout() );
		add( buttonLayout, BorderLayout.SOUTH );
		add( panelLayout, BorderLayout.CENTER );
		
		//action listeners
		lanes.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				cLayout.show( panelLayout, "gfx");
			}
		});
		
		change.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				cLayout.show( panelLayout, "break" );
			}
		});
	}
	
	/** gets NetComm for LaneClient */
	public NetComm getCom(){
		return myClient.getCom();
	}
	/** sets factory state in graphics panel */
	public void setFactoryState(FactoryStateMsg factoryState)
	{
		graphics.setFactoryState(factoryState);
	}
	/** updates factory state in graphics panel */
	public void update(FactoryUpdateMsg updateMsg)
	{
		graphics.update(updateMsg);
	}
}
