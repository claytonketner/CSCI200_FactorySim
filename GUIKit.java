import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;


@SuppressWarnings("serial")
public class GUIKit implements GUIItem, Serializable
{
	public Kit kit;
	public Movement movement;
	
	
	public GUIKit(Kit kit, double x, double y)
	{
		this.kit = kit;
		movement = new Movement(new Point2D.Double(x,y), 0);
	}
	
	public GUIKit(Kit kit, Movement movement)
	{
		this.kit = kit;
		this.movement = movement;
	}
	
	
	public void draw(Graphics2D g, long currentTime)
	{
		GUIPart guiPart;
		Painter.draw(g, Painter.ImageEnum.KIT, currentTime, movement, true);
		for ( Map.Entry<Integer, Part> part : kit.parts.entrySet() ) {
			if ( part.getKey() < 4 ) {
				guiPart = new GUIPart( part.getValue(), movement.calcPos(currentTime).x - 41.625 + ( part.getKey() ) * 27.75 ,
				                       movement.calcPos(currentTime).y - 17.5 );
			}
			else {
				guiPart = new GUIPart( part.getValue(), movement.calcPos(currentTime).x - 41.625 + ( part.getKey() - 4 ) * 27.75 ,
				                       movement.calcPos(currentTime).y + 17.5 );
			}
			guiPart.draw(g, currentTime);
		}
	}

	/** setter for movement */
	public void setMove(Movement movement)
	{
		this.movement = movement;
	}

	/** getter for movement */
	public Movement getMove() {
		return movement;
	}
}
