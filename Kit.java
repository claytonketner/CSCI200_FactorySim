import java.util.ArrayList;


public class Kit 
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

	
	public Kit(String name, String description, int partNumber)
	{
		this.name = name;
		this.description = description;
		this.number = partNumber;
		
	}
}
