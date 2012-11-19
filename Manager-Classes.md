## Server
          class implementing a server application to coordinate factory clients over a network
          (the server-side factory controller is implemented in the FactoryControlManager class)

To view the member data and functions in this class, go to the docs folder in the code repository then open the file Server.html.

***

## Part Manager
![Part Manager](images/image01.png)
### PartsClient
          This class contains the main method and communicates with the server.
          To view the Member Data and Methods in this class, Please see master/docs/PartsClient.html

### PartManager (extends JPanel) : 
          user can create, change or delete a part in PartManager .
          To view the Member Data and Methods in this class, Please see master/docs/PartManager.html


***

## Kit Manager
![Kit Manager](images/KitClient.jpg)
### KitsClient:
          This class contains the main method and communicates with the server.
          To view the Member Data and Methods in this class, Please see master/docs/KitsClient.html
### KitManager
          user can create, change or delete a kit in KitManager.
          To view the Member Data and Methods in this class, Please see master/docs/KitManager.html

***

## Factory Production Manager
Schedule
![FPM](images/FPS.jpg)
GUI View of Factory
![FPM](images/FPV.jpg)
### FactoryProductionClient
          This class contains the main method and communicates with the server.
          To view the Member Data and Methods in this class, Please see master/docs/FactoryProductionManager.html

### FactoryProductionManager (extends JPanel):
          this class handles two panels (schedule and factory view), it is about to use a buttonpanel(a panel which only contains two buttons) to switch between these two (uses cardlayout as well).
          To view the Member Data and Methods in this class, Please see master/docs/FactoryProductionManager.html

### FactoryProductionSchedulePanel
          This class generate the schedule for factory each time it updates the production list.
          To view the Member Data and Methods in this class, Please see master/docs/FactoryProductionSchedulePanel.html

### FactoryProductioButtonPanel
          This class only contains two buttons which switch between schedule and factory full view.
          To view the Member Data and Methods in this class, Please see master/docs/FactoryProductioButtonPanel.html
### FactoryProductionViewPanel
          This class contains the graphic view of whole factory
          To view the Member Data and Methods in this class, Please see master/docs/FactoryProductionViewPanel.html


***

## Lane Manager:

### LaneClient
          This class shows all the feeder, lanes, nests operating.
* Member Data:
      * layout - CardLayout for switching between connect panel and lane manager
      * pnlLane - Panel for viewing the lane
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