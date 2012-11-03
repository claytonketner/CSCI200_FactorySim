import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class GUILane implements Serializable
{
	public Lane lane;
	public Movement movement;
	
	private int laneLength;
	private ArrayList<GUILaneSegment> guiLaneSegments;
		
	public GUILane(Lane lane, int laneLength, double x, double y)
	{
		this.lane = lane;
		this.laneLength = laneLength;
		guiLaneSegments = new ArrayList<GUILaneSegment>();
		movement = new Movement(new Point2D.Double(x,y), 0);
		
		// Create the lane segments
		for (int i=0; i<laneLength+1; i++)
			guiLaneSegments.add(new GUILaneSegment(new Movement(new Point2D.Double(movement.getStartPos().x + 60*(laneLength-1) - 60*(i-1), movement.getStartPos().y), 0, -1,
								new Point2D.Double(movement.getStartPos().x + 60*(laneLength-1) - 60*(i), movement.getStartPos().y), 0, 0)));
	}

	public void draw(Graphics2D g, long currentTime)
	{
		BufferedImage i_temp = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g_temp = i_temp.createGraphics();
		
		if (lane.isLaneOn())
		{
			// Start the lane
			for (GUILaneSegment guiLaneSegment : guiLaneSegments)
				guiLaneSegment.movement.unPause(currentTime);
			
			if (guiLaneSegments.get(0).movement.arrived(currentTime))
			{
				// Set each lane segment back to their start position to keep lane animating
				for (GUILaneSegment guiLaneSegment : guiLaneSegments)
				{
					guiLaneSegment.movement = Movement.fromSpeed(guiLaneSegment.movement.getStartPos(), 0, currentTime, guiLaneSegment.movement.getEndPos(), 0, lane.getSpeed());
				}
			}
		} else {
			// Stop the lane
			for (GUILaneSegment guiLaneSegment : guiLaneSegments)
			{
				guiLaneSegment.movement.pause(currentTime);
			}
		}
		
		// Draw to the bufferedImage
		for (GUILaneSegment guiLaneSegment : guiLaneSegments)
		{
			guiLaneSegment.draw(g_temp, currentTime);
		}
		
		// Crop the bufferedImage
		BufferedImage croppedBI = i_temp.getSubimage((int)movement.getStartPos().x, (int)movement.getStartPos().y, laneLength*60, 200);
		// Draw it to the original graphics object
		g.drawImage(croppedBI, (int)movement.getStartPos().x, (int)movement.getStartPos().y, null);
		
		// Draw the shadows at the ends
		Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 90, currentTime, movement);
		Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 90, currentTime, new Movement(new Point2D.Double(movement.getStartPos().x + 60*laneLength-10, movement.getEndPos().y), Math.PI));
		
	}
}
