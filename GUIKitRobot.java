import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIKitRobot implements Serializable
{
	public KitRobot kitRobot;
	/** Set the startPos of this Movement to the robot's end point destination */
	public Movement movement; // The kit robot doesn't use this for movement - only to access its goal and desired end time because of the complex calculations required
	
	private GUIKit kit;
	private Movement baseMove, armMove, handMove;
	private final int baseStartX = 350;
	private final int baseStartY = 250;
	
	
	public GUIKitRobot(KitRobot kitRobot)
	{
		this.kitRobot = kitRobot;
		kit = null;
		movement = new Movement(new Point2D.Double(baseStartX, baseStartY), 0);
		baseMove = new Movement(new Point2D.Double(baseStartX, baseStartY), 0);
		armMove = new Movement(new Point2D.Double(0,0), 0);
		handMove = new Movement(new Point2D.Double(0,0), 0);
	}

	public void draw(Graphics2D g, long currentTime)
	{
		doCalculations(currentTime);
		
		Painter.draw(g, Painter.ImageEnum.ROBOT_RAIL, 250, -1, currentTime, new Movement(new Point2D.Double(baseStartX, baseStartY), 0), true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_BASE, 75, -1, currentTime, baseMove, true);
		Painter.draw(g, Painter.ImageEnum.KIT_ROBOT_HAND, 60, -1, currentTime, handMove, true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_ARM_1, 400, -1, currentTime, armMove, true);
		
		if (kit != null)
			kit.draw(g, currentTime);
	}
	
	private void doCalculations(long currentTime)
	{
		Point2D.Double target = new Point2D.Double(movement.getStartPos().x - baseStartX, movement.getStartPos().y - baseStartY);
		
		double theta = 0;
		if (target.x != 0 || target.y != 0)
			theta = Math.asin(target.x/(Math.sqrt(Math.pow(target.x, 2) + Math.pow(target.y, 2))));
		
		if (target.y > 0 && target.x < 0)
			theta -= Math.PI/2;
		
		armMove = Movement.fromAngularSpeed(baseMove.calcPos(currentTime), armMove.calcRot(currentTime), currentTime, baseMove.calcPos(currentTime), theta, Math.PI/4);
		handMove = armMove.offset(new Point2D.Double(180*Math.sin(armMove.calcRot(currentTime)), -180*Math.cos(armMove.calcRot(currentTime))), 0);
		
		if (kit != null)
		{
			kit.movement = handMove;
		}
		
	}
	
	public boolean arrived(long currentTime)
	{
		return (armMove.arrived(currentTime) && baseMove.arrived(currentTime) && handMove.arrived(currentTime));
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
	
	public void park()
	{
		this.movement = new Movement(new Point2D.Double(baseStartX, baseStartY), 0);
	}
}



























