import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Kit implements Serializable
{
	// Kit statuses
	public static final int INCOMPLETE = 0; // all kits initialized to incomplete
	public static final int INCORRECT = 1; // used when kit contains incorrect part in any location
	public static final int COMPLETE = 2; // signifies completed kit with correct parts
	
	private int number;
	private String name, description;
	private ArrayList<Part> parts;
	private int kitStatus; // Use kit statuses above

	/**
	 * Constructor of empty kit for use on Kit Delivery Station
	 * when conveyer brings in empty kits.
	 * 
	 */
	public Kit()
	{
		name = "";
		description = "";
		number = 0;
		parts = new ArrayList<Part>();
		kitStatus = 0;
	}
		
	public Kit(String name, String description, int kitNumber)
	{
		this.name = name;
		this.description = description;
		this.number = kitNumber;
		parts = new ArrayList<Part>();
		kitStatus = 0;
	}
	
	public void addPart( Part part ) {
		parts.add( part );
	}
}
