import java.util.ArrayList;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class V0PartRobotClient extends JFrame {
	PartRobot partRobot;
	TestPartRobot testPartRobot;
	ArrayList<Nest> nests;
	
	public V0PartRobotClient() {
		partRobot = new PartRobot();
		testPartRobot = new TestPartRobot();
		nests = new ArrayList<Nest>();
	}
	
	public static void main(String[] args) {
		V0PartRobotClient partRobotClient = new V0PartRobotClient();
	}

}

