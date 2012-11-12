import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public class GUIGantry 
{
	public Movement movement;
	
	GUIBin guiPartsBox;
	
	public GUIGantry(double x, double y)
	{
		this.movement = new Movement(new Point2D.Double(x, y), 0);
		guiPartsBox = null;
	}
	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.GANTRY_CRANE, 128, 128, currentTime, movement, true);
		Painter.draw(g, Painter.ImageEnum.GANTRY_TRUSS_MID, 705, 2000, currentTime, movement, true);
		Painter.draw(g, Painter.ImageEnum.GANTRY_TRUSS_MID, 705, 2000, currentTime, movement.offset(new Point2D.Double(0,0), Math.PI/2), true);
	}
}
