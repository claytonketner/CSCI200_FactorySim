import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIPartRobot implements Serializable
{
	public PartRobot partRobot;
	public Movement movement;
	
	
	public GUIPartRobot(PartRobot partRobot, double x, double y)
	{
		this.partRobot = partRobot;
		this.movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIPartRobot(PartRobot partRobot, Movement movement)
	{
		this.partRobot = partRobot;
		this.movement = movement;
	}
	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.PART_ROBOT_HAND, currentTime, movement, true);
	}
}
