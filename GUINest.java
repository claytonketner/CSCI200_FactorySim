import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

/** Contains data and methods for drawing and animating a nest */
public class GUINest implements GUIItem, Serializable {
	/** GUIParts in the nest (DEPRECATED) */
	public ArrayList<GUIPart> parts; // I had to add this to avoid breaking Anthony's work
	/** used to access nest data */
	public Nest nest;
	/** used to access movement data */
	public Movement movement;

	/** Initialization */
	public GUINest( Nest nest, double x, double y)
	{
		parts = new ArrayList<GUIPart>();
		this.nest = nest;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}

	/** draws the nest */
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.NEST, 80, -1, currentTime, movement, false);
		for ( GUIPart part : parts ) {
			part.draw( g, currentTime );
		}

	}

	/** add the part into a nest (DEPRECATED) */
	public void addPart( GUIPart part ) {
		parts.add( part );
	}

	/** remove the part from a nest (DEPRECATED) */
	public GUIPart removePart( int index ) {
		return parts.remove( index );
	}

	/** setter for movement */
	public void setMove(Movement movement)
	{
		this.movement = movement;
	}

	/** getter for movement */
	public Movement getMove() {
		return movement;
	}
	
	public void dumpNest() {
		nest.dumpNest();
		nest.flipSwitch();
	}
	
	public void raiseNest() {
		nest.flipSwitch();
	}
	
	public boolean getNestIsUp() {
		return nest.isNestUp();
	}
}
