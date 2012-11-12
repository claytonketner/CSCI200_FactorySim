import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class FactoryProductionViewPanel extends JPanel{
	
	//Merge with Clayton's FactoryManagerView 
	
	/**
	 * Member Data:
	 * FactoryPainter factoryPainter
	 * long timeOffset = 0
	 * 
	 */
	
	private FactoryPainter factoryPainter;
	private long timeOffset = 0;

	//the following is Clayton's code for full-view factory 
	public FactoryProductionViewPanel(FactoryStateMsg factoryState)
	{
		this.setPreferredSize(new Dimension(1600, 800));
		factoryPainter = new FactoryPainter(factoryState);
	}
	
	/** Used to set the time difference (in milliseconds) between the server time and the client time */
	public void setTimeOffset(long offset)
	{
		this.timeOffset = offset;
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		long currentTime = System.currentTimeMillis() + timeOffset;
		
		BufferedImage factoryImg = factoryPainter.drawEntireFactory(currentTime);
		g.drawImage(factoryImg, 0, 0, null);
	}
	
	
	

}
