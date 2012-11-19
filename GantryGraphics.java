import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class GantryGraphics extends JPanel {
	public static final int UPDATE_RATE = 50;
	
	private FactoryPainter painter;
	
	public GantryGraphics() {
		//add( new JLabel("GRAPHICS"));
		this.setPreferredSize(FactoryPainter.getAreaSize(FactoryPainter.FactoryArea.GANTRY_MANAGER));
		painter = new FactoryPainter( new FactoryStateMsg() );
	}
	
	public void setFactoryState(FactoryStateMsg factoryState)
	{
		painter.setFactoryState(factoryState);
	}

	public void update(FactoryUpdateMsg updateMsg)
	{
		painter.update(updateMsg);
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D)gfx;
		
		BufferedImage factoryImg = painter.drawFactoryArea(FactoryPainter.FactoryArea.GANTRY_MANAGER);
		g.drawImage(factoryImg, 0, 0, null);
	}
}
