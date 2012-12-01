import java.io.Serializable;
import java.util.ArrayList;

/** represents a vibrating lane that carries items */
@SuppressWarnings("serial")
public class Lane implements Serializable
{
	/** lane speed in pixels per second */
	private double speed = 80;
	/** true if lane is on */
	private boolean laneOn;
	/** items in this lane */
	private ArrayList<GUIItem> items;
	/** vibration amplitude */
	private double amplitude;

	/** Initialization */
	public Lane()
	{
		laneOn = true;
		amplitude = 0; // perhaps should default to 1
		items = new ArrayList<GUIItem>();
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

	/** adds item to lane, returns index where item was added */
	public int addItem(GUIItem p)
	{
		items.add(p);
		return items.size() - 1;
	}

	/** inserts item to specified index in lane, returns that index */
	public int addItem(int index, GUIItem p)
	{
		items.add(index, p);
		return index;
	}

	/** removes item with specified index from lane, returns the item removed */
	public GUIItem removeItem(int index)
	{
		return items.remove(index);
	}

	/** returns whether contains items */
	public boolean hasItems()
	{
		return !items.isEmpty();
	}
	
	/** getter for speed */
	public double getSpeed()
	{
		return speed;
	}

	/** getter for items */
	public ArrayList<GUIItem> getItems()
	{
		return items;
	}
}
