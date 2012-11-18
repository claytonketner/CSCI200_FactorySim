import java.util.ArrayList;

/** Control the lane implementation */
public class WholeLane {
	/** initialize variables */
	private Feeder myFeeder;
	/** initialize variables */
	private Nest myTopNest;
	/** initialize variables */
	private Nest myBotNest;
	/** ComboLane instance */
	private ComboLane myLane;

	/** Initialize variables */
	public WholeLane() {
		myFeeder = new Feeder();
		myLane = new ComboLane();
		myTopNest = new Nest();
		myBotNest = new Nest();
	}

	/** turn on lane */
	public void turnOnLane() {
		myLane.turnOn();
	}

	/** turn off lane */
	public void turnOffLane() {
		myLane.turnOff();
	}

	/** true if lane is on */
	public boolean areLanesOn() {
		return myLane.isLaneOn();
	}

	/** change the lane */
	public void divert() {
		myFeeder.changeLane();
	}

	/** true if top nest is full */
	public boolean isTopNestFull() {
		return myTopNest.isNestFull();
	}

	/** true if bot nest is full */
	public boolean isBotNestFull() {
		return myBotNest.isNestFull();
	}

	/**
	 * boolean variable diverter decides which lane the parts will go, if it is
	 * true, go to lane 2, if it false, go to lane 1
	 */
	public void feedToLane() {
		if (myFeeder.getLane() == 1) {
			myLane.addPartTopLane(myFeeder.getPart());
		} else { // its going to lane 2
			myLane.addPartBotLane(myFeeder.getPart());
		}
	}
	/** To do: write description  */
	public boolean topLaneToNest() {
		if (myTopNest.isNestFull()) {
			myTopNest.addPart(myLane.removePartTopLane());
			return true;
		} else {
			return false;
		}
	}
	/** To do: write description  */
	public boolean botLaneToNest() {
		if (myBotNest.isNestFull()) {
			myBotNest.addPart(myLane.removePartBotLane());
			return true;
		} else {
			return false;
		}
	}
	/** check if feeder is low  */
	public boolean isFeederLow() {
		return myFeeder.checkIfLow();
	}
	/** flip top nest switch  */
	public void flipTopNestSwitch() {
		myTopNest.flipSwitch();
	}
	/** flip bot nest switch  */
	public void flipBotNestSwitch() {
		myBotNest.flipSwitch();
	}
	/** return comboLane variable  */
	public ComboLane getComboLane() {
		return myLane;
	}
	/** return Feeder variable  */
	public Feeder getFeeder() {
		return myFeeder;
	}
	/** fill the feeder by parts  */
	public void fillFeeder(ArrayList<Part> load) {
		myFeeder.loadFeeder(load);
	}
	/** return the which lane the parts are going  */
	public int getLane() {
		return myFeeder.getLane();
	}
	/** return the speed  */
	public double getSpeed() {
		return myLane.getSpeed();
	}
}
