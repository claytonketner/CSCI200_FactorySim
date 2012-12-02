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
	private JButton btnDropGripper;
	private GridBagConstraints c = new GridBagConstraints();
	private NetComm netcomm;
	private KitAssemblyClient client;
	/**
	 * 
	 */
	public KitAssemblyBreak(KitAssemblyClient kac){
		client = kac;
		//buttons for drop parts
		setLayout(new GridBagLayout());
		btnDropGripper = new JButton("Drop Parts in Part Robot");
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
		c.fill = GridBagConstraints.HORIZONTAL;
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
		c.gridx = 0;
		c.gridy = 1;
		add(rbtBreakKitRobot,c);
		c.gridx = 1;
		c.gridy = 1;
		add(rbtFixKitRobot,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(btnDropGripper,c);
		btnDropGripper.addActionListener(this);
		rbtBreakPartRobot.addActionListener(this);
		rbtFixPartRobot.addActionListener(this);
		rbtBreakKitRobot.addActionListener(this);
		rbtFixKitRobot.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnDropGripper){
			client.getNetComm().write(new NonNormativeMsg( NonNormativeMsg.ItemEnum.PART_ROBOT, 0, NonNormativeMsg.CmdEnum.DROP_PART));
		}
		if(e.getSource() == rbtBreakPartRobot){
			client.getNetComm().write(new NonNormativeMsg( NonNormativeMsg.ItemEnum.PART_ROBOT, 0, NonNormativeMsg.CmdEnum.BREAK));
		}
		if(e.getSource() == rbtFixPartRobot){
			client.getNetComm().write(new NonNormativeMsg( NonNormativeMsg.ItemEnum.PART_ROBOT, 0, NonNormativeMsg.CmdEnum.FIX));
		}
		if(e.getSource() == rbtBreakKitRobot){
			client.getNetComm().write(new NonNormativeMsg( NonNormativeMsg.ItemEnum.KIT_ROBOT, 0, NonNormativeMsg.CmdEnum.BREAK));
		}
		if(e.getSource() == rbtFixKitRobot){
			client.getNetComm().write(new NonNormativeMsg( NonNormativeMsg.ItemEnum.KIT_ROBOT, 0, NonNormativeMsg.CmdEnum.FIX));
		}
	}

}
