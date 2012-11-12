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
	
	private void performChecks(long currentTime)
	{
		// Remove expired kit cameras
		GUIKitCamera[] cameras = (GUIKitCamera[]) factoryState.kitCameras.values().toArray();
		Integer[] keys = (Integer[]) factoryState.kitCameras.keySet().toArray();
		
		for (int i = 0; i<factoryState.kitCameras.size(); i++)
		{
			GUIKitCamera camera = cameras[i];
			if (camera.isExpired(currentTime))
				factoryState.kitCameras.remove(keys[i]);
		}
	}
	
	public BufferedImage drawEntireFactory(long currentTime)
	{
		performChecks(currentTime);
		
		BufferedImage factoryImg = new BufferedImage(1600, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = factoryImg.createGraphics();
		
		for (GUILane lane : factoryState.lanes.values()) // Lanes
			lane.draw(g, currentTime);
		
		for (GUIDiverterArm diverterArm : factoryState.diverterArms.values()) // Diverters
		{
			// Draw the diverter below the diverter arm
			(new GUIDiverter(diverterArm.movement.calcPos(currentTime).x, diverterArm.movement.calcPos(currentTime).y)).draw(g, currentTime);
			diverterArm.draw(g, currentTime);
		}
		
		for (GUIFeeder feeder : factoryState.feeders.values()) // Feeder
			feeder.draw(g, currentTime);
		
		
		return factoryImg;
	}
	
}










