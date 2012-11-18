import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class NestControlPanel extends JPanel implements ActionListener {
		FactoryControlManager fcm;
		ImageIcon nestImage;
		JPanel nestsTitleLabelPanel;
		JLabel nestsTitleLabel;
		ArrayList<JPanel> radioButtonPairPanel, nestPanels, radioButtonPairAndNestPanels;
		ArrayList<JRadioButton> upRadioButtons, downRadioButtons;
		ArrayList<ButtonGroup> radioButtonGroups;
		ArrayList<JLabel> nestImageLabels;
		
		public NestControlPanel( FactoryControlManager fcm ) {
			this.fcm = fcm;
			
			Dimension panelSize = new Dimension ( 150, 532 );
			
			//ImageIcons
			nestImage = new ImageIcon( "images/guiserver_thumbs/nest_thumb_large.png" );
			
			//JPanels
			nestsTitleLabelPanel = new JPanel();
			radioButtonPairPanel = new ArrayList<JPanel>();
			nestPanels = new ArrayList<JPanel>();
			radioButtonPairAndNestPanels = new ArrayList<JPanel>();
			
			//JLabels
			nestsTitleLabel = new JLabel();
			nestsTitleLabel.setText( "Nests" );
			nestsTitleLabel.setFont( new Font( "Serif", Font.BOLD, 24 ) );
			nestImageLabels = new ArrayList<JLabel>();
			for( int i = 0; i < 8; i++ ) {
				nestImageLabels.add( new JLabel() );
				nestImageLabels.get( i ).setIcon( nestImage );
			}
			
			//JRadioButtons
			upRadioButtons = new ArrayList<JRadioButton>();
			downRadioButtons = new ArrayList<JRadioButton>();
			for( int i = 0; i < 8; i++ ) {
				upRadioButtons.add( new JRadioButton() );
				upRadioButtons.get( i ).setText( "Up" );
				downRadioButtons.add( new JRadioButton() );
				downRadioButtons.get( i ).setText( "Down" );
			}
			
			//ButtonGroups
			radioButtonGroups = new ArrayList<ButtonGroup>();
			for( int i = 0; i < 8; i++ ) {
				radioButtonGroups.add( new ButtonGroup() );
				radioButtonGroups.get( i ).add( upRadioButtons.get( i ) );
				radioButtonGroups.get( i ).add( downRadioButtons.get( i ) );
			}
			
			//Layout
			
			nestsTitleLabelPanel.setLayout( new BoxLayout( nestsTitleLabelPanel, BoxLayout.X_AXIS ) );
			nestsTitleLabelPanel.add( Box.createGlue() );
			nestsTitleLabelPanel.add( nestsTitleLabel );
			nestsTitleLabelPanel.add( Box.createGlue() );
			
			for( int i = 0; i < 8; i++ ) {
				radioButtonPairPanel.add( new JPanel() );
				radioButtonPairPanel.get( i ).setLayout( new BoxLayout( radioButtonPairPanel.get( i ), BoxLayout.Y_AXIS ) );
				radioButtonPairPanel.get( i ).add( Box.createGlue() );
				radioButtonPairPanel.get( i ).add( upRadioButtons.get( i ) );
				radioButtonPairPanel.get( i ).add( downRadioButtons.get( i ) );
				radioButtonPairPanel.get( i ).add( Box.createGlue() );
				
				nestPanels.add( new JPanel() );
				nestPanels.get( i ).add( nestImageLabels.get( i ) );
				
				radioButtonPairAndNestPanels.add( new JPanel() );
				radioButtonPairAndNestPanels.get( i ).setLayout( new BoxLayout( radioButtonPairAndNestPanels.get( i ), BoxLayout.X_AXIS ) );
				radioButtonPairAndNestPanels.get( i ).add( Box.createGlue() );
				radioButtonPairAndNestPanels.get( i ).add( radioButtonPairPanel.get( i ) );
				radioButtonPairAndNestPanels.get( i ).add( nestPanels.get( i ) );
				radioButtonPairAndNestPanels.get( i ).add( Box.createGlue() );
			}
			
			setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			setPreferredSize( panelSize );
			setMaximumSize( panelSize );
			setMinimumSize( panelSize );
			add( nestsTitleLabelPanel );
			add( Box.createVerticalStrut( 20 ) );
			int i = 0;
			for( JPanel panel : radioButtonPairAndNestPanels ) {
				add( panel );
				i++;
				if ( i % 2 == 0 ) {
					add( Box.createGlue() );
				}
			}
		}
		
		public void actionPerformed( ActionEvent ae ) {
			// TODO Auto-generated method stub
			
		}
		
	}