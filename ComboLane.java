import java.io.Serializable;
/** class constructs basic functionality of a combolane (one top lane, one bot lane) */
public class ComboLane implements Serializable{
	/** top lane */
	private Lane myTopLane;
	/** bot lane */
	private Lane myBotLane;
	/** Initialization */
	public ComboLane(){
		myTopLane = new Lane();
		myBotLane = new Lane();
	}
	/** turn both lane on */
	public void turnOn(){
		myTopLane.turnOn();
		myBotLane.turnOn();
	}
	/** turn both lane off */
	public void turnOff(){
		myTopLane.turnOff();
		myBotLane.turnOff();
	}
	/** true if lane is on*/
	public boolean isLaneOn(){
		//top and bottom lane are the same
		return myTopLane.isLaneOn();
	}
	/** set amplitude */
	public void setAmplitude(double amplitude)
	{
		myTopLane.setAmplitude(amplitude);
		myBotLane.setAmplitude(amplitude);
	}
	/** return amplitude */
	public double getAmplitude()
	{
		//top and bottom lane have the same amplitude
		return myTopLane.getAmplitude();
	}
	/** add a part to top lane */
	public void addPartTopLane( Part p ){
		myTopLane.addPart( p );
	}
	/** add a part to bot lane */
	public void addPartBotLane( Part p ){
		myBotLane.addPart( p );
	}
	/** remove the last part from top lane */
	public Part removePartTopLane(){
		return myTopLane.removePart();
	}
	/** remove the last part from bot lane */
	public Part removePartBotLane(){
		return myBotLane.removePart();
	}
	/** return lane speed */
	public double getSpeed(){
		//top and bottom lane have the same speed
		return myTopLane.getSpeed();
	}
}
