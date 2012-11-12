import java.io.*;
import java.util.*;

/** networking message containing all information needed to generate factory state
    note that if a client sends an empty FactoryStateMsg, it means they are requesting to be kept up-to-date with the factory state as long as it is connected to the server */
@SuppressWarnings("serial")
public class FactoryStateMsg implements Serializable {
	public TreeMap<Integer, GUIPart> parts;
	public TreeMap<Integer, GUIKit> kits;
	public TreeMap<Integer, GUIPartRobot> partRobots;
	public TreeMap<Integer, GUIKitRobot> kitRobots;
	public TreeMap<Integer, GUIKitDeliveryStation> kitDeliveryStations;
	public TreeMap<Integer, GUIPallet> pallets;
	/*public TreeMap<Integer, GUIGantry> gantries;
	public TreeMap<Integer, GUIBin> bins;
	public TreeMap<Integer, GUIPartCamera> partCameras;*/
	public TreeMap<Integer, GUIKitCamera> kitCameras;
	public TreeMap<Integer, GUIFeeder> feeders;
	public TreeMap<Integer, GUILane> lanes;
	public TreeMap<Integer, GUINest> nests;
	public TreeMap<Integer, GUIDiverterArm> diverterArms;
	//public TreeMap<Integer, GUIWholeLane> wholeLanes;

	/** constructor to instantiate empty TreeMaps */
	public FactoryStateMsg() {
		parts = new TreeMap<Integer, GUIPart>();
		kits = new TreeMap<Integer, GUIKit>();
		partRobots = new TreeMap<Integer, GUIPartRobot>();
		kitRobots = new TreeMap<Integer, GUIKitRobot>();
		kitDeliveryStations = new TreeMap<Integer, GUIKitDeliveryStation>();
		pallets = new TreeMap<Integer, GUIPallet>();
		/*gantries = new TreeMap<Integer, GUIGantry>();
		bins = new TreeMap<Integer, GUIBin>();
		partCameras = new TreeMap<Integer, GUIPartCamera>();*/
		kitCameras = new TreeMap<Integer, GUIKitCamera>();
		feeders = new TreeMap<Integer, GUIFeeder>();
		lanes = new TreeMap<Integer, GUILane>();
		nests = new TreeMap<Integer, GUINest>();
		diverterArms = new TreeMap<Integer, GUIDiverterArm>();
		//wholeLanes = new TreeMap<Integer, GUIWholeLane>();
	}

	/** updates the factory state */
	public void update(FactoryUpdateMsg msg) {
		msg.parts.apply(parts);
		msg.kits.apply(kits);
		msg.partRobots.apply(partRobots);
		msg.kitRobots.apply(kitRobots);
		msg.kitDeliveryStations.apply(kitDeliveryStations);
		msg.pallets.apply(pallets);
		/*msg.gantries.apply(gantries);
		msg.bins.apply(bins);
		msg.partCameras.apply(partCameras);*/
		msg.kitCameras.apply(kitCameras);
		msg.feeders.apply(feeders);
		msg.lanes.apply(lanes);
		msg.nests.apply(nests);
		//msg.wholeLanes.apply(wholeLanes);
		for (Map.Entry<Integer, Movement> e : msg.partMoves.entrySet()) {
			parts.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.kitMoves.entrySet()) {
			kits.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.partRobotMoves.entrySet()) {
			partRobots.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.kitRobotMoves.entrySet()) {
			kitRobots.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.kitDeliveryStationMoves.entrySet()) {
			kitDeliveryStations.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.palletMoves.entrySet()) {
			pallets.get(e.getKey()).movement = e.getValue();
		}
		/*for (Map.Entry<Integer, Movement> e : msg.gantryMoves.entrySet()) {
			gantries.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.binMoves.entrySet()) {
			bins.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.partCameraMoves.entrySet()) {
			partCameras.get(e.getKey()).movement = e.getValue();
		}*/
		for (Map.Entry<Integer, Movement> e : msg.kitCameraMoves.entrySet()) {
			kitCameras.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.feederMoves.entrySet()) {
			feeders.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.laneMoves.entrySet()) {
			lanes.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.nestMoves.entrySet()) {
			nests.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.diverterArmMoves.entrySet())
			diverterArms.get(e.getKey()).movement = e.getValue();
		/*for (Map.Entry<Integer, Movement> e : msg.wholeLaneMoves.entrySet()) {
			wholeLanes.get(e.getKey()).movement = e.getValue();
		}*/
	}
}
