import java.io.*;

/** networking message specifying kits to produce in factory */
public class ProduceKitsMsg implements Serializable {
	/** kit number corresponding to type of kit to produce */
	public int kitNumber;
	/** how many new kits to produce */
	public int howMany;

	public ProduceKitsMsg(int newKitNumber, int newHowMany) {
		kitNumber = newKitNumber;
		howMany = newHowMany;
	}
}
