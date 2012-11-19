import java.io.*;
import java.util.ArrayList;

/** class constructs basic functionality of nest */
public class Nest implements Serializable {
	/** instructions say 1-10 parts per nest */
	private final int limit = 10; 
	/** parts are in the nest */
	public ArrayList<Part> nestedItems;
	/** true if nest is full */
	private boolean nestFull;
	/** true if nest is up */
	private boolean nestUp;

	/** Initialization */
	public Nest(){
		nestedItems = new ArrayList<Part>();
		nestFull = false;
		nestUp = true;
	}

	/** returns whether nest is full */
	public boolean isNestFull(){
		return nestFull;
	}

	/** load part into nest, returns whether successful */
	public boolean addPart( Part p ){
		if(nestedItems.size() < limit) {
			nestedItems.add(p);
			
			if(nestedItems.size() == limit){
				nestFull = true;
			}
			
			return true;
		} else { //nest full
			return false;
		}
	}

	/** remove part from nest */
	public Part removePart(){
		if( nestedItems.size() > 0 ){
			nestFull = false;
			return nestedItems.remove( nestedItems.size() - 1 );
		} else {
			return null;
		}
	}

	/** dump nest out */
	public void dumpNest(){
		nestedItems = new ArrayList<Part>();
		nestFull = false;
	}

	/** raises nest if its down and lowers nest if it is up */
	public void flipSwitch(){
		nestUp = !nestUp;
	}
	
	/** returns if nest is up */
	public boolean isNestUp(){
		return nestUp;
	}
}
