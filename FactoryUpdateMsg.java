import java.io.*;
import java.util.*;

/** networking message updating factory state */
public class FactoryUpdateMsg implements Serializable {
	/** time (in milliseconds) elapsed since simulation start on server */
	public long timeElapsed;

	public ItemUpdateMsg<GUIPart> parts;
	public ItemUpdateMsg<GUIKit> kits;
	public ItemUpdateMsg<GUIPartRobot> partRobots;
	public ItemUpdateMsg<GUIKitRobot> kitRobots;
	public ItemUpdateMsg<GUIKitDeliveryStation> kitDeliveryStations;
	public ItemUpdateMsg<GUIPallet> pallets;
	public ItemUpdateMsg<GUIGantry> gantries;
	public ItemUpdateMsg<GUIBin> bins;
	/*public ItemUpdateMsg<GUIPartCamera> partCameras;*/
	public ItemUpdateMsg<GUIKitCamera> kitCameras;
	public ItemUpdateMsg<GUIFeeder> feeders;
	public ItemUpdateMsg<GUILane> lanes;
	public ItemUpdateMsg<GUINest> nests;
	public ItemUpdateMsg<GUIDiverterArm> diverterArms;
	public ItemUpdateMsg<GUIKitStand> kitStands;
	//public ItemUpdateMsg<GUIWholeLane> wholeLanes;

	public TreeMap<Integer, Movement> partMoves;
	public TreeMap<Integer, Movement> kitMoves;
	public TreeMap<Integer, Movement> partRobotMoves;
	public TreeMap<Integer, Movement> kitRobotMoves;
	public TreeMap<Integer, Movement> kitDeliveryStationMoves;
	public TreeMap<Integer, Movement> palletMoves;
	public TreeMap<Integer, Movement> gantryMoves;
	public TreeMap<Integer, Movement> binMoves;
	/*public TreeMap<Integer, Movement> partCameraMoves;*/
	public TreeMap<Integer, Movement> kitCameraMoves;
	public TreeMap<Integer, Movement> feederMoves;
	public TreeMap<Integer, Movement> laneMoves;
	public TreeMap<Integer, Movement> nestMoves;
	public TreeMap<Integer, Movement> diverterArmMoves;
	public TreeMap<Integer, Movement> kitStandMoves;
	//public TreeMap<Integer, Movement> wholeLaneMoves;

	/** constructor to instantiate empty instance variables */
	public FactoryUpdateMsg() {
		parts = new ItemUpdateMsg<GUIPart>();
		kits = new ItemUpdateMsg<GUIKit>();
		partRobots = new ItemUpdateMsg<GUIPartRobot>();
		kitRobots = new ItemUpdateMsg<GUIKitRobot>();
		kitDeliveryStations = new ItemUpdateMsg<GUIKitDeliveryStation>();
		pallets = new ItemUpdateMsg<GUIPallet>();
		gantries = new ItemUpdateMsg<GUIGantry>();
		bins = new ItemUpdateMsg<GUIBin>();
		/*partCameras = new ItemUpdateMsg<GUIPartCamera>();*/
		kitCameras = new ItemUpdateMsg<GUIKitCamera>();
		feeders = new ItemUpdateMsg<GUIFeeder>();
		lanes = new ItemUpdateMsg<GUILane>();
		nests = new ItemUpdateMsg<GUINest>();
		diverterArms = new ItemUpdateMsg<GUIDiverterArm>();
		kitStands = new ItemUpdateMsg<GUIKitStand>();
		//wholeLanes = new ItemUpdateMsg<GUIWholeLane>();
		
		partMoves = new TreeMap<Integer, Movement>();
		kitMoves = new TreeMap<Integer, Movement>();
		partRobotMoves = new TreeMap<Integer, Movement>();
		kitRobotMoves = new TreeMap<Integer, Movement>();
		kitDeliveryStationMoves = new TreeMap<Integer, Movement>();
		palletMoves = new TreeMap<Integer, Movement>();
		gantryMoves = new TreeMap<Integer, Movement>();
		binMoves = new TreeMap<Integer, Movement>();
		/*partCameraMoves = new TreeMap<Integer, Movement>();*/
		kitCameraMoves = new TreeMap<Integer, Movement>();
		feederMoves = new TreeMap<Integer, Movement>();
		laneMoves = new TreeMap<Integer, Movement>();
		nestMoves = new TreeMap<Integer, Movement>();
		diverterArmMoves = new TreeMap<Integer, Movement>();
		kitStandMoves = new TreeMap<Integer, Movement>();
		//wholeLaneMoves = new TreeMap<Integer, Movement>();
	}
}
