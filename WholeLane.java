
public class WholeLane {
	private Feeder myFeeder;
	private Lane myLane1;
	private Lane myLane2;
	private Nest myTopNest;
	private Nest myBotNest;
	
	public WholeLane(){
		myFeeder = new Feeder();
		myLane1 = new Lane();
		myLane2 = new Lane();
		myTopNest = new Nest();
		myBotNest = new Nest();
	}
	
	public void turnOnLane(){
		myLane1.turnOn();
		myLane2.turnOn();
	}
	
	public void turnOffLane(){
		myLane1.turnOff();
		myLane2.turnOff();
	}
}
