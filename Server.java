import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/** class implementing a non-GUI server application to coordinate factory clients over a network */
public class Server implements ActionListener, Networked {
	/** networking port that server listens on */
	public static final int PORT = 44247;
	/** interval between timer ticks in milliseconds */
	public static final int UPDATE_RATE = 200;

	/** server socket used to set up connections with clients */
	private ServerSocket serverSocket;
	/** ArrayList of client connections */
	private ArrayList<NetComm> netComms;
	/** whether each client wants to be updated with the factory state */
	private ArrayList<Boolean> wantsState;
	/** Part types that are available to produce */
	private ArrayList<Part> partTypes;
	/** Kit types that are available to produce */
	private ArrayList<Kit> kitTypes;
	/** current production status */
	private ProduceStatusMsg status;
	/** current factory state */
	private FactoryStateMsg state;
	/** factory state changes to broadcast to clients on next timer tick */
	private FactoryUpdateMsg update;

	/** constructor for server class */
	public Server() throws IOException {
		// initialize server socket
		try {
			serverSocket = new ServerSocket(PORT);
		}
		catch (IOException ex) {
			throw ex;
		}
		// instantiate lists
		netComms = new ArrayList<NetComm>();
		wantsState = new ArrayList<Boolean>();
		partTypes = new ArrayList<Part>();
		kitTypes = new ArrayList<Kit>();
		status = new ProduceStatusMsg();
		state = new FactoryStateMsg();
		update = new FactoryUpdateMsg();
		// initialize factory state (copied from FactoryPainterTest.java)
		int laneSeparation = 120;
		state.kitStands.put(new Integer(0), new GUIKitStand(new KitStand()));
		
		GUIKitDeliveryStation guiKitDeliv = new GUIKitDeliveryStation(new KitDeliveryStation(), 
		 		   new GUILane(new ComboLane(), false, 8, 350,-10), 
		 		   new GUILane(new ComboLane(), false, 3, 350-180, -10), 10, 10);
		guiKitDeliv.inConveyor.lane.turnOff();
		guiKitDeliv.outConveyor.lane.turnOff();
		 		   
		state.kitDeliveryStations.put(new Integer(0), guiKitDeliv);
											 
		
		state.kitRobots.put(new Integer(0), new GUIKitRobot(new KitRobot()));
		state.partRobots.put(new Integer(0), new GUIPartRobot(new PartRobot()));
		
		for (int i=0; i<4; i++)
		{
			state.nests.put(new Integer(i*2), new GUINest(new Nest(), 550, 120 + laneSeparation*i));
			state.nests.put(new Integer(i*2 + 1), new GUINest(new Nest(), 550, 120 + laneSeparation*i + 50));
			
			GUILane guiLane = new GUILane(new ComboLane(), true, 6, 630, 124 + laneSeparation*i);
			guiLane.lane.turnOff();
			
			state.lanes.put(new Integer(i), guiLane);
			state.diverterArms.put(new Integer(i), new GUIDiverterArm(990, 170 + laneSeparation*i));
			state.feeders.put(new Integer(i), new GUIFeeder(new Feeder(), 1165, 170 + laneSeparation*i));
		}
		// start update timer
		new javax.swing.Timer(UPDATE_RATE, this).start();
		System.out.println("Server is ready; press ctrl+C to exit");
		// wait for clients to connect
		while (true) { // loop exits when user presses ctrl+C
			try {
				Socket socket = serverSocket.accept();
				netComms.add(new NetComm(socket, this));
				wantsState.add(false);
				System.out.println("Client " + (netComms.size() - 1) + " has joined");
			}
			catch (Exception ex) {
				System.out.println("Error accepting new client connection");
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Server server;
		try {
			server = new Server();
		}
		catch (Exception ex) {
			System.out.println("Error initializing server:");
			ex.printStackTrace();
			return;
		}
	}

	/** called during timer tick; updates simulation and broadcasts factoryUpdate to clients */
	public void actionPerformed(ActionEvent e) {
		int i;
		// TODO: don't send/use/reset factoryUpdate if nothing new
		if (e.getSource() instanceof javax.swing.Timer) {
			for (i = 0; i < wantsState.size(); i++) {
				if (wantsState.get(i)) {
					netComms.get(i).write(update);
				}
			}
			state.update(update);
			update = new FactoryUpdateMsg();
		}
	}

	/** handle message received from clients */
	public void msgReceived(Object msgObj, NetComm sender) {
		int senderIndex;
		// find who sent the message
		for (senderIndex = 0; senderIndex < netComms.size(); senderIndex++) {
			if (sender == netComms.get(senderIndex)) break;
		}
		if (senderIndex == netComms.size()) {
			System.out.println("Warning: received message from unknown client: " + msgObj);
			return;
		}
		// handle message
		if (msgObj instanceof CloseConnectionMsg) {
			// close connection with client
			// (but don't call clients.get(i).close() because client might still receive the message and get confused)
			System.out.println("Client " + senderIndex + " has left");
			netComms.remove(senderIndex);
			wantsState.remove(senderIndex);
		}
		else if (msgObj instanceof String) {
			// broadcast message to all clients (for TestClient only, will delete later)
			for (int i = 0; i < netComms.size(); i++) {
				netComms.get(i).write("Message from " + senderIndex + " to " + i + ": " + (String)msgObj);
			}
		}
		else if (msgObj instanceof NewPartMsg) {
			// add a new part type
			if (addPart(senderIndex, (NewPartMsg)msgObj, true)) {
				System.out.println("Client " + senderIndex + " added a part");
			}
			else {
				System.out.println("Client " + senderIndex + " unsuccessfully tried to add a part");
			}
		}
		else if (msgObj instanceof ChangePartMsg) {
			// change an existing part type
			if (changePart(senderIndex, (ChangePartMsg)msgObj)) {
				System.out.println("Client " + senderIndex + " changed a part");
			}
			else {
				System.out.println("Client " + senderIndex + " unsuccessfully tried to change a part");
			}
		}
		else if (msgObj instanceof DeletePartMsg) {
			// delete an existing part type
			if (deletePart(senderIndex, (DeletePartMsg)msgObj, true) != null) {
				System.out.println("Client " + senderIndex + " deleted a part");
			}
			else {
				System.out.println("Client " + senderIndex + " unsuccessfully tried to delete a part");
			}
		}
		else if (msgObj instanceof PartListMsg) {
			// send available part types to client
			netComms.get(senderIndex).write(new PartListMsg(partTypes));
			System.out.println("Sent part list to client " + senderIndex);
		}
		else if (msgObj instanceof ProduceKitsMsg) {
			// add kit production command to queue
			if (produceKits(senderIndex, (ProduceKitsMsg)msgObj)) {
				System.out.println("Client " + senderIndex + " added a production request");
			}
			else {
				System.out.println("Client " + senderIndex + " unsuccessfully tried to add a production request");
			}
		}
		else if (msgObj instanceof ProduceStatusMsg) {
			// send production status to client
			netComms.get(senderIndex).write(status);
			System.out.println("Sent production status to client " + senderIndex);
		}
		else if (msgObj instanceof FactoryStateMsg) {
			// this client wants to be updated with factory state
			wantsState.set(senderIndex, true);
                	netComms.get(senderIndex).write(state);
			System.out.println("Sent factory state to client " + senderIndex);
		}
		else {
			System.out.println("Warning: received unknown message from client " + senderIndex + ": " + msgObj);
		}
	}

	/** adds part to partTypes (if valid), if notify is true sends StringMsg to client indicating success or failure */
	private boolean addPart(int clientIndex, NewPartMsg msg, boolean notify) {
		String valid = newPartIsValid(msg.part);
		if (notify) netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.NEW_PART, valid));
		if (!valid.isEmpty()) return false;
		partTypes.add(msg.part);
		return true;
	}

	/** changes specified part (if valid and not in production), sends StringMsg to client indicating success or failure */
	private boolean changePart(int clientIndex, ChangePartMsg msg) {
		// delete old part
		Part oldPart = deletePart(clientIndex, new DeletePartMsg(msg.oldNumber), false);
		if (oldPart == null) {
			netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.CHANGE_PART, "Requested part either in production or does not exist"));
		}
		// add replacement part
		else if (!addPart(clientIndex, new NewPartMsg(msg.part), false)) {
			netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.CHANGE_PART, newPartIsValid(msg.part)));
			partTypes.add(oldPart);
		}
		else {
			netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.CHANGE_PART, ""));
		}
		return false;
	}

	/** deletes part with specified name (if exists), if notify is true sends StringMsg to client indicating success or failure,
	    returns deleted part if succeeded or null if failed */
	private Part deletePart(int clientIndex, DeletePartMsg msg, boolean notify) {
		int i, j;
		// TODO: don't delete part types in production
		/*for (i = 0; i < status.cmds.size(); i++) {
			if (status.status.get(i) == ProduceStatusMsg.KitStatus.QUEUED
			    || status.status.get(i) == ProduceStatusMsg.KitStatus.PRODUCTION) {
				Kit kit = getKitByNumber(status.cmds.get(i).kitNumber);
				for (j = 0; j < kit.partsNeeded.size(); j++) {
					if (msg.number == kit.partsNeeded.get(j).getNumber()) {
						if (notify) netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.DELETE_PART, "May not delete part that is in production"));	
						return null;
					}
				}
			}
		}*/
		// delete part with specified number
		for (i = 0; i < partTypes.size(); i++) {
			if (msg.number == partTypes.get(i).getNumber()) {
				if (notify) netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.DELETE_PART, ""));
				return partTypes.remove(i);
			}
		}
		if (notify) netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.DELETE_PART, "Part never existed or has already been deleted"));
		return null;
	}

	/** returns empty string if given part is valid (i.e. has a unique name and number), or error message if it is not */
	private String newPartIsValid(Part part) {
		for (int i = 0; i < partTypes.size(); i++) {
			if (part.getNumber() == partTypes.get(i).getNumber()) {
				return "Another part has the same number";
			}
			if (part.getName().equals(partTypes.get(i).getName())) {
				return "Another part has the same name";
			}
		}
		return "";
	}

	/** queue specified production command in production status (if valid), sends StringMsg to client indicating success or failure */
	private boolean produceKits(int clientIndex, ProduceKitsMsg msg) {
		if (msg.howMany <= 0) {
			netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.PRODUCE_KITS, "Must produce at least 1 new kit"));
			return false;
		}
		// TODO: check that kit number is valid (requires getKitByNumber())
		status.cmds.add(msg);
		status.status.add(ProduceStatusMsg.KitStatus.QUEUED);
		netComms.get(clientIndex).write(new StringMsg(StringMsg.MsgType.PRODUCE_KITS, ""));
		return true;
	}

	/** returns part type with specified part number, or null if there is no such part */
	private Part getPartByNumber(int number) {
		for (int i = 0; i < partTypes.size(); i++) {
			if (partTypes.get(i).getNumber() == number) return partTypes.get(i);
		}
		return null;
	}

	/** returns kit type with specified kit number, or null if there is no such kit */
	/*private Kit getKitByNumber(int number) {
		for (int i = 0; i < kitTypes.size(); i++) {
			if (kitTypes.get(i).getNumber() == number) return kitTypes.get(i);
		}
		return null;
	}*/
}
