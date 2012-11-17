import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIKitRobot implements Serializable
{
	public KitRobot kitRobot;
	/** non-moving base position of arm */
	private Point2D.Double basePos;
	/** (linear) movement of arm gripper */
	public Movement movement;
	
	private GUIKit kit;
	private Movement baseMove, armMove, handMove;
	//private final int baseStartX = 350;
	//private final int baseStartY = 250;	
	
	public GUIKitRobot(KitRobot kitRobot, Point2D.Double basePos)
	{
		this.kitRobot = kitRobot;
		this.basePos = basePos;
		kit = null;
		movement = new Movement(new Point2D.Double(basePos.x, basePos.y - 180), 0);
		//movement = new Movement(new Point2D.Double(baseStartX, baseStartY), 0);
		//baseMove = new Movement(new Point2D.Double(baseStartX, baseStartY), 0);
		armMove = new Movement(new Point2D.Double(0,0), 0);
		handMove = new Movement(new Point2D.Double(0,0), 0);
	}

	public void draw(Graphics2D g, long currentTime)
	{
		doCalculations(currentTime);
		
		// all images rotated about center, last parameter says whether movement pos is center or corner of image (true means center)
		Painter.draw(g, Painter.ImageEnum.ROBOT_RAIL, 250, -1, currentTime, new Movement(basePos, 0), true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_BASE, 75, -1, currentTime, new Movement(basePos, 0), true);
		Painter.draw(g, Painter.ImageEnum.KIT_ROBOT_HAND, 60, -1, currentTime, handMove, true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_ARM_1, 400, -1, currentTime, armMove, true);
		
		if (kit != null)
			kit.draw(g, currentTime);
	}
	
	private void doCalculations(long currentTime)
	{
		Point2D.Double handPos = movement.calcPos(currentTime);
		Point2D.Double target = new Point2D.Double(handPos.x - basePos.x, handPos.y - basePos.y);
		double distToHand = Math.sqrt(Math.pow(target.x, 2) + Math.pow(target.y, 2));
		
		double theta = 0;
		if (target.x != 0 || target.y != 0)
			theta = Math.atan2(target.y, target.x);
		
		//armMove = Movement.fromAngularSpeed(baseMove.calcPos(currentTime), armMove.calcRot(currentTime), currentTime, baseMove.calcPos(currentTime), theta, Math.PI/4);
		armMove = new Movement(basePos, theta + Math.PI / 2);
		//handMove = armMove.offset(new Point2D.Double(180*Math.sin(armMove.calcRot(currentTime)), -180*Math.cos(armMove.calcRot(currentTime))), 0);
		handMove = new Movement(handPos, theta + Math.PI / 2);
		
		if (kit != null)
		{
			kit.movement = handMove;
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
		//this.movement = new Movement(new Point2D.Double(baseStartX, baseStartY), 0);
		movement = movement.moveToAtSpeed(currentTime, new Point2D.Double(basePos.x, basePos.y - 180), 0, 200);
	}

	public Point2D.Double getBasePos() {
		return basePos;
	}
}



























