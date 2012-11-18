import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Lane implements Serializable
{
	/** lane speed in pixels per second */
	private double speed = 80;
	
	private boolean laneOn;
	private ArrayList<Pallet> pallets;
	private ArrayList<Part> topParts;
	private ArrayList<Part> bottomParts;
	private double amplitude;
	
	public Lane()
	{
		laneOn = true;
		amplitude = 1;
		pallets = new ArrayList<Pallet>();
		topParts = new ArrayList<Part>();
		bottomParts = new ArrayList<Part>();
	}
	
	public boolean isLaneOn()
	{
		return laneOn;
	}
	
	public void turnOff()
	{
		laneOn = false;
	}
	
	public void turnOn()
	{
		laneOn = true;
	}
	
	public void setAmplitude(double amplitude)
	{
		this.amplitude = amplitude;
	}
	
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
	
	/*public Part removePart(){
		if( parts.size() > 0 ){ //assumes Clayton's graphics depends only on the number of parts on the lane
			return parts.remove( parts.size() - 1 );
		} else { //nothing on lane
			return null;
		}
	}*/
	
//old remove part
//	public Part removePart(Part p)
//	{
//		int index = parts.indexOf(p);
//		
//		if (index == -1)
//			return null; // p is not in this lane
//		
//		parts.remove(index);
//		return p;
//	}
	
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
