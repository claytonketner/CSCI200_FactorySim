import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings("serial")
/** This class defines and controls a part robot. */
public class PartRobot implements Serializable {
	/** the parts in the robot's gripper */
	TreeMap<Integer, GUIPart> partsInGripper;
	/** Constructor */
	public PartRobot() {
		partsInGripper = new TreeMap<Integer, GUIPart>();
	}
	
	
}
