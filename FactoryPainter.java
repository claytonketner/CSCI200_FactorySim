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
	
	/**
	 * Returns a 800,600 image of the Kit Assembly View
	 * @param currentTime
	 * @return
	 */
	public BufferedImage drawKitAssembly()
	{
		state.updateTime();
		
		BufferedImage factoryImg = new BufferedImage(1600, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = factoryImg.createGraphics();

		for (GUIItem item : state.items.values())
		{
			if(item instanceof GUIKitDeliveryStation)
			item.draw(g, state.timeElapsed);
			if(item instanceof GUIPallet)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUINest)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUIKitStand)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUIKitRobot)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUIPartRobot)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUIKit)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUIPart)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUIKitCamera)
				item.draw(g, state.timeElapsed);
			if(item instanceof GUIFlash)
				item.draw(g, state.timeElapsed);
		}
			
		
		
		g.dispose();
		return factoryImg;
	}
	
}










