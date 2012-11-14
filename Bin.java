import java.util.ArrayList;


public class Bin 
{
	public Part part;
	private boolean binEmpty = false;
	
	public Bin(Part p)
	{
		part = new Part(p.getName(), p.getDescription(), p.getNumber());
	}
	
	public void setPart( Part p )
	{
		part.setDescription(p.getDescription());
		part.setName(p.getName());
		part.setNumber(p.getNumber());
	}

	public void dumpBin()
	{
		binEmpty = true;
	}
}
