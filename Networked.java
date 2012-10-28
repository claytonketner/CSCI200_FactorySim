/** indicates that implementing class is capable of receiving messages across the network */
interface Networked {
  /** handle message received from the network */
  public void msgReceived(Object msgObj, NetComm sender);
}
