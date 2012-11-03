import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class PartRobot implements Serializable {
	ArrayList<Part> partsInGripper;
	Point2D.Double position;
	Movement movement;
	
	public PartRobot() {
		partsInGripper = new ArrayList<Part>();
		position = new Point2D.Double( 300, 300 ); //these values are random
		movement = new Movement( position, 0 );
	}
}
