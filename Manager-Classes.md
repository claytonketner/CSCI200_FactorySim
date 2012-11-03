### Server
          class implementing a non-GUI server application to coordinate factory clients over a network

***

* Member Constants (all public and static):
     * Port - networking port that server listens on
     * SettingsPath - path of file where factory state is saved
* Member Data (all private):
      * serverSocket - server socket used to set up connections with clients
      * netComms - ArrayList of client connections
      * wantsFactoryState - ArrayList of booleans indicating whether each client wants to be updated with the factory state
      * partTypes - ArrayList of Parts that are available to produce
      * kitTypes - ArrayList of Kits that are available to produce
      * produceStatus - ProduceStatusMsg storing current kit production status
      * factoryState - FactoryStateMsg storing current factory state
      * factoryUpdate - FactoryUpdateMsg storing changes to broadcast to clients on next timer tick
* Methods:
      * Server - constructor for server class
      * main - instantiates a new Server
      * actionPerformed - called during timer tick; updates simulation and broadcasts factoryUpdate to clients
      * msgReceived - handle message received from clients (takes message and NetComm that received the message); generally calls another method to handle the specific message
      * addPart - takes an AddPartMsg and client index, adds part to partTypes (if valid), sends StringMsg to client indicating success or failure
      * changePart - takes a ChangePartMsg and client index, changes specified part (if valid and not in production), sends StringMsg to client indicating success or failure
      * deletePart - takes a DeletePartMsg and client index, deletes part with specified name (if exists), sends StringMsg to client indicating success or failure
      * listParts - takes client index and sends partTypes to client in a PartListMsg (called when server receives a PartListMsg, but received message is not a parameter because it doesn’t influence behavior of the method)
      * addKit - takes an AddKitMsg and client index, adds kit to kitTypes (if valid), sends StringMsg to client indicating success or failure
      * changeKit - takes a ChangeKitMsg and client index, changes specified kit (if valid and not in production), sends StringMsg to client indicating success or failure
      * deleteKit - takes a DeleteKitMsg and client index, deletes kit with specified name (if exists), sends StringMsg to client indicating success or failure
      * listKits - takes client index and sends kitTypes to client in a KitListMsg (called when server receives a KitListMsg, but received message is not a parameter because it doesn’t influence behavior of the method)
      * produceKits - takes a ProduceKitsMsg and client index and appends it to produceStatus (if valid) indicating that it is queued, sends StringMsg to client indicating success or failure
      * sendProduceStatus - takes client index and sends produceStatus to client (called when server receives a ProduceStatusMsg)
      * sendFactoryState - takes client index, sets wantsFactoryState to true for this client, and sends current factoryState to client (called when server receives a FactoryStateMsg)
      * changeNormative - takes a NonNormativeMsg and client index, breaks or fixes specified item, sends StringMsg to client indicating success or failure
      * loadSettings - load factory state from file
      * saveSettings - save factory state to file

***

### Part Manager

![Part Manager](http://usc-csci200-fall2012.github.com/team11/design/images/image01.jpg)
### PartClient
          This class contains the main method and communicates with the server.
* Member Data:
      * parts - ArrayList of all parts
      * netComm - NetComm instance to communicate with server
      * pnlConnect - ConnectPanel to let user connect to server
      * pnlPart- PartPanel for prompting user input
* Methods:
      * msgReceived - handles message from server (takes message and
      * NetComm that received the message)
      * actionPerformed() - handle the input, send any modification of the parts to server
      * PartPanel:        
* Member Data:                
      * lblPartNumber - Label for part number
      * lblPartDescription - Label for part Desc                                                   
      * lblPartName - Label for part name
      * lblPartImage - Label for part image
      * lblPartSelected - Label for prompting the user to select a part
      * btnCreatePart - button for making a part
      * btnDelete - Delete a selected part
      * btnChange - Change a selected part
      * txtPartNumber - TextField for part number
      * txtPartDescription - TextField for part description
      * txtPartName - TextField for part name
      * txtPartImagePath - TextField for part image path
      * jcbParts - ComboBox for all of the parts
                      
* Methods:
      * actionPerformed() - handle the input, send any modification of the parts to server