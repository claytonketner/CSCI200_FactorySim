import java.io.*;

/** networking message indicating to delete a part */
public class DeletePartMsg implements Serializable {
	/** part number of part to delete */
	public int number;

	public DeletePartMsg(int delNumber) {
		number = delNumber;
	}
}
