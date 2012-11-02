import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class GUIPart implements Serializable 
{
	public Part part;
	public Movement movement;
	
	private Painter.ImageEnum partType;
	
	
	public GUIPart(Part part, Painter.ImageEnum partType, double x, double y)
	{
		this.part = part;
		this.partType = partType;  
		movement = new Movement(new Point2D.Double(x,y), 0);

	}

	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, partType, -1, 25, currentTime, movement);
	}
}
