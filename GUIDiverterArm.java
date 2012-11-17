import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class GUIDiverterArm implements GUIItem, Serializable {
	public Movement movement;
	
	public GUIDiverterArm( double x, double y )
	{
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public void draw( Graphics2D g, long currentTime ){
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
