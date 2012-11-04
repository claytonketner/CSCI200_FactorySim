import java.io.Serializable;

/**
 * Incomplete class only for v.0
 * Does not check kit completeness
 * 
 * @author Justin
 *
 */
@SuppressWarnings("serial")
public class KitCamera implements Serializable {
	//Kit currentKit;
	boolean takingPicture;
	GUIKitCamera guiKitCamera;

	public KitCamera () {
		takingPicture = false;
		guiKitCamera = new GUIKitCamera();
	}
	
	public void takePicture() {
		takingPicture = true;
	}
}
