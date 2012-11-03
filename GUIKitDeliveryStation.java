import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUIKitDeliveryStation implements Serializable
{
	public KitDeliveryStation kitDeliveryStation;
	public Movement movement;
	public GUILane inConveyor, outConveyor;
	
	private ArrayList<GUIPallet> guiPallets;
	
	
	public GUIKitDeliveryStation(KitDeliveryStation kitDeliveryStation, GUILane inConveyor, GUILane outConveyor, double x, double y)
	{
		this.kitDeliveryStation = kitDeliveryStation;
		this.inConveyor = inConveyor;
		this.outConveyor = outConveyor;
		this.movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIKitDeliveryStation(KitDeliveryStation kitDeliveryStation, GUILane inConveyor, GUILane outConveyor, Movement movement)
	{
		this.kitDeliveryStation = kitDeliveryStation;
		this.inConveyor = inConveyor;
		this.outConveyor = outConveyor;
		this.movement = movement;
	}

	public void draw(Graphics2D g, long currentTime)
	{	
		inConveyor.draw(g, currentTime);
		outConveyor.draw(g, currentTime);

	}
}