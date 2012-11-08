import java.io.*;
import java.util.*;

/** networking message listing all available parts */
public class PartListMsg implements Serializable {
	/** ArrayList of available parts */
	public ArrayList<Part> parts;

	public PartListMsg(ArrayList<Part> newParts) {
		parts = newParts;
	}
}
