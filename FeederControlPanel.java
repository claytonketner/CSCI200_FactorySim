import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class FeederControlPanel extends JPanel implements ActionListener {
		FactoryControlClient fcc;
		Dimension feederNumberLabelSize;
		JPanel feederTitleLabelPanel;
		JLabel feederTitleLabel;
		ArrayList<JPanel> feederPanels, feederHeaderPanels,feederNumberLabelPanels, partsLowLabelPanels, controlPanels, diverterPanels, feedPartsPanels, rearGatePanels, partsFedPanels;
		ArrayList<JLabel> feederNumberLabels, partsLowLabels, diverterLabels, feedPartsLabels, rearGateLabels, partsFedLabels, partsFedNumberLabels;
		ArrayList<JRadioButton> diverterRightRadioButtons, diverterLeftRadioButtons, feedPartsOnRadioButtons, feedPartsOffRadioButtons, rearGateRaisedRadioButtons, rearGateLoweredRadioButtons;
		ArrayList<ButtonGroup> diverterRadioButtonGroups, feedPartsRadioButtonGroups, rearGateRadioButtonGroups;
		
		public FeederControlPanel( FactoryControlClient fcc ) {
			this.fcc = fcc;
			
			//Dimensions
			feederNumberLabelSize = new Dimension( 20, 20 );
			
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
				diverterLeftRadioButtons.add( new JRadioButton() );
				diverterLeftRadioButtons.get( i ).setText( "Left" );
				diverterLeftRadioButtons.get( i ).addActionListener( this );
				
				feedPartsOnRadioButtons.add( new JRadioButton() );
				feedPartsOnRadioButtons.get( i ).setText( "On" );
				feedPartsOnRadioButtons.get( i ).addActionListener( this );
				feedPartsOffRadioButtons.add( new JRadioButton() );
				feedPartsOffRadioButtons.get( i ).setText( "Off" );
				feedPartsOffRadioButtons.get( i ).addActionListener( this );
				
				rearGateRaisedRadioButtons.add( new JRadioButton() );
				rearGateRaisedRadioButtons.get( i ).setText( "Raised" );
				rearGateRaisedRadioButtons.get( i ).addActionListener( this );
				rearGateLoweredRadioButtons.add( new JRadioButton() );
				rearGateLoweredRadioButtons.get( i ).setText( "Lowered" );
				rearGateLoweredRadioButtons.get( i ).addActionListener( this );
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
			add( Box.createVerticalStrut( 20 ) );
			for ( int i = 0; i < 4; i++ ) {
				add( feederHeaderPanels.get( i ) );
				add( controlPanels.get( i ) );
			}
		}

		public void actionPerformed( ActionEvent ae ) {
			// TODO Auto-generated method stub
			
		}
	}
