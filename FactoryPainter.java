import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class FactoryPainter 
{
	// Use painter static methods to scale and crop the images
	
	private FactoryStateMsg state;
	
	public enum FactoryArea {
		KIT_MANAGER, PART_MANAGER, LANE_MANAGER
	}
	
	public FactoryPainter(FactoryStateMsg factoryState)
	{
		this.state = factoryState;
	}
	
	public void update(FactoryUpdateMsg updateMsg)
	{
		state.update(updateMsg);
	}
	
	private void performChecks()
	{
		// Remove expired kit cameras
		if (state.kitCameras.size() == 0)
			return;

		GUIKitCamera[] cameras = (GUIKitCamera[]) state.kitCameras.values().toArray();
		Integer[] keys = (Integer[]) state.kitCameras.keySet().toArray();

		for (int i = 0; i<state.kitCameras.size(); i++)
		{
			GUIKitCamera camera = cameras[i];
			if (camera.isExpired(state.timeElapsed))
				state.kitCameras.remove(keys[i]);
		}
	}

	public BufferedImage drawEntireFactory()
	{
		state.updateTime();
		performChecks();
		
		BufferedImage factoryImg = new BufferedImage(1600, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = factoryImg.createGraphics();
		
		for (GUINest nest : state.nests.values()) // Nests
			nest.draw(g, state.timeElapsed);
		
		for (GUILane lane : state.lanes.values()) // Lanes
			lane.draw(g, state.timeElapsed);
		
		for (GUIDiverterArm diverterArm : state.diverterArms.values()) // Diverters
		{
			// Draw the diverter below the diverter arm
			(new GUIDiverter(diverterArm.movement.calcPos(state.timeElapsed).x+42, 
							 diverterArm.movement.calcPos(state.timeElapsed).y)).draw(g, state.timeElapsed);
			diverterArm.draw(g, state.timeElapsed);
		}
		
		for (GUIFeeder feeder : state.feeders.values()) // Feeder
			feeder.draw(g, state.timeElapsed);
		
		for (GUIKitStand kitStand : state.kitStands.values()) // Kit stand
			kitStand.draw(g, state.timeElapsed);
		
		for (GUIKitDeliveryStation kitDeliveryStation : state.kitDeliveryStations.values()) // Kit delivery station
			kitDeliveryStation.draw(g, state.timeElapsed);
		
		for (GUIPartRobot partRobot : state.partRobots.values()) // Part robot
			partRobot.draw(g, state.timeElapsed);
		
		for (GUIKitRobot kitRobot : state.kitRobots.values()) // Kit robot
			kitRobot.draw(g, state.timeElapsed);
		
		for (GUIBin bin : state.bins.values()) // Bins
			bin.draw(g, state.timeElapsed, false);
		
		for (GUIGantry gantry : state.gantries.values()) // Gantry
			gantry.draw(g, state.timeElapsed);
		
		
		g.dispose();
		return factoryImg;
	}
	
}










