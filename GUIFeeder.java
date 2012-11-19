import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

@SuppressWarnings("serial")
/** Contains data and methods for drawing and animating a feeder */
public class GUIFeeder implements GUIItem, Serializable {
	/** used to access feeder data */
	public Feeder feeder;
	/** used to access movement data */
	public Movement movement;
	/** Initialization */
	public GUIFeeder(Feeder feeder, double x, double y)
	{
		this.feeder = feeder;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	/** draws the feeder */
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.FEEDER, currentTime, movement, true);
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
	
	/** returns if the diverter is set to feed parts to top lane*/
	public boolean getDiverterTop() {
		return feeder.getDiverterTop();
	}
	
	/** sets which lane parts should be fed to */
	public void setDiverterTop( boolean topLane ) {
		feeder.setDiverterTop( topLane );
	}
	
	public boolean getPartsLow() {
		return feeder.checkIfLow();
	}
}
