import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class KitDeliveryStation implements Serializable {
	ArrayList<Pallet> pallets;
	
	public KitDeliveryStation() {
		pallets = new ArrayList<Pallet>();
	}
}
