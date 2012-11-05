import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

@SuppressWarnings("serial")
public class GUINest implements Serializable {
	public Nest nest;
	public Movement movement;
	
	public GUINest( Nest nest, double x, double y)
	{
		this.nest = nest;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.NEST, currentTime, movement, false);
	}
}
