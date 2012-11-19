import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.Timer;

/**
 * This class is the control panel inside FactoryControlManager
 * that controls the Part Robot device
 *
 */
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
		int cameraNumber, gripperNumber, nestNumber, kit1Pos, kit2Pos;
		
		/**
		 * Constructor; sets layout for panel
		 * 
		 * @param fcm pointer to FactoryControlManager object
		 */
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
		
		/**
		 * Returns true if the part robot is on
		 * 
		 * @return boolean variable that is true if the part robot is on
		 */
				
		public boolean getPartRobotOn () { return partRobotOnButton.isSelected(); }
		
		/**
		 * Turns the part robot on and off
		 * 
		 * @param on boolean variable if part robot is on or off
		 */
		public void setPartRobotOn ( boolean on ) {
			partRobotOnButton.setSelected( on );
			partRobotOffButton.setSelected( !on );
			if ( on ) 
				resetMoveButtons();
		}
		
		/**
		 * Sets the text of the pausePlayButton. This is used to switch it between "Pause" or "Play"
		 * 
		 * @param text String variable to set the text of the pausePlayButton
		 */
		public void setPausePlayButtonText( String text ) { pausePlayButton.setText( text ); }
		
		/**
		 * Sets the cancelMoveButton to enabled/disabled
		 * 
		 * @param enabled boolean variable if the cancelMoveButton should be enabled or not
		 */
		public void setCancelMoveButtonEnabled( boolean enabled ) { cancelMoveButton.setEnabled( enabled ); }
		
		/**
		 * Sets the pausePlayButton to enabled/disabled
		 * 
		 * @param enabled boolean variable if the pausePlayButton should be enabled or not
		 */
		public void setPausePlayButtonEnabled( boolean enabled ) { pausePlayButton.setEnabled( enabled ); }
		
		public void setNestButtonsEnabled( boolean enabled ) {
			for ( JButton nest : nestButtons ) {
				nest.setEnabled( enabled );
			}
		}
		
		/**
		 * Sets all the kit position buttons to enabled or disabled
		 * 
		 * @param enabled boolean variable signifying if the buttons should be enabled/disabled
		 */
		public void setKitButtonsEnabled( boolean enabled ) {
			for( JButton kitPos : kit1PositionButtons ) {
				kitPos.setEnabled( enabled );
			}
			for( JButton kitPos : kit2PositionButtons ) {
				kitPos.setEnabled( enabled );
			}
		}
		
		/**
		 * This method resets the enabled/disabled state of all the buttons for the user
		 * to begin inputting a new task for the robot
		 */
		public void resetMoveButtons() {
			if ( getPartRobotOn() ) {
				setKitButtonsEnabled( false );
				setNestButtonsEnabled( true );
			}
		}
		
		/**
		 * Sets all the takePictureButtons to enabled/disabled
		 * This is used to prevent the user from taking more than one picture at a time
		 * 
		 * @param enabled boolean variable signifying if the buttons should be enabled/disabled
		 */
		public void setTakePictureButtonsEnabled( boolean enabled ) {
			for( JButton button : takePictureButtons ) {
				button.setEnabled( enabled );
			}
		}
		
		/**
		 * Sets the text in the JTextField below each nest which states the contents of that nest
		 * 
		 * @param partName the name of the part that is in the nest
		 * @param nestNumber which nest JTextField is to be updated with partName
		 */
		public void setPartContent( String partName, int nestNumber ) {
			nestPartContentsTextFields.get( nestNumber ).setText( partName );
		}
		
		/**
		 * Turns the red "light" on which would signify an improperly assembled or incomplete kit.
		 * This method also starts a timer after turning the red "light" on so that it turns off
		 * after 3 seconds.
		 * 
		 * @param on boolean variable if the red "light" should be turned on/off
		 */
		public void redLightOn( boolean on ) {
			if ( on == true ) {
				colorLabels.get( cameraNumber * 2 ).setIcon( pictureConfirmationColors.get( 0 ) );
				cameraLightTimer.start();
			}
			else {
				colorLabels.get( cameraNumber * 2 ).setIcon( pictureConfirmationColors.get( 2 ) );
			}
		}
		
		/**
		 * Turns the green "light" on which would signify an properly assembled kit.
		 * This method also starts a timer after turning the green "light" on so that it turns off
		 * after 3 seconds.
		 * 
		 * @param on boolean variable if the green "light" should be turned on/off
		 */
		public void greenLightOn( boolean on ) {
			if ( on == true ) {
				colorLabels.get( cameraNumber * 2 + 1 ).setIcon( pictureConfirmationColors.get( 1 ) );
				cameraLightTimer.start();
			}
			else {
				colorLabels.get( cameraNumber * 2 + 1 ).setIcon( pictureConfirmationColors.get( 3 ) );
			}
		}
		
		/**
		 * Gives functionality to all the JButtons, JRadioButtons, and Timers in the
		 * PartRobotControlPanel
		 * 
		 */
		public void actionPerformed( ActionEvent ae ) {
			
			String cmd = "";
			if ( ae.getActionCommand() != null) 
				cmd = ae.getActionCommand();

			// get entry corresponding to part robot
			int prKey = fcm.server.partRobotID;
			Object stateObj = fcm.server.getState().items.get(prKey);
			GUIPartRobot partRobot;
			if (stateObj instanceof GUIPartRobot) {
				partRobot = (GUIPartRobot)stateObj;
			}
			else {
				System.out.println("Error: part robot index variable does not point to a part robot");
				return;
			}
			
			/*
			 *If the actionCommand originates from a nest, the kitButtons are enabled so the
			 *user can select a location to put the part. The for loop finds which nest the
			 *command originated from
			 */
			if ( cmd.equals( "nest" ) ) {
				setNestButtonsEnabled( false );
				setKitButtonsEnabled( true );
				for( int i = 0; i < nestButtons.size(); i++ ) {
					if( ae.getSource() == nestButtons.get( i ) ) {
						nestNumber = i;
						// get entry corresponding to this nest
						int nestKey = fcm.server.nestIDs.get(nestNumber);
						stateObj = fcm.server.getState().items.get(nestKey);
						if (stateObj instanceof GUINest) {
							GUINest nest = (GUINest)stateObj;
							// prepare factory update message
							FactoryUpdateMsg update = new FactoryUpdateMsg();
							update.setTime(fcm.server.getState()); // set time in update message
							Point2D.Double target = new Point2D.Double(nest.movement.getStartPos().x, nest.movement.getStartPos().y + 25);
							double dist = Math.sqrt(Math.pow(target.x - partRobot.getBasePos().x, 2) + Math.pow(target.y - partRobot.getBasePos().y, 2));
							if (dist > GUIPartRobot.ARM_LENGTH) {
								// target is too far away, scale to arm length
								target.x = partRobot.getBasePos().x + (target.x - partRobot.getBasePos().x) * GUIPartRobot.ARM_LENGTH / dist;
								target.y = partRobot.getBasePos().y + (target.y - partRobot.getBasePos().y) * GUIPartRobot.ARM_LENGTH / dist;
							}
							update.itemMoves.put(prKey, partRobot.movement.moveToAtSpeed(update.timeElapsed, target, 0, GUIPartRobot.SPEED));
							fcm.server.applyUpdate(update); // apply and broadcast update message
						}
						else {
							System.out.println("Error: lane index variable does not point to a lane");
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			
			/*
			 * If the actionCommand originates from a kit1 position button then all other movement
			 * buttons are disabled until the robot finishes its task. The for loop finds which
			 * kit1 position the command originated from
			 */
			else if ( cmd.equals( "kit_pos_1" ) ) {
				setKitButtonsEnabled( false );
				setCancelMoveButtonEnabled( false );
				setPausePlayButtonEnabled( true );
				for( int i = 0; i < kit1PositionButtons.size(); i++ ) {
					if( ae.getSource() == kit1PositionButtons.get( i ) ) {
						kit1Pos = i;
						// get entry corresponding to this kit stand
						int kitStandKey = fcm.server.kitStandID;
						stateObj = fcm.server.getState().items.get(kitStandKey);
						if (stateObj instanceof GUIKitStand) {
							GUIKitStand kitStand = (GUIKitStand)stateObj;
							// prepare factory update message
							FactoryUpdateMsg update = new FactoryUpdateMsg();
							update.setTime(fcm.server.getState()); // set time in update message
							Point2D.Double target = new Point2D.Double(kitStand.movement.getStartPos().x, kitStand.movement.getStartPos().y - 90);
							double dist = Math.sqrt(Math.pow(target.x - partRobot.getBasePos().x, 2) + Math.pow(target.y - partRobot.getBasePos().y, 2));
							if (dist > GUIPartRobot.ARM_LENGTH) {
								// target is too far away, scale to arm length
								target.x = partRobot.getBasePos().x + (target.x - partRobot.getBasePos().x) * GUIPartRobot.ARM_LENGTH / dist;
								target.y = partRobot.getBasePos().y + (target.y - partRobot.getBasePos().y) * GUIPartRobot.ARM_LENGTH / dist;
							}
							update.itemMoves.put(prKey, partRobot.movement.moveToAtSpeed(update.timeElapsed, target, 0, GUIPartRobot.SPEED));
							fcm.server.applyUpdate(update); // apply and broadcast update message
						}
						else {
							System.out.println("Error: lane index variable does not point to a lane");
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			
			/*
			 * If the actionCommand originates from a kit2 position button then all other movement
			 * buttons are disabled until the robot finishes its task. The for loop finds which
			 * kit2 position the command originated from
			 */
			else if ( cmd.equals( "kit_pos_2" ) ) {
				setKitButtonsEnabled( false );
				setCancelMoveButtonEnabled( false );
				setPausePlayButtonEnabled( true );
				for( int i = 0; i < kit2PositionButtons.size(); i++ ) {
					if( ae.getSource() == kit2PositionButtons.get( i ) ) {
						kit1Pos = i;
						// get entry corresponding to this kit stand
						int kitStandKey = fcm.server.kitStandID;
						stateObj = fcm.server.getState().items.get(kitStandKey);
						if (stateObj instanceof GUIKitStand) {
							GUIKitStand kitStand = (GUIKitStand)stateObj;
							// prepare factory update message
							FactoryUpdateMsg update = new FactoryUpdateMsg();
							update.setTime(fcm.server.getState()); // set time in update message
							Point2D.Double target = kitStand.movement.getStartPos();
							double dist = Math.sqrt(Math.pow(target.x - partRobot.getBasePos().x, 2) + Math.pow(target.y - partRobot.getBasePos().y, 2));
							if (dist > GUIPartRobot.ARM_LENGTH) {
								// target is too far away, scale to arm length
								target.x = partRobot.getBasePos().x + (target.x - partRobot.getBasePos().x) * GUIPartRobot.ARM_LENGTH / dist;
								target.y = partRobot.getBasePos().y + (target.y - partRobot.getBasePos().y) * GUIPartRobot.ARM_LENGTH / dist;
							}
							update.itemMoves.put(prKey, partRobot.movement.moveToAtSpeed(update.timeElapsed, target, 0, GUIPartRobot.SPEED));
							fcm.server.applyUpdate(update); // apply and broadcast update message
						}
						else {
							System.out.println("Error: lane index variable does not point to a lane");
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			
			//Finds which part robot gripper is selected
			else if ( cmd.equals( "gripper_button" ) ) {
				for ( int i = 0; i < partRobotGripperButtons.size(); i++ ) {
					if ( ae.getSource() == partRobotGripperButtons.get( i ) )
						gripperNumber = i;
				}
			}
			
			/*
			 * Once a take picture button is selected, all the other take picture buttons are disabled
			 * until the result is returned. The for loop finds which take picture button was activated
			 */
			else if ( cmd.equals( "take_picture" ) ) {
				setTakePictureButtonsEnabled( false );
				for ( int i = 0; i < takePictureButtons.size(); i++ ) {
					if ( ae.getSource() == takePictureButtons.get( i ) )
						cameraNumber = i;
				}
			}
			
			//This will turn the camera confirmation lights off when triggered
			else if ( ae.getSource() == cameraLightTimer ) {
				redLightOn( false );
				greenLightOn( false );
				setTakePictureButtonsEnabled( true );
			}
			
			//This button will reset all the buttons to their original enabled/disabled state.
			else if ( ae.getSource() == cancelMoveButton ) {
				setNestButtonsEnabled( true );
				setKitButtonsEnabled( false );
			}
			
			//This button allows the user to pause the robot mid-task
			else if ( ae.getSource() == pausePlayButton ) {
				if ( pausePlayButton.getText().equals( "Pause" ) )
					setPausePlayButtonText( "Play" );
				else
					setPausePlayButtonText( "Pause" );
			}
			
			//This will turn the Part Robot on
			else if ( ae.getSource() == partRobotOnButton ) {
				setNestButtonsEnabled( true );
				setNestButtonsEnabled( true );
				setCancelMoveButtonEnabled( true );
			}
			
			//This will turn the Part Robot on
			else if ( ae.getSource() == partRobotOffButton ) {
				setNestButtonsEnabled( false );
				setKitButtonsEnabled( false );
				setCancelMoveButtonEnabled( false );
				setPausePlayButtonEnabled( false );
			}
		}
	}
