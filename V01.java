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
	
	int paintCount = 0;
	
	public V01()
	{
		this.setPreferredSize(new Dimension(400,600));
		
		Painter.loadImages();
		
		Part p = new Part("p1", "a random part", 5);
		gp = new GUIPart(p, Painter.ImageEnum.RAISIN, 100, 100);
		gp.movement = new Movement(gp.movement.calcPos(System.currentTimeMillis()), 0, System.currentTimeMillis(), new Point2D.Double(200,200), 10, System.currentTimeMillis()+5000);
		
		guiKitDeliveryStation = new GUIKitDeliveryStation(new KitDeliveryStation(), new GUILane(new Lane(), 3, 190,10), new GUILane(new Lane(), 3, 10, 10), 10, 10);
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		
		guiKitDeliveryStation.draw(g, System.currentTimeMillis());
		gp.draw(g, System.currentTimeMillis());
		
		if (paintCount % 100 == 0)
			if (guiKitDeliveryStation.inConveyor.lane.isLaneOn())
				guiKitDeliveryStation.inConveyor.lane.turnOff();
			else
				guiKitDeliveryStation.inConveyor.lane.turnOn();
		
		paintCount++;
	}
	
}
