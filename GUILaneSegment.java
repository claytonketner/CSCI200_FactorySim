import java.awt.Graphics2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUILaneSegment implements Serializable
{
	public Movement movement;
	
	public GUILaneSegment(Movement movement)
	{
		this.movement = movement;
	}
	
	public void draw(Graphics2D g, long currentTime)
	{
		// Draw with width 60
		Painter.draw(g, Painter.ImageEnum.LANE, 61, -1, currentTime, movement, false);
	}
}
