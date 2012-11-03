
public class ComboLane {
	private Lane myTopLane;
	private Lane myBotLane;
	
	public ComboLane(){
		myTopLane = new Lane();
		myBotLane = new Lane();
	}
	
	public void turnOn(){
		myTopLane.turnOn();
		myBotLane.turnOn();
	}
	
	public void turnOff(){
		myTopLane.turnOff();
		myBotLane.turnOff();
	}
	
	public boolean isLaneOn(){
		//top and bottom lane are the same
		return myTopLane.isLaneOn();
	}
	
	public void setAmplitude(double amplitude)
	{
		myTopLane.setAmplitude(amplitude);
		myBotLane.setAmplitude(amplitude);
	}
	
	public double getAmplitude()
	{
		//top and bottom lane have the same amplitude
		return myTopLane.getAmplitude();
	}
	
	public void addPartTopLane( Part p ){
		myTopLane.addPart( p );
	}
	
	public void addPartBotLane( Part p ){
		myBotLane.addPart( p );
	}
	
	public Part removePartTopLane(){
		return myTopLane.removePart();
	}
	
	public Part removePartBotLane(){
		return myBotLane.removePart();
	}
	
	public double getSpeed(){
		//top and bottom lane have the same speed
		return myTopLane.getSpeed();
	}
}
