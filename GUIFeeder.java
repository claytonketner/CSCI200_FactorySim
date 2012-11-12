import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

@SuppressWarnings("serial")
public class GUIFeeder implements Serializable {
	public Feeder feeder;
	public Movement movement;
	
	public GUIFeeder(Feeder feeder, double x, double y)
	{
		this.feeder = feeder;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.FEEDER, currentTime, movement, true);
	}
}
