import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


public class GUIGantry implements Serializable
{
	public Movement movement;
	
	GUIBin guiBin;
	
	public GUIGantry(double x, double y)
	{
		this.movement = new Movement(new Point2D.Double(x, y), 0);
		guiBin = null;
	}
	
	public void addBin(GUIBin guiBin)
	{
		if (this.guiBin != null)
			System.err.println("Cannot add a bin to the gantry! It already has one!");
		
		this.guiBin = guiBin;
	}
	
	public GUIBin removeBin()
	{
		GUIBin tempBin = guiBin;
		guiBin = null;
		return tempBin;
	}
	
	public void draw(Graphics2D g, long currentTime)
	{
		if (guiBin != null)
		{
			guiBin.movement = movement;
			guiBin.draw(g, currentTime, true);
		}
		
		Painter.draw(g, Painter.ImageEnum.GANTRY_CRANE, 128, 128, currentTime, movement, true);
		Movement horizGantryMove = movement.offset(new Point2D.Double(1600/2 - movement.calcPos(currentTime).x, 0), 0);
		Movement vertGantryMove = movement.offset(new Point2D.Double(0, 800/2 - movement.calcPos(currentTime).y), 0);
		Painter.draw(g, Painter.ImageEnum.GANTRY_TRUSS_H, 2000, 168, currentTime, horizGantryMove, true);
		Painter.draw(g, Painter.ImageEnum.GANTRY_TRUSS_V, 168, 2000, currentTime, vertGantryMove, true);
	}
}
