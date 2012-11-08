import java.io.*;
import java.util.*;

/** networking message updating factory state */
public class FactoryUpdateMsg implements Serializable {
	ItemUpdateMsg<GUIPart> parts;
	ItemUpdateMsg<GUIKit> kits;

	TreeMap<Integer, Movement> partMoves;
	TreeMap<Integer, Movement> kitMoves;
}
