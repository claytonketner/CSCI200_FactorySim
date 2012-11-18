import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class GantryRobotControlPanel extends JPanel implements ActionListener {	
		FactoryControlManager fcm;
		ImageIcon gantryRobotImage, partsBoxImage, feederImage;
		JPanel gantryRobotTitleLabelPanel, gantryRobotImageLabelPanel, robotOnOffButtonPanel, robotPauseCancelButtonPanel, partsBinsLabelPanel, sparePartsLabelPanel; 
		JPanel blankPanel1, blankPanel2, partsBoxStoragePanel, feederPanel, sparePartsPanel;
		JLabel gantryRobotTitleLabel, gantryRobotImageLabel, partsBinsLabel, sparePartsLabel;
		JButton pausePlayButton, cancelMoveButton;	
		JRadioButton gantryRobotOnButton, gantryRobotOffButton;
		ButtonGroup onOffButtonGroup;
		Dimension boxButtonSize, blankPanelSize, textFieldSize, boxPanelSize, feederPanelSize, controlButtonSize;
		ArrayList<JPanel> singlePartsBoxPanels, singleFeederPanels, singlePurgeBoxPanels, singleSparePartsPanels;
		ArrayList<JButton> partsBoxStorageButtons, feederButtons, partPurgeBoxButtons, sparePartsButtons;
		ArrayList<JTextField> partsBoxStorageTextFields, feederTextFields, sparePartsTextFields;
		
		public GantryRobotControlPanel( FactoryControlManager fcm ) {
			this.fcm = fcm;
			
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
			controlButtonSize = new Dimension( 60, 40 );
			
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
			singlePurgeBoxPanels = new ArrayList<JPanel>();
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
			partsBoxStorageButtons = new ArrayList<JButton>();
			feederButtons = new ArrayList<JButton>();
			partPurgeBoxButtons = new ArrayList<JButton>();
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
				
				partPurgeBoxButtons.add( new JButton() );
				partPurgeBoxButtons.get( i ).setIcon( partsBoxImage );
				partPurgeBoxButtons.get( i ).setPreferredSize( boxButtonSize );
				partPurgeBoxButtons.get( i ).setMaximumSize( boxButtonSize );
				partPurgeBoxButtons.get( i ).setMinimumSize( boxButtonSize );
				
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
			for( JButton boxButton : partPurgeBoxButtons ) {
				feederPanel.add( boxButton, b );
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
