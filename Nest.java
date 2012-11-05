import java.util.ArrayList;

public class Nest {
	private final int limit = 10; //instructions say 1-10 parts per nest
	
	private ArrayList<GUIPart> nestedItems;
	private boolean nestFull;
	private boolean mySwitch;

	public Nest(){
		nestedItems = new ArrayList<GUIPart>();
		nestFull = false;
		mySwitch = false;
	}
	
	public boolean isNestFull(){
		return nestFull;
	}
	
	public boolean addPart( GUIPart part ){ //returns true if add was successful
		if(nestedItems.size() < limit) {
			nestedItems.add(part);
			
			if(nestedItems.size() == limit){
				nestFull = true;
			}
			
			return true;
		} else { //nest full
			return false;
		}
	}
	
	public GUIPart removePart(){
		if( nestedItems.size() > 0 ){
			nestFull = false;
			return nestedItems.remove( nestedItems.size() - 1 );
		} else {
			return null;
		}
	}
	
	public void dumpNest(){
		nestedItems = new ArrayList<GUIPart>();
		nestFull = false;
	}
	
	public void flipSwitch(){
		mySwitch = !mySwitch;
	}
}
