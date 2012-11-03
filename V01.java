import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class V01 extends JPanel
{
	GUIPart gp;
	GUIKitDeliveryStation guiKitDeliveryStation;
	GUIKitRobot guiKitRobot;
	GUIKitStand guiKitStand;
	
	int paintCount = 0;
	
	public V01()
	{
		this.setPreferredSize(new Dimension(500,600));
		
		Painter.loadImages();
		
		Part p = new Part("p1", "a random part", 5);
		gp = new GUIPart(p, Painter.ImageEnum.RAISIN, 0, 0);
		gp.movement = new Movement(gp.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(200,200), 10, System.currentTimeMillis()+5000);
		
		guiKitDeliveryStation = new GUIKitDeliveryStation(new KitDeliveryStation(), new GUILane(new ComboLane(), 3, 190,10), new GUILane(new ComboLane(), 3, 10, 10), 10, 10);
		
		guiKitRobot = new GUIKitRobot(new KitRobot());
		guiKitStand = new GUIKitStand(new KitStand());
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		long currentTime = System.currentTimeMillis();
		
		guiKitDeliveryStation.draw(g, currentTime);
		gp.draw(g, currentTime);
		guiKitRobot.draw(g, currentTime);
		guiKitStand.draw(g, currentTime);
		
		if (paintCount % 100 == 0)
			if (guiKitDeliveryStation.inConveyor.lane.isLaneOn())
				guiKitDeliveryStation.inConveyor.lane.turnOff();
			else
				guiKitDeliveryStation.inConveyor.lane.turnOn();
		
		paintCount++;
	}
	
}
