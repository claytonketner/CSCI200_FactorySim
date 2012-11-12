import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class GUIServer extends JFrame implements ActionListener {
	ImageIcon laneImage, kitStandImage;
	KitRobotPanel kitRobotPanel;
	GantryRobotPanel gantryRobotPanel;
	PartRobotPanel partRobotPanel;
	NestPanel nestPanel;
	LanePanel lanePanel;
	FeederPanel feederPanel;
	
	public GUIServer() {
		laneImage = new ImageIcon( "images/lane/lane.png" );
		kitStandImage = new ImageIcon( "images/kit/kit_table_thumb.png" );
		kitRobotPanel = new KitRobotPanel( this );
		gantryRobotPanel = new GantryRobotPanel( this );
		partRobotPanel = new PartRobotPanel( this );
		nestPanel = new NestPanel();
		lanePanel = new LanePanel();
		feederPanel = new FeederPanel( this );
		
		setLayout( new FlowLayout( FlowLayout.LEFT, 0, 0 ) );
		add( kitRobotPanel );
		add( gantryRobotPanel );
		add( partRobotPanel );
		add( nestPanel );
		add( lanePanel );
		add( feederPanel );		
	}
	
	
	public static void main(String[] args) {
		GUIServer gs = new GUIServer();
		gs.setSize( 800, 600 );
		gs.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gs.setVisible( true );
	}

	public void actionPerformed( ActionEvent ae ) {
		
	}
	
	private class KitRobotPanel extends JPanel {
		ImageIcon kitRobotImage, kitDeliveryStationImage;
		JPanel kitRobotLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, dropOffPickUpButtonPanel, posButtonPanel, blankPanel;
		JLabel kitRobotLabel, takePictureLabel, kitStatusLabel, kitRobotImageLabel, kitStandImageLabel, kitDeliveryStationImageLabel;
		JButton pausePlayButton, cancelMoveButton, dropOffButton, pickUpButton, takePictureButton;	
		JRadioButton kitRobotOnButton, kitRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension panelSize, posButtonSize, blankPanelSize;
		ArrayList<JButton> kitStandPositionButtons;
		
		public KitRobotPanel( GUIServer guiServer ) {
			//ImageIcons
			kitRobotImage = new ImageIcon( "images/robots/kitRobot_thumb.png" );
			kitDeliveryStationImage = new ImageIcon( "images/misc/kit_delivery_station_thumb.png" );
			
			//JPanels
			kitRobotLabelPanel = new JPanel();
			robotOnOffButtonPanel = new JPanel();
			robotPauseCancelButtonPanel = new JPanel();
			dropOffPickUpButtonPanel = new JPanel();
			posButtonPanel = new JPanel();
			blankPanel = new JPanel();
			
			//JLabels
			kitRobotLabel = new JLabel();
			kitRobotLabel.setText( "Kit Robot" );
			kitRobotLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
			takePictureLabel = new JLabel();
			takePictureLabel.setText( "<-- Click for Kit Inspection" );
			kitStatusLabel = new JLabel();
			kitRobotImageLabel = new JLabel();
			kitRobotImageLabel.setIcon( kitRobotImage );
			kitStandImageLabel = new JLabel();
			kitStandImageLabel.setIcon( kitStandImage );
			kitDeliveryStationImageLabel = new JLabel();
			kitDeliveryStationImageLabel.setIcon( kitDeliveryStationImage );
			
			//JButtons
			pausePlayButton = new JButton();
			pausePlayButton.setText( "Pause" );
			pausePlayButton.setEnabled( false );
			cancelMoveButton = new JButton();
			cancelMoveButton.setText( "Cancel" );
			dropOffButton = new JButton();
			dropOffButton.setText( "Drop Off" );
			dropOffButton.setEnabled( false );
			pickUpButton = new JButton();
			pickUpButton.setText( "Pick Up" );
			takePictureButton = new JButton();
			takePictureButton.setText( "<html><body>Take<br/>Picture</body></html>" );
			
			//JRadioButtons
			kitRobotOnButton = new JRadioButton();
			kitRobotOnButton.setText( "ON" );
			kitRobotOnButton.setSelected( true );
			kitRobotOffButton = new JRadioButton();
			kitRobotOffButton.setText( "OFF" );
			onOffButtonGroup = new ButtonGroup();
			onOffButtonGroup.add( kitRobotOnButton );
			onOffButtonGroup.add( kitRobotOffButton );
			
			//Misc
			panelSize = new Dimension( 350, 300 );
			posButtonSize = new Dimension( 20, 20 );
			blankPanelSize = new Dimension( 20, 100 );
			kitStandPositionButtons = new ArrayList<JButton>();
			for ( int i = 0; i < 3; i++ ) {
				kitStandPositionButtons.add( new JButton() );
				kitStandPositionButtons.get( i ).setText( "" + ( i + 1 ) );
				kitStandPositionButtons.get( i ).setPreferredSize( posButtonSize );
				kitStandPositionButtons.get( i ).setMaximumSize( posButtonSize );
				kitStandPositionButtons.get( i ).setMinimumSize( posButtonSize );
				kitStandPositionButtons.get( i ).setMargin( new Insets( 1, 1, 1, 1 ) );
			}
			

			kitRobotLabelPanel.setLayout( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
			kitRobotLabelPanel.add( kitRobotLabel );
			
			posButtonPanel.setLayout( new BoxLayout( posButtonPanel, BoxLayout.Y_AXIS ) );
			posButtonPanel.add( Box.createGlue() );
			for( JButton button : kitStandPositionButtons ) {
				posButtonPanel.add( button );
				posButtonPanel.add( Box.createGlue() );
			}
			
			blankPanel.setPreferredSize( blankPanelSize );
			blankPanel.setMaximumSize( blankPanelSize );
			blankPanel.setMinimumSize( blankPanelSize );
			
			robotOnOffButtonPanel.setLayout( new BoxLayout( robotOnOffButtonPanel, BoxLayout.X_AXIS ) );
			robotOnOffButtonPanel.add( Box.createGlue() );
			robotOnOffButtonPanel.add( kitRobotOnButton );
			robotOnOffButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			robotOnOffButtonPanel.add( kitRobotOffButton );
			robotOnOffButtonPanel.add( Box.createGlue() );
			
			robotPauseCancelButtonPanel.setLayout( new BoxLayout( robotPauseCancelButtonPanel, BoxLayout.X_AXIS ) );
			robotPauseCancelButtonPanel.add( Box.createGlue() );
			robotPauseCancelButtonPanel.add( pausePlayButton );
			robotPauseCancelButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			robotPauseCancelButtonPanel.add( cancelMoveButton );
			robotPauseCancelButtonPanel.add( Box.createGlue() );	
			
			dropOffPickUpButtonPanel.setLayout( new BoxLayout( dropOffPickUpButtonPanel, BoxLayout.X_AXIS ) );
			dropOffPickUpButtonPanel.add( Box.createGlue() );
			dropOffPickUpButtonPanel.add( dropOffButton );
			dropOffPickUpButtonPanel.add(Box.createHorizontalStrut( 15 ) );
			dropOffPickUpButtonPanel.add( pickUpButton );
			dropOffPickUpButtonPanel.add( Box.createGlue() );
				
			setPreferredSize( panelSize );
			setMaximumSize( panelSize );
			setMinimumSize( panelSize );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setLayout( new GridBagLayout() );
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = c.gridy = 0;
			c.gridwidth = 2;
			c.gridheight = 3;
			c.fill = GridBagConstraints.BOTH;
			add( kitRobotImageLabel, c );
			c.gridy = 4;
			c.gridheight = 3;
			add( kitStandImageLabel, c );
			c.gridx = 2;
			c.gridy = 4;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.VERTICAL;
			add( posButtonPanel, c );
			c.gridx = 3;
			c.gridy = 1;
			c.gridheight = 6;
			add( blankPanel, c );
			c.gridx = 2;
			c.gridy = 0;
			c.gridwidth = 4;
			c.gridheight = 1;
			c.fill = GridBagConstraints.NONE;
			add( kitRobotLabel, c );
			c.gridx = 4;
			c.gridy = 1;
			c.gridwidth = 6;
			c.gridheight = 1;
			add( robotOnOffButtonPanel, c );
			c.gridy = 2;
			add( robotPauseCancelButtonPanel, c );
			c.gridy = 4;
			c.gridwidth = 4;
			add( kitDeliveryStationImageLabel, c );
			c.gridy = 5;
			add( dropOffPickUpButtonPanel, c );
			c.gridy = 6;
			c.gridwidth = 2;
			add( kitStatusLabel, c );
			c.gridx = 0;
			c.gridy = 8;
			c.gridwidth = 1;
			add( takePictureButton, c );
			c.gridx = 3;
			c.gridwidth = 2;
			add( takePictureLabel, c );			
			
		}
	}
	
	private class PartRobotPanel extends JPanel {
		
		
		public PartRobotPanel( GUIServer guiServer ) {
			
		}
	}

	private class GantryRobotPanel extends JPanel {
		
		
		public GantryRobotPanel( GUIServer guiServer ) {
			
		}
	}
	
	private class NestPanel extends JPanel {
		
	}
	
	private class LanePanel extends JPanel {
		
	}
	
	private class FeederPanel extends JPanel {
		
		
		public FeederPanel( GUIServer guiServer ) {
			
		}
	}
}
