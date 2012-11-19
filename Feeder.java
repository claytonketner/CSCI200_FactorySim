import java.io.*;
import java.util.ArrayList;

/** class constructs basic functionality of feeder */
public class Feeder implements Serializable {
	/** true if goes to top lane, false otherwise */
	private boolean diverter;
	/** true if parts are low */
	private boolean partsLow;
	/** arraylist of parts are going to load into feeder */
	private ArrayList<Part> parts;

	/** Initialize variables */
	public Feeder(){
		diverter = false;
		partsLow = true;
		parts = new ArrayList<Part>();
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
		System.out.println( "" + partsLow );
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
		System.out.println( "" + diverter );
	}

	/** returns lane ID that parts are fed to */
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

	/** return part */
	public Part getPart(){
		if( parts.size() > 0 ){
			return parts.remove( parts.size() - 1 );
		} else {
			return null;
		}
	}
}
