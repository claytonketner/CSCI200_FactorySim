import java.io.Serializable;


@SuppressWarnings("serial")
public class Pallet implements Serializable {
	GUIPallet guiPallet;
	Kit kit;
	boolean hasKit;
	
	public Pallet() {
		guiPallet = new GUIPallet();
		kit = new Kit();
		hasKit = false;
	}
}
