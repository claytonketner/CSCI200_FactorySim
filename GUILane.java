import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

//Hey Clayton just so you know the lane class is meant to be one side of the lane
//this makes it easier to store the object without having to keep track of where it is
//the wholelane class will have 2 lane objects representing each side
//let me know if you want me to change something -Anthony
//Edit: I created a combo lane class and changed your Lane to ComboLane in GUILane and V01

@SuppressWarnings("serial")
public class GUILane implements Serializable
{
	public ComboLane lane;
	public Movement movement; // For storing lane position info
	
	boolean isForParts;
	private ArrayList<GUIPallet> pallets;
	private ArrayList<GUIPart> topParts, bottomParts;
	
	private int laneLength;
	private ArrayList<GUILaneSegment> guiLaneSegments;
		
	public GUILane(ComboLane lane, boolean isForParts, int laneLength, double x, double y)
	{
		this.lane = lane;
		
		this.isForParts = isForParts;
		if (isForParts)
		{
			topParts = new ArrayList<GUIPart>();
			bottomParts = new ArrayList<GUIPart>();
		} else pallets = new ArrayList<GUIPallet>();
		
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
		
		checkMotion(currentTime);
		
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
		Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 90, currentTime, movement, false);
		Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 90, currentTime, 
					 new Movement(new Point2D.Double(movement.getStartPos().x + 60*laneLength-10, movement.getEndPos().y), Math.PI), false);
		
		
		// Draw the pallets or parts
		if (isForParts)
		{
			for (GUIPart p : topParts)
				p.draw(g, currentTime);
			for (GUIPart p : bottomParts)
				p.draw(g, currentTime);
		} else {
			for (GUIPallet p : pallets)
				p.draw(g, currentTime);
		}
		
	}
	
	private void checkMotion(long currentTime)
	{
		if (lane.isLaneOn())
		{
			// Start the lane
			for (GUILaneSegment guiLaneSegment : guiLaneSegments)
				guiLaneSegment.movement.unPause(currentTime);
			
			if (isForParts)
			{
				for (GUIPart p : topParts)
					p.movement.unPause(currentTime);
				for (GUIPart p : bottomParts)
					p.movement.unPause(currentTime);
			} else {
				for (GUIPallet p : pallets)
					p.movement.unPause(currentTime);
			}
			
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
			
			if (isForParts)
			{
				for (GUIPart p : topParts)
					p.movement.pause(currentTime);
				for (GUIPart p : bottomParts)
					p.movement.pause(currentTime);
			} else {
				for (GUIPallet p : pallets)
					p.movement.pause(currentTime);
			}
		}
	}
	
	public void addPallet()
	{
		GUIPallet pallet = new GUIPallet(new Pallet(), new GUIKit(new Kit(), 0,0), movement.getStartPos().x+60*laneLength, movement.getStartPos().y+46);
		pallets.add(pallet);
		pallet.movement = Movement.fromSpeed(pallet.movement.getStartPos(), Math.PI/2, System.currentTimeMillis(), new Point2D.Double((movement.getStartPos().x+60), pallet.movement.getStartPos().y), Math.PI/2, lane.getSpeed());
	}
	
	public GUIPallet removePallet(GUIPallet pallet)
	{
		return pallets.remove(pallets.indexOf(pallet));
	}
}




























