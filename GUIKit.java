import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.TreeMap;

import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class GUIKit implements Serializable
{
	public Kit kit;
	public Movement movement;
	
	
	public GUIKit(Kit kit, double x, double y)
	{
		this.kit = kit;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}

	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.KIT, currentTime, movement);
	}
}
