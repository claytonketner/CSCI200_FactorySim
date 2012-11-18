import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FactoryControlManager extends JFrame implements ActionListener {
	Server server;
	ImageIcon kitStandImage;
	JPanel mainGUIPanel, nestLaneFeederPanel, controlPanel, cardLayoutAndControlPanel, kitQueuePanel;
	KitRobotControlPanel kitRobotPanel;
	GantryRobotControlPanel gantryRobotPanel;
	PartRobotControlPanel partRobotPanel;
	NestControlPanel nestPanel;
	LaneControlPanel lanePanel;
	FeederControlPanel feederPanel;
	JButton kitRobotButton, partRobotButton, gantryRobotButton, nestLaneFeederButton;
	Dimension mainGUIPanelSize, controlPanelSize, kitQueueSize, controlButtonSize;
	CardLayout cl;
	
	public FactoryControlManager(Server server) {
		//store reference to server
		this.server = server;

		//ImageIcons
		kitStandImage = new ImageIcon( "images/guiserver_thumbs/kit_table_thumb.png" );
		
		//JPanels
		mainGUIPanel = new JPanel();
		nestLaneFeederPanel = new JPanel();
		controlPanel = new JPanel();
		cardLayoutAndControlPanel = new JPanel();
		kitQueuePanel = new JPanel();
		kitRobotPanel = new KitRobotControlPanel( this );
		gantryRobotPanel = new GantryRobotControlPanel( this );
		partRobotPanel = new PartRobotControlPanel( this );
		nestPanel = new NestControlPanel( this );
		lanePanel = new LaneControlPanel( this );
		feederPanel = new FeederControlPanel( this );
		
		//Dimensions
		mainGUIPanelSize = new Dimension( 690, 522 );
		controlPanelSize = new Dimension( 690, 40 );
		kitQueueSize = new Dimension( 94, 562 );
		controlButtonSize = new Dimension( 160, 30 );
		
		//JButtons
		kitRobotButton = new JButton();
		kitRobotButton.setText( "Kit Robot" );
		kitRobotButton.setPreferredSize( controlButtonSize );
		kitRobotButton.setMaximumSize( controlButtonSize );
		kitRobotButton.setMinimumSize( controlButtonSize );
		kitRobotButton.addActionListener( this );
		partRobotButton = new JButton();
		partRobotButton.setText( "Part Robot" );
		partRobotButton.setPreferredSize( controlButtonSize );
		partRobotButton.setMaximumSize( controlButtonSize );
		partRobotButton.setMinimumSize( controlButtonSize );
		partRobotButton.addActionListener( this );
		gantryRobotButton = new JButton();
		gantryRobotButton.setText( "Gantry Robot" );
		gantryRobotButton.setPreferredSize( controlButtonSize );
		gantryRobotButton.setMaximumSize( controlButtonSize );
		gantryRobotButton.setMinimumSize( controlButtonSize );
		gantryRobotButton.addActionListener( this );
		nestLaneFeederButton = new JButton();
		nestLaneFeederButton.setText( "Nests Lanes Feeders" );
		nestLaneFeederButton.setPreferredSize( controlButtonSize );
		nestLaneFeederButton.setMaximumSize( controlButtonSize );
		nestLaneFeederButton.setMinimumSize( controlButtonSize );
		nestLaneFeederButton.addActionListener( this );
		
		//Layout
		cl = new CardLayout();
	
		nestLaneFeederPanel.setLayout( new BoxLayout( nestLaneFeederPanel, BoxLayout.X_AXIS ) );
		nestLaneFeederPanel.add( nestPanel );
		nestLaneFeederPanel.add( lanePanel );
		nestLaneFeederPanel.add( feederPanel );
		
		mainGUIPanel.setLayout( cl );
		mainGUIPanel.setPreferredSize( mainGUIPanelSize );
		mainGUIPanel.setMaximumSize( mainGUIPanelSize );
		mainGUIPanel.setMinimumSize( mainGUIPanelSize );
		mainGUIPanel.add( kitRobotPanel, "kit_robot_panel" );
		mainGUIPanel.add( partRobotPanel, "part_robot_panel" );
		mainGUIPanel.add( gantryRobotPanel, "gantry_robot_panel" );
		mainGUIPanel.add( nestLaneFeederPanel, "nest_lane_feeder_panel" );
		
		controlPanel.setLayout( new BoxLayout( controlPanel, BoxLayout.X_AXIS ) );
		controlPanel.setBorder( BorderFactory.createLineBorder( Color.black ) );
		controlPanel.setPreferredSize( controlPanelSize );
		controlPanel.setMaximumSize( controlPanelSize );
		controlPanel.setMinimumSize( controlPanelSize );
		controlPanel.add( Box.createGlue() );
		controlPanel.add( kitRobotButton );
		controlPanel.add( Box.createGlue() );
		controlPanel.add( partRobotButton );
		controlPanel.add( Box.createGlue() );
		controlPanel.add( gantryRobotButton );
		controlPanel.add( Box.createGlue() );
		controlPanel.add( nestLaneFeederButton );
		controlPanel.add( Box.createGlue() );
		
		cardLayoutAndControlPanel.setLayout( new BoxLayout( cardLayoutAndControlPanel, BoxLayout.Y_AXIS ) );
		cardLayoutAndControlPanel.add( mainGUIPanel );
		cardLayoutAndControlPanel.add( controlPanel );
		
		kitQueuePanel.setBorder( BorderFactory.createLineBorder( Color.black ) );
		kitQueuePanel.setPreferredSize( kitQueueSize );
		kitQueuePanel.setMaximumSize( kitQueueSize );
		kitQueuePanel.setMinimumSize( kitQueueSize );
		
		setLayout( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
		add( kitQueuePanel );
		add( cardLayoutAndControlPanel );

		setSize( 800, 600 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible( true );
		addWindowListener(new WindowCloseListener());
	}

	public void actionPerformed( ActionEvent ae ) {
		if ( ae.getSource() == kitRobotButton ) {
			cl.show( mainGUIPanel,  "kit_robot_panel" );
		}
		else if ( ae.getSource() == partRobotButton ) {
			cl.show( mainGUIPanel,  "part_robot_panel" );
		}
		else if ( ae.getSource() == gantryRobotButton ) {
			cl.show( mainGUIPanel,  "gantry_robot_panel" );
		}
		else if ( ae.getSource() == nestLaneFeederButton ) {
			cl.show( mainGUIPanel,  "nest_lane_feeder_panel" );
		}
	}

	/** class to handle window close event */
	private class WindowCloseListener extends WindowAdapter {
		/** handle window close event */
		public void windowClosing(WindowEvent e) {
			server.saveSettings();
		}
	}
}
