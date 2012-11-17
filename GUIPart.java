import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIPart implements Serializable 
{
	public Part part;
	public Movement movement;
	
	
	public GUIPart(Part part, double x, double y )
	{
		this.part = part;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIPart(Part part, double x, double y, double rotation)
	{
		this.part = part;
		movement = new Movement(new Point2D.Double(x,y), rotation);
	}
	
	public GUIPart(Part part, Movement movement)
	{
		this.part = part;
		this.movement = movement;
	}

	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, part.getImage(), -1, 25, currentTime, movement, true);
	}
}
