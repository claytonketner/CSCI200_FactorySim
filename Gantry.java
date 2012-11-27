import java.io.Serializable;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class Gantry implements Serializable
{
	
	/** Bin map for purging */
	public static TreeMap<Integer, GUIBin> purgeBinMap;
	/** Bin map for spare parts */
	public static TreeMap<Integer, GUIBin> sparePartsBinMap;
	/** Map of bin origin (each bin of parts is derived from this map) */
	public static TreeMap<Integer, GUIBin> originBinMap;
	/** Constructor */
	public Gantry() 
	{
		purgeBinMap = new TreeMap<Integer, GUIBin>();
		sparePartsBinMap = new TreeMap<Integer, GUIBin>();
		originBinMap = new TreeMap<Integer, GUIBin>();
	}
}
