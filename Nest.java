import java.util.ArrayList;

public class Nest {
	private final int limit = 25; //might have to change this value depending on image sizes
	
	private ArrayList<Part> nestedItems;
	private boolean nestFull;
	private boolean mySwitch;

	public Nest(){
		nestedItems = new ArrayList<Part>();
		nestFull = false;
		mySwitch = false;
	}
	
	public boolean isNestFull(){
		return nestFull;
	}
	
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
	
	public Part removePart(){
		if( nestedItems.size() > 0 ){
			nestFull = false;
			return nestedItems.remove( nestedItems.size() - 1 );
		} else {
			return null;
		}
	}
	
	public void dumpNest(){
		nestedItems = new ArrayList<Part>();
		nestFull = false;
	}
	
	public void flipSwitch(){
		mySwitch = !mySwitch;
	}
}
