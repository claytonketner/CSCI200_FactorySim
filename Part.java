import java.io.Serializable;


@SuppressWarnings("serial")
public class Part implements Serializable 
{
	private String name, description;
	private int number; 
	
	public Part() {
		name = "";
		description = "";
		number = 0;
	}
	
	public Part(String name, String description, int partNumber)
	{
		this.name = name;
		this.description = description;
		this.number = partNumber;
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
}
