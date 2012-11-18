import java.io.Serializable;


@SuppressWarnings("serial")
public class Part implements Serializable 
{
	private String name, description;
	private int number;
	private Painter.ImageEnum image;
	
	public Part() {
		name = "";
		description = "";
		number = 0;
		image = Painter.ImageEnum.CORNFLAKE;
	}
	
	public Part(String name, String description, int partNumber, Painter.ImageEnum image)
	{
		this.name = name;
		this.description = description;
		this.number = partNumber;
		this.image = image;
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

	public Painter.ImageEnum getImage()
	{
		return image;
	}

	public void setImage(Painter.ImageEnum image) {
		this.image = image;
	}
}
