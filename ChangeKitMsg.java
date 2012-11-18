import java.io.*;

/** networking message indicating to change a kit */
public class ChangeKitMsg implements Serializable {
	/** old number of kit to change */
	public int oldNumber;
	/** replacement kit */
	public Kit kit;
	/** constructor to set up ChangeKitMsg with old number of Kit and new Kit */
	public ChangeKitMsg(int oldNumberVal, Kit newKit) {
		oldNumber = oldNumberVal;
		kit = newKit;
	}
}
