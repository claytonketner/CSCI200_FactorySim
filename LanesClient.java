import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class LanesClient extends JFrame implements ActionListener {
	LaneDemo LaneDemoPanel;
	JPanel layout;
	CardLayout cl;
	
	public LanesClient(){
		//initialize layout panel with cardlayout
		layout = new JPanel();
		cl = new CardLayout();
		layout.setLayout(cl);
		
		//add panels to layout
		LaneDemoPanel = new LaneDemo();
		layout.add(LaneDemoPanel, "gfx");
		
		//add layout panel
		add(layout);
		
		Timer t = new Timer(50, this);
		t.start();
	}
	
	public static void main(String[] args){
		LanesClient LClient = new LanesClient();
		LClient.setSize(800, 600);
		LClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LClient.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		this.repaint();
	}
}
