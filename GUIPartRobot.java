import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;


public class GUIPartRobot implements GUIItem, Serializable
{
	/** speed of part robot in pixels per second */
	public static final double SPEED = 100;
	/** length of robot arm */
	public static final double ARM_LENGTH = 180;

	/** reference to part robot instance */
	public PartRobot partRobot;
	/** non-moving base position of arm */
	private Point2D.Double basePos;
	/** (linear) movement of arm gripper */
	public Movement movement;
	
	/** constructor for GUIPartRobot */
	public GUIPartRobot(PartRobot partRobot, Point2D.Double basePos)
	{
		this.partRobot = partRobot;
		this.basePos = basePos;
		movement = new Movement(new Point2D.Double(basePos.x, basePos.y + ARM_LENGTH), 0);
	}

	/** draw part robot at specified time */
	public void draw(Graphics2D g, long currentTime)
	{
		// do position calculations
		Point2D.Double handPos = movement.calcPos(currentTime);
		double theta = Math.atan2(handPos.y - basePos.y, handPos.x - basePos.x);
		Movement armMove = new Movement(basePos, theta + Math.PI / 2);
		Movement handMove = new Movement(handPos, theta + Math.PI / 2);
		
		Painter.draw(g, Painter.ImageEnum.ROBOT_RAIL, 250, -1, currentTime, new Movement(basePos, 0), true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_BASE, 75, -1, currentTime, new Movement(basePos, 0), true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_ARM_1, 400, -1, currentTime, armMove, true);
		Painter.draw(g, Painter.ImageEnum.PART_ROBOT_HAND, 150, -1, currentTime, handMove, true);
		for ( Map.Entry<Integer, GUIPart> part : partRobot.partsInGripper.entrySet() ) {
			part.getValue().movement = new Movement(new Point2D.Double(handPos.x + (part.getKey() - 1.5) * 140 * 150 / 510 * Math.sin(theta) + 150 * 150 / 510 * Math.cos(theta), handPos.y - (part.getKey() - 1.5) * 140 * 150 / 510 * Math.cos(theta) + 150 * 150 / 510 * Math.sin(theta)), handMove.getStartRot());
			part.getValue().draw( g, currentTime );
		}
		
	}
	
	/** add specified part to specified gripper */
	public void addPartToGripper ( Integer gripperNumber, GUIPart part ) {
		partRobot.partsInGripper.put( gripperNumber, part);
	}
	
	/** remove part from specified gripper, return removed part */
	public GUIPart removePartFromGripper ( Integer gripperNumber ) {
		return partRobot.partsInGripper.remove( gripperNumber );
	}
	
	/** returns whether arrived at goal position */
	public boolean arrived(long currentTime)
	{
		return movement.arrived(currentTime);
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



























