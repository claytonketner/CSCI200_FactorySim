import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIPallet implements Serializable
{
	public Pallet pallet;
	public Movement movement;
	
	
	public GUIPallet(Pallet pallet, double x, double y)
	{
		this.pallet = pallet;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIPallet(Pallet pallet, Movement movement)
	{
		this.pallet = pallet;
		this.movement = movement;
	}

	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.PALLET, currentTime, movement);
	}
}
