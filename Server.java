import java.io.*;
import java.net.*;
import java.util.*;

/** class implementing a non-GUI server application to coordinate factory clients over a network */
public class Server implements Networked {
	/** networking port that server listens on */
	public static final int PORT = 44247;

	/** server socket used to set up connections with clients */
	private ServerSocket serverSocket;
	/** ArrayList of client connections */
	private ArrayList<NetComm> netComms;
	/** whether each client wants to be updated with the factory state */
	private ArrayList<Boolean> wantsFactoryState;
	/** Part types that are available to produce */
	private ArrayList<Part> partTypes;
	/** Kit types that are available to produce */
	private ArrayList<Kit> kitTypes;
	/** current production status */
	private ProduceStatusMsg produceStatus;
	/** current factory state */
	private FactoryStateMsg factoryState;
	/** factory state changes to broadcast to clients on next timer tick */
	private FactoryUpdateMsg factoryUpdate;

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
		wantsFactoryState = new ArrayList<Boolean>();
		System.out.println("Server is ready; press ctrl+C to exit");
		// wait for clients to connect
		while (true) { // loop exits when user presses ctrl+C
			try {
				Socket socket = serverSocket.accept();
				netComms.add(new NetComm(socket, this));
				wantsFactoryState.add(false);
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

	/** handle message received from clients */
	public void msgReceived(Object msgObj, NetComm sender) {
		int senderIndex;
		// find who sent the message
		for (senderIndex = 0; senderIndex < netComms.size(); senderIndex++) {
			if (sender == netComms.get(senderIndex)) {
				break;
			}
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
		}
		else if (msgObj instanceof String) {
			// broadcast message to all clients (for TestClient only, will delete later)
			for (int i = 0; i < netComms.size(); i++) {
				netComms.get(i).write("Message from " + senderIndex + " to " + i + ": " + (String)msgObj);
			}
		}
		else if (msgObj instanceof ProduceStatusMsg) {
			// send produceStatus to client
			netComms.get(senderIndex).write(produceStatus);
		}
		else if (msgObj instanceof FactoryStateMsg) {
			// this client wants to be updated with factory state
			wantsFactoryState.set(senderIndex, true);
                	netComms.get(senderIndex).write(factoryState);
		}
		else {
			System.out.println("Warning: received unknown message from client " + senderIndex + ": " + msgObj);
		}
	}
}
