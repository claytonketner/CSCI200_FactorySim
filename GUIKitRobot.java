import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIKitRobot implements Serializable
{
	public KitRobot kitRobot;
	public Movement movement; // The kit robot doesn't use this for movement - only to access its goal and desired end time because of the complex calculations required
	
	
	public GUIKitRobot(KitRobot kitRobot, double x, double y)
	{
		this.kitRobot = kitRobot;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIKitRobot(KitRobot kitRobot, Movement movement)
	{
		this.kitRobot = kitRobot;
		this.movement = movement;
	}

	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.KIT_ROBOT_HAND, currentTime, movement);
	}
}
