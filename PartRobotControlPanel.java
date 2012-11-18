import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class PartRobotControlPanel extends JPanel implements ActionListener {
		FactoryControlManager fcm;
		ImageIcon partRobotImage, nestImage;
		JPanel robotOnOffButtonPanel, robotPauseCancelButtonPanel, partRobotGripperButtonPanel, partRobotTitleLabelPanel, kit1Panel, kit2Panel, nestPanel, takePicPanel, blankPanel1, blankPanel2;
		JLabel partRobotTitleLabel, partRobotImageLabel;
		JButton pausePlayButton, cancelMoveButton;	
		JRadioButton partRobotOnButton, partRobotOffButton;
		ButtonGroup onOffButtonGroup, partRobotGripperButtonGroup;
		Dimension textFieldSize, kitButtonSize, nestButtonSize, nestPanelSize, takePicPanelSize, blankPanelSize, pictureConfirmationPanelSize, takePicButtonSize, controlButtonSize;
		ArrayList<JButton> kit1PositionButtons, kit2PositionButtons, nestButtons, takePictureButtons;
		ArrayList<JRadioButton> partRobotGripperButtons;
		ArrayList<ImageIcon> kitPosImages;
		ArrayList<JTextField> nestPartContentsTextFields;
		ArrayList<JLabel> colorLabels;
		ArrayList<ImageIcon> pictureConfirmationColors;
		ArrayList<JPanel> pictureConfirmationPanels, nests, cameras;
		Timer cameraLightTimer;
		int cameraNumber, gripperNumber;
		
		public PartRobotControlPanel( FactoryControlManager fcm ) {
			this.fcm = fcm;
			
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
			controlButtonSize = new Dimension( 60, 40 );
			
			//Timers
			cameraLightTimer = new Timer( 3000, this );
			cameraLightTimer.setRepeats( false );
			
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
			pausePlayButton.setPreferredSize( controlButtonSize );
			pausePlayButton.setMaximumSize( controlButtonSize );
			pausePlayButton.setMinimumSize( controlButtonSize );
			pausePlayButton.setMargin( new Insets( 0, 0, 0, 0 ) );
			pausePlayButton.setEnabled( false );
			pausePlayButton.addActionListener( this );
			cancelMoveButton = new JButton();
			cancelMoveButton.setText( "<html><body style=\"text-align:center;\">Cancel<br/>Move</body></html>" );
			cancelMoveButton.setPreferredSize( controlButtonSize );
			cancelMoveButton.setMaximumSize( controlButtonSize );
			cancelMoveButton.setMinimumSize( controlButtonSize );
			cancelMoveButton.setMargin( new Insets( 0, 0, 0, 0 ) );
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
				kit1PositionButtons.get( i ).setActionCommand( "kit_pos_1" );
				kit1PositionButtons.get( i ).addActionListener( this );
				kit1PositionButtons.get( i ).setPreferredSize( kitButtonSize );
				kit1PositionButtons.get( i ).setMaximumSize( kitButtonSize );
				kit1PositionButtons.get( i ).setMinimumSize( kitButtonSize );
				
				//Buttons for second kit on kit stand
				kit2PositionButtons.add( new JButton() );
				kit2PositionButtons.get( i ).setIcon( kitPosImages.get( i ) );
				kit2PositionButtons.get( i ).setMargin( new Insets( 0, 0, 0, 0 ) );
				kit2PositionButtons.get( i ).setActionCommand( "kit_pos_2" );
				kit2PositionButtons.get( i ).addActionListener( this );
				kit2PositionButtons.get( i ).setPreferredSize( kitButtonSize );
				kit2PositionButtons.get( i ).setMaximumSize( kitButtonSize );
				kit2PositionButtons.get( i ).setMinimumSize( kitButtonSize );
				
				//Buttons for each of the 8 nests
				nestButtons.add( new JButton() );
				nestButtons.get( i ).setIcon( nestImage );
				nestButtons.get( i ).setMargin( new Insets( 0, 0, 0, 0 ) );
				nestButtons.get( i ).setActionCommand( "nest" );
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
				takePictureButtons.get( i ).setText( "<html><body style=\"text-align:center;\">Take<br/>Pic</body></html>" );
				takePictureButtons.get( i ).setMargin( new Insets( 1, 1, 1, 1 ) );
				takePictureButtons.get( i ).setActionCommand( "take_picture" );
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
				partRobotGripperButtons.get( i ).setActionCommand( "gripper_button" );
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
			
			setKitButtonsEnabled( false );
			
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
		
		public void setPartRobotOn ( boolean on ) {
			partRobotOnButton.setSelected( on );
			partRobotOffButton.setSelected( !on );
		}
		
		public void setPausePlayButtonText( String text ) { pausePlayButton.setText( text ); }
		
		public void setCancelMoveButtonEnabled( boolean enabled ) { cancelMoveButton.setEnabled( enabled ); }
		
		public void setPausePlayButtonEnabled( boolean enabled ) { pausePlayButton.setEnabled( enabled ); }
		
		public void setNestButtonsEnabled( boolean enabled ) {
			for ( JButton nest : nestButtons ) {
				nest.setEnabled( enabled );
			}
		}
		
		public void setKitButtonsEnabled( boolean enabled ) {
			for( JButton kitPos : kit1PositionButtons ) {
				kitPos.setEnabled( enabled );
			}
			for( JButton kitPos : kit2PositionButtons ) {
				kitPos.setEnabled( enabled );
			}
		}
		
		public void setTakePictureButtonsEnabled( boolean enabled ) {
			for( JButton button : takePictureButtons ) {
				button.setEnabled( enabled );
			}
		}
		
		public void setPartContent( String partName, int nestNumber ) {
			nestPartContentsTextFields.get( nestNumber ).setText( partName );
		}
		
		public void redLightOn( boolean on ) {
			if ( on == true ) {
				colorLabels.get( cameraNumber * 2 ).setIcon( pictureConfirmationColors.get( 0 ) );
				cameraLightTimer.start();
			}
			else {
				colorLabels.get( cameraNumber * 2 ).setIcon( pictureConfirmationColors.get( 2 ) );
			}
		}
		
		public void greenLightOn( boolean on ) {
			if ( on == true ) {
				colorLabels.get( cameraNumber * 2 + 1 ).setIcon( pictureConfirmationColors.get( 1 ) );
				cameraLightTimer.start();
			}
			else {
				colorLabels.get( cameraNumber * 2 + 1 ).setIcon( pictureConfirmationColors.get( 3 ) );
			}
		}
		
		public void actionPerformed( ActionEvent ae ) {
			if ( ae.getActionCommand().equals( "nest" ) ) {
				setNestButtonsEnabled( false );
				setKitButtonsEnabled( true );
			}
			else if ( ae.getActionCommand().equals( "kit_pos_1" ) ) {
				setKitButtonsEnabled( false );
				setCancelMoveButtonEnabled( false );
				setPausePlayButtonEnabled( true );
			}
			else if ( ae.getActionCommand().equals( "kit_pos_2" ) ) {
				setKitButtonsEnabled( false );
				setCancelMoveButtonEnabled( false );
				setPausePlayButtonEnabled( true );
			}
			else if ( ae.getActionCommand().equals( "gripper_button" ) ) {
				for ( int i = 0; i < partRobotGripperButtons.size(); i++ ) {
					if ( ae.getSource() == partRobotGripperButtons.get( i ) )
						gripperNumber = i;
				}
			}
			else if ( ae.getActionCommand().equals( "take_picture" ) ) {
				setTakePictureButtonsEnabled( false );
				for ( int i = 0; i < takePictureButtons.size(); i++ ) {
					if ( ae.getSource() == takePictureButtons.get( i ) )
						cameraNumber = i;
				}
				redLightOn( true );
			}
			else if ( ae.getSource() == cameraLightTimer ) {
				redLightOn( false );
				greenLightOn( false );
				setTakePictureButtonsEnabled( true );
			}
			else if ( ae.getSource() == cancelMoveButton ) {
				setNestButtonsEnabled( true );
				setKitButtonsEnabled( false );
			}
			else if ( ae.getSource() == pausePlayButton ) {
				if ( pausePlayButton.getText().equals( "Pause" ) )
					setPausePlayButtonText( "Play" );
				else
					setPausePlayButtonText( "Pause" );
			}
			else if ( ae.getSource() == partRobotOnButton ) {
				setNestButtonsEnabled( true );
				setNestButtonsEnabled( true );
				setCancelMoveButtonEnabled( true );
			}
			else if ( ae.getSource() == partRobotOffButton ) {
				setNestButtonsEnabled( false );
				setKitButtonsEnabled( false );
				setCancelMoveButtonEnabled( false );
				setPausePlayButtonEnabled( false );
			}
		}
	}