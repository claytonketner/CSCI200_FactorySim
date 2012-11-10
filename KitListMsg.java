import java.io.*;
import java.util.*;

/** networking message listing all available kits */
public class KitListMsg implements Serializable {
	/** ArrayList of available kits */
	public ArrayList<Kit> kits;

	public KitListMsg(ArrayList<Kit> newKits) {
		kits = newKits;
	}
}
