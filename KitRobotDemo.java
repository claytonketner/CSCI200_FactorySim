import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class KitRobotDemo extends JPanel
{
	GUIKitDeliveryStation guiKitDeliveryStation;
	GUIKitRobot guiKitRobot;
	GUIKitStand guiKitStand;
	
	int paintCount = 0;
	
	private enum Status {
		GETKITFROMINCONVEYOR, PUTKITONSTAND, GETKITFROMSTAND, TAKINGPICTURE, PUTKITONOUTCONVEYOR, IDLE
	}
	
	private Status status = Status.IDLE;
	
	public KitRobotDemo()
	{
		this.setPreferredSize(new Dimension(800,600));
		
		Painter.loadImages();
		
		guiKitDeliveryStation = new GUIKitDeliveryStation(new KitDeliveryStation(), new GUILane(new ComboLane(), false, 8, 300,10), new GUILane(new ComboLane(), false, 3, 300-180, 10), 10, 10);
		guiKitRobot = new GUIKitRobot(new KitRobot());
		guiKitStand = new GUIKitStand(new KitStand());
				
		guiKitDeliveryStation.inConveyor.addPallet();
	}
	
	public void paint(Graphics gfx)
	{
		long currentTime = System.currentTimeMillis();

		checkStatus(currentTime);
		
		Graphics2D g = (Graphics2D)gfx;
		
		guiKitDeliveryStation.draw(g, currentTime);
		guiKitStand.draw(g, currentTime);
		guiKitRobot.draw(g, currentTime);
		
		paintCount++;
	}
	
	private void checkStatus(long currentTime)
	{
		if (status == Status.IDLE && guiKitDeliveryStation.inConveyor.hasFullPalletAtEnd(currentTime))
		{
			guiKitRobot.movement = guiKitRobot.movement.moveToAtSpeed(guiKitDeliveryStation.inConveyor.getLocationOfEndPallet(currentTime), 0, 100);
			status = Status.GETKITFROMINCONVEYOR;
			return;
		}
		
		if (status == Status.GETKITFROMINCONVEYOR && guiKitRobot.arrived(currentTime))
		{
			guiKitRobot.setKit(guiKitDeliveryStation.inConveyor.removeEndPalletKit());
			guiKitRobot.movement = guiKitRobot.movement.moveToAtSpeed(guiKitStand.getCameraStationLocation(), 0, 100);
			status = Status.PUTKITONSTAND;
			return;
		}
		
		if (status == Status.PUTKITONSTAND && guiKitRobot.arrived(currentTime))
		{
			guiKitStand.addKit(guiKitRobot.removeKit(), GUIKitStand.StationNumber.THREE);
			guiKitRobot.park();
			status = Status.TAKINGPICTURE;
			return;
		}
		
		if (status == Status.TAKINGPICTURE && guiKitRobot.arrived(currentTime))
		{
			guiKitRobot.movement = new Movement(guiKitStand.getCameraStationLocation(), 0);
			status = Status.GETKITFROMSTAND;
			return;
		}
		
		if (status == Status.GETKITFROMSTAND && guiKitRobot.arrived(currentTime))
		{
			guiKitRobot.setKit(guiKitStand.removeKit(GUIKitStand.StationNumber.THREE));
			guiKitRobot.movement = new Movement(guiKitDeliveryStation.getOutConveyorLocation(), 0);
			status = Status.PUTKITONOUTCONVEYOR;
			return;
		}
		
		if (status == Status.PUTKITONOUTCONVEYOR && guiKitRobot.arrived(currentTime))
		{
			guiKitDeliveryStation.outConveyor.addPallet(new GUIPallet(new Pallet(), guiKitRobot.removeKit(), 
					guiKitDeliveryStation.outConveyor.movement.getStartPos().x-50+60*guiKitDeliveryStation.outConveyor.getLaneLength(), guiKitDeliveryStation.outConveyor.movement.getStartPos().y-12));
			guiKitRobot.park();
			status = Status.IDLE;
			return;
		}
		
		
		if (guiKitDeliveryStation.outConveyor.hasFullPalletAtEnd(currentTime))
		{
			guiKitDeliveryStation.outConveyor.removeEndPallet();
			guiKitDeliveryStation.inConveyor.addPallet();
		}
	}
	
}














