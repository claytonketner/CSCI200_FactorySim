import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class KitAssemblyBreak extends JPanel implements ActionListener{


	private JRadioButton rbtDrop,rbtBreakPartRobot,rbtFixPartRobot,rbtBreakKitRobot,rbtFixKitRobot;
	private static final long serialVersionUID = 1L;
	private ButtonGroup partButtonGroup,kitButtonGroup;
	private JButton btnDropGripper1,btnDropGripper2,btnDropGripper3,btnDropGripper4;
	private GridBagConstraints c = new GridBagConstraints();
	private NetComm netcomm;
	/**
	 * 
	 */
	public KitAssemblyBreak(KitAssemblyClient kac){
		netcomm = kac.getNetComm();
		//buttons for drop parts
		setLayout(new GridBagLayout());
		btnDropGripper1 = new JButton("Drop Part in Gripper 1");
		btnDropGripper2 = new JButton("Drop Part in Gripper 2");
		btnDropGripper3 = new JButton("Drop Part in Gripper 3");
		btnDropGripper4 = new JButton("Drop Part in Gripper 4");
		//group buttons
		rbtBreakPartRobot = new JRadioButton("Break Part Robot");
		rbtFixPartRobot = new JRadioButton("Fix Part Robot");
		rbtBreakKitRobot = new JRadioButton("Break Kit Robot");
		rbtFixKitRobot = new JRadioButton("Fix Kit Robot");
		kitButtonGroup = new ButtonGroup();
		kitButtonGroup.add(rbtBreakKitRobot);
		kitButtonGroup.add(rbtFixKitRobot);
		partButtonGroup = new ButtonGroup();
		partButtonGroup.add(rbtBreakPartRobot);
		partButtonGroup.add(rbtFixPartRobot);
		//layout
		c.fill = c.HORIZONTAL;
		c.insets = new Insets(20,10,0,0);
		c.weightx = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		add(rbtBreakPartRobot,c);
		c.gridx = 1;
		c.gridy = 0;
		add(rbtFixPartRobot,c);
		c.gridx = 2;
		c.gridy = 0;
		add(rbtBreakKitRobot,c);
		c.gridx = 3;
		c.gridy = 0;
		add(rbtFixKitRobot,c);
		c.gridx = 0;
		c.gridy = 1;
		add(btnDropGripper1,c);
		c.gridx = 1;
		c.gridy = 1;
		add(btnDropGripper2,c);
		c.gridx = 2;
		c.gridy = 1;
		add(btnDropGripper3,c);
		c.gridx = 3;
		c.gridy = 1;
		add(btnDropGripper4,c);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnDropGripper1){
			
//			netcomm.write(new BreakPartRobotMsg());
		}

		if(e.getSource() == btnDropGripper2){
			
//			netcomm.write(new BreakPartRobotMsg());
		}

		if(e.getSource() == btnDropGripper3){
			
//			netcomm.write(new BreakPartRobotMsg());
		}

		if(e.getSource() == btnDropGripper4){
			
//			netcomm.write(new BreakPartRobotMsg());
		}
	}

}
