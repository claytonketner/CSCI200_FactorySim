import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class FactoryPainter 
{
	// Use painter static methods to scale and crop the images
	
	private FactoryStateMsg state;
	
	public enum FactoryArea {
		KIT_MANAGER, PART_MANAGER, LANE_MANAGER
	}
	
	public FactoryPainter()
	{
		this.state = null;
	}
	
	public FactoryPainter(FactoryStateMsg factoryState)
	{
		this.state = factoryState;
	}
	
	public void update(FactoryUpdateMsg updateMsg)
	{
		state.update(updateMsg);
	}
	
	public void setFactoryState(FactoryStateMsg factoryState)
	{
		this.state = factoryState;
	}

	/**
	 * Returns a 1600, 800 image of the entire factory
	 * @param currentTime
	 * @return
	 */
	public BufferedImage drawEntireFactory()
	{
		state.updateTime();
		
		BufferedImage factoryImg = new BufferedImage(1600, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = factoryImg.createGraphics();

		for (GUIItem item : state.items.values())
		{
			item.draw(g, state.timeElapsed);
		}
		
		g.dispose();
		return factoryImg;
	}
	
	// TODO: move performChecks() to Server.java
	/*private void performChecks(long currentTime)
	{
		// Remove expired kit cameras
		if (state.kitCameras.size() == 0)
			return;

		GUIKitCamera[] cameras = (GUIKitCamera[]) state.kitCameras.values().toArray();
		Integer[] keys = (Integer[]) state.kitCameras.keySet().toArray();

		for (int i = 0; i<state.kitCameras.size(); i++)
		{
			GUIKitCamera camera = cameras[i];
			if (camera.isExpired(currentTime))
				state.kitCameras.remove(keys[i]);
		}
	}*/
}










