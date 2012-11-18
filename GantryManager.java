import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GantryManager extends JPanel {
	
	public static final int UPDATE_RATE = 50;
	
	private GantryClient client;
	private FactoryPainter painter;

	
	public GantryManager(GantryClient client)
	{
		this.client = client;
		setLayout(new BorderLayout());
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
		
		BufferedImage factoryImg = painter.drawFactoryArea(FactoryPainter.FactoryArea.FEEDER_MANAGER);
		g.drawImage(factoryImg, 0, 0, null);
	}
}