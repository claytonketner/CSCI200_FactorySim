import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class FactoryControlClient extends JFrame implements ActionListener {
	ImageIcon kitStandImage;
	JPanel mainGUIPanel, nestLaneFeederPanel, controlPanel, cardLayoutAndControlPanel, kitQueuePanel;
	KitRobotPanel kitRobotPanel;
	GantryRobotPanel gantryRobotPanel;
	PartRobotPanel partRobotPanel;
	NestPanel nestPanel;
	LanePanel lanePanel;
	FeederPanel feederPanel;
	JButton kitRobotButton, partRobotButton, gantryRobotButton, nestLaneFeederButton;
	Dimension mainGUIPanelSize, controlPanelSize, kitQueueSize;
	CardLayout cl;
	
	public FactoryControlClient() {
		//ImageIcons
		kitStandImage = new ImageIcon( "images/guiserver_thumbs/kit_table_thumb.png" );
		
		//JPanels
		mainGUIPanel = new JPanel();
		nestLaneFeederPanel = new JPanel();
		controlPanel = new JPanel();
		cardLayoutAndControlPanel = new JPanel();
		kitQueuePanel = new JPanel();
		kitRobotPanel = new KitRobotPanel( this );
		gantryRobotPanel = new GantryRobotPanel( this );
		partRobotPanel = new PartRobotPanel( this );
		nestPanel = new NestPanel( this );
		lanePanel = new LanePanel( this );
		feederPanel = new FeederPanel( this );
		
		//JButtons
		kitRobotButton = new JButton();
		kitRobotButton.setText( "Kit Robot" );
		kitRobotButton.addActionListener( this );
		partRobotButton = new JButton();
		partRobotButton.setText( "Part Robot" );
		partRobotButton.addActionListener( this );
		gantryRobotButton = new JButton();
		gantryRobotButton.setText( "Gantry Robot" );
		gantryRobotButton.addActionListener( this );
		nestLaneFeederButton = new JButton();
		nestLaneFeederButton.setText( "Nests Lanes Feeders" );
		nestLaneFeederButton.addActionListener( this );
		
		//Dimensions
		mainGUIPanelSize = new Dimension( 690, 522 );
		controlPanelSize = new Dimension( 690, 40 );
		kitQueueSize = new Dimension( 94, 562 );
		
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
		controlPanel.add( Box.createHorizontalStrut( 5 ) );
		controlPanel.add( partRobotButton );
		controlPanel.add( Box.createHorizontalStrut( 5 ) );
		controlPanel.add( gantryRobotButton );
		controlPanel.add( Box.createHorizontalStrut( 5 ) );
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
	}
	
	
	public static void main(String[] args) {
		new FactoryControlClient();
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
	
	//*************************************************************KIT ROBOT PANEL************************************************************************************//
	
	private class KitRobotPanel extends JPanel implements ActionListener {
		FactoryControlClient fcc;
		ImageIcon kitRobotImage, kitStandImage, kitDeliveryStationImage;
		JPanel kitRobotLabelPanel, kitRobotImageLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, dropOffPickUpButtonPanel, posButtonPanel, blankPanel1, blankPanel2;
		JLabel kitRobotLabel, takePictureLabel, kitStatusLabel, kitRobotImageLabel, kitStandImageLabel, kitDeliveryStationImageLabel;
		JButton pausePlayButton, cancelMoveButton, dropOffButton, pickUpButton, takePictureButton;	
		JRadioButton kitRobotOnButton, kitRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension posButtonSize, blankPanel1Size, blankPanel2Size, takePictureButtonSize;
		ArrayList<JButton> kitStandPositionButtons;
		
		public KitRobotPanel( FactoryControlClient fcc ) {
			this.fcc = fcc;
			
			//ImageIcons
			kitRobotImage = new ImageIcon( "images/guiserver_thumbs/kitRobot_thumb.png" );
			kitStandImage = new ImageIcon( "images/guiserver_thumbs/kit_table_thumb.png" );
			kitDeliveryStationImage = new ImageIcon( "images/guiserver_thumbs/kit_delivery_station_thumb.png" );
			
			//Dimensions
			posButtonSize = new Dimension( 40, 40 );
			blankPanel1Size = new Dimension( 80, 300 );
			blankPanel2Size = new Dimension( 100, 100 );
			takePictureButtonSize = new Dimension( 80, 30 );
			
			//JPanels
			kitRobotLabelPanel = new JPanel();
			kitRobotImageLabelPanel = new JPanel();
			robotOnOffButtonPanel = new JPanel();
			robotPauseCancelButtonPanel = new JPanel();
			dropOffPickUpButtonPanel = new JPanel();
			posButtonPanel = new JPanel();
			blankPanel1 = new JPanel();
			blankPanel2 = new JPanel();
			
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
			pausePlayButton.addActionListener( this );
			cancelMoveButton = new JButton();
			cancelMoveButton.setText( "Cancel" );
			cancelMoveButton.addActionListener( this );
			dropOffButton = new JButton();
			dropOffButton.setText( "Drop Off" );
			dropOffButton.setEnabled( false );
			dropOffButton.addActionListener( this );
			pickUpButton = new JButton();
			pickUpButton.setText( "Pick Up" );
			pickUpButton.addActionListener( this );
			takePictureButton = new JButton();
			takePictureButton.setPreferredSize( takePictureButtonSize );
			takePictureButton.setMaximumSize( takePictureButtonSize );
			takePictureButton.setMinimumSize( takePictureButtonSize );
			takePictureButton.setMargin( new Insets( 0, 0, 0, 0 ) );
			takePictureButton.setText( "Take Pic" );
			takePictureButton.addActionListener( this );
			kitStandPositionButtons = new ArrayList<JButton>();
			for ( int i = 0; i < 3; i++ ) {
				kitStandPositionButtons.add( new JButton() );
				kitStandPositionButtons.get( i ).setText( "" + ( i + 1 ) );
				kitStandPositionButtons.get( i ).setPreferredSize( posButtonSize );
				kitStandPositionButtons.get( i ).setMaximumSize( posButtonSize );
				kitStandPositionButtons.get( i ).setMinimumSize( posButtonSize );
				kitStandPositionButtons.get( i ).setMargin( new Insets( 1, 1, 1, 1 ) );
				kitStandPositionButtons.get( i ).addActionListener( this );
			}
			
			//JRadioButtons
			kitRobotOnButton = new JRadioButton();
			kitRobotOnButton.setText( "ON" );
			kitRobotOnButton.addActionListener( this );
			kitRobotOffButton = new JRadioButton();
			kitRobotOffButton.setText( "OFF" );
			kitRobotOffButton.addActionListener( this );
			onOffButtonGroup = new ButtonGroup();
			onOffButtonGroup.add( kitRobotOnButton );
			onOffButtonGroup.add( kitRobotOffButton );
			
			
			//Layout
			posButtonPanel.setLayout( new BoxLayout( posButtonPanel, BoxLayout.Y_AXIS ) );
			posButtonPanel.add( Box.createGlue() );
			for( JButton button : kitStandPositionButtons ) {
				posButtonPanel.add( button );
				posButtonPanel.add( Box.createGlue() );
			}
			
			kitRobotLabelPanel.setLayout( new BoxLayout( kitRobotLabelPanel, BoxLayout.X_AXIS ) );
			kitRobotLabelPanel.add( Box.createGlue() );
			kitRobotLabelPanel.add( kitRobotLabel );
			kitRobotLabelPanel.add( Box.createGlue() );
			
			kitRobotImageLabelPanel.add( kitRobotImageLabel );
			
			blankPanel1.setPreferredSize( blankPanel1Size );
			blankPanel1.setMaximumSize( blankPanel1Size );
			blankPanel1.setMinimumSize( blankPanel1Size );
			
			blankPanel2.setPreferredSize( blankPanel2Size );
			blankPanel2.setMaximumSize( blankPanel2Size );
			blankPanel2.setMinimumSize( blankPanel2Size );
			
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
			dropOffPickUpButtonPanel.add(Box.createHorizontalStrut( 70 ) );
			dropOffPickUpButtonPanel.add( pickUpButton );
			dropOffPickUpButtonPanel.add( Box.createGlue() );
				
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setLayout( new GridBagLayout() );
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = c.gridy = 0;
			c.gridwidth = 10;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( kitRobotLabelPanel, c );
			c.gridy = 2;
			c.gridwidth = 2;
			c.gridheight = 1;
			add( robotOnOffButtonPanel, c );
			c.gridy = 3;
			add( robotPauseCancelButtonPanel, c );
			c.gridy = 4;
			c.gridheight = 4;
			c.fill = GridBagConstraints.BOTH;
			add( kitStandImageLabel, c );
			c.gridy = 8;
			c.gridheight = 1;
			c.fill = GridBagConstraints.NONE;
			add( takePictureButton, c );
			c.gridx = 2;
			c.gridy = 4;
			c.gridwidth = 1;
			c.gridheight = 4;
			c.fill = GridBagConstraints.VERTICAL;
			add( posButtonPanel, c );
			c.gridy = 8;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.fill = GridBagConstraints.NONE;
			add( kitStatusLabel, c );
			c.gridx = 3;
			c.gridy = 1;
			c.gridwidth = 1;
			c.gridheight = 8;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( blankPanel1, c );
			c.gridx = 4;
			c.gridy = 2;
			c.gridwidth = 4;
			c.gridheight = 2;
			c.fill = GridBagConstraints.NONE;
			add( kitDeliveryStationImageLabel, c );
			c.gridx = 4;
			c.gridy = 4;
			c.gridwidth = 6;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( dropOffPickUpButtonPanel, c );
			c.gridx = 4;
			c.gridy = 4;
			c.gridwidth = 1;
			c.gridheight = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( blankPanel2, c );
			c.gridx = 5;
			c.gridy = 7;
			c.gridwidth = 2;
			c.gridheight = 4;
			c.fill = GridBagConstraints.NONE;
			add( kitRobotImageLabelPanel, c );
			
		}
		
		public void actionPerformed( ActionEvent ae ) {
			
		}
	}

	//*************************************************************PART ROBOT PANEL************************************************************************************//
	
	private class PartRobotPanel extends JPanel implements ActionListener {
		FactoryControlClient fcc;
		ImageIcon partRobotImage, nestImage;
		JPanel robotOnOffButtonPanel, robotPauseCancelButtonPanel, partRobotGripperButtonPanel, partRobotTitleLabelPanel, kit1Panel, kit2Panel, nestPanel, takePicPanel, blankPanel1, blankPanel2;
		JLabel partRobotTitleLabel, partRobotImageLabel;
		JButton pausePlayButton, cancelMoveButton;	
		JRadioButton partRobotOnButton, partRobotOffButton;
		ButtonGroup onOffButtonGroup, partRobotGripperButtonGroup;
		Dimension textFieldSize, kitButtonSize, nestButtonSize, nestPanelSize, takePicPanelSize, blankPanelSize, pictureConfirmationPanelSize, takePicButtonSize;
		ArrayList<JButton> kit1PositionButtons, kit2PositionButtons, nestButtons, takePictureButtons;
		ArrayList<JRadioButton> partRobotGripperButtons;
		ArrayList<ImageIcon> kitPosImages;
		ArrayList<JTextField> nestPartContentsTextFields;
		ArrayList<JLabel> colorLabels;
		ArrayList<ImageIcon> pictureConfirmationColors;
		ArrayList<JPanel> pictureConfirmationPanels, nests, cameras;
		
		public PartRobotPanel( FactoryControlClient fcc ) {
			this.fcc = fcc;
			
			//ImageIcons
			partRobotImage = new ImageIcon( "images/guiserver_thumbs/partRobot_thumb.png" );
			nestImage = new ImageIcon( "images/guiserver_thumbs/nest_thumb.png" );
			kitPosImages = new ArrayList<ImageIcon>();
			for ( int i = 0; i < 8; i++ ) {
				kitPosImages.add( new ImageIcon( "images/guiserver_thumbs/kit_pos/pos" + i + ".png" ) );
			}
			pictureConfirmationColors = new ArrayList<ImageIcon>();
			for( int i = 0; i < 4; i++ ) {
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/red.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/green.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/dark_red.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/dark_green.png" ) );
			}
			
			//Dimensions
			textFieldSize = new Dimension( 39, 15 );
			kitButtonSize = new Dimension( 28, 35 );
			nestButtonSize = new Dimension( 39, 25 );
			nestPanelSize = new Dimension( 40, 380 );
			blankPanelSize = new Dimension( 80, 380 );
			takePicPanelSize = new Dimension( 40, 380 );
			pictureConfirmationPanelSize = new Dimension( 40, 20 );
			takePicButtonSize = new Dimension( 40, 40 );
			
			//JPanels
			robotOnOffButtonPanel = new JPanel();
			robotPauseCancelButtonPanel = new JPanel();
			partRobotGripperButtonPanel = new JPanel();
			partRobotTitleLabelPanel = new JPanel();
			kit1Panel = new JPanel();
			kit2Panel = new JPanel();
			nestPanel = new JPanel();
			takePicPanel = new JPanel();
			blankPanel1 = new JPanel();
			blankPanel2 = new JPanel();
			pictureConfirmationPanels = new ArrayList<JPanel>();
			nests = new ArrayList<JPanel>();
			cameras = new ArrayList<JPanel>();
			
			//JLabels
			partRobotTitleLabel = new JLabel();
			partRobotTitleLabel.setText( "Part Robot" );
			partRobotTitleLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
			partRobotImageLabel = new JLabel();
			partRobotImageLabel.setIcon( partRobotImage );
			colorLabels = new ArrayList<JLabel>();
			for( int i = 0; i < 8; i++ ) {
				colorLabels.add( new JLabel() );
				if ( i % 2 == 0 ) {
					colorLabels.get( i ).setIcon( pictureConfirmationColors.get( 2 ) );
				}
				else
					colorLabels.get( i ).setIcon( pictureConfirmationColors.get( 3 ) );
			}
			
			//JButtons
			pausePlayButton = new JButton();
			pausePlayButton.setText( "Pause" );
			pausePlayButton.setEnabled( false );
			pausePlayButton.addActionListener( this );
			cancelMoveButton = new JButton();
			cancelMoveButton.setText( "Cancel" );
			cancelMoveButton.addActionListener( this );
			kit1PositionButtons = new ArrayList<JButton>();
			kit2PositionButtons = new ArrayList<JButton>();
			nestButtons = new ArrayList<JButton>();
			takePictureButtons = new ArrayList<JButton>();
			
			for ( int i = 0; i < 8; i++ ) {
				//Buttons for first kit on kit stand
				kit1PositionButtons.add( new JButton() );
				kit1PositionButtons.get( i ).setIcon( kitPosImages.get( i ) );
				kit1PositionButtons.get( i ).setMargin( new Insets( 0, 0, 0, 0 ) );
				kit1PositionButtons.get( i ).addActionListener( this );
				kit1PositionButtons.get( i ).setPreferredSize( kitButtonSize );
				kit1PositionButtons.get( i ).setMaximumSize( kitButtonSize );
				kit1PositionButtons.get( i ).setMinimumSize( kitButtonSize );
				//kit1PositionButtons.get( i ).setEnabled( false );
				
				//Buttons for second kit on kit stand
				kit2PositionButtons.add( new JButton() );
				kit2PositionButtons.get( i ).setIcon( kitPosImages.get( i ) );
				kit2PositionButtons.get( i ).setMargin( new Insets( 0, 0, 0, 0 ) );
				kit2PositionButtons.get( i ).addActionListener( this );
				kit2PositionButtons.get( i ).setPreferredSize( kitButtonSize );
				kit2PositionButtons.get( i ).setMaximumSize( kitButtonSize );
				kit2PositionButtons.get( i ).setMinimumSize( kitButtonSize );
				//kit2PositionButtons.get( i ).setEnabled( false );
				
				//Buttons for each of the 8 nests
				nestButtons.add( new JButton() );
				nestButtons.get( i ).setIcon( nestImage );
				nestButtons.get( i ).setMargin( new Insets( 0, 0, 0, 0 ) );
				nestButtons.get( i ).addActionListener( this );
				nestButtons.get( i ).setPreferredSize( nestButtonSize );
				nestButtons.get( i ).setMaximumSize( nestButtonSize );
				nestButtons.get( i ).setMinimumSize( nestButtonSize );
				
			}
			
			for ( int i = 0; i < 4; i++ ) {
				//Buttons to take pictures of nest pairs
				takePictureButtons.add( new JButton() );
				takePictureButtons.get( i ).setPreferredSize( takePicButtonSize );
				takePictureButtons.get( i ).setMaximumSize( takePicButtonSize );
				takePictureButtons.get( i ).setMinimumSize( takePicButtonSize );
				takePictureButtons.get( i ).setText( "<html><body>Take<br/>Pic</body></html>" );
				takePictureButtons.get( i ).setMargin( new Insets( 1, 1, 1, 1 ) );
				takePictureButtons.get( i ).addActionListener( this );
			}
			
			
			//JRadioButtons
			partRobotOnButton = new JRadioButton();
			partRobotOnButton.setText( "ON" );
			partRobotOnButton.addActionListener( this );
			partRobotOffButton = new JRadioButton();
			partRobotOffButton.setText( "OFF" );
			partRobotOffButton.addActionListener( this );
			onOffButtonGroup = new ButtonGroup();
			onOffButtonGroup.add( partRobotOnButton );
			onOffButtonGroup.add( partRobotOffButton );
			partRobotGripperButtons = new ArrayList<JRadioButton>();
			partRobotGripperButtonGroup = new ButtonGroup();
			for ( int i = 0; i < 4; i++ ) {
				partRobotGripperButtons.add( new JRadioButton() );
				partRobotGripperButtons.get( i ).addActionListener( this );
				partRobotGripperButtons.get( i ).setMargin( new Insets( 0, 0, 0, 0 ) );
				partRobotGripperButtonGroup.add( partRobotGripperButtons.get( i ) );
			}
			
			//JTextFields
			nestPartContentsTextFields = new ArrayList<JTextField>();
			for ( int i = 0; i < 8; i++ ) {
				nestPartContentsTextFields.add( new JTextField() );
				nestPartContentsTextFields.get( i ).setEditable( false );
				nestPartContentsTextFields.get( i ).setPreferredSize( textFieldSize );
				nestPartContentsTextFields.get( i ).setMaximumSize( textFieldSize );
				nestPartContentsTextFields.get( i ).setMinimumSize( textFieldSize );
			}
			
			
			//Layout
			
			blankPanel1.setPreferredSize( blankPanelSize );
			blankPanel1.setMaximumSize( blankPanelSize );
			blankPanel1.setMinimumSize( blankPanelSize );
			
			blankPanel2.setPreferredSize( blankPanelSize );
			blankPanel2.setMaximumSize( blankPanelSize );
			blankPanel2.setMinimumSize( blankPanelSize );
			
			partRobotTitleLabelPanel.setLayout( new BoxLayout( partRobotTitleLabelPanel, BoxLayout.X_AXIS ) );
			partRobotTitleLabelPanel.add( Box.createGlue() );
			partRobotTitleLabelPanel.add( partRobotTitleLabel );
			partRobotTitleLabelPanel.add( Box.createGlue() );
			
			kit1Panel.setBorder( new TitledBorder ( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ), "Kit Position 1" ) );
			kit1Panel.setLayout( new GridLayout( 2, 4, 0, 0 ) );
			for ( JButton button : kit1PositionButtons ) {
				kit1Panel.add( button );
			}
			
			kit2Panel.setBorder( new TitledBorder ( BorderFactory.createEtchedBorder( EtchedBorder.RAISED ), "Kit Position 2" ) );
			kit2Panel.setLayout( new GridLayout( 2, 4, 0, 0 ) );
			for ( JButton button : kit2PositionButtons ) {
				kit2Panel.add( button );
			}
			
			takePicPanel.setLayout( new BoxLayout( takePicPanel, BoxLayout.Y_AXIS ) ); 
			takePicPanel.setPreferredSize( takePicPanelSize );
			takePicPanel.setMaximumSize( takePicPanelSize );
			takePicPanel.setMinimumSize( takePicPanelSize );
			takePicPanel.add( Box.createVerticalStrut( 10 ) );
			for ( int i = 0; i < 4; i++ ){
				pictureConfirmationPanels.add( new JPanel() );
				pictureConfirmationPanels.get( i ).setLayout( new BoxLayout( pictureConfirmationPanels.get( i ), BoxLayout.X_AXIS ) );
				pictureConfirmationPanels.get( i ).setPreferredSize( pictureConfirmationPanelSize );
				pictureConfirmationPanels.get( i ).setMaximumSize( pictureConfirmationPanelSize );
				pictureConfirmationPanels.get( i ).setMinimumSize( pictureConfirmationPanelSize );
				pictureConfirmationPanels.get( i ).add( colorLabels.get( i * 2 ) );
				pictureConfirmationPanels.get( i ).add( colorLabels.get( i * 2 + 1 ) );
				cameras.add( new JPanel() );
				cameras.get( i ).setLayout( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
				cameras.get( i ).add( takePictureButtons.get( i ) );
				cameras.get( i ).add( pictureConfirmationPanels.get( i ) );
				takePicPanel.add( cameras.get( i ) );
				if ( i < 3 )
					takePicPanel.add( Box.createGlue() );
			}
			takePicPanel.add( Box.createVerticalStrut( 10 ) );
			
			robotOnOffButtonPanel.setLayout( new BoxLayout( robotOnOffButtonPanel, BoxLayout.X_AXIS ) );
			robotOnOffButtonPanel.add( Box.createGlue() );
			robotOnOffButtonPanel.add( partRobotOnButton );
			robotOnOffButtonPanel.add(Box.createHorizontalStrut( 40 ) );
			robotOnOffButtonPanel.add( partRobotOffButton );
			robotOnOffButtonPanel.add( Box.createGlue() );
			
			robotPauseCancelButtonPanel.setLayout( new BoxLayout( robotPauseCancelButtonPanel, BoxLayout.X_AXIS ) );
			robotPauseCancelButtonPanel.add( Box.createGlue() );
			robotPauseCancelButtonPanel.add( pausePlayButton );
			robotPauseCancelButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			robotPauseCancelButtonPanel.add( cancelMoveButton );
			robotPauseCancelButtonPanel.add( Box.createGlue() );	
			
			nestPanel.setLayout( new BoxLayout( nestPanel, BoxLayout.Y_AXIS ) );
			nestPanel.setPreferredSize( nestPanelSize );
			nestPanel.setMaximumSize( nestPanelSize );
			nestPanel.setMinimumSize( nestPanelSize );
			for( int i = 0; i < 8; i++ ) {
				nests.add( new JPanel() );
				nests.get( i ).setLayout( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
				nests.get( i ).add( nestButtons.get( i ) );
				nests.get( i ).add( nestPartContentsTextFields.get( i ) );
				nestPanel.add( nests.get( i ) );
			}
			
			partRobotGripperButtonPanel.setLayout( new BoxLayout( partRobotGripperButtonPanel, BoxLayout.X_AXIS ) );
			partRobotGripperButtonPanel.add( Box.createHorizontalStrut( 7 ) );
			for( int i = 0; i < 3; i++ ) {
				partRobotGripperButtonPanel.add( partRobotGripperButtons.get( i ) );
				partRobotGripperButtonPanel.add( Box.createGlue() );
			}
			partRobotGripperButtonPanel.add( partRobotGripperButtons.get( 3 ) );
			partRobotGripperButtonPanel.add( Box.createHorizontalStrut( 7 ) );
				
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setLayout( new GridBagLayout() );
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = c.gridy = 0;
			c.gridwidth = 8;
			c.gridheight = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( partRobotTitleLabelPanel, c );
			c.gridy = 2;
			c.gridwidth = 2;
			c.gridheight = 1;
			add( robotOnOffButtonPanel, c );
			c.gridy = 3;
			add( robotPauseCancelButtonPanel, c );
			c.gridy = 6;
			c.gridheight = 3;
			c.fill = GridBagConstraints.NONE;
			add( kit1Panel, c );
			c.gridy = 9;
			add( kit2Panel, c );
			c.gridx = 2;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 12;
			add( blankPanel1, c );
			c.gridx = 3;
			c.gridy = 5;
			c.gridwidth = 2;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( partRobotGripperButtonPanel, c );
			c.gridy = 6;
			c.gridheight = 6;
			c.fill = GridBagConstraints.NONE;
			add( partRobotImageLabel, c );
			c.gridx = 5;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 12;
			add( blankPanel2, c );
			c.gridx = 6;
			add( nestPanel, c );
			c.gridx = 7;
			add( takePicPanel, c );
			
		}
		
		public void actionPerformed( ActionEvent ae ) {
			
		}
	}

	//*************************************************************GANTRY ROBOT PANEL************************************************************************************//
	
	private class GantryRobotPanel extends JPanel implements ActionListener {	
		FactoryControlClient fcc;
		ImageIcon gantryRobotImage, partsBoxImage, feederImage;
		JPanel gantryRobotTitleLabelPanel, gantryRobotImageLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, partsBinsLabelPanel, sparePartsLabelPanel; 
		JPanel blankPanel1, blankPanel2, partsBoxStoragePanel, feederPanel, sparePartsPanel;
		JLabel gantryRobotTitleLabel, gantryRobotImageLabel, partsBinsLabel, sparePartsLabel;
		JButton pausePlayButton, cancelMoveButton;	
		JRadioButton gantryRobotOnButton, gantryRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension boxButtonSize, blankPanelSize, textFieldSize, boxPanelSize, feederPanelSize;
		ArrayList<JPanel> singlePartsBoxPanels, singleFeederPanels, singleSparePartsPanels;
		ArrayList<JButton> partsBoxStorageButtons, feederButtons, sparePartsButtons;
		ArrayList<JTextField> partsBoxStorageTextFields, feederTextFields, sparePartsTextFields;
		ArrayList<JLabel> partPurgeBoxLabels;
		
		public GantryRobotPanel( FactoryControlClient fcc ) {
			this.fcc = fcc;
			
			//ImageIcons
			gantryRobotImage = new ImageIcon( "images/guiserver_thumbs/gantry_thumb.png" );
			partsBoxImage = new ImageIcon( "images/guiserver_thumbs/partsbox_thumb.png" );
			feederImage = new ImageIcon( "images/guiserver_thumbs/feeder_thumb.png" );
			
			//Dimensions
			boxButtonSize = new Dimension( 85, 85 );
			blankPanelSize = new Dimension( 40, 300 );
			textFieldSize = new Dimension( 40, 15 );
			boxPanelSize = new Dimension( 85, 110 );
			feederPanelSize = new Dimension( 191, 100 );
			
			//JPanels
			gantryRobotTitleLabelPanel = new JPanel();
			gantryRobotImageLabelPanel = new JPanel();
			robotOnOffButtonPanel = new JPanel();
			robotPauseCancelButtonPanel = new JPanel();
			partsBinsLabelPanel = new JPanel();
			sparePartsLabelPanel = new JPanel();
			blankPanel1 = new JPanel();
			blankPanel2 = new JPanel();
			partsBoxStoragePanel = new JPanel();
			feederPanel = new JPanel();
			sparePartsPanel = new JPanel();
			singlePartsBoxPanels = new ArrayList<JPanel>();
			singleFeederPanels = new ArrayList<JPanel>();
			singleSparePartsPanels = new ArrayList<JPanel>();
			
			//JTextFields
			partsBoxStorageTextFields = new ArrayList<JTextField>();
			feederTextFields = new ArrayList<JTextField>();
			sparePartsTextFields = new ArrayList<JTextField>();
			for( int i = 0; i < 4; i++ ) {
				partsBoxStorageTextFields.add( new JTextField() );
				partsBoxStorageTextFields.get( i ).setEditable( false );
				partsBoxStorageTextFields.get( i ).setPreferredSize( textFieldSize );
				partsBoxStorageTextFields.get( i ).setMaximumSize( textFieldSize );
				partsBoxStorageTextFields.get( i ).setMinimumSize( textFieldSize );
				
				feederTextFields.add( new JTextField() );
				feederTextFields.get( i ).setEditable( false );
				feederTextFields.get( i ).setPreferredSize( textFieldSize );
				feederTextFields.get( i ).setMaximumSize( textFieldSize );
				feederTextFields.get( i ).setMinimumSize( textFieldSize );
				
				sparePartsTextFields.add( new JTextField() );
				sparePartsTextFields.get( i ).setEditable( false );
				sparePartsTextFields.get( i ).setPreferredSize( textFieldSize );
				sparePartsTextFields.get( i ).setMaximumSize( textFieldSize );
				sparePartsTextFields.get( i ).setMinimumSize( textFieldSize );
			}
			
			//JLabels
			gantryRobotTitleLabel = new JLabel();
			gantryRobotTitleLabel.setText( "Gantry Robot" );
			gantryRobotTitleLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
			gantryRobotImageLabel = new JLabel();
			gantryRobotImageLabel.setIcon( gantryRobotImage );
			partsBinsLabel = new JLabel();
			partsBinsLabel.setText( "Parts Bins" );
			partsBinsLabel.setFont( new Font( "Serif", Font.BOLD, 20 ) );
			sparePartsLabel = new JLabel();
			sparePartsLabel.setText( "Spare Parts" );
			sparePartsLabel.setFont( new Font( "Serif", Font.BOLD, 20 ) );
			partPurgeBoxLabels = new ArrayList<JLabel>();
			for( int i = 0; i < 4; i++ ) {
				partPurgeBoxLabels.add( new JLabel() );
				partPurgeBoxLabels.get( i ).setIcon( partsBoxImage );
			}
			
			//JButtons
			pausePlayButton = new JButton();
			pausePlayButton.setText( "Pause" );
			pausePlayButton.setEnabled( false );
			pausePlayButton.addActionListener( this );
			cancelMoveButton = new JButton();
			cancelMoveButton.setText( "Cancel" );
			cancelMoveButton.addActionListener( this );
			partsBoxStorageButtons = new ArrayList<JButton>();
			feederButtons = new ArrayList<JButton>();
			sparePartsButtons = new ArrayList<JButton>();
			for( int i = 0; i < 4; i++ ) {
				partsBoxStorageButtons.add( new JButton() );
				partsBoxStorageButtons.get( i ).setIcon( partsBoxImage );
				partsBoxStorageButtons.get( i ).setPreferredSize( boxButtonSize );
				partsBoxStorageButtons.get( i ).setMaximumSize( boxButtonSize );
				partsBoxStorageButtons.get( i ).setMinimumSize( boxButtonSize );
				
				feederButtons.add( new JButton() );
				feederButtons.get( i ).setIcon( feederImage );
				feederButtons.get( i ).setMargin( new Insets( 0, 0, 0, 0 ) );
				feederButtons.get( i ).setContentAreaFilled( false );
				//feederButtons.get( i ).setBorderPainted( false );
				
				sparePartsButtons.add( new JButton() );
				sparePartsButtons.get( i ).setIcon( partsBoxImage );
				sparePartsButtons.get( i ).setPreferredSize( boxButtonSize );
				sparePartsButtons.get( i ).setMaximumSize( boxButtonSize );
				sparePartsButtons.get( i ).setMinimumSize( boxButtonSize );
			}
			
			//JRadioButtons
			gantryRobotOnButton = new JRadioButton();
			gantryRobotOnButton.setText( "ON" );
			gantryRobotOnButton.addActionListener( this );
			gantryRobotOffButton = new JRadioButton();
			gantryRobotOffButton.setText( "OFF" );
			gantryRobotOffButton.addActionListener( this );
			onOffButtonGroup = new ButtonGroup();
			onOffButtonGroup.add( gantryRobotOnButton );
			onOffButtonGroup.add( gantryRobotOffButton );
			
			//Layout
			
			gantryRobotTitleLabelPanel.setLayout( new BoxLayout( gantryRobotTitleLabelPanel, BoxLayout.X_AXIS ) );
			gantryRobotTitleLabelPanel.add( Box.createHorizontalStrut( 50 ) );
			gantryRobotTitleLabelPanel.add( gantryRobotTitleLabel );
			gantryRobotTitleLabelPanel.add( Box.createGlue() );
			
			partsBinsLabelPanel.setLayout( new BoxLayout( partsBinsLabelPanel, BoxLayout.X_AXIS ) );
			partsBinsLabelPanel.add( Box.createGlue() );
			partsBinsLabelPanel.add( partsBinsLabel );
			partsBinsLabelPanel.add( Box.createGlue() );
			
			sparePartsLabelPanel.setLayout( new BoxLayout( sparePartsLabelPanel, BoxLayout.X_AXIS ) );
			sparePartsLabelPanel.add( Box.createGlue() );
			sparePartsLabelPanel.add( sparePartsLabel );
			sparePartsLabelPanel.add( Box.createGlue() );
			
			gantryRobotImageLabelPanel.add( gantryRobotImageLabel );
			
			blankPanel1.setPreferredSize( blankPanelSize );
			blankPanel1.setMaximumSize( blankPanelSize );
			blankPanel1.setMinimumSize( blankPanelSize );
			
			blankPanel2.setPreferredSize( blankPanelSize );
			blankPanel2.setMaximumSize( blankPanelSize );
			blankPanel2.setMinimumSize( blankPanelSize );
			
			robotOnOffButtonPanel.setLayout( new BoxLayout( robotOnOffButtonPanel, BoxLayout.X_AXIS ) );
			robotOnOffButtonPanel.add( Box.createGlue() );
			robotOnOffButtonPanel.add( gantryRobotOnButton );
			robotOnOffButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			robotOnOffButtonPanel.add( gantryRobotOffButton );
			robotOnOffButtonPanel.add( Box.createGlue() );
			
			robotPauseCancelButtonPanel.setLayout( new BoxLayout( robotPauseCancelButtonPanel, BoxLayout.X_AXIS ) );
			robotPauseCancelButtonPanel.add( Box.createGlue() );
			robotPauseCancelButtonPanel.add( pausePlayButton );
			robotPauseCancelButtonPanel.add(Box.createHorizontalStrut( 20 ) );
			robotPauseCancelButtonPanel.add( cancelMoveButton );
			robotPauseCancelButtonPanel.add( Box.createGlue() );	
			
			for ( int i = 0; i < 4; i++ ) {
				
				singlePartsBoxPanels.add( new JPanel() );
				singlePartsBoxPanels.get( i ).setLayout( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
				singlePartsBoxPanels.get( i ).setPreferredSize( boxPanelSize );
				singlePartsBoxPanels.get( i ).setMaximumSize( boxPanelSize );
				singlePartsBoxPanels.get( i ).setMinimumSize( boxPanelSize );
				singlePartsBoxPanels.get( i ).add( partsBoxStorageButtons.get( i ) );
				singlePartsBoxPanels.get( i ).add( partsBoxStorageTextFields.get( i ) );
				
				singleFeederPanels.add( new JPanel() );
				singleFeederPanels.get( i ).setLayout( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
				singleFeederPanels.get( i ).setPreferredSize( feederPanelSize );
				singleFeederPanels.get( i ).setMaximumSize( feederPanelSize );
				singleFeederPanels.get( i ).setMinimumSize( feederPanelSize );
				singleFeederPanels.get( i ).add( feederButtons.get( i ) );
				singleFeederPanels.get( i ).add( feederTextFields.get( i ) );
				
				singleSparePartsPanels.add( new JPanel() );
				singleSparePartsPanels.get( i ).setLayout( new FlowLayout( FlowLayout.CENTER, 0, 0 ) );
				singleSparePartsPanels.get( i ).setPreferredSize( boxPanelSize );
				singleSparePartsPanels.get( i ).setMaximumSize( boxPanelSize );
				singleSparePartsPanels.get( i ).setMinimumSize( boxPanelSize );
				singleSparePartsPanels.get( i ).add( sparePartsButtons.get( i ) );
				singleSparePartsPanels.get( i ).add( sparePartsTextFields.get( i ) );	
			}
			
			partsBoxStoragePanel.setLayout( new GridBagLayout() );
			GridBagConstraints a = new GridBagConstraints();
			a.gridx = a.gridy = 0;
			a.gridwidth = 2;
			a.fill = GridBagConstraints.HORIZONTAL;
			partsBoxStoragePanel.add( partsBinsLabelPanel, a );
			a.gridy++;
			a.gridwidth = 1;
			a.gridheight = 2;
			a.fill = GridBagConstraints.NONE;
			a.insets = new Insets( 10, 10, 10, 10 );
			partsBoxStoragePanel.add( singlePartsBoxPanels.get( 0 ), a );
			a.gridx++;
			partsBoxStoragePanel.add( singlePartsBoxPanels.get( 1 ), a );
			a.gridx--;
			a.gridy = 3;
			partsBoxStoragePanel.add( singlePartsBoxPanels.get( 2 ), a );
			a.gridx++;
			partsBoxStoragePanel.add( singlePartsBoxPanels.get( 3 ), a );
			
			feederPanel.setLayout( new GridBagLayout() );
			GridBagConstraints b = new GridBagConstraints();
			b.gridx = b.gridy = 0;
			b.insets = new Insets( 10, 0, 0, 0 );
			for( JPanel feeder : singleFeederPanels ) {
				feederPanel.add( feeder, b );
				b.gridy++;
			}
			b.gridx++;
			b.gridy = 0;
			for( JLabel boxLabel : partPurgeBoxLabels ) {
				feederPanel.add( boxLabel, b );
				b.gridy++;
			}
			
			sparePartsPanel.setLayout( new BoxLayout( sparePartsPanel, BoxLayout.Y_AXIS ) );
			sparePartsPanel.add( Box.createVerticalStrut( 10 ) );
			sparePartsPanel.add( sparePartsLabelPanel );
			for ( JPanel sparePartsBox : singleSparePartsPanels ) {
				sparePartsPanel.add( sparePartsBox );
				sparePartsPanel.add( Box.createGlue() );
			}
				
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setLayout( new GridBagLayout() );
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = c.gridy = 0;
			c.gridwidth =  2;
			c.gridheight = 4;
			add( gantryRobotImageLabelPanel, c );
			c.gridy = 4;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( robotOnOffButtonPanel, c );
			c.gridy = 5;
			add( robotPauseCancelButtonPanel, c );
			c.gridy = 6;
			c.gridwidth = 2;
			c.gridheight = 6;
			c.fill = GridBagConstraints.NONE;
			add( partsBoxStoragePanel, c );
			c.gridx = 2;
			c.gridy = 0;
			c.gridwidth = 7;
			c.gridheight = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( gantryRobotTitleLabelPanel, c );
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 10;
			c.fill = GridBagConstraints.VERTICAL;
			add( blankPanel1, c );
			c.gridx = 3;
			c.gridwidth = 4;
			c.gridheight = 10;
			c.fill = GridBagConstraints.NONE;
			add( feederPanel, c );
			c.gridx = 7;
			c.gridwidth = 1;
			add( blankPanel2, c );
			c.gridx = 8;
			c.gridwidth = 2;
			add( sparePartsPanel, c );
		}

		public void actionPerformed( ActionEvent ae ) {
			// TODO Auto-generated method stub
			
		}
	}
	
	//*************************************************************NEST PANEL************************************************************************************//
	
	private class NestPanel extends JPanel implements ActionListener {
		FactoryControlClient fcc;
		ImageIcon nestImage;
		JPanel nestsTitleLabelPanel;
		JLabel nestsTitleLabel;
		ArrayList<JPanel> radioButtonPairPanel, nestPanels, radioButtonPairAndNestPanels;
		ArrayList<JRadioButton> upRadioButtons, downRadioButtons;
		ArrayList<ButtonGroup> radioButtonGroups;
		ArrayList<JLabel> nestImageLabels;
		
		public NestPanel( FactoryControlClient fcc ) {
			this.fcc = fcc;
			
			Dimension panelSize = new Dimension ( 150, 522 );
			
			//ImageIcons
			nestImage = new ImageIcon( "images/guiserver_thumbs/nest_thumb_large.png" );
			
			//JPanels
			nestsTitleLabelPanel = new JPanel();
			radioButtonPairPanel = new ArrayList<JPanel>();
			nestPanels = new ArrayList<JPanel>();
			radioButtonPairAndNestPanels = new ArrayList<JPanel>();
			
			//JLabels
			nestsTitleLabel = new JLabel();
			nestsTitleLabel.setText( "Nests" );
			nestsTitleLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
			nestImageLabels = new ArrayList<JLabel>();
			for( int i = 0; i < 8; i++ ) {
				nestImageLabels.add( new JLabel() );
				nestImageLabels.get( i ).setIcon( nestImage );
			}
			
			//JRadioButtons
			upRadioButtons = new ArrayList<JRadioButton>();
			downRadioButtons = new ArrayList<JRadioButton>();
			for( int i = 0; i < 8; i++ ) {
				upRadioButtons.add( new JRadioButton() );
				upRadioButtons.get( i ).setText( "Up" );
				downRadioButtons.add( new JRadioButton() );
				downRadioButtons.get( i ).setText( "Down" );
			}
			
			//ButtonGroups
			radioButtonGroups = new ArrayList<ButtonGroup>();
			for( int i = 0; i < 8; i++ ) {
				radioButtonGroups.add( new ButtonGroup() );
				radioButtonGroups.get( i ).add( upRadioButtons.get( i ) );
				radioButtonGroups.get( i ).add( downRadioButtons.get( i ) );
			}
			
			//Layout
			
			nestsTitleLabelPanel.setLayout( new BoxLayout( nestsTitleLabelPanel, BoxLayout.X_AXIS ) );
			nestsTitleLabelPanel.add( Box.createGlue() );
			nestsTitleLabelPanel.add( nestsTitleLabel );
			nestsTitleLabelPanel.add( Box.createGlue() );
			
			for( int i = 0; i < 8; i++ ) {
				radioButtonPairPanel.add( new JPanel() );
				radioButtonPairPanel.get( i ).setLayout( new BoxLayout( radioButtonPairPanel.get( i ), BoxLayout.Y_AXIS ) );
				radioButtonPairPanel.get( i ).add( Box.createGlue() );
				radioButtonPairPanel.get( i ).add( upRadioButtons.get( i ) );
				radioButtonPairPanel.get( i ).add( downRadioButtons.get( i ) );
				radioButtonPairPanel.get( i ).add( Box.createGlue() );
				
				nestPanels.add( new JPanel() );
				nestPanels.get( i ).add( nestImageLabels.get( i ) );
				
				radioButtonPairAndNestPanels.add( new JPanel() );
				radioButtonPairAndNestPanels.get( i ).setLayout( new BoxLayout( radioButtonPairAndNestPanels.get( i ), BoxLayout.X_AXIS ) );
				radioButtonPairAndNestPanels.get( i ).add( Box.createGlue() );
				radioButtonPairAndNestPanels.get( i ).add( radioButtonPairPanel.get( i ) );
				radioButtonPairAndNestPanels.get( i ).add( nestPanels.get( i ) );
				radioButtonPairAndNestPanels.get( i ).add( Box.createGlue() );
			}
			
			setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setPreferredSize( panelSize );
			setMaximumSize( panelSize );
			setMinimumSize( panelSize );
			add( nestsTitleLabelPanel );
			add( Box.createVerticalStrut( 20 ) );
			int i = 0;
			for( JPanel panel : radioButtonPairAndNestPanels ) {
				add( panel );
				i++;
				if ( i % 2 == 0 ) {
					add( Box.createGlue() );
				}
			}
		}
		
		public void actionPerformed( ActionEvent ae ) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//*************************************************************LANE PANEL************************************************************************************//
	
	private class LanePanel extends JPanel implements ActionListener {
		
		FactoryControlClient fcc;
		ImageIcon laneImage;
		JPanel laneTitleLabelPanel;
		JLabel laneTitleLabel;
		ArrayList<JPanel> checkBoxPanel, lanePanels, checkBoxAndLanePanels;
		ArrayList<JCheckBox> increaseAmplitudeCheckBoxes;
		ArrayList<JLabel> laneImageLabels, increaseAmplitudeLabels;
		
		public LanePanel( FactoryControlClient fcc ) {
			this.fcc = fcc;
			
			Dimension panelSize = new Dimension ( 150, 522 );
			
			//ImageIcons
			laneImage = new ImageIcon( "images/guiserver_thumbs/lane_thumb.png" );
			
			//JPanels
			laneTitleLabelPanel = new JPanel();
			checkBoxPanel = new ArrayList<JPanel>();
			lanePanels = new ArrayList<JPanel>();
			checkBoxAndLanePanels = new ArrayList<JPanel>();
			
			//JLabels
			laneTitleLabel = new JLabel();
			laneTitleLabel.setText( "Lanes" );
			laneTitleLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
			laneImageLabels = new ArrayList<JLabel>();
			increaseAmplitudeLabels = new ArrayList<JLabel>();
			for( int i = 0; i < 4; i++ ) {
				laneImageLabels.add( new JLabel() );
				laneImageLabels.get( i ).setIcon( laneImage );
				
				increaseAmplitudeLabels.add( new JLabel() );
				increaseAmplitudeLabels.get( i ).setText( "<html><body>Increase<br/>Amplitude</body></html>" );
			}
			
			//JCheckBoxes
			increaseAmplitudeCheckBoxes = new ArrayList<JCheckBox>();
			for( int i = 0; i < 4; i++ ) {
				increaseAmplitudeCheckBoxes.add( new JCheckBox() );
			}
			
			//Layout
			
			laneTitleLabelPanel.setLayout( new BoxLayout( laneTitleLabelPanel, BoxLayout.X_AXIS ) );
			laneTitleLabelPanel.add( Box.createGlue() );
			laneTitleLabelPanel.add( laneTitleLabel );
			laneTitleLabelPanel.add( Box.createGlue() );
			
			for( int i = 0; i < 4; i++ ) {
				checkBoxPanel.add( new JPanel() );
				checkBoxPanel.get( i ).setLayout( new BoxLayout( checkBoxPanel.get( i ), BoxLayout.Y_AXIS ) );
				checkBoxPanel.get( i ).add( Box.createGlue() );
				checkBoxPanel.get( i ).add( increaseAmplitudeLabels.get( i ) );
				checkBoxPanel.get( i ).add( increaseAmplitudeCheckBoxes.get( i ) );
				checkBoxPanel.get( i ).add( Box.createGlue() );
				
				lanePanels.add( new JPanel() );
				lanePanels.get( i ).add( laneImageLabels.get( i ) );
				
				checkBoxAndLanePanels.add( new JPanel() );
				checkBoxAndLanePanels.get( i ).setLayout( new BoxLayout( checkBoxAndLanePanels.get( i ), BoxLayout.X_AXIS ) );
				checkBoxAndLanePanels.get( i ).add( Box.createGlue() );
				checkBoxAndLanePanels.get( i ).add( checkBoxPanel.get( i ) );
				checkBoxAndLanePanels.get( i ).add( lanePanels.get( i ) );
				checkBoxAndLanePanels.get( i ).add( Box.createGlue() );
			}
			
			setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setPreferredSize( panelSize );
			setMaximumSize( panelSize );
			setMinimumSize( panelSize );
			add( laneTitleLabelPanel );
			add( Box.createVerticalStrut( 20 ) );
			for( JPanel panel : checkBoxAndLanePanels ) {
				add( panel );
				add( Box.createGlue() );
			}
			
		}

		public void actionPerformed( ActionEvent ae ) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//*************************************************************FEEDER PANEL************************************************************************************//
	
	private class FeederPanel extends JPanel implements ActionListener {
		
		
		public FeederPanel( FactoryControlClient fcc ) {
			
		}

		public void actionPerformed( ActionEvent ae ) {
			// TODO Auto-generated method stub
			
		}
	}
}
