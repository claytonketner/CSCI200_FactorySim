import java.io.Serializable;


@SuppressWarnings("serial")
public class KitRobot implements Serializable {
	private Kit kit;

	
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
