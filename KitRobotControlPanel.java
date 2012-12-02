import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class is the control panel inside FactoryControlManager
 * that controls the Kit Robot device
 *
 */
@SuppressWarnings("serial")
public class KitRobotControlPanel extends JPanel implements ActionListener {
		FactoryControlManager fcm;
		ImageIcon kitRobotImage, kitStandImage, kitDeliveryStationImage;
		JPanel kitRobotLabelPanel, kitRobotImageLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, dropOffPickUpButtonPanel;
		JPanel posButtonPanel, blankPanel1, blankPanel2, pictureConfirmationPanel, cameraPanel, lightKeyPanel, redLightDescPanel, yellowLightDescPanel, greenLightDescPanel, kitPanel;
		JLabel kitRobotLabel, takePictureLabel, kitStatusLabel, kitRobotImageLabel, kitStandImageLabel, kitDeliveryStationImageLabel, redColorLabel, yellowColorLabel, greenColorLabel;
		JLabel redLightDescLabel, yellowLightDescLabel, greenLightDescLabel;
		JButton pausePlayButton, cancelMoveButton, dropOffButton, pickUpButton, takePictureButton;	
		JRadioButton kitRobotOnButton, kitRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension posButtonSize, blankPanel1Size, blankPanel2Size, takePictureButtonSize, pictureConfirmationPanelSize, controlButtonSize;
		ArrayList<JLabel> lightKeyColors;
		ArrayList<JButton> kitStandPositionButtons;
		ArrayList<ImageIcon> pictureConfirmationColors;
		Timer cameraLightTimer;
		boolean firstButtonSelected = false; // tracks if the user has already made a source selection, i.e. the next button selected will be the destination
		
		/**
		 * Constructor; sets layout for panel
		 * 
		 * @param fcm pointer to FactoryControlManager object
		 */
		public KitRobotControlPanel( FactoryControlManager fcm ) {
			this.fcm = fcm;
			
			//ImageIcons
			kitRobotImage = new ImageIcon( "images/guiserver_thumbs/kitRobot_thumb.png" );
			kitStandImage = new ImageIcon( "images/guiserver_thumbs/kit_table_thumb.png" );
			kitDeliveryStationImage = new ImageIcon( "images/guiserver_thumbs/kit_delivery_station_thumb.png" );
			pictureConfirmationColors = new ArrayList<ImageIcon>();
			for( int i = 0; i < 4; i++ ) {
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/red_kit.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/yellow_kit.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/green_kit.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/dark_red_kit.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/dark_yellow_kit.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/dark_green_kit.png" ) );
			}
			
			//Dimensions
			posButtonSize = new Dimension( 40, 40 );
			blankPanel1Size = new Dimension( 80, 300 );
			blankPanel2Size = new Dimension( 100, 100 );
			takePictureButtonSize = new Dimension( 40, 40 );
			pictureConfirmationPanelSize = new Dimension( 20, 40 );
			controlButtonSize = new Dimension( 60, 40 );
			
			//JPanels
			kitRobotLabelPanel = new JPanel();
			kitRobotImageLabelPanel = new JPanel();
			robotOnOffButtonPanel = new JPanel();
			robotPauseCancelButtonPanel = new JPanel();
			dropOffPickUpButtonPanel = new JPanel();
			posButtonPanel = new JPanel();
			blankPanel1 = new JPanel();
			blankPanel2 = new JPanel();
			pictureConfirmationPanel = new JPanel();
			cameraPanel = new JPanel();
			lightKeyPanel = new JPanel();
			redLightDescPanel = new JPanel();
			yellowLightDescPanel = new JPanel();
			greenLightDescPanel = new JPanel();
			
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
			redColorLabel = new JLabel();
			redColorLabel.setIcon( pictureConfirmationColors.get( 3 ) );
			yellowColorLabel = new JLabel();
			yellowColorLabel.setIcon( pictureConfirmationColors.get( 4 ) );
			greenColorLabel = new JLabel();
			greenColorLabel.setIcon( pictureConfirmationColors.get( 5 ) );
			redLightDescLabel = new JLabel();
			redLightDescLabel.setText( "Kit is incorrectly assembled" );
			yellowLightDescLabel = new JLabel();
			yellowLightDescLabel.setText( "Kit is incomplete" );
			greenLightDescLabel = new JLabel();
			greenLightDescLabel.setText( "Kit is correctly assembled" );
			lightKeyColors = new ArrayList<JLabel>();
			for( int i = 0; i < 3; i++ ) {
				lightKeyColors.add( new JLabel() );
				lightKeyColors.get( i ).setIcon( pictureConfirmationColors.get( i ) );
			}
			
			//JButtons
			pausePlayButton = new JButton();
			pausePlayButton.setText( "Pause" );
			pausePlayButton.setEnabled( false );
			pausePlayButton.setPreferredSize( controlButtonSize );
			pausePlayButton.setMaximumSize( controlButtonSize );
			pausePlayButton.setMinimumSize( controlButtonSize );
			pausePlayButton.setMargin( new Insets( 0, 0, 0, 0 ) );
			pausePlayButton.addActionListener( this );
			cancelMoveButton = new JButton();
			cancelMoveButton.setText( "<html><body style=\"text-align:center;\">Cancel<br/>Move</body></html>" );
			cancelMoveButton.setPreferredSize( controlButtonSize );
			cancelMoveButton.setMaximumSize( controlButtonSize );
			cancelMoveButton.setMinimumSize( controlButtonSize );
			cancelMoveButton.setMargin( new Insets( 0, 0, 0, 0 ) );
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
			takePictureButton.setText( "<html><body style=\"text-align:center;\">Take<br/>Pic</body></html>" );
			takePictureButton.setMargin( new Insets( 1, 1, 1, 1 ) );
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
			
			//Timer
			cameraLightTimer = new Timer( 3000, this );
			cameraLightTimer.setRepeats( false );
			
			
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
			
			redLightDescPanel.setLayout( new BoxLayout( redLightDescPanel, BoxLayout.X_AXIS ) );
			//redLightDescPanel.add( Box.createHorizontalStrut( 10 ) );
			redLightDescPanel.add( lightKeyColors.get( 0 ) );
			redLightDescPanel.add( Box.createHorizontalStrut( 10 ) );
			redLightDescPanel.add( redLightDescLabel );
			redLightDescPanel.add( Box.createGlue() );
			
			yellowLightDescPanel.setLayout( new BoxLayout( yellowLightDescPanel, BoxLayout.X_AXIS ) );
			//yellowLightDescPanel.add( Box.createHorizontalStrut( 10 ) );
			yellowLightDescPanel.add( lightKeyColors.get( 1 ) );
			yellowLightDescPanel.add( Box.createHorizontalStrut( 10 ) );
			yellowLightDescPanel.add( yellowLightDescLabel );
			yellowLightDescPanel.add( Box.createGlue() );
			
			greenLightDescPanel.setLayout( new BoxLayout( greenLightDescPanel, BoxLayout.X_AXIS ) );
			//greenLightDescPanel.add( Box.createHorizontalStrut( 10 ) );
			greenLightDescPanel.add( lightKeyColors.get( 2 ) );
			greenLightDescPanel.add( Box.createHorizontalStrut( 10 ) );
			greenLightDescPanel.add( greenLightDescLabel );
			greenLightDescPanel.add( Box.createGlue() );
			
			lightKeyPanel.setLayout( new BoxLayout( lightKeyPanel, BoxLayout.Y_AXIS ) );
			lightKeyPanel.add( Box.createVerticalStrut( 15 ) );
			lightKeyPanel.add( redLightDescPanel );
			lightKeyPanel.add( yellowLightDescPanel );
			lightKeyPanel.add( greenLightDescPanel );
			lightKeyPanel.add( Box.createVerticalStrut( 15 ) );
			
			pictureConfirmationPanel.setLayout( new BoxLayout( pictureConfirmationPanel, BoxLayout.Y_AXIS ) );
			pictureConfirmationPanel.add( redColorLabel );
			pictureConfirmationPanel.add( yellowColorLabel );
			pictureConfirmationPanel.add( greenColorLabel );
			
			cameraPanel.setLayout( new BoxLayout( cameraPanel, BoxLayout.X_AXIS ) );
			cameraPanel.add( takePictureButton );
			cameraPanel.add( pictureConfirmationPanel );
			
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
			dropOffPickUpButtonPanel.add(Box.createHorizontalStrut( 55 ) );
			dropOffPickUpButtonPanel.add( pickUpButton );
			dropOffPickUpButtonPanel.add( Box.createGlue() );
				
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setLayout( new GridBagLayout() );
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = c.gridy = 0;
			c.gridwidth = 12;
			c.gridheight = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( kitRobotLabelPanel, c );
			c.gridy = 1;
			c.gridheight = 3;
			add( lightKeyPanel, c );
			c.gridy = 4;
			c.gridwidth = 4;
			c.gridheight = 1;
			c.fill = GridBagConstraints.NONE;
			add( robotOnOffButtonPanel, c );
			c.gridy = 5;
			c.gridheight = 2;
			add( robotPauseCancelButtonPanel, c );
			c.gridy = 7;
			c.gridwidth = 3;
			c.gridheight = 6;
			add( kitStandImageLabel, c );
			c.gridx = 2;
			c.gridy = 13;
			c.gridwidth = 1;
			c.gridheight = 2;
			add( cameraPanel, c );
			c.gridx = 3;
			c.gridy = 7;
			c.gridwidth = 1;
			c.gridheight = 6;
			c.fill = GridBagConstraints.VERTICAL;
			add( posButtonPanel, c );
			c.gridx = 4;
			c.gridy = 4;
			c.gridheight = 13;
			add( blankPanel1, c );
			c.gridx = 5;
			c.gridwidth = 7;
			c.gridheight = 3;
			c.fill = GridBagConstraints.NONE;
			add( kitDeliveryStationImageLabel, c );
			c.gridy = 7;
			c.gridheight = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			add( dropOffPickUpButtonPanel, c );
			c.gridy = 8;
			c.gridwidth = 2;
			c.gridheight = 9;
			add( blankPanel2, c );
			c.gridx = 7;
			c.gridy = 9;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.NONE;
			add( kitRobotImageLabelPanel, c );
			
			//Initialize Kit Robot on/off
			GUIKitRobot kitRobot = fcm.server.getKitRobot();
			if( kitRobot.kitRobot.state == KitRobot.KRState.OFF )
				setKitRobotOn( false );
			else
				setKitRobotOn( true );
		}
		
		/**
		 * Returns true if the kit robot is on
		 * 
		 * @return boolean variable if the kit robot is on
		 */
		public boolean getKitRobotOn() { return kitRobotOnButton.isSelected(); }
		
		/**
		 * Turns the kit robot on and off
		 * 
		 * @param on boolean variable if kit robot is on or off
		 */
		public void setKitRobotOn ( boolean on ) {
			kitRobotOnButton.setSelected( on );
			kitRobotOffButton.setSelected( !on );
			if ( on )
				resetMoveButtons();
			else
				disableMoveButtons();
		}
		
		/**
		 * Return if a button has already been selected in order to tell if
		 * the button pressed is the source or the destination of the kit robot
		 * 
		 * @return boolean variable if the first button has already been selected or not
		 */
		public boolean getFirstButtonSelected() { return firstButtonSelected; }
		
		/**
		 * Sets the firstButtonSelected variable. Used after source of movement is selected
		 * 
		 * @param selected boolean variable storing if one button has already been selected
		 */
		public void setFirstButtonSelected( boolean selected ) { firstButtonSelected = selected; }
		
		/**
		 * Sets the pickUpButton to enabled/disabled
		 * 
		 * @param enabled boolean variable if the pickUpButton should be enabled or not
		 */
		public void setPickUpButtonEnabled( boolean enabled ) { pickUpButton.setEnabled( enabled ); }
		
		/**
		 * Sets the dropOffButton to enabled/disabled
		 * 
		 * @param enabled boolean variable if the dropOffButton should be enabled or not
		 */
		public void setDropOffButtonEnabled( boolean enabled ) { dropOffButton.setEnabled( enabled ); }
		
		/**
		 * Sets the pausePlayButton to enabled/disabled
		 * 
		 * @param enabled boolean variable if the pausePlayButton should be enabled or not
		 */
		public void setPausePlayButtonEnabled( boolean enabled ) { pausePlayButton.setEnabled( enabled ); }
		
		/**
		 * Sets the cancelMoveButton to enabled/disabled
		 * 
		 * @param enabled boolean variable if the cancelMoveButton should be enabled or not
		 */
		public void setCancelMoveButtonEnabled( boolean enabled ) { cancelMoveButton.setEnabled( enabled ); }
		
		/**
		 * Sets the text of the pausePlayButton. This is used to switch it between "Pause" or "Play"
		 * 
		 * @param text String variable to set the text of the pausePlayButton
		 */
		public void setPausePlayButtonText( String text ) { pausePlayButton.setText( text ); }
		
		/**
		 * Enables or disables the first two kit stand position buttons. These are the
		 * kit positions where the kits are assembled, not inspected.
		 * 
		 * @param enabled boolean variable if the buttons are to be enabled/disabled
		 */
		public void setKitStandAssemblyPositionButtonsEnabled( boolean enabled ) {
			kitStandPositionButtons.get( 0 ).setEnabled( enabled );
			kitStandPositionButtons.get( 1 ).setEnabled( enabled );
		}
		
		/**
		 * Enables or disables the last kit stand position button. This is the location
		 * where the kit is inspected, not assembled.
		 * 
		 * @param enabled boolean variable if the button is enabled/disabled
		 */
		public void setInspectionPositionEnabled( boolean enabled ) { kitStandPositionButtons.get( 2 ).setEnabled( enabled ); }
		
		/**
		 * This method calls other button enabling methods to disable all movement buttons
		 * while the robot is off or still completing a task
		 * 
		 */
		public void disableMoveButtons() {
			setPickUpButtonEnabled( false );
			setDropOffButtonEnabled( false );
			setKitStandAssemblyPositionButtonsEnabled( false );
			setInspectionPositionEnabled( false );
			setCancelMoveButtonEnabled( false );
		}
		
		/**
		 * This method resets the enabled/disabled state of all the buttons for the user
		 * to begin inputting a new task for the robot
		 */
		public void resetMoveButtons() {
			if ( getKitRobotOn() ) {
				setPickUpButtonEnabled( true );
				setDropOffButtonEnabled( false );
				setKitStandAssemblyPositionButtonsEnabled( true );
				setInspectionPositionEnabled( true );
				setCancelMoveButtonEnabled( true );
				firstButtonSelected = false;
			}
		}
		
		/**
		 * Turns the red "light" on which would signify an improperly assembled kit.
		 * This method also starts a timer after turning the red "light" on so that it turns off
		 * after 3 seconds.
		 * 
		 * @param on boolean variable if the red "light" should be turned on/off
		 */
		public void redLightOn( boolean on ) {
			if ( on == true ) {
				redColorLabel.setIcon( pictureConfirmationColors.get( 0 ) );
				cameraLightTimer.start();
			}
			else {
				redColorLabel.setIcon( pictureConfirmationColors.get( 3 ) );
			}
		}
		
		/**
		 * Turns the yellow "light" on which would signify an incomplete kit.
		 * This method also starts a timer after turning the yellow "light" on so that it turns off
		 * after 3 seconds.
		 * 
		 * @param on boolean variable if the yellow "light" should be turned on/off
		 */
		public void yellowLightOn( boolean on ) {
			if ( on == true ) {
				yellowColorLabel.setIcon( pictureConfirmationColors.get( 1 ) );
				cameraLightTimer.start();
			}
			else {
				yellowColorLabel.setIcon( pictureConfirmationColors.get( 4 ) );
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
				greenColorLabel.setIcon( pictureConfirmationColors.get( 2 ) );
				cameraLightTimer.start();
			}
			else {
				greenColorLabel.setIcon( pictureConfirmationColors.get( 5 ) );
			}
		}
		
		/**
		 * Gives functionality to all the JButtons, JRadioButtons, and Timers in the
		 * KitRobotControlPanel
		 * 
		 */
		public void actionPerformed( ActionEvent ae ) {
			// get entry corresponding to kit robot
			int krKey = fcm.server.kitRobotID;
			GUIKitRobot kitRobot = fcm.server.getKitRobot();

			//Once the pickup button is pressed, user can only select one of the first two
			//kit stand positions
			if ( ae.getSource() == pickUpButton ) {
				setInspectionPositionEnabled( false );
				setFirstButtonSelected( true );
				setPickUpButtonEnabled( false );
				// prepare factory update message
				FactoryUpdateMsg update = new FactoryUpdateMsg();
				update.setTime(fcm.server.getState()); // set time in update message
				kitRobot.park(update.timeElapsed); // park kit robot
				update.itemMoves.put(krKey, kitRobot.movement);
				fcm.server.applyUpdate(update); // apply and broadcast update message
			}
			
			// TODO: the code to move to the different kit stands is identical except for the y offset, so the code below can be shortened considerably
			//If this is the first button selected, the user can only select the inspection position as destination
			//If this is the second button selected, all buttons are disabled until the robot completes the task
			else if ( ae.getSource() == kitStandPositionButtons.get( 0 ) ) {
				setPickUpButtonEnabled( false );
				setKitStandAssemblyPositionButtonsEnabled( false );
				if ( getFirstButtonSelected() ) {
					disableMoveButtons();
					setPausePlayButtonEnabled( true );
					setCancelMoveButtonEnabled( false );
				}
				setFirstButtonSelected( true );
				// get entry corresponding to this kit stand
				int kitStandKey = fcm.server.kitStandID;
				GUIKitStand kitStand = fcm.server.getKitStand();
				// prepare factory update message
				FactoryUpdateMsg update = new FactoryUpdateMsg(fcm.server.getState());
				Point2D.Double target = new Point2D.Double(kitStand.movement.getStartPos().x, kitStand.movement.getStartPos().y - 90);
				double dist = Math.sqrt(Math.pow(target.x - kitRobot.getBasePos().x, 2) + Math.pow(target.y - kitRobot.getBasePos().y, 2));
				if (dist > GUIKitRobot.ARM_LENGTH) {
					// target is too far away, scale to arm length
					target.x = kitRobot.getBasePos().x + (target.x - kitRobot.getBasePos().x) * GUIKitRobot.ARM_LENGTH / dist;
					target.y = kitRobot.getBasePos().y + (target.y - kitRobot.getBasePos().y) * GUIKitRobot.ARM_LENGTH / dist;
				}
				update.itemMoves.put(krKey, kitRobot.movement.moveToAtSpeed(update.timeElapsed, target, 0, GUIKitRobot.SPEED));
				fcm.server.applyUpdate(update); // apply and broadcast update message
				return; // no need to check if other buttons selected
			}
			
			//If this is the first button selected, the user can only select the inspection position as destination
			//If this is the second button selected, all buttons are disabled until the robot completes the task
			else if ( ae.getSource() == kitStandPositionButtons.get( 1 ) ) {
				setPickUpButtonEnabled( false );
				setKitStandAssemblyPositionButtonsEnabled( false );
				if ( getFirstButtonSelected() ) {
					disableMoveButtons();
					setPausePlayButtonEnabled( true );
					setCancelMoveButtonEnabled( false );
				}
				setFirstButtonSelected( true );
				// get entry corresponding to this kit stand
				int kitStandKey = fcm.server.kitStandID;
				GUIKitStand kitStand = fcm.server.getKitStand();
				// prepare factory update message
				FactoryUpdateMsg update = new FactoryUpdateMsg(fcm.server.getState());
				Point2D.Double target = kitStand.movement.getStartPos();
				double dist = Math.sqrt(Math.pow(target.x - kitRobot.getBasePos().x, 2) + Math.pow(target.y - kitRobot.getBasePos().y, 2));
				if (dist > GUIKitRobot.ARM_LENGTH) {
					// target is too far away, scale to arm length
					target.x = kitRobot.getBasePos().x + (target.x - kitRobot.getBasePos().x) * GUIKitRobot.ARM_LENGTH / dist;
					target.y = kitRobot.getBasePos().y + (target.y - kitRobot.getBasePos().y) * GUIKitRobot.ARM_LENGTH / dist;
				}
				update.itemMoves.put(krKey, kitRobot.movement.moveToAtSpeed(update.timeElapsed, target, 0, GUIKitRobot.SPEED));
				fcm.server.applyUpdate(update); // apply and broadcast update message
				return; // no need to check if other buttons selected
			}
			
			//If this is the first button selected, the user can only drop the kit off at the kit delivery station
			//If this is the second button selected, all buttons are disabled until the robot complete the task
			else if ( ae.getSource() == kitStandPositionButtons.get( 2 ) ) {
				setPickUpButtonEnabled( false );
				setKitStandAssemblyPositionButtonsEnabled( false );
				setInspectionPositionEnabled( false );
				if ( !getFirstButtonSelected() ) {
					setDropOffButtonEnabled( true );
					setFirstButtonSelected( true );
				}
				else {
					setPausePlayButtonEnabled( true );
					setCancelMoveButtonEnabled( false );
				}
				// get entry corresponding to this kit stand
				int kitStandKey = fcm.server.kitStandID;
				GUIKitStand kitStand = fcm.server.getKitStand();
				// prepare factory update message
				FactoryUpdateMsg update = new FactoryUpdateMsg(fcm.server.getState());
				Point2D.Double target = new Point2D.Double(kitStand.movement.getStartPos().x, kitStand.movement.getStartPos().y + 90);
				double dist = Math.sqrt(Math.pow(target.x - kitRobot.getBasePos().x, 2) + Math.pow(target.y - kitRobot.getBasePos().y, 2));
				if (dist > GUIKitRobot.ARM_LENGTH) {
					// target is too far away, scale to arm length
					target.x = kitRobot.getBasePos().x + (target.x - kitRobot.getBasePos().x) * GUIKitRobot.ARM_LENGTH / dist;
					target.y = kitRobot.getBasePos().y + (target.y - kitRobot.getBasePos().y) * GUIKitRobot.ARM_LENGTH / dist;
				}
				update.itemMoves.put(krKey, kitRobot.movement.moveToAtSpeed(update.timeElapsed, target, 0, GUIKitRobot.SPEED));
				fcm.server.applyUpdate(update); // apply and broadcast update message
				return; // no need to check if other buttons selected
			}
			
			//This will always be the second button selected so all buttons will be disabled until the robot finished its task
			else if ( ae.getSource() == dropOffButton ) {
				disableMoveButtons();
				setPausePlayButtonEnabled( true );
				setCancelMoveButtonEnabled( false );
			}
			
			//This button will reset all the buttons to their original enabled/disabled state and set firstButtonSelected to zero.
			else if ( ae.getSource() == cancelMoveButton ) {
				setFirstButtonSelected( false );
				resetMoveButtons();
			}
			
			//This button allows the user to pause the robot mid-task
			else if ( ae.getSource() == pausePlayButton ) {
				if ( pausePlayButton.getText().equals( "Pause" ) )
					setPausePlayButtonText( "Play" );
				else
					setPausePlayButtonText( "Pause" );
			}
			
			//This will send a request to the server to check if the kit is properly assembled
			else if ( ae.getSource() == takePictureButton ) {
				//request from server
			}
			
			//This will turn the camera confirmation lights off when triggered
			else if ( ae.getSource() == cameraLightTimer ) {
				redLightOn( false );
				yellowLightOn( false );
				greenLightOn( false );
			}
			
			//This will turn the Kit Robot on
			else if ( ae.getSource() == kitRobotOnButton ) {
				resetMoveButtons();
				if ( kitRobot.kitRobot.state == KitRobot.KRState.OFF ) {
					kitRobot.kitRobot.state = KitRobot.KRState.IDLE;
					// prepare factory update message
					FactoryUpdateMsg update = new FactoryUpdateMsg( fcm.server.getState() );
					update.putItems.put( krKey, kitRobot ); // put updated kit robot in update message
					fcm.server.applyUpdate( update ); // apply and broadcast update message
				}
			}
			
			//This will turn the Kit Robot off
			else if ( ae.getSource() == kitRobotOffButton ) {
				disableMoveButtons();
				setPausePlayButtonEnabled( false );
				if ( kitRobot.kitRobot.state != KitRobot.KRState.OFF ) {
					kitRobot.kitRobot.state = KitRobot.KRState.OFF;
					// prepare factory update message
					FactoryUpdateMsg update = new FactoryUpdateMsg( fcm.server.getState() );
					update.putItems.put( krKey, kitRobot ); // put updated kit robot in update message
					fcm.server.applyUpdate( update ); // apply and broadcast update message
				}
			}
		}
	}
