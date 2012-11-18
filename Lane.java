import java.io.Serializable;
import java.util.ArrayList;


public class Lane implements Serializable
{
	/** speed equals to 80 */
	private double speed = 80;
	/** true if lane is on */
	private boolean laneOn;
	/** parts are in the lane */
	private ArrayList<Part> parts;
	/** amplitude */
	private double amplitude;
	/** Initialization */
	public Lane()
	{
		laneOn = true;
		amplitude = 1;
	}
	/** true if lane is on */
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
	/** set amplitude */
	public void setAmplitude(double amplitude)
	{
		this.amplitude = amplitude;
	}
	/** return amplitude */
	public double getAmplitude()
	{
		return amplitude;
	}
	/** add a part to lane */
	public void addPart(Part p)
	{
		parts.add(p);
	}
	/** remove the last part from lane */
	public Part removePart(){
		if( parts.size() > 0 ){ //assumes Clayton's graphics depends only on the number of parts on the lane
			return parts.remove( parts.size() - 1 );
		} else { //nothing on lane
			return null;
		}
	}
	
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
	/** return speed */
	public double getSpeed()
	{
		return speed;
	}
}