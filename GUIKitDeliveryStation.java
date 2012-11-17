import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GUIKitDeliveryStation implements GUIItem, Serializable
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
		checkStatus(currentTime);
		
		inConveyor.draw(g, currentTime);
		outConveyor.draw(g, currentTime);
	}
	
	private void checkStatus(long currentTime)
	{
		if (inConveyor.hasEmptyPalletAtEnd(currentTime))
		{
			inConveyor.removeEndPallet();
			inConveyor.lane.turnOn();
		}
		if (inConveyor.hasFullPalletAtEnd(currentTime))
		{
			inConveyor.lane.turnOff();
		}
	}
	
	public Point2D.Double getOutConveyorLocation()
	{
		return new Point2D.Double(outConveyor.movement.getStartPos().x + outConveyor.getLaneLength()*60 - 50, outConveyor.movement.getStartPos().y + 60);
	}

	/** setter for movement */
	public void setMove(Movement movement)
	{
		this.movement = movement;
	}

	/** getter for movement */
	public Movement getMove() {
		return movement;
	}
}
