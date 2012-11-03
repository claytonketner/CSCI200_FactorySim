import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class KitRobot implements Serializable {
	Kit kit;
	Point2D.Double position;
	Movement movement;
	
	public KitRobot() {
		kit = new Kit();
		position = new Point2D.Double( 300, 300 ); //these values are random
		movement = new Movement( position, 0 );
	}
}
