import java.io.*;
import java.util.ArrayList;

public class Feeder implements Serializable {
	private boolean diverter;
	private boolean partsLow;
	private ArrayList<Part> parts;
	
	public Feeder(){
		diverter = false;
		partsLow = true;
		parts = new ArrayList<Part>();
	}
	
	public void setPartsLow(){
		partsLow = true;
	}
	
	public void setPartsUnlow(){
		partsLow = false;
	}
	
	public boolean checkIfLow(){
		return partsLow;
	}
	
	public void changeLane(){
		diverter = !diverter;
	}
	
	public int getLane(){
		if( diverter ) {
			return 2;
		} else {
			return 1;
		}
	}
	
	public void loadFeeder( ArrayList<Part> load ){
		parts = load;
	}
	
	public Part getPart(){
		if( parts.size() > 0 ){
			return parts.remove( parts.size() - 1 );
		} else {
			return null;
		}
	}
}
