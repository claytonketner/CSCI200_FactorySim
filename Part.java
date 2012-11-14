import java.io.Serializable;


@SuppressWarnings("serial")
public class Part implements Serializable 
{
	private String name, description;
	private int number; 
	public GUIPart link;  //MUST be public- do not change
	
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
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public void setNumber(int number)
	{
		this.number = number;
	}
	
	public void linkWithPart(GUIPart gui)
	{
		this.link = gui;
	}
}
