import java.io.*;
import java.util.*;

/** networking message listing status of all kits in production */
public class ProduceStatusMsg implements Serializable {
	/** possible statuses that a kit command can have */
	public enum KitStatus {
		QUEUED, PRODUCTION, COMPLETE
	}

	/** ArrayList of ProduceKitsMsg’s that have been sent to server */
	public ArrayList<ProduceKitsMsg> kitCmds;
	/** ArrayList indicating status of each kit command */
	public ArrayList<KitStatus> kitStatus;

	/** constructor for empty ProduceStatusMsg */
	public ProduceStatusMsg() {
		kitCmds = new ArrayList<ProduceKitsMsg>();
		kitStatus = new ArrayList<KitStatus>();
	}
}
