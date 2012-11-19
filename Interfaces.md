### Networked
           indicates that implementing class is capable of receiving messages across the network
           Server class and all client (but not panel) classes implement this
* Methods:
      * msgReceived - handle message received from the network (takes message and NetComm that received the message)

### GUIItem
           implemented by paintable factory items that are part of the factory state
* Methods:
      * draw - draw the item at the specified time
      * setMove - setter for movement (getter not needed by FactoryStateMsg)