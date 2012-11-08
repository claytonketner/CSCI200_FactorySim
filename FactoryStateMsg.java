import java.io.*;
import java.util.*;

/** networking message containing all information needed to generate factory state
    note that if a client sends an empty FactoryStateMsg, it means they are requesting to be kept up-to-date with the factory state as long as it is connected to the server */
public class FactoryStateMsg implements Serializable {
	public TreeMap<Integer, GUIPart> parts;
	public TreeMap<Integer, GUIKit> kits;

	/** updates the factory state */
	public void update(FactoryUpdateMsg msg) {
		msg.parts.apply(parts);
		msg.kits.apply(kits);
		for (Map.Entry<Integer, Movement> e : msg.partMoves.entrySet()) {
			parts.get(e.getKey()).movement = e.getValue();
		}
		for (Map.Entry<Integer, Movement> e : msg.kitMoves.entrySet()) {
                        kits.get(e.getKey()).movement = e.getValue();
                }
	}
}
