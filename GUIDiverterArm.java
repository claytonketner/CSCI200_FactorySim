import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
/** Draws the diverter arm */
public class GUIDiverterArm implements GUIItem, Serializable {
	/** used to access the Movement class */
	public Movement movement;
	/** initialize variables */
	public GUIDiverterArm( double x, double y )
	{
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	/** draws the diverter arm */
	public void draw( Graphics2D g, long currentTime )
	{
		// Draw the diverter below the diverter arm
		(new GUIDiverter(movement.calcPos(currentTime).x+42, 
		                 movement.calcPos(currentTime).y)).draw(g, currentTime);
		// draw the diverter arm
		Painter.draw(g, Painter.ImageEnum.DIVERTER_ARM, -1, 140, currentTime, movement, true);
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
}
