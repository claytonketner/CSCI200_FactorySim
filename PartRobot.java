import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class PartRobot implements Serializable {
	TreeMap<Integer, GUIPart> partsInGripper;
	
	public PartRobot() {
		partsInGripper = new TreeMap<Integer, GUIPart>();
	}
	
	public void addPartToGripper ( Integer gripperNumber, GUIPart part ) {
		partsInGripper.put( gripperNumber, part);
	}
	
	public GUIPart removePartFromGripper ( Integer gripperNumber ) {
		return partsInGripper.remove( gripperNumber );
	}
}
