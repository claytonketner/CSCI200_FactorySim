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
	/** To Do: write description */
	private boolean mySwitch;
	/** Initialization */
	public Nest(){
		nestedItems = new ArrayList<Part>();
		nestFull = false;
		mySwitch = false;
	}
	/** true if nest is full */
	public boolean isNestFull(){
		return nestFull;
	}
	/** load parts into nest */
	public boolean addPart( Part p ){ //returns true if add was successful
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
	/** To Do: write description */
	public void flipSwitch(){
		mySwitch = !mySwitch;
	}
}
