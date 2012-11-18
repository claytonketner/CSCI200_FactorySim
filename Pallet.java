import java.io.Serializable;


@SuppressWarnings("serial")
public class Pallet implements Serializable {
	private Kit kit;
	
	public Pallet(Kit kit) {
		this.kit = kit;
	}
	
	public void addKit(Kit kit)
	{
		if (this.kit == null)
			this.kit = kit;
		else
			throw new IllegalArgumentException("Cannot add a kit to this pallet -- it already has one");
	}
	
	public boolean hasKit()
	{
		return (kit != null);
	}
	
	public Kit removeKit()
	{
		Kit tempKit = kit;
		kit = null;
		return tempKit;
	}

	/** getter for kit */
	public Kit getKit()
	{
		return kit;
	}
}
