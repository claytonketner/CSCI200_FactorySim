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
	ImageIcon laneImage, kitStandImage, kitImage;
	KitRobotPanel kitRobotPanel;
	GantryRobotPanel gantryRobotPanel;
	PartRobotPanel partRobotPanel;
	NestPanel nestPanel;
	LanePanel lanePanel;
	FeederPanel feederPanel;
	
	public GUIServer() {
		laneImage = new ImageIcon( "images/lane/lane.png" );
		kitStandImage = new ImageIcon( "images/kit/kit_table.png" );
		kitImage = new ImageIcon( "images/kit/empty_kit.png" );
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
		JPanel mainLeftPanel, mainRightPanel;
		ImagePanel imagePanel, kitStandPanel;
		JPanel kitRobotLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, laneSegmentPanel, dropOffPickUpButtonPanel, cameraPanel;
		JLabel kitRobotLabel, takePictureLabel;
		JButton pausePlayButton, cancelMoveButton, dropOffButton, pickUpButton, takePictureButton;	
		JRadioButton kitRobotOnButton, kitRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension panelSize, leftPanelSize, rightPanelSize;
		ArrayList<JButton> kitStandPositionButtons;
		
		public KitRobotPanel( GUIServer guiServer ) {
			mainLeftPanel = new JPanel();
			mainRightPanel = new JPanel();
			imagePanel = new ImagePanel( "C:/Users/Justin/team11/images/robots/kitRobot_thumb.png" );
			kitStandPanel = new ImagePanel( "C:/Users/Justin/team11/images/kit/kit_table.png" );
			kitRobotLabelPanel = new JPanel();
			robotOnOffButtonPanel = new JPanel();
			robotPauseCancelButtonPanel = new JPanel();
			laneSegmentPanel = new JPanel();
			dropOffPickUpButtonPanel = new JPanel();
			cameraPanel = new JPanel();
			kitRobotLabel = new JLabel();
			takePictureLabel = new JLabel();
			pausePlayButton = new JButton();
			cancelMoveButton = new JButton();
			dropOffButton = new JButton();
			pickUpButton = new JButton();
			takePictureButton = new JButton();
			kitRobotOnButton = new JRadioButton();
			kitRobotOffButton = new JRadioButton();
			onOffButtonGroup = new ButtonGroup();
			panelSize = new Dimension( 300, 300 );
			leftPanelSize = new Dimension( 100, 300 );
			rightPanelSize = new Dimension( 200, 300 );
			kitStandPositionButtons = new ArrayList<JButton>();
			for ( int i = 0; i < 3; i++ ) {
				kitStandPositionButtons.add( new JButton() );
				kitStandPositionButtons.get( i ).setText( "Kit Pos " + ( i + 1 ) );
			}
			
			kitRobotLabel.setText( "Kit Robot" );
			
			kitRobotOnButton.setText( "ON" );
			kitRobotOnButton.setSelected( true );
			kitRobotOffButton.setText( "OFF" );
			
			onOffButtonGroup.add( kitRobotOnButton );
			onOffButtonGroup.add( kitRobotOffButton );
			
			pausePlayButton.setText( "Pause" );
			pausePlayButton.setEnabled( false );
			
			cancelMoveButton.setText( "Cancel" );
			
			dropOffButton.setText( "Drop Off" );
			
			pickUpButton.setText( "Pick Up" );
			
			takePictureButton.setText( "Take Picture" );
			
			takePictureLabel.setText( "<-- Click for Kit Inspection" );
			
			kitRobotLabelPanel.setLayout( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
			kitRobotLabelPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ) );
			kitRobotLabelPanel.add( kitRobotLabel );
			
			robotOnOffButtonPanel.setLayout( new BoxLayout( robotOnOffButtonPanel, BoxLayout.X_AXIS ) );
			robotOnOffButtonPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ) );
			robotOnOffButtonPanel.add( Box.createGlue() );
			robotOnOffButtonPanel.add( kitRobotOnButton );
			robotOnOffButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			robotOnOffButtonPanel.add( kitRobotOffButton );
			robotOnOffButtonPanel.add( Box.createGlue() );
			
			robotPauseCancelButtonPanel.setLayout( new BoxLayout( robotPauseCancelButtonPanel, BoxLayout.X_AXIS ) );
			robotPauseCancelButtonPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ) );
			robotPauseCancelButtonPanel.add( Box.createGlue() );
			robotPauseCancelButtonPanel.add( pausePlayButton );
			robotPauseCancelButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			robotPauseCancelButtonPanel.add( cancelMoveButton );
			robotPauseCancelButtonPanel.add( Box.createGlue() );	
			
			dropOffPickUpButtonPanel.setLayout( new BoxLayout( dropOffPickUpButtonPanel, BoxLayout.X_AXIS ) );
			dropOffPickUpButtonPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ) );
			dropOffPickUpButtonPanel.add( Box.createGlue() );
			dropOffPickUpButtonPanel.add( dropOffButton );
			dropOffPickUpButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			dropOffPickUpButtonPanel.add( pickUpButton );
			dropOffPickUpButtonPanel.add( Box.createGlue() );
			
			cameraPanel.setLayout( new BoxLayout( cameraPanel, BoxLayout.X_AXIS ) );
			cameraPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ) );
			cameraPanel.add( Box.createGlue() );
			cameraPanel.add( takePictureButton );
			cameraPanel.add( takePictureLabel );
			cameraPanel.add( Box.createGlue() );
			
			imagePanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ) );
			
			kitStandPanel.setBorder( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ) );
			
			mainLeftPanel.setPreferredSize( leftPanelSize );
			mainLeftPanel.setMaximumSize( leftPanelSize );
			mainLeftPanel.setMinimumSize( leftPanelSize );
			mainLeftPanel.setLayout( new BoxLayout( mainLeftPanel, BoxLayout.Y_AXIS ) );
			mainLeftPanel.add( imagePanel );
			mainLeftPanel.add( kitStandPanel );
			
			mainRightPanel.setPreferredSize( rightPanelSize );
			mainRightPanel.setMaximumSize( rightPanelSize );
			mainRightPanel.setMinimumSize( rightPanelSize );
			mainRightPanel.setLayout( new BoxLayout( mainRightPanel, BoxLayout.Y_AXIS ) );
			mainRightPanel.add( kitRobotLabelPanel );
			mainRightPanel.add( robotOnOffButtonPanel );
			mainRightPanel.add( robotPauseCancelButtonPanel );
			mainRightPanel.add( laneSegmentPanel );
			mainRightPanel.add( dropOffPickUpButtonPanel );
			mainRightPanel.add( Box.createGlue() );
			mainRightPanel.add( cameraPanel );
			
			setPreferredSize( panelSize );
			setMaximumSize( panelSize );
			setMinimumSize( panelSize );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setLayout( new GridBagLayout() );
			GridBagConstraints c = new GridBagConstraints();
			
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
	
	public class ImagePanel extends JPanel {
		private BufferedImage image;

	    public ImagePanel( String imageDir ) {
	       try {                
	          image = ImageIO.read( new File( imageDir ) );
	       } catch (IOException ex) {
	            System.err.println( "error loading image" );
	       }
	    }
	    public void paintComponent( Graphics g ) {
	        super.paintComponent( g );
	        g.drawImage( image, 0, 0, null );         
	    }
	}
}
