import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class V01 extends JPanel
{
	GUIKitDeliveryStation guiKitDeliveryStation;
	GUIKitRobot guiKitRobot;
	GUIKitStand guiKitStand;
	
	int paintCount = 0;
	
	public V01()
	{
		this.setPreferredSize(new Dimension(800,600));
		
		Painter.loadImages();
		
		guiKitDeliveryStation = new GUIKitDeliveryStation(new KitDeliveryStation(), new GUILane(new ComboLane(), false, 8, 300,10), new GUILane(new ComboLane(), false, 3, 300-180, 10), 10, 10);
		guiKitRobot = new GUIKitRobot(new KitRobot());
		guiKitStand = new GUIKitStand(new KitStand());
		
		GUIKit kit = new GUIKit(new Kit(), 300+180, 0);
		
		guiKitDeliveryStation.inConveyor.addPallet();
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		long currentTime = System.currentTimeMillis();
		
		guiKitDeliveryStation.draw(g, currentTime);
		guiKitStand.draw(g, currentTime);
		guiKitRobot.draw(g, currentTime);

		
		if (paintCount % 100 == 0)
			if (guiKitDeliveryStation.inConveyor.lane.isLaneOn())
				guiKitDeliveryStation.inConveyor.lane.turnOff();
			 else
				guiKitDeliveryStation.inConveyor.lane.turnOn();
		
		paintCount++;
	}
	
}
