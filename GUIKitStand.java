import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.io.Serializable;
import java.util.TreeMap;


/** Contains data and methods for drawing and animating a kit stand */
@SuppressWarnings("serial")
public class GUIKitStand implements GUIItem, Serializable
{
	/** used to access kit stand data  */
	private KitStand kitStand;
	/** the kit location being stored in the station  */
	private TreeMap<StationNumber, GUIKit> kits;
	/** used to access movement data  */
	public Movement movement;
	/** the location in the station  */
	public static enum StationNumber {
		ONE, TWO, THREE
	}
	/** Constructor  */
	public GUIKitStand(KitStand kitStand)
	{
		this.kitStand = kitStand;
		this.movement = new Movement(new Point2D.Double(100, 300), 0);
		kits = new TreeMap<StationNumber, GUIKit>();
	}
	/** add kit to the specific location in the station  */
	public void addKit(GUIKit guiKit, StationNumber snum)
	{
		if (kits.get(snum) == null)
			kits.put(snum, guiKit);
		else
			System.err.println("Couldn't put a kit into station number " + snum.toString() + ". There is a kit already there!");
	}
	/** remove kit from the station  */
	public GUIKit removeKit(StationNumber snum)
	{
		GUIKit tempKit = kits.get(snum);
		kits.put(snum, null);
		return tempKit;
	}
	/** return the kit in the specific location in the station  */
	public GUIKit getKit(StationNumber snum)
	{
		return kits.get(snum);
	}
	/** draw the kits in the station  */
	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.KIT_TABLE, 175, -1, currentTime, movement, true);
		for (StationNumber snum : kits.keySet())
		{
			GUIKit kit = getKit(snum);
			if (kit != null)
			{
				int yOffset = 0;
				if (snum == StationNumber.ONE)
					yOffset = -90;
				if (snum == StationNumber.THREE)
					yOffset = 90;
				
				kit.movement = movement.offset(new Point2D.Double(0, yOffset), 0);
				kit.draw(g, currentTime);
			}
		}
	}
	/** return camera station  */
	public Point2D.Double getCameraStationLocation()
	{
		return new Point2D.Double(movement.getStartPos().x + 175/2, movement.getStartPos().y + 300/2);
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

















