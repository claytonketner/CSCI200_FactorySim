import java.io.Serializable;


@SuppressWarnings("serial")
/** This class defines and controls a kit robot. */
public class KitRobot implements Serializable {
	/** a Kit variable of the kit in its pallet */
	private Kit kit;

	/** initialize variables */
	public KitRobot() {
		kit = null;
	}
	
	public void setKit(Kit kit)
	{
		if (this.kit == null)
			this.kit = kit;
		else
			throw new IllegalArgumentException("Cannot give the kit robot another kit! It is already holding one.");
	}
	/** remove kit from the robot */
	public Kit removeKit()
	{
		Kit tempKit = kit;
		kit = null;
		return tempKit;
	}

	public Kit getKit()
	{
		return kit;
	}
}
