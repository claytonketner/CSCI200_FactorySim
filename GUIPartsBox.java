import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

@SuppressWarnings("serial")
public class GUIPartsBox implements Serializable {
	public Movement movement;
	public GUIPart part;
	
	public GUIPartsBox( GUIPart gp, double x, double y )
	{
		part = gp;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.PARTS_BOX, currentTime, movement, false);
	}
}