import java.io.*;

/** networking message indicating to change a part */
public class ChangePartMsg implements Serializable {
	/** old number of part to change */
	public int oldNumber;
	/** replacement part */
	public Part part;

	public ChangePartMsg(int oldNumberVal, Part newPart) {
		oldNumber = oldNumberVal;
		part = newPart;
	}
}
