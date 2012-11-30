import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class BreakLanePanel extends JPanel {
	private JButton test;
	private JLabel test2;
	
	private JLabel jamLanes;
	private JLabel partJumps;
	private JRadioButton jamLane;
	private JRadioButton unjamLane;
	private JButton partJumper;	
	
	
	
	/** Initialize */
	public BreakLanePanel() {
		jamLanes = new JLabel( "Lane Jammer" );		
		jamLane = new JRadioButton( "On" );
		unjamLane = new JRadioButton( "Off" );
		unjamLane.setSelected( true );
		partJumps = new JLabel( "Makes a part on the lane jump: " );
		partJumper = new JButton( "JUMP" );
		
		//layout
		setLayout( new GridBagLayout() );
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		
		add(jamLanes, c);
		
		c.gridx = 1;
		add(jamLane, c );
		
		c.gridx = 2;
		add(unjamLane, c);
		
		c.gridx = 0;
		c.gridy = 1;
		add(partJumps, c);
		
		c.gridx = 1;
		add(partJumper, c);
		
		//action listeners
		jamLane.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				unjamLane.setSelected(false);
			}
		});
		
		unjamLane.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ){
				jamLane.setSelected(false);
			}
		});
	}
}
