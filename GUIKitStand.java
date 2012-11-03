import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;
import java.util.TreeMap;


@SuppressWarnings("serial")
public class GUIKitStand implements Serializable
{
	private KitStand kitStand;
	private TreeMap<StationNumber, GUIKit> kits;
	private Movement movement;
	
	public enum StationNumber {
		ONE, TWO, THREE
	}
	
	public GUIKitStand(KitStand kitStand)
	{
		this.kitStand = kitStand;
		this.movement = new Movement(new Point2D.Double(100, 300), 0);
	}
	
	public void addKit(GUIKit guiKit, StationNumber snum)
	{
		if (kits.get(snum) == null)
			kits.put(snum, guiKit);
		else
			System.err.println("Couldn't put a kit into station number " + snum.toString() + ". There is a kit already there!");
	}
	
	public GUIKit removeKit(StationNumber snum)
	{
		GUIKit tempKit = kits.get(snum);
		kits.put(snum, null);
		return tempKit;
	}
	
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.KIT_TABLE, 175, -1, currentTime, movement, true);
	}
}

















