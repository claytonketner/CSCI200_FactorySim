import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIPallet implements GUIItem, Serializable
{
	public Pallet pallet;
	public Movement movement;
	
	private GUIKit guiKit;
	
	
	public GUIPallet(Pallet pallet, GUIKit guiKit, double x, double y)
	{
		this.pallet = pallet;
		this.guiKit = guiKit;
		movement = new Movement(new Point2D.Double(x,y), Math.PI/2);
	}
	
	public GUIPallet(Pallet pallet, GUIKit guiKit, Movement movement)
	{
		this.pallet = pallet;
		this.guiKit = guiKit;
		this.movement = movement;
	}
	
	public void addKit(GUIKit guiKit)
	{
		if (this.guiKit == null)
			this.guiKit = guiKit;
		else
			System.err.println("Cannot add a kit to this pallet -- it already has one");
	}
	
	public boolean hasKit()
	{
		return !(guiKit == null);
	}
	
	public GUIKit removeKit()
	{
		GUIKit tempKit = guiKit;
		guiKit = null;
		return tempKit;
	}

	public void draw(Graphics2D g, long currentTime)
	{
		Painter.draw(g, Painter.ImageEnum.PALLET, 80, -1, currentTime, movement, false);
		if (guiKit == null)
			return;
		
		guiKit.movement = movement.offset(new Point2D.Double(40, 60), -Math.PI / 2);
		guiKit.draw(g, currentTime);
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
