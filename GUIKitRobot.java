import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIKitRobot implements Serializable
{
	public KitRobot kitRobot;
	/** Set the startPos of this Movement to the robot's end point destination */
	public Movement movement; // The kit robot doesn't use this for movement - only to access its goal and desired end time because of the complex calculations required
	
	private Movement baseMove, armMove, handMove;
	
	
	public GUIKitRobot(KitRobot kitRobot)
	{
		this.kitRobot = kitRobot;
		movement = new Movement(new Point2D.Double(300,0), 0);
		baseMove = new Movement(new Point2D.Double(300,300), 0);
		armMove = new Movement(new Point2D.Double(300,300), 0);
		handMove = new Movement(new Point2D.Double(300,300), 0);
	}

	public void draw(Graphics2D g, long currentTime)
	{
		doCalculations(currentTime);
		
		Painter.draw(g, Painter.ImageEnum.ROBOT_BASE, 75, -1, currentTime, baseMove, true);
		Painter.draw(g, Painter.ImageEnum.KIT_ROBOT_HAND, 60, -1, currentTime, handMove, true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_ARM_1, 75, -1, currentTime, armMove, true);
	}
	
	private void doCalculations(long currentTime)
	{
		Point2D.Double target = movement.getStartPos();
		
		armMove.slaveTranslation(baseMove, 0, 0, currentTime);
		handMove.slaveTranslation(armMove, 180*Math.sin(armMove.calcRot(currentTime)), -180*Math.cos(armMove.calcRot(currentTime)), currentTime);
	}
}
