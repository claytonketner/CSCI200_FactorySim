import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.ImageIcon;


public class GUIPart 
{
	public Part part;
	public Movement movement;
	
	private TreeMap<String, ImageIcon> allPartImages; // Not sure where this should be, but I guess it can stay here for V0
	private ImageIcon image;
	
	public GUIPart(Part part, double x, double y)
	{
		this.part = part;
		image = allPartImages.get(part.getName());
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public void draw(Graphics g, long currentTime)
	{
		g.drawImage(image.getImage(), (int)(movement.calcPos(currentTime).x), (int)(movement.calcPos(currentTime).y), null);
	}
}
