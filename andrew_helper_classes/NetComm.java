import java.io.*;
import java.net.*;

/** encapsulates networked communication with a single client or server;
    this class is based on PlayerSocket.java in Andrew's HW3 submission */
public class NetComm {
  private Socket socket; /**< client socket that is endpoint for network communication */
  private ObjectInputStream in; /**< reads messages related to this client over the network */
  private ObjectOutputStream out; /**< writes messages related to this client over the network */

  /** constructor for network communication object */
  public NetComm(Socket newSocket) throws IOException {
    if (newSocket == null) {
      throw new NullPointerException("Socket cannot be null");
    }
    socket = newSocket;
    try {
      out = new ObjectOutputStream(socket.getOutputStream()); // always add before input stream, see http://stackoverflow.com/questions/8088557/getinputstream-blocks
      in = new ObjectInputStream(socket.getInputStream());
    }
    // pass exceptions onto caller
    catch (IOException ex) {
      throw ex;
    }
  }

  /** returns object read from input stream,
      or a CloseConnectionMsg if either client or server disconnected from network */
  public final Object read() {
    try {
      return in.readObject();
    }
    // exceptions below are thrown when client disconnected
    catch (EOFException ex) {
      return new CloseConnectionMsg();
    }
    catch (SocketException ex) {
      return new CloseConnectionMsg();
    }
    catch (StreamCorruptedException ex) {
      System.out.println("Input stream corrupted: " + ex.getMessage());
      return new CloseConnectionMsg();
    }
    // print stack trace and disconnect from network if unknown error
    // (if I don't know what causes the error then I can't write code to handle it)
    catch (Exception ex) {
      System.out.println("Network read error:");
      ex.printStackTrace();
      return new CloseConnectionMsg();
    }
  }

  /** write specified object to output stream */
  public final void write(Object obj) {
    try {
      out.writeObject(obj);
      out.flush();
    }
    // print error message if unknown error
    // (if I don't know what causes the error then I can't write code to handle it)
    catch (IOException ex) {
      System.out.println("Network write error: " + ex.toString());
    }
  }

  /** close connection */
  public void close() {
    try {
      out.writeObject(new CloseConnectionMsg());
      out.flush();
    }
    catch (Exception ex) {
      // ignore exceptions
    }
  }
}
