import java.io.*;
import java.util.*;

/** generic class updating the state of all items of specified type T */
public class ItemUpdateMsg<T> implements Serializable {
	public TreeMap<Integer, T> putItems;
	public ArrayList<Integer> removeItems;

	public void apply(TreeMap<Integer, T> items) {
		items.putAll(putItems);
		for (int i : removeItems) {
			items.remove(i);
		}
	}
}
