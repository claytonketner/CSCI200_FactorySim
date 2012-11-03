import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIPallet implements Serializable
{
	public Pallet pallet;
	public Movement movement;
	
	private GUIKit guiKit;
	
	
	public GUIPallet(Pallet pallet, GUIKit guiKit, double x, double y)
	{
		this.pallet = pallet;
		this.guiKit = guiKit;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIPallet(Pallet pallet, GUIKit guiKit, Movement movement)
	{
		this.pallet = pallet;
		this.guiKit = guiKit;
		this.movement = movement;
	}

	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.PALLET, currentTime, movement);
	}
}
