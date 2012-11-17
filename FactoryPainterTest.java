import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
		
		// Copy and paste this into the server's factory state initialization
		//#######################################################################################
		FactoryStateMsg state = new FactoryStateMsg();
		int laneSeparation = 120;
		state.kitStands.put(new Integer(0), new GUIKitStand(new KitStand()));
		
		GUIKitDeliveryStation guiKitDeliv = new GUIKitDeliveryStation(new KitDeliveryStation(), 
		 		   new GUILane(new ComboLane(), false, 8, 350,-10), 
		 		   new GUILane(new ComboLane(), false, 3, 350-180, -10), 10, 10);
		guiKitDeliv.inConveyor.lane.turnOff();
		guiKitDeliv.outConveyor.lane.turnOff();
		 		   
		state.kitDeliveryStations.put(new Integer(0), guiKitDeliv);
											 
		state.kitRobots.put(new Integer(0), new GUIKitRobot(new KitRobot(), new Point2D.Double(350, 250)));
		state.partRobots.put(new Integer(0), new GUIPartRobot(new PartRobot()));
		GUIGantry guiGantry = new GUIGantry(100, 100);
		guiGantry.movement = guiGantry.movement.moveToAtSpeed(0, new Point2D.Double(500,500), 0, 50);
		guiGantry.addBin(new GUIBin(new GUIPart(new Part(), 0, 0), new Bin(new Part(), 10), 0, 0));
		state.gantries.put(new Integer(0), guiGantry);
		
		for (int i=0; i<4; i++)
		{
			state.nests.put(new Integer(i*2), new GUINest(new Nest(), 550, 120 + laneSeparation*i));
			state.nests.put(new Integer(i*2 + 1), new GUINest(new Nest(), 550, 120 + laneSeparation*i + 50));
			
			GUILane guiLane = new GUILane(new ComboLane(), true, 6, 630, 124 + laneSeparation*i);
			guiLane.lane.turnOff();
			
			state.lanes.put(new Integer(i), guiLane);
			state.diverterArms.put(new Integer(i), new GUIDiverterArm(990, 170 + laneSeparation*i));
			state.feeders.put(new Integer(i), new GUIFeeder(new Feeder(), 1165, 170 + laneSeparation*i));
		}
		//#######################################################################################

		switch (choice)
		{
		case 1:
			jp = new FactoryProductionViewPanel();
			((FactoryProductionViewPanel)jp).setFactoryState(state);
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
