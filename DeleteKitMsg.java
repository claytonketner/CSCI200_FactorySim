import java.io.*;

/** networking message indicating to delete a kit */
public class DeleteKitMsg implements Serializable {
	/** kit number of kit to delete */
	public int number;

	public DeleteKitMsg(int delNumber) {
		number = delNumber;
	}
}
