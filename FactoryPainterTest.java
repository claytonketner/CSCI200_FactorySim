import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class FactoryPainterTest implements ActionListener
{
	static JFrame jf;
	
	public FactoryPainterTest()
	{
	}
	
	
	public static void main(String[] args)
	{
		Painter.loadImages();

		int numOptions = 1;
		// print options
		System.out.println("Options:");
		System.out.println("\t1 -- Factory manager view. Show entire factory.");
		System.out.print("\n\n");

		Scanner s = new Scanner(System.in);
		int choice = -1;

		// retrieve option from user
		while (true)
		{	
			System.out.print("Choose a number: ");
			
			String inStr = s.next().toLowerCase().trim();
			
			try {
				choice = Integer.parseInt(inStr);
				if (choice < 1 || choice > numOptions)
					throw new Exception();
				break;
			} catch (Exception e) {
			}
		}
		
		// instantiate JFrame and put correct demo panel in it
		jf = new JFrame("CSCI 200 -- Team 11");
		JPanel jp = new JPanel();
		
		FactoryStateMsg factoryState = new FactoryStateMsg(); // ################ This needs to have stuff in it!
		factoryState.kitRobots.put(new Integer(0), new GUIKitRobot(new KitRobot()));

		switch (choice)
		{
		case 1:
			jp = new FactoryProductionViewPanel(factoryState);
			break;
		default:
			jp = null;
			break;
		}
		
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		
		// start timer
		Timer t = new Timer(50, new FactoryPainterTest());
		t.start();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		jf.repaint();
	}
}
