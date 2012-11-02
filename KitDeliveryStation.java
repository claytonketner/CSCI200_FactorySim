import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class KitDeliveryStation implements Serializable {
	GUIKitDeliveryStation guiKitDeliveryStation;
	ArrayList<Pallet> pallets;
	
	public KitDeliveryStation() {
		guiKitDeliveryStation = new GUIKitDeliveryStation();
		pallets = new ArrayList<Pallet>();
	}
}
