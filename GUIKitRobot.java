import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIKitRobot implements GUIItem, Serializable
{
	public KitRobot kitRobot;
	/** non-moving base position of arm */
	private Point2D.Double basePos;
	/** (linear) movement of arm gripper */
	public Movement movement;
	
	private GUIKit kit;
	
	public GUIKitRobot(KitRobot kitRobot, Point2D.Double basePos)
	{
		this.kitRobot = kitRobot;
		this.basePos = basePos;
		kit = null;
		movement = new Movement(new Point2D.Double(basePos.x, basePos.y - 180), 0);
	}

	public void draw(Graphics2D g, long currentTime)
	{
		// do position calculations
		Point2D.Double handPos = movement.calcPos(currentTime);
		double theta = Math.atan2(handPos.y - basePos.y, handPos.x - basePos.x);
		Movement armMove = new Movement(basePos, theta + Math.PI / 2);
		Movement handMove = new Movement(handPos, theta + Math.PI / 2);
		
		// draw images
		// all images rotated about center, last parameter to draw() says whether movement pos is center or corner of image (true means center)
		Painter.draw(g, Painter.ImageEnum.ROBOT_RAIL, 250, -1, currentTime, new Movement(basePos, 0), true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_BASE, 75, -1, currentTime, new Movement(basePos, 0), true);
		Painter.draw(g, Painter.ImageEnum.KIT_ROBOT_HAND, 60, -1, currentTime, handMove, true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_ARM_1, 400, -1, currentTime, armMove, true);
		
		if (kit != null)
		{
			kit.movement = handMove;
			kit.draw(g, currentTime);
		}
	}
	
	public boolean arrived(long currentTime)
	{
		return (movement.arrived(currentTime));
	}
	
	public void setKit(GUIKit kit)
	{
		if (this.kit != null)
		{
			System.err.println("KitRobot already has a kit!");
			return;
		}
		
		this.kit = kit;
	}
	
	public GUIKit removeKit()
	{
		GUIKit temp = kit;
		kit = null;
		return temp;
	}
	
	public void park(long currentTime)
	{
		movement = movement.moveToAtSpeed(currentTime, new Point2D.Double(basePos.x, basePos.y - 180), 0, 200);
	}

	/** getter for basePos */
	public Point2D.Double getBasePos() {
		return basePos;
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



























