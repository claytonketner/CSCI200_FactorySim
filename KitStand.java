import java.io.Serializable;
import java.util.TreeMap;


@SuppressWarnings("serial")
public class KitStand implements Serializable
{
	TreeMap<Integer,Kit> kits; // 0-1 are positions for incomplete kits, 2 is the inspection position
	GUIKitCamera guiKitCamera;
	
	public KitStand()
	{
		kits = new TreeMap<Integer,Kit>();
		//guiKitCamera = new GUIKitCamera( new KitCamera() );  //commented out for v.0
	}
}
