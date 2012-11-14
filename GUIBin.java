import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

@SuppressWarnings("serial")
public class GUIBin implements Serializable {
	public Movement movement;
	public GUIPart part;
	public Bin bin;
	
	public GUIBin( GUIPart gp, Bin bin, double x, double y )
	{
		part = gp;
		movement = new Movement(new Point2D.Double(x,y), 0);
		this.bin = bin;
	}
	
	public void draw( Graphics2D g, long currentTime ){
		Painter.draw(g, Painter.ImageEnum.PARTS_BOX, currentTime, movement, false);
	}
}