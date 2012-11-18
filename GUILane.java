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

public class GUILane implements GUIItem, Serializable
{
	/** width (in pixels) of one segment */
	public static final int SEG_WIDTH = 60;

	public ComboLane lane;
	public Point2D.Double pos;
	public Movement movement; // For storing lane position info
	
	boolean isForParts;
	private ArrayList<GUIPallet> pallets;
	private ArrayList<GUIPart> topParts, bottomParts;
	
	/** number of lane segments */
	private int nSegments;
	
	private final int conveyorEndPadding = 30;
		
	public GUILane(ComboLane lane, boolean isForParts, int nSegments, double x, double y)
	{
		this.lane = lane;
		
		this.isForParts = isForParts;
		if (isForParts)
		{
			topParts = new ArrayList<GUIPart>();
			bottomParts = new ArrayList<GUIPart>();
		} else pallets = new ArrayList<GUIPallet>();
		
		this.nSegments = nSegments;
		pos = new Point2D.Double(x, y);
		movement = new Movement(new Point2D.Double(), 0);
	}

	public void draw(Graphics2D g, long currentTime)
	{
		BufferedImage i_temp = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g_temp = i_temp.createGraphics();
		
		checkMotion(currentTime);
		
		// Draw to the bufferedImage
		for (int i = 0; i < nSegments + 2; i++) // draw more segments than lane width because partial segments will be drawn at ends
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
			movement.moveToAtSpeed(currentTime, new Point2D.Double(-SEG_WIDTH, 0), 0, lane.getSpeed());
			
			if (isForParts)
			{
				// part movement never seems to have been set in the first place,
				// so Andrew doesn't know what to set end pos to and commented this out for now
				/*for (GUIPart p : topParts)
					p.movement.unPause(currentTime);
				for (GUIPart p : bottomParts)
					p.movement.unPause(currentTime);*/
			} else {
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
	}

	/** whether should move all segments back 1 segment width */
	public boolean shouldReset(long currentTime) {
		return movement.arrived(currentTime + Server.UPDATE_RATE);
	}

	/** move lane segments back 1 segment width (must call this before lane moves a segment width) */
	public void reset(long currentTime)
	{
		movement = movement.offset(new Point2D.Double(SEG_WIDTH, 0), 0)
		                   .moveToAtSpeed(currentTime, new Point2D.Double(-SEG_WIDTH, 0), 0, lane.getSpeed());
	}
	
	public void addPallet()
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
	}
	
	public GUIPallet removeEndPallet()
	{
		GUIPallet removedPallet = pallets.remove(0);
		// Move all other pallet's destinations down one

		for (GUIPallet p : pallets)
			p.movement = Movement.fromSpeed(p.movement.getStartPos(), Math.PI/2, System.currentTimeMillis(), getPalletEndPos(p), Math.PI/2, lane.getSpeed());
		return removedPallet;
	}
	
	public GUIKit removeEndPalletKit()
	{
		return pallets.get(0).removeKit();
	}
	
	public boolean hasEmptyPalletAtEnd(long currentTime)
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
	}
	
	public boolean containsPallets()
	{
		if (pallets.size() == 0)
			return false;
		return true;
	}
	
	public Point2D.Double getLocationOfEndPallet(long currentTime)
	{
		if (pallets.size() == 0)
			return null;
		
		return new Point2D.Double(pallets.get(0).movement.calcPos(currentTime).x+SEG_WIDTH, pallets.get(0).movement.calcPos(currentTime).y+40);
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
