import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.TreeMap;

import javax.swing.ImageIcon;


public class GUIKit 
{
	public Kit kit;
	public Movement movement;
	
	private String kitImagePath = "images/kit.png";
	private static ImageIcon image;
	
	public GUIKit(Kit kit, double x, double y)
	{
		this.kit = kit;
		movement = new Movement(new Point2D.Double(x,y), 0);
		
		// If the image hasn't been loaded for the first time, do so
		if (image == null)
		{
			try {
				image = new ImageIcon(kitImagePath);
			} catch (Exception e) {
				System.err.println("Loading the kit image failed!");
				e.printStackTrace();
			}
		}
	}

	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, image, currentTime, movement);
	}
}
