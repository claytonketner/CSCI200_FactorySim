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
		ArrayList<JPanel> checkBoxPanel, lanePanels, checkBoxAndLanePanels;
		ArrayList<JCheckBox> increaseAmplitudeCheckBoxes;
		ArrayList<JLabel> laneImageLabels, increaseAmplitudeLabels;
		
		public LaneControlPanel( FactoryControlManager fcm ) {
			this.fcm = fcm;
			
			Dimension panelSize = new Dimension ( 150, 532 );
			
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
				increaseAmplitudeLabels.get( i ).setText( "<html><body style=\"text-align:center;\">Increase<br/>Amplitude</body></html>" );
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
