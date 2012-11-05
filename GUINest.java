import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GUINest implements Serializable {
	public ArrayList<GUIPart> parts; // I had to add this to avoid breaking Anthony's work
	public Nest nest;
	public Movement movement;
	
	public GUINest( Nest nest, double x, double y)
	{
		parts = new ArrayList<GUIPart>();
		this.nest = nest;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.NEST, currentTime, movement, false);
		for ( GUIPart part : parts ) {
			part.draw( g, currentTime );
		}

	}
	
	public void addPart( GUIPart part ) {
		parts.add( part );
	}
	
	public GUIPart removePart( int index ) {
		return parts.remove( index );
	}
}
