import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;


public class GUIPartRobot implements GUIItem, Serializable
{
	public PartRobot partRobot;
	/** non-moving base position of arm */
	private Point2D.Double basePos;
	/** (linear) movement of arm gripper */
	public Movement movement; 
	
	/*private Movement baseMove, armMove, handMove;
	public final int baseStartX = 350;
	public final int baseStartY = 340;*/
	
	
	public GUIPartRobot(PartRobot partRobot, Point2D.Double basePos)
	{
		this.partRobot = partRobot;
		this.basePos = basePos;
		movement = new Movement(new Point2D.Double(basePos.x, basePos.y + 180), 0);
		/*baseMove = new Movement(new Point2D.Double(baseStartX, baseStartY), Math.PI );
		armMove = new Movement(new Point2D.Double(0,0), Math.PI );
		handMove = new Movement(new Point2D.Double(0,0), Math.PI );*/
	}

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
	
	/*private void doCalculations(long currentTime)
	{
		Point2D.Double target = new Point2D.Double(movement.getStartPos().x - baseStartX, movement.getStartPos().y - baseStartY);
		
		double theta = Math.PI;
		if (target.x != 0 || target.y != 0)
			theta = Math.asin(target.x/(Math.sqrt(Math.pow(target.x, 2) + Math.pow(target.y, 2))));
		
		if (target.y > 0 && target.x < 0)
			theta -= Math.PI/2;
		
		armMove = Movement.fromAngularSpeed(baseMove.calcPos(currentTime), armMove.calcRot(currentTime), currentTime, baseMove.calcPos(currentTime), theta, Math.PI/4);
		handMove = armMove.offset(new Point2D.Double(180*Math.sin(armMove.calcRot(currentTime)), -180*Math.cos(armMove.calcRot(currentTime))), 0);
		for ( Map.Entry<Integer, GUIPart> part : partRobot.partsInGripper.entrySet() ) {
			part.getValue().movement = handMove;
		}
		
	}*/
	
	public void addPartToGripper ( Integer gripperNumber, GUIPart part ) {
		partRobot.partsInGripper.put( gripperNumber, part);
	}
	
	public GUIPart removePartFromGripper ( Integer gripperNumber ) {
		return partRobot.partsInGripper.remove( gripperNumber );
	}
	
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



























