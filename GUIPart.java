import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class GUIPart 
{
	public Part part;
	public Movement movement;
	
	// Image constants
	public static final int RAISIN = 0;
	
	private static ArrayList<ImageIcon> allPartImages; // Not sure where this should be, but I guess it can stay here for V0
	private ImageIcon image;
	
	
	public GUIPart(Part part, int imageID, double x, double y)
	{
		this.part = part;
		movement = new Movement(new Point2D.Double(x,y), 0);
		
		if (allPartImages == null)
		{
			// Images need to be loaded
			allPartImages = new ArrayList<ImageIcon>();
			allPartImages.add(RAISIN, new ImageIcon("images/parts/raisin.png"));
		}
		
		try {
			image = allPartImages.get(imageID);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Part image doesn't exist!");
			e.printStackTrace();
		}
	}

	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, image, -1, 25, currentTime, movement);
	}
}