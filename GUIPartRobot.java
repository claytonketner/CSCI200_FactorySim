import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;


@SuppressWarnings("serial")
public class GUIPartRobot implements Serializable
{
	public PartRobot partRobot;
	/** Set the startPos of this Movement to the robot's end point destination */
	public Movement movement; 
	
	private Movement baseMove, armMove, handMove;
	public final int baseStartX = 350;
	public final int baseStartY = 340;
	
	
	public GUIPartRobot(PartRobot partRobot)
	{
		this.partRobot = partRobot;
		movement = new Movement(new Point2D.Double(baseStartX, baseStartY), Math.PI );
		baseMove = new Movement(new Point2D.Double(baseStartX, baseStartY), Math.PI );
		armMove = new Movement(new Point2D.Double(0,0), Math.PI );
		handMove = new Movement(new Point2D.Double(0,0), Math.PI );
	}

	public void draw(Graphics2D g, long currentTime)
	{
		doCalculations(currentTime);
		
		Painter.draw(g, Painter.ImageEnum.ROBOT_RAIL, 250, -1, currentTime, new Movement(new Point2D.Double(baseStartX, baseStartY), 0), true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_BASE, 75, -1, currentTime, baseMove, true);
		Painter.draw(g, Painter.ImageEnum.ROBOT_ARM_1, 400, -1, currentTime, armMove, true);
		Painter.draw(g, Painter.ImageEnum.PART_ROBOT_HAND, 150, -1, currentTime, handMove, true);
		for ( Map.Entry<Integer, GUIPart> part : partRobot.partsInGripper.entrySet() ) {
			part.getValue().draw( g, currentTime );
		}
		
	}
	
	private void doCalculations(long currentTime)
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
		
	}
	
	public void addPartToGripper ( Integer gripperNumber, GUIPart part ) {
		partRobot.partsInGripper.put( gripperNumber, part);
	}
	
	public GUIPart removePartFromGripper ( Integer gripperNumber ) {
		return partRobot.partsInGripper.remove( gripperNumber );
	}
	
	public boolean arrived(long currentTime)
	{
		return (armMove.arrived(currentTime) && baseMove.arrived(currentTime) && handMove.arrived(currentTime));
	}
	
	
	public void park()
	{
		this.movement = new Movement(new Point2D.Double(baseStartX, baseStartY), 0);
	}
}



























