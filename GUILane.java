import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GUILane implements GUIItem, Serializable
{
	/** width (in pixels) of one segment */
	public static final int SEG_WIDTH = 60;
	/** instance of Lane */
	private Lane lane;
	/** location of top left corner of lane */
	private Point2D.Double pos;
	/** instance of Movement that controls the movement of the lane */
	public Movement movement;
	/** if true, draw parts, otherwise draw pallets */
	boolean isForParts;
	/** stores double values of pallet location */
	private ArrayList<java.lang.Double> palletOffsets;
	/** stores double values of top part location */
	private ArrayList<java.lang.Double> topPartOffsets;
	/** stores double values of bot part location */
	private ArrayList<java.lang.Double> bottomPartOffsets;
	
	/** number of lane segments */
	private int nSegments;
	/** used to determine pallet location */
	private final int conveyorEndPadding = 30;
	/** Initialization */
	public GUILane(Lane lane, boolean isForParts, int nSegments, double x, double y)
	{
		this.lane = lane;
		
		this.isForParts = isForParts;
		
		this.nSegments = nSegments;
		pos = new Point2D.Double(x, y);
		movement = new Movement(new Point2D.Double(), 0);
		palletOffsets = new ArrayList<java.lang.Double>();
		topPartOffsets = new ArrayList<java.lang.Double>();
		bottomPartOffsets = new ArrayList<java.lang.Double>();
	}
	/** using movement to dertermine the locations of stuffs and Painter to draw in lane*/
	public void draw(Graphics2D g, long currentTime)
	{
		int i;
		BufferedImage i_temp = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g_temp = i_temp.createGraphics();
		
		// Draw to the bufferedImage
		for (i = 0; i < nSegments + 2; i++) // draw more segments than lane width because partial segments will be drawn at ends
		{
			Painter.draw(g_temp, Painter.ImageEnum.LANE, SEG_WIDTH + 1, -1, currentTime,
			             movement.offset(new Point2D.Double(pos.x + SEG_WIDTH*((nSegments-1) - (i-1)), pos.y), 0), false);
		}
		
		// Crop the bufferedImage
		BufferedImage croppedBI = i_temp.getSubimage(Math.max((int)pos.x, 0), Math.max((int)pos.y, 0), getLength(), 200);
		// Draw it to the original graphics object
		g.drawImage(croppedBI, (int)pos.x, (int)pos.y, null);
		
		// Draw the shadows at the ends
		if (pos.y < 0)
		{
			Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 82, currentTime, new Movement(pos, 0), false);
			Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 82, currentTime, 
					 new Movement(new Point2D.Double(pos.x + getLength()-10, pos.y), Math.PI), false);
		} else {
			Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 91, currentTime, new Movement(pos, 0), false);
			Painter.draw(g, Painter.ImageEnum.SHADOW2, 10, 91, currentTime, 
						 new Movement(new Point2D.Double(pos.x + getLength()-10, pos.y), Math.PI), false);
		}
		
		
		// Draw the pallets and/or parts
		for (i = 0; i < lane.getPallets().size(); i++)
		{
			new GUIPallet(lane.getPallets().get(i), new Movement(getPalletLocation(i, currentTime), Math.PI / 2)).draw(g, currentTime);
		}
		// TODO: draw parts
		/*if (isForParts)
		{
			for (GUIPart p : topParts)
				p.draw(g, currentTime);
			for (GUIPart p : bottomParts)
				p.draw(g, currentTime);
		} else {
			for (GUIPallet p : pallets)
				p.draw(g, currentTime);
		}*/
		
	}

	public boolean isLaneOn()
	{
		return lane.isLaneOn();
	}
	/** turn off the lane and stop the current time */
	public void turnOff(long currentTime)
	{
		lane.turnOff();
		movement = movement.freeze(currentTime); // stop the lane
	}
	/** start the lane */
	public void turnOn(long currentTime)
	{
		lane.turnOn();
		movement = movement.moveToAtSpeed(currentTime, new Point2D.Double(-SEG_WIDTH, 0), 0, lane.getSpeed()); // start the lane
	}

	/** whether should move all segments back 1 segment width */
	public boolean shouldReset(long currentTime) {
		return lane.isLaneOn() && movement.arrived(currentTime + Server.UPDATE_RATE);
	}

	/** move lane segments back 1 segment width (must call this before lane moves a segment width) */
	public void reset(long currentTime)
	{
		for (int i = 0; i < palletOffsets.size(); i++)
		{
			palletOffsets.set(i, palletOffsets.get(i) - SEG_WIDTH);
		}
		for (int i = 0; i < topPartOffsets.size(); i++)
		{
			topPartOffsets.set(i, topPartOffsets.get(i) - SEG_WIDTH);
		}
		for (int i = 0; i < bottomPartOffsets.size(); i++)
		{
			bottomPartOffsets.set(i, bottomPartOffsets.get(i) - SEG_WIDTH);
		}
		movement = movement.offset(new Point2D.Double(SEG_WIDTH, 0), 0)
		                   .moveToAtSpeed(currentTime, new Point2D.Double(-SEG_WIDTH, 0), 0, lane.getSpeed());
	}
	/** calls another method with the same name  */
	public void addPallet()
	{
		addPallet(new GUIPallet(new Pallet(new Kit()), pos.x-50+getLength(), pos.y-12), 0);
	}
	/** Overwrite the above method with given GUIPallet and current time */
	public void addPallet(GUIPallet p, long currentTime)
	{
		// find index to insert pallet at
		double x = p.movement.calcPos(currentTime).x;
		int i;
		for (i = 0; i < palletOffsets.size(); i++)
		{
			if (x < pos.x + palletOffsets.get(i)) break;
		}
		// insert pallet
		lane.addPallet(i, p.pallet);
		palletOffsets.add(i, p.movement.calcPos(currentTime).x - pos.x);
	}
	/** remove the end pallet and move everything down one space */
	public GUIPallet removeEndPallet(long currentTime)
	{
		Movement tempMove = new Movement(getPalletLocation(0, currentTime), Math.PI / 2);
		palletOffsets.remove(0);
		return new GUIPallet(lane.removePallet(0), tempMove);
	}
	/** remove kit from the end pallet */
	public Kit removeEndPalletKit()
	{
		return lane.getPallets().get(0).removeKit();
	}
	/** return true if last pallet is empty */
	public boolean hasEmptyPalletAtEnd(long currentTime)
	{
		return palletAtEnd(0, currentTime) && !lane.getPallets().get(0).hasKit();
	}
	/** return true if last pallet is full */
	public boolean hasFullPalletAtEnd(long currentTime)
	{
		return palletAtEnd(0, currentTime) && lane.getPallets().get(0).hasKit();
	}
	/** true if the pallet that given index points to is at end */
	public boolean palletAtEnd(int index, long currentTime)
	{
		if (index < 0 || index >= palletOffsets.size()) return false;
		return conveyorEndPadding + 120 * index >= movement.calcPos(currentTime).x + palletOffsets.get(index);
	}
	/** return true if pallets are on the lane */
	public boolean containsPallets()
	{
		return lane.hasPallets();
	}
	/** if it is at end, return the end location, other wise, return current location */
	public Point2D.Double getPalletLocation(int index, long currentTime)
	{
		if (index < 0 || index >= palletOffsets.size()) return null;
		if (palletAtEnd(index, currentTime))
		{
			return new Point2D.Double(pos.x + conveyorEndPadding + 120 * index, pos.y - 12);
		}
		else
		{
			return movement.offset(new Point2D.Double(pos.x + palletOffsets.get(index), pos.y - 12), 0).calcPos(currentTime);
		}
	}

	public int getLength()
	{
		return nSegments * SEG_WIDTH;
	}
	
	public int getNSegments()
	{
		return nSegments;
	}

	public Point2D.Double getPalletEndPos(GUIPallet pallet)
	{
		return new Point2D.Double(pos.x+conveyorEndPadding, pallet.movement.getStartPos().y);
	}

	/** getter for position */
	public Point2D.Double getPos()
	{
		return pos;
	}

	/** setter for movement */
	public void setMove(Movement movement)
	{
		this.movement = movement;
	}

	/** getter for movement */
	public Movement getMove()
	{
		return movement;
	}
}
