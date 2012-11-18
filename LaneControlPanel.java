import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class LaneControlPanel extends JPanel implements ActionListener {
		
		FactoryControlManager fcm;
		ImageIcon laneImage;
		JPanel laneTitleLabelPanel;
		JLabel laneTitleLabel;
		ArrayList<JPanel> checkBoxPanel, lanePanels, checkBoxLaneAndButtonPanels;
		ArrayList<JCheckBox> increaseAmplitudeCheckBoxes;
		ArrayList<JLabel> laneImageLabels, increaseAmplitudeLabels;
		ArrayList<JRadioButton> onRadioButtons, offRadioButtons;
		ArrayList<ButtonGroup> radioButtonGroups;
		int laneNumber;
		
		public LaneControlPanel( FactoryControlManager fcm ) {
			this.fcm = fcm;
			
			Dimension panelSize = new Dimension ( 150, 532 );
			
			//ImageIcons
			laneImage = new ImageIcon( "images/guiserver_thumbs/lane_thumb.png" );
			
			//JPanels
			laneTitleLabelPanel = new JPanel();
			checkBoxPanel = new ArrayList<JPanel>();
			lanePanels = new ArrayList<JPanel>();
			checkBoxLaneAndButtonPanels = new ArrayList<JPanel>();
			
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
				increaseAmplitudeLabels.get( i ).setText( "<html><body style=\"text-align:center;\">Increase<br/>Amplitude</body></html>" );
			}
			
			//JCheckBoxes
			increaseAmplitudeCheckBoxes = new ArrayList<JCheckBox>();
			for( int i = 0; i < 4; i++ ) {
				increaseAmplitudeCheckBoxes.add( new JCheckBox() );
				increaseAmplitudeCheckBoxes.get( i ).addActionListener( this );
				increaseAmplitudeCheckBoxes.get( i ).setActionCommand( "check_box" );
			}
			
			//JRadioButtons
			onRadioButtons = new ArrayList<JRadioButton>();
			offRadioButtons = new ArrayList<JRadioButton>();
			for( int i = 0; i < 4; i++ ) {
				onRadioButtons.add( new JRadioButton() );
				onRadioButtons.get( i ).setText( "On" );
				onRadioButtons.get( i ).addActionListener( this );
				onRadioButtons.get( i ).setActionCommand( "on_button" );
				offRadioButtons.add( new JRadioButton() );
				offRadioButtons.get( i ).setText( "Off" );
				offRadioButtons.get( i ).addActionListener( this );
				offRadioButtons.get( i ).setActionCommand( "on_button" );
			}
			
			//ButtonGroups
			radioButtonGroups = new ArrayList<ButtonGroup>();
			for( int i = 0; i < 4; i++ ) {
				radioButtonGroups.add( new ButtonGroup() );
				radioButtonGroups.get( i ).add( onRadioButtons.get( i ) );
				radioButtonGroups.get( i ).add( offRadioButtons.get( i ) );
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
				checkBoxPanel.get( i ).add( onRadioButtons.get( i ) );
				checkBoxPanel.get( i ).add( offRadioButtons.get( i ) );
				checkBoxPanel.get( i ).add( Box.createGlue() );
				
				lanePanels.add( new JPanel() );
				lanePanels.get( i ).add( laneImageLabels.get( i ) );
				
				checkBoxLaneAndButtonPanels.add( new JPanel() );
				checkBoxLaneAndButtonPanels.get( i ).setLayout( new BoxLayout( checkBoxLaneAndButtonPanels.get( i ), BoxLayout.X_AXIS ) );
				checkBoxLaneAndButtonPanels.get( i ).add( Box.createGlue() );
				checkBoxLaneAndButtonPanels.get( i ).add( checkBoxPanel.get( i ) );
				checkBoxLaneAndButtonPanels.get( i ).add( lanePanels.get( i ) );
				checkBoxLaneAndButtonPanels.get( i ).add( Box.createGlue() );
			}
			
			setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setPreferredSize( panelSize );
			setMaximumSize( panelSize );
			setMinimumSize( panelSize );
			add( laneTitleLabelPanel );
			add( Box.createVerticalStrut( 20 ) );
			for( JPanel panel : checkBoxLaneAndButtonPanels ) {
				add( panel );
				add( Box.createGlue() );
			}
			
		}

		public void setLaneOnButton( boolean on, int laneNumber ) {
			onRadioButtons.get( laneNumber ).setSelected( on );
			offRadioButtons.get( laneNumber ).setSelected( !on );
		}
		
		public void setIncreaseVibrationCheckBox( boolean increaseAmplitude, int laneNumber ) {
			increaseAmplitudeCheckBoxes.get( laneNumber ).setSelected( increaseAmplitude );
		}
		
		public void actionPerformed( ActionEvent ae ) {
			
			if( ae.getActionCommand().equals( "on_button" ) ) {
				for ( int i = 0; i < onRadioButtons.size(); i++ ) {
					if ( ae.getSource() == onRadioButtons.get( i ) )
						laneNumber = i;
				}
			}
			else if( ae.getActionCommand().equals( "off_button" ) ) {
				for ( int i = 0; i < offRadioButtons.size(); i++ ) {
					if ( ae.getSource() == offRadioButtons.get( i ) )
						laneNumber = i;
				}
			}
			else if( ae.getActionCommand().equals( "check_box" ) ) {
				for ( int i = 0; i < increaseAmplitudeCheckBoxes.size(); i++ ) {
					if ( ae.getSource() == increaseAmplitudeCheckBoxes.get( i ) )
						laneNumber = i;
				}
			}
			
		}
		
	}
