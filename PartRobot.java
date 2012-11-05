import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class PartRobot implements Serializable {
	ArrayList<Part> partsInGripper;
	
	public PartRobot() {
		partsInGripper = new ArrayList<Part>();
	}
}
