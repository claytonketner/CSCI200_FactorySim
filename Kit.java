import java.io.Serializable;
import java.util.ArrayList;


public class Kit implements Serializable
{
	// Kit statuses
	public static final int INCOMPLETE = 0;
	public static final int COMPLETE = 1;
	public static final int INQUEUE = 2;
	public static final int PARTMISSING = 3;
	
	private int number;
	private String name, description;
	private ArrayList<Part> partsNeeded;
	private int kitStatus; // Use kit statuses above

	/**
	 * Constructor of empty kit for use on Kit Delivery Station
	 * when conveyor brings in empty kits.
	 * 
	 */
	public Kit()
	{
		name = "";
		description = "";
		number = 0;
		partsNeeded = new ArrayList<Part>();
	}
		
	public Kit(String name, String description, int kitNumber)
	{
		this.name = name;
		this.description = description;
		this.number = kitNumber;
		partsNeeded = new ArrayList<Part>();
	}
}
