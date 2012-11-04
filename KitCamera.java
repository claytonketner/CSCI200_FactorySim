import java.io.Serializable;

/**
 * Incomplete class only for v.0
 * Does not check kit completeness
 * 
 *
 */
@SuppressWarnings("serial")
public class KitCamera implements Serializable {
	//Kit currentKit;
	boolean takingPicture;

	public KitCamera () {
		takingPicture = false;
	}
	
	public void takePicture() {
		takingPicture = true;
	}
}
