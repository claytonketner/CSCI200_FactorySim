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
	/** set partslow true */
	public void setPartsLow(){
		partsLow = true;
	}
	/** set partslow false */
	public void setPartsUnlow(){
		partsLow = false;
	}
	/** return partslow */
	public boolean checkIfLow(){
		return partsLow;
	}
	/** flip boolean diverter */
	public void changeLane(){
		diverter = !diverter;
	}
	/** 2 if diverter is true, 1 otherwise */
	public int getLane(){
		if( diverter ) {
			return 2;
		} else {
			return 1;
		}
	}
	/** parts load into feeder */
	public void loadFeeder( ArrayList<Part> load ){
		parts = load;
	}
	/** return parts */
	public Part getPart(){
		if( parts.size() > 0 ){
			return parts.remove( parts.size() - 1 );
		} else {
			return null;
		}
	}
}
