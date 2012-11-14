import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;


@SuppressWarnings("serial")
public class GUIKit implements Serializable
{
	public Kit kit;
	public Movement movement;
	public TreeMap<Integer, GUIPart> parts; // this variable is deprecated, do not use in new code, use parts in non-gui Kit class instead
						// for Clayton: I suggest reusing the movement in the GUIKit class for drawing the parts in the kit, but call offset() to offset it by the correct amount
	
	
	public GUIKit(Kit kit, double x, double y)
	{
		this.kit = kit;
		kit.linkWithKit(this);  //easy access from corresponding Kit object
		movement = new Movement(new Point2D.Double(x,y), 0);
		parts = new TreeMap<Integer, GUIPart>();
	}

	public void addPart( Integer index, GUIPart part ) {
		part.movement = movement;
		if ( index < 4 ) {
			part.movement = new Movement( new Point2D.Double( movement.getStartPos().x - 41.625 + ( index ) * 27.75 , movement.getStartPos().y - 17.5 ), 0 ); 
		}
		else if ( index > 3 ) {
			part.movement = new Movement( new Point2D.Double( movement.getStartPos().x - 41.625 + ( index - 4 ) * 27.75 , movement.getStartPos().y + 17.5 ), 0 );
		}
		parts.put( index, part );
		//kit.addPart(index, part.part);
	}
	
	public GUIPart removePart( Integer index ) {
		//kit.removePart(index);
		return parts.remove( index );
	}
	
	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.KIT, currentTime, movement, true);
		for ( Map.Entry<Integer, GUIPart> part : parts.entrySet() ) {
			part.getValue().draw(g, currentTime);
		}
	}
}
