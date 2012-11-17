import java.awt.Graphics2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIKitCamera implements GUIItem, Serializable 
{
	KitCamera kitCamera;
	Movement movement;
	long birthTime, lifeLength;
	
	public GUIKitCamera ( KitCamera kitCamera, Movement movement, long currentTime, long lifeLength ) {
		this.kitCamera = kitCamera;
		this.movement = movement;
		this.birthTime = currentTime;
		this.lifeLength = lifeLength;
	}
	
	public boolean isExpired(long currentTime)
	{
		if (birthTime - currentTime > lifeLength)
			return true;
		return false;
	}
	
	public void draw(Graphics2D g, long currentTime)
	{
		if (!isExpired(currentTime))
			Painter.draw(g, Painter.ImageEnum.CAMERA_FLASH, currentTime, movement, true);
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
