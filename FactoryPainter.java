import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class FactoryPainter 
{
	// Use painter static methods to scale and crop the images
	
	private FactoryStateMsg factoryState;
	
	public enum FactoryArea {
		KIT_MANAGER, PART_MANAGER, LANE_MANAGER
	}
	
	public FactoryPainter(FactoryStateMsg factoryState)
	{
		this.factoryState = factoryState;
	}
	
	public void update(FactoryUpdateMsg updateMsg)
	{
		factoryState.update(updateMsg);
	}
	
	private void performChecks()
	{
		// Remove expired kit cameras
		if (factoryState.kitCameras.size() == 0)
			return;

		GUIKitCamera[] cameras = (GUIKitCamera[]) factoryState.kitCameras.values().toArray();
		Integer[] keys = (Integer[]) factoryState.kitCameras.keySet().toArray();

		for (int i = 0; i<factoryState.kitCameras.size(); i++)
		{
			GUIKitCamera camera = cameras[i];
			if (camera.isExpired(factoryState.timeElapsed))
				factoryState.kitCameras.remove(keys[i]);
		}
	}

	public BufferedImage drawEntireFactory()
	{
		factoryState.updateTime();
		performChecks();
		
		BufferedImage factoryImg = new BufferedImage(1600, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = factoryImg.createGraphics();
		
		for (GUINest nest : factoryState.nests.values()) // Nests
			nest.draw(g, factoryState.timeElapsed);
		
		for (GUILane lane : factoryState.lanes.values()) // Lanes
			lane.draw(g, factoryState.timeElapsed);
		
		for (GUIDiverterArm diverterArm : factoryState.diverterArms.values()) // Diverters
		{
			// Draw the diverter below the diverter arm
			(new GUIDiverter(diverterArm.movement.calcPos(factoryState.timeElapsed).x+42, 
							 diverterArm.movement.calcPos(factoryState.timeElapsed).y)).draw(g, factoryState.timeElapsed);
			diverterArm.draw(g, factoryState.timeElapsed);
		}
		
		for (GUIFeeder feeder : factoryState.feeders.values()) // Feeder
			feeder.draw(g, factoryState.timeElapsed);
		
		for (GUIKitStand kitStand : factoryState.kitStands.values()) // Kit stand
			kitStand.draw(g, factoryState.timeElapsed);
		
		for (GUIKitDeliveryStation kitDeliveryStation : factoryState.kitDeliveryStations.values()) // Kit delivery station
			kitDeliveryStation.draw(g, factoryState.timeElapsed);
		
		for (GUIPartRobot partRobot : factoryState.partRobots.values()) // Part robot
			partRobot.draw(g, factoryState.timeElapsed);
		
		for (GUIKitRobot kitRobot : factoryState.kitRobots.values()) // Kit robot
			kitRobot.draw(g, factoryState.timeElapsed);
		
		for (GUIBin bin : factoryState.bins.values()) // Bins
			bin.draw(g, factoryState.timeElapsed, false);
		
		for (GUIGantry gantry : factoryState.gantries.values()) // Gantry
			gantry.draw(g, factoryState.timeElapsed);
		
		
		g.dispose();
		return factoryImg;
	}
	
}










