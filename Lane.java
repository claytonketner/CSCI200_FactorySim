import java.io.Serializable;
import java.util.ArrayList;


public class Lane implements Serializable
{
	private int speed = 10;
	
	private boolean laneOn;
	private ArrayList<Part> parts;
	private double amplitude;
	
	public Lane()
	{
		laneOn = true;
	}
	
	public boolean isLaneOn()
	{
		return laneOn;
	}
	
	public void setAmplitude(double amplitude)
	{
		this.amplitude = amplitude;
	}
	
	public double getAmplitude()
	{
		return amplitude;
	}
	
	public void addPart(Part p)
	{
		parts.add(p);
	}
	
	public Part removePart(Part p)
	{
		int index = parts.indexOf(p);
		
		if (index == -1)
			return null; // p is not in this lane
		
		parts.remove(index);
		return p;
	}
	
	public void turnOn(){
		laneOn = true;
	}
	
	public void turnOff(){
		laneOn = false;
	}
}