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
		kitRobotClient.setSize(600, 400);
		kitRobotClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kitRobotClient.setVisible(true);
	}

}
