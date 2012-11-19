import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class is the control panel inside FactoryControlManager
 * that controls all the Feeder devices
 *
 */
@SuppressWarnings("serial")
public class FeederControlPanel extends JPanel implements ActionListener {
		FactoryControlManager fcm;
		Dimension feederNumberLabelSize;
		JPanel feederTitleLabelPanel;
		JLabel feederTitleLabel;
		ArrayList<JPanel> feederPanels, feederHeaderPanels,feederNumberLabelPanels, partsLowLabelPanels, controlPanels, diverterPanels, feedPartsPanels, rearGatePanels, partsFedPanels;
		ArrayList<JLabel> feederNumberLabels, partsLowLabels, diverterLabels, feedPartsLabels, rearGateLabels, partsFedLabels, partsFedNumberLabels;
		ArrayList<JRadioButton> diverterRightRadioButtons, diverterLeftRadioButtons, feedPartsOnRadioButtons, feedPartsOffRadioButtons, rearGateRaisedRadioButtons, rearGateLoweredRadioButtons;
		ArrayList<ButtonGroup> diverterRadioButtonGroups, feedPartsRadioButtonGroups, rearGateRadioButtonGroups;
		int feederNumber;
		Timer updatePartLowAndCount;
		
		/**
		 * Constructor; sets layout for panel
		 * 
		 * @param fcm pointer to FactoryControlManager object
		 */
		public FeederControlPanel( FactoryControlManager fcm ) {
			this.fcm = fcm;
			
			//Dimensions
			feederNumberLabelSize = new Dimension( 20, 20 );
			
			//Timers
			updatePartLowAndCount = new Timer( 1000, this );
			updatePartLowAndCount.start();
			
			//JPanels
			feederTitleLabelPanel = new JPanel();
			feederPanels = new ArrayList<JPanel>();
			feederHeaderPanels = new ArrayList<JPanel>();
			feederNumberLabelPanels = new ArrayList<JPanel>();
			partsLowLabelPanels = new ArrayList<JPanel>();
			controlPanels = new ArrayList<JPanel>();
			diverterPanels = new ArrayList<JPanel>();
			feedPartsPanels = new ArrayList<JPanel>();
			rearGatePanels = new ArrayList<JPanel>();
			partsFedPanels = new ArrayList<JPanel>();
			
			//JLabels
			feederTitleLabel = new JLabel();
			feederTitleLabel.setText( "Feeders" );
			feederTitleLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
			feederNumberLabels = new ArrayList<JLabel>();
			partsLowLabels = new ArrayList<JLabel>();
			diverterLabels = new ArrayList<JLabel>();
			feedPartsLabels = new ArrayList<JLabel>();
			rearGateLabels = new ArrayList<JLabel>();
			partsFedLabels = new ArrayList<JLabel>();
			partsFedNumberLabels = new ArrayList<JLabel>();
			for ( int i = 0; i < 4; i++ ) {
				feederNumberLabels.add( new JLabel() );
				feederNumberLabels.get( i ).setText( "" + ( i + 1 ) );
				feederNumberLabels.get( i ).setPreferredSize( feederNumberLabelSize );
				feederNumberLabels.get( i ).setMaximumSize( feederNumberLabelSize );
				feederNumberLabels.get( i ).setMinimumSize( feederNumberLabelSize );
				feederNumberLabels.get( i ).setVerticalAlignment( JLabel.TOP );
				feederNumberLabels.get( i ).setHorizontalAlignment( JLabel.CENTER );
				
				partsLowLabels.add( new JLabel() );
				partsLowLabels.get( i ).setForeground( Color.red );
				
				diverterLabels.add( new JLabel() );
				diverterLabels.get( i ).setText( "Diverter" );
				
				feedPartsLabels.add( new JLabel() );
				feedPartsLabels.get( i ).setText( "Feed Parts" );
				
				rearGateLabels.add( new JLabel() );
				rearGateLabels.get( i ).setText( "Rear Gate" );
				
				partsFedLabels.add( new JLabel() );
				partsFedLabels.get( i ).setText( "Parts Fed" );
				
				partsFedNumberLabels.add( new JLabel() );
				partsFedNumberLabels.get( i ).setText( "0" );
				partsFedNumberLabels.get( i ).setFont( new Font( "Serif", Font.BOLD, 18 ) );	
			}
			
			//JRadioButtons
			diverterRightRadioButtons = new ArrayList<JRadioButton>();
			diverterLeftRadioButtons = new ArrayList<JRadioButton>();
			feedPartsOnRadioButtons = new ArrayList<JRadioButton>();
			feedPartsOffRadioButtons = new ArrayList<JRadioButton>();
			rearGateRaisedRadioButtons = new ArrayList<JRadioButton>();
			rearGateLoweredRadioButtons = new ArrayList<JRadioButton>();
			for( int i = 0; i < 4; i++ ) {
				diverterRightRadioButtons.add( new JRadioButton() );
				diverterRightRadioButtons.get( i ).setText( "Right" );
				diverterRightRadioButtons.get( i ).addActionListener( this );
				diverterRightRadioButtons.get( i ).setActionCommand( "diverter_right" );
				diverterLeftRadioButtons.add( new JRadioButton() );
				diverterLeftRadioButtons.get( i ).setText( "Left" );
				diverterLeftRadioButtons.get( i ).addActionListener( this );
				diverterLeftRadioButtons.get( i ).setActionCommand( "diverter_left" );
				
				feedPartsOnRadioButtons.add( new JRadioButton() );
				feedPartsOnRadioButtons.get( i ).setText( "On" );
				feedPartsOnRadioButtons.get( i ).addActionListener( this );
				feedPartsOnRadioButtons.get( i ).setActionCommand( "feeder_on" );
				feedPartsOffRadioButtons.add( new JRadioButton() );
				feedPartsOffRadioButtons.get( i ).setText( "Off" );
				feedPartsOffRadioButtons.get( i ).addActionListener( this );
				feedPartsOffRadioButtons.get( i ).setActionCommand( "feeder_off" );
				
				rearGateRaisedRadioButtons.add( new JRadioButton() );
				rearGateRaisedRadioButtons.get( i ).setText( "Raised" );
				rearGateRaisedRadioButtons.get( i ).addActionListener( this );
				rearGateRaisedRadioButtons.get( i ).setActionCommand( "rear_gate_raised" );
				rearGateLoweredRadioButtons.add( new JRadioButton() );
				rearGateLoweredRadioButtons.get( i ).setText( "Lowered" );
				rearGateLoweredRadioButtons.get( i ).addActionListener( this );
				rearGateLoweredRadioButtons.get( i ).setActionCommand( "rear_gate_lowered" );
			}
			
			//ButtonGroups
			diverterRadioButtonGroups = new ArrayList<ButtonGroup>();
			feedPartsRadioButtonGroups = new ArrayList<ButtonGroup>();
			rearGateRadioButtonGroups = new ArrayList<ButtonGroup>();
			for( int i = 0; i < 4; i++ ) {
				diverterRadioButtonGroups.add( new ButtonGroup() );
				diverterRadioButtonGroups.get( i ).add( diverterRightRadioButtons.get( i ) );
				diverterRadioButtonGroups.get( i ).add( diverterLeftRadioButtons.get( i ) );
				
				feedPartsRadioButtonGroups.add( new ButtonGroup() );
				feedPartsRadioButtonGroups.get( i ).add( feedPartsOnRadioButtons.get( i ) );
				feedPartsRadioButtonGroups.get( i ).add( feedPartsOffRadioButtons.get( i ) );
				
				rearGateRadioButtonGroups.add( new ButtonGroup() );
				rearGateRadioButtonGroups.get( i ).add( rearGateRaisedRadioButtons.get( i ) );
				rearGateRadioButtonGroups.get( i ).add( rearGateLoweredRadioButtons.get( i ) );				
			}
			
			//Layout
			feederTitleLabelPanel.setLayout( new BoxLayout( feederTitleLabelPanel, BoxLayout.X_AXIS ) );
			feederTitleLabelPanel.add( Box.createGlue() );
			feederTitleLabelPanel.add( feederTitleLabel );
			feederTitleLabelPanel.add( Box.createGlue() );
			
			for( int i = 0; i < 4; i ++ ) {
				feederNumberLabelPanels.add( new JPanel() );
				feederNumberLabelPanels.get( i ).setPreferredSize( feederNumberLabelSize );
				feederNumberLabelPanels.get( i ).setMaximumSize( feederNumberLabelSize );
				feederNumberLabelPanels.get( i ).setMinimumSize( feederNumberLabelSize );
				feederNumberLabelPanels.get( i ).add( feederNumberLabels.get( i ) );
				
				partsLowLabelPanels.add( new JPanel() );
				partsLowLabelPanels.get( i ).setLayout( new BoxLayout( partsLowLabelPanels.get( i ), BoxLayout.X_AXIS ) );
				partsLowLabelPanels.get( i ).add( Box.createGlue() );
				partsLowLabelPanels.get( i ).add( partsLowLabels.get( i ) );
				partsLowLabelPanels.get( i ).add( Box.createGlue() );
				
				feederHeaderPanels.add( new JPanel() );
				feederHeaderPanels.get( i ).setLayout( new BoxLayout( feederHeaderPanels.get( i ), BoxLayout.X_AXIS ) );
				feederHeaderPanels.get( i ).setBorder( BorderFactory.createLineBorder( Color.black ) );
				feederHeaderPanels.get( i ).add( feederNumberLabelPanels.get( i ) );
				feederHeaderPanels.get( i ).add( partsLowLabelPanels.get( i ) );
				
				diverterPanels.add( new JPanel() );
				diverterPanels.get( i ).setLayout( new BoxLayout( diverterPanels.get( i ), BoxLayout.Y_AXIS ) );
				diverterPanels.get( i ).add( diverterLabels.get( i ) );
				diverterPanels.get( i ).add( Box.createGlue() );
				diverterPanels.get( i ).add( diverterRightRadioButtons.get( i ) );
				diverterPanels.get( i ).add( diverterLeftRadioButtons.get( i ) );
				diverterPanels.get( i ).add( Box.createGlue() );
				
				feedPartsPanels.add( new JPanel() );
				feedPartsPanels.get( i ).setLayout( new BoxLayout( feedPartsPanels.get( i ), BoxLayout.Y_AXIS ) );
				feedPartsPanels.get( i ).add( feedPartsLabels.get( i ) );
				feedPartsPanels.get( i ).add( Box.createGlue() );
				feedPartsPanels.get( i ).add( feedPartsOnRadioButtons.get( i ) );
				feedPartsPanels.get( i ).add( feedPartsOffRadioButtons.get( i ) );
				feedPartsPanels.get( i ).add( Box.createGlue() );
				
				rearGatePanels.add( new JPanel() );
				rearGatePanels.get( i ).setLayout( new BoxLayout( rearGatePanels.get( i ), BoxLayout.Y_AXIS ) );
				rearGatePanels.get( i ).add( rearGateLabels.get( i ) );
				rearGatePanels.get( i ).add( Box.createGlue() );
				rearGatePanels.get( i ).add( rearGateRaisedRadioButtons.get( i ) );
				rearGatePanels.get( i ).add( rearGateLoweredRadioButtons.get( i ) );
				rearGatePanels.get( i ).add( Box.createGlue() );
				
				partsFedPanels.add( new JPanel() );
				partsFedPanels.get( i ).setLayout( new BoxLayout( partsFedPanels.get( i ), BoxLayout.Y_AXIS ) );
				partsFedPanels.get( i ).setBorder( BorderFactory.createLineBorder( Color.black ) );
				partsFedPanels.get( i ).add( partsFedLabels.get( i ) );
				partsFedPanels.get( i ).add( Box.createVerticalStrut( 15 ) );
				partsFedPanels.get( i ).add( partsFedNumberLabels.get( i ) );
				partsFedPanels.get( i ).add( Box.createGlue() );
				
				controlPanels.add( new JPanel() );
				controlPanels.get( i ).setLayout( new BoxLayout( controlPanels.get( i ), BoxLayout.X_AXIS ) );
				controlPanels.get( i ).setBorder( BorderFactory.createLineBorder( Color.black ) );
				controlPanels.get( i ).add( Box.createGlue() );
				controlPanels.get( i ).add( diverterPanels.get( i ) );
				controlPanels.get( i ).add( Box.createGlue() );
				controlPanels.get( i ).add( feedPartsPanels.get( i ) );
				controlPanels.get( i ).add( Box.createGlue() );
				controlPanels.get( i ).add( rearGatePanels.get( i ) );
				controlPanels.get( i ).add( Box.createGlue() );
				controlPanels.get( i ).add( partsFedPanels.get( i ) );				
			}
			
			setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			add( feederTitleLabelPanel );
			add( Box.createVerticalStrut( 15 ) );
			for ( int i = 0; i < 4; i++ ) {
				add( feederHeaderPanels.get( i ) );
				add( controlPanels.get( i ) );
			}
		}

		/**
		 * Sets the parts low message for a specified feeder
		 * 
		 * @param partsLow boolean variable 
		 * @param feederNumber specifies which feeder is low on parts
		 */
		public void setPartsLow ( boolean partsLow, int feederNumber ) {
			if ( partsLow )
				partsLowLabels.get( feederNumber ).setText( "Parts Low" );
			else
				partsLowLabels.get( feederNumber ).setText( "" );
		}
		
		/**
		 * Updates the number of parts fed from the specified feeder
		 * 
		 * @param partsFed number of parts fed
		 * @param feederNumber specifies which feeder the "partsFed" number corresponds to
		 */
		public void setPartsFedCount ( int partsFed, int feederNumber ) {
			partsFedNumberLabels.get( feederNumber ).setText( "" + partsFed );
		}
		
		/**
		 * Sets the direction of the diverter for a specified feeder. "right" would correspond to up
		 * as viewed from the factory manager graphics panels. The use of "right" and "left" is to fulfill
		 * the original kitting cell description
		 * 
		 * @param setRight boolean variable specifying if the diverter should be moved to the right
		 * @param feederNumber specifies which feeder's diverter should be controlled
		 */
		public void setDiverterRight( boolean setRight, int feederNumber ) {
			diverterRightRadioButtons.get( feederNumber ).setSelected( setRight );
			diverterLeftRadioButtons.get( feederNumber ).setSelected( !setRight );
		}
		
		/**
		 * Sets a feeder on/off
		 * 
		 * @param on boolean variable if the feeder is on/off
		 * @param feederNumber specifies the feeder to control
		 */
		public void setFeedPartsOn( boolean on, int feederNumber ) {
			feedPartsOnRadioButtons.get( feederNumber ).setSelected( on );
			feedPartsOffRadioButtons.get( feederNumber ).setSelected( !on );
		}
		
		/**
		 * Sets the rear gate to raised/lowered
		 * 
		 * @param raised boolean variable, true if rear gate is raised
		 * @param feederNumber specifies the feeder to control
		 */
		public void setRearGateRaised( boolean raised, int feederNumber ) {
			rearGateRaisedRadioButtons.get( feederNumber ).setSelected( raised );
			rearGateLoweredRadioButtons.get( feederNumber ).setSelected( !raised );
		}
		
		/**
		 * Gives functionality to all the JRadioButtons in the panel
		 */
		public void actionPerformed( ActionEvent ae ) {
			//each "if" or "if else" statement checks for the command origination button
			
			if( ae.getSource() == updatePartLowAndCount ) {
				for( int feederNumber = 0; feederNumber < 4; feederNumber++ ) {
					// get entry corresponding to this feeder
					int key = fcm.server.feederIDs.get( feederNumber );
					Object stateObj = fcm.server.getState().items.get(key);
					if (stateObj instanceof GUIFeeder) {
						GUIFeeder feeder = (GUIFeeder)stateObj;
						setPartsLow( feeder.getPartsLow(), feederNumber );
						setPartsFedCount( feeder.partsFed(), feederNumber );
					}
					else {
						System.out.println("Error: feeder index variable does not point to a feeder");
					}
				}
			}
			
			else if ( ae.getActionCommand().equals( "diverter_right" ) ) {
				for( int i = 0; i < diverterRightRadioButtons.size(); i++ ) {
					if ( ae.getSource() == diverterRightRadioButtons.get( i ) ) {
						feederNumber = i;
						// get entry corresponding to this diverter arm
						int key = fcm.server.diverterArmIDs.get( feederNumber );
						Object stateObj = fcm.server.getState().items.get(key);
						if (stateObj instanceof GUIDiverterArm) {
							GUIDiverterArm diverterArm = (GUIDiverterArm)stateObj;
							// prepare factory update message
							FactoryUpdateMsg update = new FactoryUpdateMsg();
							update.setTime(fcm.server.getState()); // set time in update message
							update.itemMoves.put(key, new Movement(diverterArm.movement.getStartPos(), 0.7));
							fcm.server.applyUpdate(update); // apply and broadcast update message
						}
						else {
							System.out.println("Error: diverter arm index variable does not point to a diverter arm");
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			else if ( ae.getActionCommand().equals( "diverter_left" ) ) {
				for( int i = 0; i < diverterLeftRadioButtons.size(); i++ ) {
					if ( ae.getSource() == diverterLeftRadioButtons.get( i ) ){
						feederNumber = i;
						// get entry corresponding to this diverter arm
						int key = fcm.server.diverterArmIDs.get( feederNumber );
						Object stateObj = fcm.server.getState().items.get(key);
						if (stateObj instanceof GUIDiverterArm) {
							GUIDiverterArm diverterArm = (GUIDiverterArm)stateObj;
							// prepare factory update message
							FactoryUpdateMsg update = new FactoryUpdateMsg();
							update.setTime(fcm.server.getState()); // set time in update message
							update.itemMoves.put(key, new Movement(diverterArm.movement.getStartPos(), -0.7));
							fcm.server.applyUpdate(update); // apply and broadcast update message
						}
						else {
							System.out.println("Error: diverter arm index variable does not point to a diverter arm");
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			else if ( ae.getActionCommand().equals( "feeder_on" ) ) {
				for( int i = 0; i < feedPartsOnRadioButtons.size(); i++ ) {
					if ( ae.getSource() == feedPartsOnRadioButtons.get( i ) ){
						feederNumber = i;
						// get entry corresponding to this feeder
						int key = fcm.server.feederIDs.get(feederNumber);
						Object stateObj = fcm.server.getState().items.get(key);
						if (stateObj instanceof GUIFeeder) {
							GUIFeeder feeder = (GUIFeeder)stateObj;
							if ( !feeder.isFeeding() ) { // only start feeding if feeder is not feeding
								// prepare factory update message
								FactoryUpdateMsg update = new FactoryUpdateMsg();
								update.setTime(fcm.server.getState()); // set time in update message
								feeder.flipFeedingSwitch(); // start feeding
								update.putItems.put(key,feeder); // put updated lane in update message
								fcm.server.applyUpdate(update); // apply and broadcast update message
							}
						}
						else {
							System.out.println("Error: feeder index variable does not point to a feeder" );
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			else if ( ae.getActionCommand().equals( "feeder_off" ) ) {
				for( int i = 0; i < feedPartsOffRadioButtons.size(); i++ ) {
					if ( ae.getSource() == feedPartsOffRadioButtons.get( i ) ){
						feederNumber = i;
						// get entry corresponding to this feeder
						int key = fcm.server.feederIDs.get(feederNumber);
						Object stateObj = fcm.server.getState().items.get(key);
						if (stateObj instanceof GUIFeeder) {
							GUIFeeder feeder = (GUIFeeder)stateObj;
							if ( feeder.isFeeding() ) { // only stop feeding if feeder is feeding
								// prepare factory update message
								FactoryUpdateMsg update = new FactoryUpdateMsg();
								update.setTime(fcm.server.getState()); // set time in update message
								feeder.flipFeedingSwitch(); // stop feeding
								update.putItems.put(key,feeder); // put updated lane in update message
								fcm.server.applyUpdate(update); // apply and broadcast update message
							}
						}
						else {
							System.out.println("Error: feeder index variable does not point to a feeder" );
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			else if ( ae.getActionCommand().equals( "rear_gate_raised" ) ) {
				for( int i = 0; i < rearGateRaisedRadioButtons.size(); i++ ) {
					if ( ae.getSource() == rearGateRaisedRadioButtons.get( i ) ){
						feederNumber = i;
						// get entry corresponding to this feeder
						int key = fcm.server.feederIDs.get(feederNumber);
						Object stateObj = fcm.server.getState().items.get(key);
						if (stateObj instanceof GUIFeeder) {
							GUIFeeder feeder = (GUIFeeder)stateObj;
							if ( !feeder.isGateRaised() ) { // only raise gate if gate is lowered
								// prepare factory update message
								FactoryUpdateMsg update = new FactoryUpdateMsg();
								update.setTime(fcm.server.getState()); // set time in update message
								feeder.flipFeederGateSwitch(); // raise gate
								update.putItems.put(key,feeder); // put updated lane in update message
								fcm.server.applyUpdate(update); // apply and broadcast update message
							}
						}
						else {
							System.out.println("Error: feeder index variable does not point to a feeder" );
						}
						return; // no need to check if other buttons selected
					}
				}
			}
			else if ( ae.getActionCommand().equals( "rear_gate_lowered" ) ) {
				for( int i = 0; i < rearGateLoweredRadioButtons.size(); i++ ) {
					if ( ae.getSource() == rearGateLoweredRadioButtons.get( i ) ){
						feederNumber = i;
						// get entry corresponding to this feeder
						int key = fcm.server.feederIDs.get(feederNumber);
						Object stateObj = fcm.server.getState().items.get(key);
						if (stateObj instanceof GUIFeeder) {
							GUIFeeder feeder = (GUIFeeder)stateObj;
							if ( feeder.isGateRaised() ) { // only lower gate if gate is raised
								// prepare factory update message
								FactoryUpdateMsg update = new FactoryUpdateMsg();
								update.setTime(fcm.server.getState()); // set time in update message
								feeder.flipFeederGateSwitch(); // lower gate
								feeder.purgeFeeder(); //purge feeder
								update.putItems.put(key,feeder); // put updated lane in update message
								fcm.server.applyUpdate(update); // apply and broadcast update message
							}
						}
						else {
							System.out.println("Error: feeder index variable does not point to a feeder" );
						}
						return; // no need to check if other buttons selected
					}
				}
			}	
		}
	}
