import java.io.*;
import java.util.*;

/** generic class updating the state of all items of specified type T */
public class ItemUpdateMsg<T> implements Serializable {
	/** TreeMap in which key is ID of new or updated item and entry is new or updated item */
	public TreeMap<Integer, T> putItems;
	/** ArrayList containing IDs of deleted items */
	public ArrayList<Integer> removeItems;

	/** constructor to instantiate empty instance variables */
	public ItemUpdateMsg() {
		putItems = new TreeMap<Integer, T>();
		removeItems = new ArrayList<Integer>();
	}

	/** update specified TreeMap of items with changes specified in this message */
	public void apply(TreeMap<Integer, T> items) {
		items.putAll(putItems);
		for (int i : removeItems) {
			items.remove(i);
		}
	}
}
