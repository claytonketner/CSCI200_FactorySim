## Server
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

## Part Manager

![Part Manager](images/image01.png)
### PartsClient
          This class contains the main method and communicates with the server.
* Member Data:
      * private CardLayout layout - use the cardlayout to switch screen between connectpanel and mPanel
      * private ConnectPanel cPanel - allow the client to connect to server
      * private PartManager mPanel - the panel to display part manager
      * private NetComm netComm - NetComm instance to communicate with server
* Methods:
      * msgReceived - handles message from server (takes message and
NetComm that received the message)
      * actionPerformed() - handles the input, send any modification of the parts to server

### PartManager (extends JPanel) : 
* Constructor: PartManager( PartsClient pc ) - uses the GridBagLayout to line up the GUI components          
* Member Data:                
      * private PartsClient myClient - fetch data from the client
      * private JLabel pName - prompt user the part name
      * private JLabel pNumber - prompt user the part number
      * private JLabel pInfo - prompt user the part description
      * private JLabel pEdit - prompt user the number of which part he wants to change/delete
      * private JTextField tName - textfield to enter the part name
      * private JTextField tNumber - textfield to enter the part number
      * private JTextField tInfo - textfield to enter the part description
      * private JTextField tEdit - textfield to enter the number of which part he wants to change/delete
      * private JButton create - button to create a kit
      * private JButton change - button to change a kit
      * private JButton delete - button to delete a kit
      * private JScrollPane scroll - to display a list of available parts can be changed, deleted (in case the parts are too many)
      * private JPanel parts - a list of available parts can be changed, deleted
      * private JLabel msg - display helpful info
                      
* Methods:
      * actionPerformed() - handle the input, send any modification of the parts to server

***

## Kit Manager
![Kit Manager](images/image00.png)
### KitClient:
          This class contains the main method and communicates with the server.
* Member Data:
      * parts- ArrayList of all the parts
      * kits - ArrayList of all the kits
      * netComm- socket class to communicate with server
      * pnlConnect - ConnectPanel to let user connect to server
      * pnlKit- KitPanel for prompting user input
* Methods:
      * getKitDescription() – get the description of the kit
      * geKitNumber() – get the number of the kit                                                                        * getKitName() – get the name of the kit
      * getPartsInKit() - get the arraylist of parts in the kit                                                          
      * msgReceived - handles message from server (takes message and NetComm that received the message)
      * actionPerformed - receives action events from pnlConnect

### KitPanel
          user can create a new kit, or order a existing kit.
* Member Data:
      * lblCreateKitNumber - Label for prompting user the number of the new kit
      * lblCreateKitDescription - Label for kit Desc                                                   
      * lblCreateKitName - Label for kit name
      * lblCreatePartsInKit - Label for parts in a kit
      * lblSelectKit - Label for prompting user the name of  a kit he wants to change or delete
      * btnDeleteKit - button for deleting a kit                                                                             * btnCreateKit- button for Creating a new kit
      * btnChangeKit - button for changing a selected kit
      * jcbPartsInKit - ArrayList of ComboBox for selecting parts in a kit when 
creating or modifing a kit. (some of them can be empty, but parts in a kit at most can be 8)
      * txtKitNumber - TextField for kit number
      * txtKitDescription - TextField for kit description
      * txtKitName - TextField for kit name
      * jcbAvailableKits - ComboBox for the avaiable kits
* Methods:
      * actionPerformed() - handle the input, send any modification of the kits to         
server

***

## Factory Production Manager
![FPM](images/image03.png)
GUI View of Factory (most of the labeled items are contained in the factoryState variable)
![FPM](images/image02.jpg)
### FactoryProductionClient
          This class contains the main method and communicates with the server.
* Member Data:
      	private NetComm netComm - NetComm instance to communicate with server
	private ConnectPanel conp - allows the client to connect to the server
	private CardLayout cardlayout - uses the cardlayout to switch screens between ConnectPanel and FactoryProductionManager
	private FactoryProductionManager fpm - panel to display schedule and factory full view
* Methods:
      * initialize() - initializes all of the variables and uses cardlayout to switch screens

### FactoryProductionManager (extends JPanel):
          this class handles two panels (schedule and factory view), it is about to use a buttonpanel(a panel which only contains two buttons) to switch between these two (uses cardlayout as well).
* Constructor: FactoryProductionManager(FactoryProductionClient client) - communicate with the client
* Member Data:
      * private FactoryProductionClient fpc - fetch data from client
      * private CardLayout cardlayout - uses cardlayout to switch screens
      * private FactoryProductionSchedulePanel fpsp - instance of schedule panel that displays the schedule and prompts user to produce kit
      * private FactoryProductioButtonPanel fpbp - instance of button panel that prompts user to switch screens
      * private FactoryProductionViewPanel fpvp - instance of full view panel that displays the full-view factory
      * private JPanel mainpanel - this panel contains schedule panel and view panel and is displayed in the center of the screen, button panel is displayed at the bottom of the screen. this panel's layout is cardlayout.
* Methods:
      * actionPerformed(ActionEvent e) - after user click the switch buttons, go to the right screen

### FactoryProductionSchedulePanel
* Constructor: FactoryProductionSchedulePanel()
* Member Data: 
      * public ArrayList<JLabel> lblKitsNames - ArrayList of labels that display all kinds of available kit (each kit can have three different status)
      * public ArrayList<JLabel> lblKitsNumbers - ArrayList of labels that display the quantity of different kinds of kit
      * public ArrayList<JLabel> lblKitsStatus - arraylist of all kinds of available kit's status
      * private JLabel lblDisplayName - display "Kit Name: " message in the schedule
      * private JLabel lblDisplayNumber - display "Quantity: " message in the schedule
      * private JLabel lblDisplayStatus - display "Status: " message in the schedule
      * private JButton btnProduce - a button that sends info that what/how many kits are about produce in the factory
      * private JLabel lblSelectKit - display "Select a Kit" message in the schedule
      * private String[] jcbKitStrings - Kits' Names
      * private JComboBox jcbSelectKit - combobox for listing all of available kits' name
      * private JTextField txtKitQuantity - prompt user to enter how many kit he wants to produce
      * private JLabel picture - the final image of the selected kit
      * private int row,col - track the number of rows and cols of schedule
      * private TreeMap<String , String> schedule - first key is kit's name, second key is kit's status. uses TreeMap to store the data of kit
* Methods:
      * initialize() - initialize the variables
      * makeSchedule() - line up the schedule using GridBagLayout
      * updateLabel(String name) - update the label "picture" to display correct image that match the kit selected in combobox
      * actionPerformed(ActionEvent e) - handle the user's selection in combobox

### FactoryProductioButtonPanel
* Constuctor: FactoryProductioButtonPanel() - line up the two buttons
* Member Data:
      * JButton btnSwitchSchedule,btnSwitchView - two buttons that switch between schedule and full view

### FactoryProductionViewPanel
* Constuctor: FactoryProductionViewPanel()


***

## Lane Manager:

### LaneClient
          This class shows all the feeder, lanes, nests operating.
* Member Data:
      * Lanes - ArrayList of 4 WholeLanes.
      * pnlBreakThings - Panel for breaking things
      * pnlConnect - ConnectPanel to let user connect to server
      * netComm- NetComm instance to communicate with the server
      * factoryState - FactoryStateMsg that is kept in sync with the server copy
* Methods:
      * takePictureOfLaneNests - takes a picture of specified lane’s nests if possible (lane index passed as parameter)
      * getPartFromNest - gets a part from specified nest (nest index passed as parameter)
      * feedFeeder - fill specified feeder (feeder index passed as parameter)
      * divertFeeder - switches the position of specified feeder’s diverter (feeder index passed as parameter)
      * fixLane - attempts to fix specified lane by increasing vibration (lane index passed as parameter)
                        paintWholeLanes() - paints all feeders, lanes, nests, parts
      * actionPerformed - receives action events from pnlConnect
      * msgReceived - handles message from server (takes message and NetComm that received the message)
        Mockup of the client:
![Lane Manager](images/image08.gif)

***

### BreakThings
          Panel with options for breaking parts of the lanes
* Member Data:
      * btnBreakFeeder - ArrayList of 4 buttons that prevents the corresponding feeder from working
      * btnBreakLane - ArrayList of 4 buttons that causes the corresponding lane to stop
      * btnBreakNest - ArrayList of 8 buttons that causes the corresponding nest to spill
      * btnFixFeeder - ArrayList of 4 buttons that fix corresponding feeder
      * btnFixLane - ArrayList of 4 buttons that fix corresponding lane
      * btnFixNest - ArrayList of 8 buttons that fix corresponding nest
* Methods:
      * actionListener - sends status of feeders, lanes, nests to server

***

## Kit Assembly Manager

### KitAssemblyClient
          This class contains the main method and communicates with the server.
* Member Data:
      * queuedKits - ArrayList of currently queued kits
      * kitStandKits - ArrayList of kits on kitting stand (index 0-1 for assembly, 2
for inspection station).
      * netComm ( socket ) - instance of NetComm class for communication
with the server.
      * factoryState - FactoryStateMsg that is kept in sync with the server copy
      * kitCamera - instance of KitCamera
      * kitRobot - instance of a kit robot
      * partRobot - instance of a part robot
      * pnlConnect - declaration of ConnectPanel for connecting to server
      * pnlKitAssembly - JPanel to add the kitAssemblyGraphicsPanel and
      * pnlSwitchPanelButton to
      * pnlSwitchPanelButton - JPanel for a button to switch to the non-
      * normative controls panel (contained inside pnlKitAssembly)
      * kitAssemblyGraphicsPanel - declaration of instance of
      * KitAssemblyGraphicsPanel (contained inside pnlKitAssembly)
      * kitAssemblyBreakPanel - declaration of instance of
      * KitAssemblyBreakPanel
* Methods:
      * sendKitRobotTask( startStation, endStation, time )  - sends task to kit
robot.
      * sendPartRobotTask( nestNumber, kitStation, time ) - sends task to part
robot.
      * getKitRobotPos - returns position of kit robot
      * getPartRobotPos - returns position of part robot
      * removeKitFromQueue - removes kit from queuedKits
      * removeKitFromKitStand - removes kit from kit stand
      * actionPerformed - receives action events from pnlConnect
      * msgReceived - handles message from server (takes message and NetComm that received the message)

Kit Assembly Panel (pnlKitAssembly) mock-up:

![KAM](images/image04.png)

***

### KitAssemblyGraphicsPanel
          This class is a JPanel that displays the kitting stand, the kit robot, the kit delivery station, and the part robot.
* Member Data:
* Methods:
      * paintKitAssemblyPanel - paints background images and instructs objects
        to repaint themselves.
      * takePicture - takes a picture

***

### KitAssemblyBreakPanel
           This class allows the user to break the kit robot, part robot, and kit delivery station
* Member Data:
      * lblKitRobot - JLabel for kit robot
      * lblPartRobot - JLabel for part robot
      * lblKitDelivery - JLabel for kit delivery station
      * radioBreakKitRobot - JRadioButton to break the kit robot
      * radioFixKitRobot - JRadioButton to fix the kit robot
      * radioBreakPartRobot - JRadioButton to break the part robot
      * radioFixPartRobot - JRadioButton to fix the part robot
      * radioBreakKitDelivery - JRadioButton to break the kit delivery station
      * radioFixKitDelivery - JRadioButton to fix the kit delivery station
Methods:
      * actionListener - send message to server via netComm updating the
status of the kit robot, part robot, or kit delivery station.

KitAssemblyBreakPanel mock-up:

![fsdfs](images/image09.png)


***

## Gantry Robot Manager

### GantryClient
           This class contains the main method and communicates with the server.
* Member Data:
      * netComm- NetComm instance to communicate with server
      * pnlConnect - ConnectPanel to let user connect to server
      * factoryState - FactoryStateMsg that is kept in sync with the server copy
      * pnlGantry - instance of GantryPanel contained implementing a
 GridBagLayout manager consisting of pnlGantryNorm and
 pnlGantryNonNorm
* Methods:
      * sendGantryTask( startStation, endStation, time )  - sends task to gantry        
      * getGantryPos - returns position of gantry
      * getBinPos - returns position of bin
      * updateInfo - notifies server of any and all changes to bins and gantry
                        actionPerformed - receives action events from pnlConnect
      * msgReceived - handles message from server (takes message and NetComm that received the message)
                        
### GantryPanel
           This class is a container panel consisting 3 subpanels. Elements are arranged
using GridBagLayout. It displays a wide view of the gantry robot.
* Member Data:
      * pnlGantryNorm- instance of GantryNormPanel: normative panel displaying
inset of gantry
      * pnlGantryNonNorm- instance of GantryBreakPanel nonnormative break
panel displaying inset of gantry
      * gb- layout variable (type: GridBagLayout)
### GantryNormPanel
           This class consists of a normative view of all elements. It will respond to user
interaction from the GantryBreakPanel class.
* Member Data:
      * bins- ArrayList of bins on the floor
      * gantry - gantry robot
      * binLocations - ArrayList of all possible locations of bins
      * originalLocations - ArrayList of bins’ original location before being moved
around  (subset of binLocations)
      * purgeLocations - arraylist of purge stations (subset of binLocations)
      * tempLocations - arraylist of temporary locations (subset of binLocations)
* Methods:
      * paintGantryPanel - paints background images and instructs objects to
repaint themselves.
      * takePicture - takes a picture
### GantryBreakPanel
           This class allows the user to break the gantry
* Member Data:
      * lblGantry- JLabel for gantry
      * radioBreakGantry - JRadioButton to break the kit robot
      * lblBreakGantry - accompanying label for radioBreakGantry
      * radioFixGantry - JRadioButton to fix the gantry
      * lblFixGantry - accompanying label for radioFixGantry
* Methods:
      * actionListener - send message to server via netComm updating the
status of the gantry

Gantry Mockup image:

![fdsfsdf](images/image06.jpg)


***