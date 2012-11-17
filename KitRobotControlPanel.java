import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class KitRobotControlPanel extends JPanel implements ActionListener {
		FactoryControlClient fcc;
		ImageIcon kitRobotImage, kitStandImage, kitDeliveryStationImage;
		JPanel kitRobotLabelPanel, kitRobotImageLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, dropOffPickUpButtonPanel;
		JPanel posButtonPanel, blankPanel1, blankPanel2, pictureConfirmationPanel, cameraPanel;
		JLabel kitRobotLabel, takePictureLabel, kitStatusLabel, kitRobotImageLabel, kitStandImageLabel, kitDeliveryStationImageLabel, redColorLabel, greenColorLabel;
		JButton pausePlayButton, cancelMoveButton, dropOffButton, pickUpButton, takePictureButton;	
		JRadioButton kitRobotOnButton, kitRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension posButtonSize, blankPanel1Size, blankPanel2Size, takePictureButtonSize, pictureConfirmationPanelSize;
		ArrayList<JButton> kitStandPositionButtons;
		ArrayList<ImageIcon> pictureConfirmationColors;
		
		public KitRobotControlPanel( FactoryControlClient fcc ) {
			this.fcc = fcc;
			
			//ImageIcons
			kitRobotImage = new ImageIcon( "images/guiserver_thumbs/kitRobot_thumb.png" );
			kitStandImage = new ImageIcon( "images/guiserver_thumbs/kit_table_thumb.png" );
			kitDeliveryStationImage = new ImageIcon( "images/guiserver_thumbs/kit_delivery_station_thumb.png" );
			pictureConfirmationColors = new ArrayList<ImageIcon>();
			for( int i = 0; i < 4; i++ ) {
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/red.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/green.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/dark_red.png" ) );
				pictureConfirmationColors.add( new ImageIcon( "images/guiserver_thumbs/colors/dark_green.png" ) );
			}
			
			//Dimensions
			posButtonSize = new Dimension( 40, 40 );
			blankPanel1Size = new Dimension( 80, 300 );
			blankPanel2Size = new Dimension( 100, 100 );
			takePictureButtonSize = new Dimension( 40, 40 );
			pictureConfirmationPanelSize = new Dimension( 20, 40 );
			
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
			redColorLabel.setIcon( pictureConfirmationColors.get( 2 ) );
			greenColorLabel = new JLabel();
			greenColorLabel.setIcon( pictureConfirmationColors.get( 3 ) );
			
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
			
			pictureConfirmationPanel.setLayout( new BoxLayout( pictureConfirmationPanel, BoxLayout.Y_AXIS ) );
			pictureConfirmationPanel.add( redColorLabel );
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
			add( cameraPanel, c );
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
		
		public void setPickUpButtonEnabled( boolean enabled ) {
			pickUpButton.setEnabled( enabled );
		}
		
		public void setDropOffButtonEnabled( boolean enabled ) {
			dropOffButton.setEnabled( enabled );
		}
		
		
		
		public void actionPerformed( ActionEvent ae ) {
			
		}
	}
