import java.awt.Graphics2D;

/** implemented by paintable factory items that are part of the factory state */
public interface GUIItem {
	/** draw the item at the specified time */
	public void draw(Graphics2D g, long currentTime);
	/** setter for movement */
	public void setMove(Movement movement);
	/** getter for movement */
	public Movement getMove();
}
