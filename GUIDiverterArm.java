import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

@SuppressWarnings("serial")
public class GUIDiverterArm implements Serializable {
	public Movement movement;
	
	public GUIDiverterArm( double x, double y )
	{
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.DIVERTER_ARM, currentTime, movement, true);
	}
}
