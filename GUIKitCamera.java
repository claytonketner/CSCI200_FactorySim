import java.io.Serializable;


@SuppressWarnings("serial")
public class GUIKitCamera implements Serializable {
	KitCamera kitCamera;
	
	public GUIKitCamera ( KitCamera kitCamera ) {
		this.kitCamera = kitCamera;
	}
}
