import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class PartRobot implements Serializable {
	TreeMap<Integer, GUIPart> partsInGripper;
	
	public PartRobot() {
		partsInGripper = new TreeMap<Integer, GUIPart>();
	}
	
	
}
