import java.util.ArrayList;

public class WholeLane {
	private Feeder myFeeder;
	private Nest myTopNest;
	private Nest myBotNest;
	//These lanes are actually on the same lane
	//lane1 is the top side of the lane
	//lane2 is the bottom side of the lane
	private ComboLane myLane;
	
	public WholeLane(){
		myFeeder = new Feeder();
		myLane = new ComboLane();
		myTopNest = new Nest();
		myBotNest = new Nest();
	}
	
	public void turnOnLane(){
		myLane.turnOn();
	}
	
	public void turnOffLane(){
		myLane.turnOff();
	}
	
	public boolean areLanesOn(){
		return myLane.isLaneOn();
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
			myLane.addPartTopLane( myFeeder.getPart() );
		} else { //its going to lane 2
			myLane.addPartBotLane( myFeeder.getPart() );
		}
	}
	
	public boolean topLaneToNest(){
		if( myTopNest.isNestFull() ){
			myTopNest.addPart( myLane.removePartTopLane() );
			return true;
		} else {
			return false;
		}
	}
	
	public boolean botLaneToNest(){
		if( myBotNest.isNestFull() ){
			myBotNest.addPart( myLane.removePartBotLane() );
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
	
	public ComboLane getComboLane(){
		return myLane;
	}
	
	public void fillFeeder( ArrayList<Part> load ){
		myFeeder.loadFeeder(load);
	}
}
