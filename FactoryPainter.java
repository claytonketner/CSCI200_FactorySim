import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class FactoryPainter 
{
	// Use painter static methods to scale and crop the images
	
	private FactoryStateMsg factoryState;
	private int timeOffset;
	
	public enum FactoryArea {
		KIT_MANAGER, PART_MANAGER, LANE_MANAGER
	}
	
	public FactoryPainter()
	{
		this.factoryState = null;
		timeOffset = 0;
	}
	
	public FactoryPainter(FactoryStateMsg factoryState)
	{
		this.factoryState = factoryState;
		timeOffset = 0;
	}
	
	public void setTimeOffset(int timeOffset)
	{
		this.timeOffset = timeOffset;
	}
	
	public void update(FactoryUpdateMsg updateMsg)
	{
		factoryState.update(updateMsg);
	}
	
	public void setFactoryState(FactoryStateMsg factoryState)
	{
		this.factoryState = factoryState;
	}

	public BufferedImage drawEntireFactory(long currentTime)
	{
		currentTime = currentTime + timeOffset;
		performChecks(currentTime);
		
		BufferedImage factoryImg = new BufferedImage(1600, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = factoryImg.createGraphics();
		
		for (GUINest nest : factoryState.nests.values()) // Nests
			nest.draw(g, currentTime);
		
		for (GUILane lane : factoryState.lanes.values()) // Lanes
			lane.draw(g, currentTime);
		
		for (GUIDiverterArm diverterArm : factoryState.diverterArms.values()) // Diverters
		{
			// Draw the diverter below the diverter arm
			(new GUIDiverter(diverterArm.movement.calcPos(currentTime).x+42, 
							 diverterArm.movement.calcPos(currentTime).y)).draw(g, currentTime);
			diverterArm.draw(g, currentTime);
		}
		
		for (GUIFeeder feeder : factoryState.feeders.values()) // Feeder
			feeder.draw(g, currentTime);
		
		for (GUIKitStand kitStand : factoryState.kitStands.values()) // Kit stand
			kitStand.draw(g, currentTime);
		
		for (GUIKitDeliveryStation kitDeliveryStation : factoryState.kitDeliveryStations.values()) // Kit delivery station
			kitDeliveryStation.draw(g, currentTime);
		
		for (GUIPartRobot partRobot : factoryState.partRobots.values()) // Part robot
			partRobot.draw(g, currentTime);
		
		for (GUIKitRobot kitRobot : factoryState.kitRobots.values()) // Kit robot
			kitRobot.draw(g, currentTime);
		
		for (GUIBin bin : factoryState.bins.values()) // Bins
			bin.draw(g, currentTime, false);
		
		for (GUIGantry gantry : factoryState.gantries.values()) // Gantry
			gantry.draw(g, currentTime);
		
		
		g.dispose();
		return factoryImg;
	}
	
	
	private void performChecks(long currentTime)
	{
		// Remove expired kit cameras
		if (factoryState.kitCameras.size() == 0)
			return;

		GUIKitCamera[] cameras = (GUIKitCamera[]) factoryState.kitCameras.values().toArray();
		Integer[] keys = (Integer[]) factoryState.kitCameras.keySet().toArray();

		for (int i = 0; i<factoryState.kitCameras.size(); i++)
		{
			GUIKitCamera camera = cameras[i];
			if (camera.isExpired(currentTime))
				factoryState.kitCameras.remove(keys[i]);
		}
	}
}










