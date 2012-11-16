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
		nestPanel = new NestPanel();
		lanePanel = new LanePanel();
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
	
	private class KitRobotPanel extends JPanel implements ActionListener {
		FactoryControlClient guiServer;
		ImageIcon kitRobotImage, kitStandImage, kitDeliveryStationImage;
		JPanel kitRobotLabelPanel, kitRobotImageLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, dropOffPickUpButtonPanel, posButtonPanel, blankPanel1, blankPanel2;
		JLabel kitRobotLabel, takePictureLabel, kitStatusLabel, kitRobotImageLabel, kitStandImageLabel, kitDeliveryStationImageLabel;
		JButton pausePlayButton, cancelMoveButton, dropOffButton, pickUpButton, takePictureButton;	
		JRadioButton kitRobotOnButton, kitRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension posButtonSize, blankPanel1Size, blankPanel2Size, takePictureButtonSize;
		ArrayList<JButton> kitStandPositionButtons;
		
		public KitRobotPanel( FactoryControlClient guiServer ) {
			this.guiServer = guiServer;
			
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
			kitRobotOnButton.setSelected( true );
			kitRobotOnButton.addActionListener( this );
			kitRobotOffButton = new JRadioButton();
			kitRobotOffButton.setText( "OFF" );
			kitRobotOffButton.addActionListener( this );
			onOffButtonGroup = new ButtonGroup();
			onOffButtonGroup.add( kitRobotOnButton );
			onOffButtonGroup.add( kitRobotOffButton );
			
			posButtonPanel.setLayout( new BoxLayout( posButtonPanel, BoxLayout.Y_AXIS ) );
			posButtonPanel.add( Box.createGlue() );
			for( JButton button : kitStandPositionButtons ) {
				posButtonPanel.add( button );
				posButtonPanel.add( Box.createGlue() );
			}
			
			//Layout
			
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

	
	private class PartRobotPanel extends JPanel implements ActionListener {
		FactoryControlClient guiServer;
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
		
		public PartRobotPanel( FactoryControlClient guiServer ) {
			this.guiServer = guiServer;
			
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
			partRobotOnButton.setSelected( true );
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

	private class GantryRobotPanel extends JPanel {
		
		public GantryRobotPanel( FactoryControlClient guiServer ) {
			
		}
	}
	
	private class NestPanel extends JPanel {
		
	}
	
	private class LanePanel extends JPanel {
		
	}
	
	private class FeederPanel extends JPanel {
		
		
		public FeederPanel( FactoryControlClient guiServer ) {
			
		}
	}
}
