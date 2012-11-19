import java.io.*;
import java.util.ArrayList;

/** class constructs basic functionality of feeder */
public class Feeder implements Serializable {
	/** true if parts go to top lane, false otherwise */
	private boolean diverter;
	/** true if parts are low */
	private boolean partsLow;
	/** true if gate is lowered */
	private boolean gateLowered;
	/** true if parts are being fed */
	private boolean feedParts;
	/** true if feeder is on */
	private boolean imOn;
	/** arraylist of parts are going to load into feeder */
	private ArrayList<Part> parts;
	/** counts number of parts fed */
	private int fedCount;

	/** Initialize variables */
	public Feeder(){
		diverter = false;
		partsLow = true;
		gateLowered = true;
		feedParts = false;
		imOn = true;
		parts = new ArrayList<Part>();
		fedCount = 0;
	}
	/** set partsLow true */
	public void setPartsLow(){
		partsLow = true;
	}

	/** set partsLow false */
	public void setPartsUnlow(){
		partsLow = false;
	}

	/** returns whether parts are low */
	public boolean checkIfLow(){
		return partsLow;
	}

	/** flip boolean diverter */
	public void changeLane(){
		diverter = !diverter;
	}
	
	public boolean getDiverterTop() {
		return diverter;
	}
	
	public void setDiverterTop( boolean topLane ) {
		if ( !diverter && topLane || diverter && !topLane )
			changeLane();
	}

	/** returns lane number that parts are fed to (1 = bottom, 2= top) */
	public int getLane(){
		if( diverter ) {
			return 2;
		} else {
			return 1;
		}
	}

	/** load parts into feeder */
	public void loadFeeder( ArrayList<Part> load ){
		parts = load;
	}
	
	/** empties the feeder */
	public void purgeFeeder(){
		parts = new ArrayList<Part>();
	}

	/** return part and increments fedCount*/
	public Part getPart(){
		if( parts.size() > 0 ){
			fedCount++;
			return parts.remove( parts.size() - 1 );
		} else {
			return null;
		}
	}
	
	/** raise the gate, sets gateLowered to false */
	public void raiseGate(){
		gateLowered = false;
	}
	
	/** lower the gate, sets gateLowered to true */
	public void lowerGate(){
		gateLowered = true;
	}
	
	/** returns if the gate is lowered */
	public boolean isGateLowered(){
		return gateLowered;
	}
	
	/** start feeding parts, sets feedParts to true */
	public void startFeeding(){
		feedParts = true;
	}
	
	/** stop feeding parts, set feedParts to false */
	public void stopFeeding(){
		feedParts = false;
	}
	
	/** returns if the feeder is feeding parts */
	public boolean isFeeding(){
		return feedParts;
	}
	
	/** turn on feeder */
	public void turnOn(){
		imOn = true;
	}
	
	/** turn off feeder */
	public void turnOff(){
		imOn = false;
	}
	
	/** returns if the feeder is on */
	public boolean isOn(){
		return imOn;
	}
	
	/** returns number of parts fed */
	public int partsFed(){
		return fedCount;
	}
	
	/** resets fedCount to 0 */
	public void resetCount(){
		fedCount = 0;
	}
}
