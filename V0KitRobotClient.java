import javax.swing.JFrame;


@SuppressWarnings("serial")
public class V0KitRobotClient extends JFrame {
	KitRobot kitRobot;
	KitDeliveryStation kitDeliveryStation;
	TestKitRobot testKitRobot;
	
	public V0KitRobotClient() {
		kitRobot = new KitRobot();
		kitDeliveryStation = new KitDeliveryStation();
		testKitRobot = new TestKitRobot();
	}
	
	public static void main(String[] args) {
		V0KitRobotClient kitRobotClient = new V0KitRobotClient();
	}

}
