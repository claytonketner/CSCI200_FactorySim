import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;


@SuppressWarnings("serial")
public class Kit implements Serializable
{
	// Kit statuses
	public static final int INCOMPLETE = 0; // all kits initialized to incomplete
	public static final int INCORRECT = 1; // used when kit contains incorrect part in any location
	public static final int COMPLETE = 2; // signifies completed kit with correct parts
	
	private int number;
	private String name, description;
	private int kitStatus; // Use kit statuses above
	public TreeMap<Integer, Part> parts = new TreeMap<Integer, Part>(); //ArrayList of parts contained in kit

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
		kitStatus = INCOMPLETE;
	}
		
	public Kit(String name, String description, int kitNumber)
	{
		this.name = name;
		this.description = description;
		this.number = kitNumber;
		kitStatus = INCOMPLETE;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public int getStatus()
	{
		return kitStatus;
	}
	
	public boolean addPart(int index, Part p)
	{
		if(parts.size() <= 8)
		{
			parts.put(index, p);
			return true;
		}
		else
		{
			return false;
		}	
	}
//	public boolean removePart()
//	{
//		return
//	}
}
