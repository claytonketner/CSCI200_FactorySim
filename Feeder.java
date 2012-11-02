
public class Feeder {
	boolean diverter;
	boolean partsLow;
	int amount;
	
	public Feeder(){
		diverter = false;
		partsLow = true;
		amount = 0;
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
}
