import java.io.Serializable;


@SuppressWarnings("serial")
public class Pallet implements Serializable {
	Kit kit;
	boolean hasKit;
	
	public Pallet() {
		kit = new Kit();
		hasKit = false;
	}
}
