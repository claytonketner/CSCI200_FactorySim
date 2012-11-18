/*
Andrew's notes about GUILane:
lane segments move width of themselves repeatedly
pallet movements try to pile up if reaches end
*/

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

	public Lane lane;
	/** location of top left corner of lane */
	private Point2D.Double pos;
	public Movement movement;
	
	boolean isForParts;
	/*private ArrayList<GUIPallet> pallets;
	private ArrayList<GUIPart> topParts, bottomParts;*/
	private ArrayList<java.lang.Double> palletOffsets;
	private ArrayList<java.lang.Double> topPartOffsets;
	private ArrayList<java.lang.Double> bottomPartOffsets;
	
	/** number of lane segments */
	private int nSegments;
	
	private final int conveyorEndPadding = 30;
		
	public GUILane(Lane lane, boolean isForParts, int nSegments, double x, double y)
	{
		this.lane = lane;
		
		this.isForParts = isForParts;
		/*if (isForParts)
		{
			topParts = new ArrayList<GUIPart>();
			bottomParts = new ArrayList<GUIPart>();
		} else pallets = new ArrayList<GUIPallet>();*/
		
		this.nSegments = nSegments;
		pos = new Point2D.Double(x, y);
		movement = new Movement(new Point2D.Double(), 0);
		palletOffsets = new ArrayList<java.lang.Double>();
		topPartOffsets = new ArrayList<java.lang.Double>();
		bottomPartOffsets = new ArrayList<java.lang.Double>();
	}

	public void draw(Graphics2D g, long currentTime)
	{
		int i;
		BufferedImage i_temp = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g_temp = i_temp.createGraphics();
		
		//checkMotion(currentTime);
		
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
	
	/*private void checkMotion(long currentTime)
	{
		if (lane.isLaneOn())
		{
			// Start the lane
			movement.moveToAtSpeed(currentTime, new Point2D.Double(-SEG_WIDTH, 0), 0, lane.getSpeed());
			
			if (isForParts)
			{
				// part movement never seems to have been set in the first place,
				// so Andrew doesn't know what to set end pos to and commented this out for now
				/*for (GUIPart p : topParts)
					p.movement.unPause(currentTime);
				for (GUIPart p : bottomParts)
					p.movement.unPause(currentTime);*/
/*			} else {
				for (GUIPallet p : pallets)
					p.movement.moveToAtSpeed(currentTime, getPalletEndPos(p), 0, lane.getSpeed());
			}
			
			if (shouldReset(currentTime)) reset(currentTime);
		} else {
			// Stop the lane
			movement.freeze(currentTime);
			
			if (isForParts)
			{
				for (GUIPart p : topParts)
					p.movement = p.movement.freeze(currentTime);
				for (GUIPart p : bottomParts)
					p.movement = p.movement.freeze(currentTime);
			} else {
				for (GUIPallet p : pallets)
					p.movement = p.movement.freeze(currentTime);
			}
		}
	}*/

	public void turnOff(long currentTime)
	{
		lane.turnOff();
		movement.freeze(currentTime); // stop the lane
	}

	public void turnOn(long currentTime)
	{
		lane.turnOn();
		movement.moveToAtSpeed(currentTime, new Point2D.Double(-SEG_WIDTH, 0), 0, lane.getSpeed()); // start the lane
	}

	/** whether should move all segments back 1 segment width */
	public boolean shouldReset(long currentTime) {
		return lane.isLaneOn() && movement.arrived(currentTime + Server.UPDATE_RATE);
	}

	/** move lane segments back 1 segment width (must call this before lane moves a segment width) */
	public void reset(long currentTime)
	{
		movement = movement.offset(new Point2D.Double(SEG_WIDTH, 0), 0)
		                   .moveToAtSpeed(currentTime, new Point2D.Double(-SEG_WIDTH, 0), 0, lane.getSpeed());
	}
	
	/*public void addPallet()
	{
		GUIPallet pallet = new GUIPallet(new Pallet(), new GUIKit(new Kit(), 0,0), pos.x-50+getLength(), pos.y-12);
		pallets.add(pallet);
		pallet.movement = Movement.fromSpeed(pallet.movement.getStartPos(), Math.PI/2, System.currentTimeMillis(), 
				new Point2D.Double((pos.x+conveyorEndPadding+120*(pallets.size()-1)), pallet.movement.getStartPos().y), Math.PI/2, lane.getSpeed());
	}
	
	public void addPallet(GUIPallet pallet)
	{
		pallets.add(pallet);
		pallet.movement = Movement.fromSpeed(pallet.movement.getStartPos(), Math.PI/2, System.currentTimeMillis(), 
				getPalletEndPos(pallet), Math.PI/2, lane.getSpeed());
	}*/

	public void addPallet()
	{
		addPallet(new GUIPallet(new Pallet(new Kit()), pos.x-50+getLength(), pos.y-12), 0);
	}

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
	
	/*public GUIPallet removeEndPallet()
	{
		GUIPallet removedPallet = pallets.remove(0);
		// Move all other pallet's destinations down one

		for (GUIPallet p : pallets)
			p.movement = Movement.fromSpeed(p.movement.getStartPos(), Math.PI/2, System.currentTimeMillis(), getPalletEndPos(p), Math.PI/2, lane.getSpeed());
		return removedPallet;
	}*/

	public GUIPallet removeEndPallet(long currentTime)
	{
		Movement tempMove = new Movement(getPalletLocation(0, currentTime), Math.PI / 2);
		palletOffsets.remove(0);
		return new GUIPallet(lane.removePallet(0), tempMove);
	}
	
	public Kit removeEndPalletKit()
	{
		return lane.getPallets().get(0).removeKit();
	}
	
	/*public boolean hasEmptyPalletAtEnd(long currentTime)
	{
		if (pallets.size() == 0)
			return false;
		
		GUIPallet p = pallets.get(0);
			if (p.movement.arrived(currentTime))
//				if (p.movement.calcPos(currentTime).x == pos.x+conveyorEndPadding)
					if (!p.hasKit())
						return true;
		return false;
	}
	
	public boolean hasFullPalletAtEnd(long currentTime)
	{
		if (pallets.size() == 0)
			return false;
		
		GUIPallet p = pallets.get(0);
			if (p.movement.arrived(currentTime))
//				if (p.movement.calcPos(currentTime).x == pos.x+conveyorEndPadding)
					if (p.hasKit())
						return true;
		return false;
	}*/

	public boolean hasEmptyPalletAtEnd(long currentTime)
	{
		return palletAtEnd(0, currentTime) && !lane.getPallets().get(0).hasKit();
	}

	public boolean hasFullPalletAtEnd(long currentTime)
	{
		return palletAtEnd(0, currentTime) && lane.getPallets().get(0).hasKit();
	}

	public boolean palletAtEnd(int index, long currentTime)
	{
		if (index < 0 || index >= palletOffsets.size()) return false;
		return conveyorEndPadding + 120 * index <= movement.calcPos(currentTime).x;
	}
	
	public boolean containsPallets()
	{
		return lane.hasPallets();
	}

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
	
	/*public Point2D.Double getLocationOfEndPallet(long currentTime)
	{
		if (pallets.size() == 0)
			return null;
		
		return new Point2D.Double(pallets.get(0).movement.calcPos(currentTime).x+SEG_WIDTH, pallets.get(0).movement.calcPos(currentTime).y+40);
	}*/

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
