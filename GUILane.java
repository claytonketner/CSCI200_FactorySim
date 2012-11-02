import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import javax.swing.ImageIcon;


public class GUILane implements Serializable
{
	public Lane lane;
	public Movement movement;
	
	private int laneLength;
		
	public GUILane(Lane lane, int laneLength, double x, double y)
	{
		this.lane = lane;
		this.laneLength = laneLength;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUILane(Lane lane, Movement movement)
	{
		this.lane = lane;
		this.movement = movement;
	}

	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.LANE, currentTime, movement);
	}
}
