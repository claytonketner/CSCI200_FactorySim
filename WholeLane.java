
public class WholeLane {
	private Feeder myFeeder;
	private Nest myTopNest;
	private Nest myBotNest;
	//These lanes are actually on the same lane
	//lane1 is the top side of the lane
	//lane2 is the bottom side of the lane
	private Lane myLane1;
	private Lane myLane2;
	
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
	
	public boolean areLanesOn(){ //Both lanes should be the same
		return myLane1.isLaneOn() && myLane2.isLaneOn();
	}
	
	public void divert(){
		myFeeder.changeLane();
	}
	
	public boolean isTopNestFull(){
		return myTopNest.isNestFull();
	}
	
	public boolean isBotNestFull(){
		return myBotNest.isNestFull();
	}
	
	public void feedToLane(){
		if( myFeeder.getLane() == 1 ){
			myLane1.addPart( myFeeder.getPart() );
		} else { //its going to lane 2
			myLane2.addPart( myFeeder.getPart() );
		}
	}
	
	public boolean topLaneToNest(){
		if( myTopNest.isNestFull() ){
			myTopNest.addPart(myLane1.removePart());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean botLaneToNest(){
		if( myBotNest.isNestFull() ){
			myBotNest.addPart(myLane2.removePart());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isFeederLow(){
		return myFeeder.checkIfLow();
	}
	
	public void flipTopNestSwitch(){
		myTopNest.flipSwitch();
	}
	
	public void flipBotNestSwitch(){
		myBotNest.flipSwitch();
	}
}
