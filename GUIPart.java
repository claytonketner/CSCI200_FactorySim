import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIPart implements Serializable 
{
	public Part part;
	public Movement movement;
	
	private Painter.ImageEnum partType;
	
	
	public GUIPart(Part part, Painter.ImageEnum partType, double x, double y )
	{
		this.part = part;
		part.linkWithPart(this); //allows for easy access from the Part class
		this.partType = partType;  
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIPart(Part part, Painter.ImageEnum partType, double x, double y, double rotation)
	{
		this.part = part;
		this.partType = partType;  
		movement = new Movement(new Point2D.Double(x,y), rotation);
	}
	
	public GUIPart(Part part, Painter.ImageEnum partType, Movement movement)
	{
		this.part = part;
		this.partType = partType;
		this.movement = movement;
	}

	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, partType, -1, 25, currentTime, movement, true);
	}
}
