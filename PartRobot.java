import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings("serial")
/** This class defines and controls a part robot. */
public class PartRobot implements Serializable {
	/** the parts in the robot's gripper */
	TreeMap<Integer, GUIPart> partsInGripper;
	
	/** states that a part robot could be in */
	public enum PRState {
		OFF, BROKEN, IDLE
	}
	
	/** what part robot is currently doing */
	public PRState state;
	
	/** Constructor */
	public PartRobot() {
		partsInGripper = new TreeMap<Integer, GUIPart>();
	}
	
	
}
