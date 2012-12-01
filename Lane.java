import java.io.Serializable;
import java.util.ArrayList;

/** represents either a lane of pallets or a double lane of parts */
@SuppressWarnings("serial")
public class Lane implements Serializable
{
	/** lane speed in pixels per second */
	private double speed = 80;
	/** true if lane is on */
	private boolean laneOn;
	/** pallets in this lane */
	private ArrayList<Pallet> pallets;
	/** parts in the top half of this lane */
	private ArrayList<Part> topParts;
	/** parts in the bottom half of this lane */
	private ArrayList<Part> bottomParts;
	/** vibration amplitude */
	private double amplitude;

	/** Initialization */
	public Lane()
	{
		laneOn = true;
		amplitude = 1; // perhaps should default to 1
		pallets = new ArrayList<Pallet>();
		topParts = new ArrayList<Part>();
		bottomParts = new ArrayList<Part>();
	}

	/** returns whether lane is on */
	public boolean isLaneOn()
	{
		return laneOn;
	}

	/** turn off lane (set laneOn to false) */
	public void turnOff()
	{
		laneOn = false;
	}

	/** turn on lane (set laneOn to true) */
	public void turnOn()
	{
		laneOn = true;
	}

	/** setter for amplitude */
	public void setAmplitude(double amplitude)
	{
		this.amplitude = amplitude;
	}

	/** getter for amplitude */
	public double getAmplitude()
	{
		return amplitude;
	}

	/** adds pallet to lane, returns index where pallet was added */
	public int addPallet(Pallet p)
	{
		pallets.add(p);
		return pallets.size() - 1;
	}

	/** inserts pallet to specified index in lane, returns that index */
	public int addPallet(int index, Pallet p)
	{
		pallets.add(index, p);
		return index;
	}

	/** removes pallet with specified index from top lane, returns the pallet removed */
	public Pallet removePallet(int index)
	{
		return pallets.remove(index);
	}

	/** returns whether contains pallets */
	public boolean hasPallets()
	{
		return !pallets.isEmpty();
	}
	
	/** adds part to top lane, returns index where part was added */
	public int addTopPart(Part p)
	{
		topParts.add(p);
		return topParts.size() - 1;
	}

	/** removes part with specified index from top lane, returns the part removed */
	public Part removeTopPart(int index)
	{
		return topParts.remove(index);
	}

	/** returns whether contains parts in top lane */
	public boolean hasTopParts()
	{
		return !topParts.isEmpty();
	}
	
	/** adds part to bottom lane, returns index where part was added */
	public int addBottomPart(Part p)
	{
		bottomParts.add(p);
		return bottomParts.size() - 1;
	}

	/** removes part with specified index from bottom lane, returns the part removed */
	public Part removeBottomPart(int index)
	{
		return bottomParts.remove(index);
	}

	/** returns whether contains parts in bottom lane */
	public boolean hasBottomParts()
	{
		return !bottomParts.isEmpty();
	}
	
	/** getter for speed */
	public double getSpeed()
	{
		return speed;
	}

	/** getter for pallets */
	public ArrayList<Pallet> getPallets()
	{
		return pallets;
	}

	/** getter for top parts */
	public ArrayList<Part> getTopParts()
	{
		return topParts;
	}

	/** getter for bottom parts */
	public ArrayList<Part> getBottomParts()
	{
		return bottomParts;
	}
}
