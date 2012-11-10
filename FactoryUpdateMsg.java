import java.io.*;
import java.util.*;

/** networking message updating factory state */
public class FactoryUpdateMsg implements Serializable {
	public ItemUpdateMsg<GUIPart> parts;
	public ItemUpdateMsg<GUIKit> kits;

	public TreeMap<Integer, Movement> partMoves;
	public TreeMap<Integer, Movement> kitMoves;

	/** constructor to instantiate empty instance variables */
	public FactoryUpdateMsg() {
		parts = new ItemUpdateMsg<GUIPart>();
		kits = new ItemUpdateMsg<GUIKit>();
		partMoves = new TreeMap<Integer, Movement>();
		kitMoves = new TreeMap<Integer, Movement>();
	}
}
