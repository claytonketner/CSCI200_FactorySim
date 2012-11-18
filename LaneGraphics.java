import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class LaneGraphics extends JPanel {
	public static final int UPDATE_RATE = 50;
	
	private FactoryPainter painter;
	
	public LaneGraphics() {
		//add( new JLabel("GRAPHICS"));
		this.setPreferredSize(new Dimension(1600, 800));
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
		
		BufferedImage factoryImg = painter.drawEntireFactory();
		g.drawImage(factoryImg, -550, -100, null);
		
		//g.drawString("x: " + mouseX + " Y: " + mouseY, 1500, 790);
	}
}
