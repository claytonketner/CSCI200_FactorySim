### \*All data classes implement Serializable

### Movement 
      To view the Member Data and Methods in this class, please go to docs/Movement.html
    
***

### Part
      To view the Member Data and Methods in this class, please go to docs/Movement.html

***

### GUIPart
      To view the Member Data and Methods in this class, please go to docs/GUIPart.html

***

### GUIPartsBox
      Contains data and methods for drawing and animating a part box
* Constructor: GUIPartsBox( GUIPart gp, double x, double y )
* Member Data: 
      * public Movement movement - used to access movement data
      * public GUIPart part - used to access part data
* Methods: 
      * void draw( Graphics2D g, long currentTime )

***

### Kit
      This class defines a kit and its attributes, use gridLayout to define the part location.
* Constructor: 
      * Kit()
      * Kit(String name, String description, int kitNumber)
* Member Data:
      * public static final int INCOMPLETE = 0 - all kits initialized to incomplete
      * public static final int INCORRECT = 1 - used when kit contains incorrect part in any location
      * public static final int COMPLETE = 2 - signifies completed kit with correct parts
      * private number – int kit number.
      * private name – string kit name
      * private description – string kit description
      * private partsNeeded - list of what parts does this kit need
      * private kitStatus - is it completed, incomplete, or a part’s location is wrong
        
* Methods:
      * void addPart( Part part ) - add part in the kit
      * -------methods blow are not included in V0---------------
      * setKitName() – set the name of the kit
      * getKitStatus() - return the kit status
      * setKitStatus() - set the kit status
      * setKitDescription() – return the description of the kit
      * setKitNumber() – set the number of the kit (it has to be a specific one)
      * setPartsInKit(ArrayList<Kit> kits) - set the parts in a kit
      * getPartsInKit() - get the parts in a kit

***

### KitStand
      This class defines a kit stand and its attributes upon which kits will be assembled
Constructor: KitStand()
* Member Data: 
      * TreeMap<Integer,Kit> kits - 0-1 are positions for incomplete kits, 2 is the inspection position
      * GUIKitCamera guiKitCamera - used to access gui kit camera data
* Methods: 
      * None


***

### GUIKitStand
      Contains data and methods for drawing and animating a kit stand
* Constructor: GUIKitStand(KitStand kitStand)

* Member Data:
      * private KitStand kitStand - used to access kit stand data
      * private TreeMap<StationNumber, GUIKit> kits - the kit location being stored in the station
      * private Movement movement - used to access movement data
      * static enum StationNumber: ONE, TWO, THREE - the location in the station
	
* Methods:
      * void addKit(GUIKit guiKit, StationNumber snum) - add kit to the specific location in the station
      * GUIKit removeKit(StationNumber snum) - remove kit from the station
      * GUIKit getKit(StationNumber snum) - return the kit in the specific location in the station
      * void draw(Graphics2D g, long currentTime) - draw the kits in the station
      * Point2D.Double getCameraStationLocation() - return camera station

***

### GUIKit
      Contains data and methods for drawing and animating a kit
        
* Constructor: GUIKit(Kit kit, double x, double y)
        
* Member data:
      * public Kit kit - used to access kit data
      * public Movement movement - used to access movement data
      * public TreeMap<Integer, GUIPart> parts - treemap of the parts
* Methods:
      * addPart( Integer index, GUIPart part ) - add part in the kit according to the index
      * removePart( Integer index ) - remove the part from the kit
      * void draw(Graphics2D g, long currentTime) - draws the kit

***

### PartRobot
      This class defines and controls a part robot.
Constructor: PartRobot()        
* Member Data:
      * ArrayList<Part> partsInGripper - the parts in the robot's gripper
* Methods:
     

***

### GUIDiverterArm 
      Draws the diverter arm
        
* Constructor: GUIDiverterArm (double x, double y)
        
* Member data:
      * public Movement movement- used to access the Movement class
        
* Methods:
      * void draw( Graphics2D g, long currentTime )

***

### GUIDiverter 
      Contains data and methods for drawing and animating a diverter
        
* Constructor: GUIDiverter (double x, double y)
        
* Member data:
     * public Movement movement - used to access the movement data
* Methods:
     * void draw( Graphics2D g, long currentTime ) - draws the diverter

***

### ComboLane 
      Contains data and methods for drawing and animating a pair of lanes
        Not sure if it needs Serializable yet
* Constructor: ComboLane()
        
* Member data:
     * private Lane myTopLane - used to access the top lane
     * private Lane myBotLane - used to access the bottom lane
* Methods:
     * void turnOn() - turn on both lanes
     * void turnOff() - turn off both lanes
     * boolean isLaneOn() - return if this pair of lanes is on
     * void setAmplitude(double amplitude) - set the amplitude of this pair of lanes
     * double getAmplitude() - get the amplitude of this pair of lanes
     * void addPartTopLane(Part p) - add a part to top lane
     * void addPartBotLane(Part p) - add a part to bottom lane
     * Part removePartTopLane() - remove end part from top lane
     * Part removePartBotLane() - remove end part from bottom lane
     * double getSpeed() - return speed for both lanes

***

###GUIPartRobot
      Contains data and methods for drawing and animating a part robot
        
* Constructor: GUIPartRobot(PartRobot partRobot)
        
* Member data:
     * public PartRobot partRobot - used to access part robot data
     * public Movement movement - set the start position
     * private Movement baseMove, armMove, handMove - robot movement
     * private final int baseStartX = 400 - baseX location
     * private final int baseStartY = 330 - baseY location

        
* Methods:
     * boolean arrived(long currentTime) - true if past end time
     * doCalculations(long currentTime) - calculates the movement of the robot
     * void park() - stops at the base
     * void void draw(Graphics2D g, long currentTime) draws the part
     * addPartToGripper ( Integer gripperNumber, GUIPart part ) - add part to one of the part robot's grippers
     * GUIPart removePartFromGripper ( Integer gripperNumber ) - remove part from the part robot according to the gripper number

***

###KitRobot   
     This class defines and controls a kit robot.
* Constructor: KitRobot()        
* Member Data:
     * private Kit kit - a Kit variable of the kit in its hands/grippers
     * ----methods below are not included in V0----------------
     * isBroken - boolean variable if robot is broken
     * GUIKitRobot guiKitRobot - used to easily link to the GUI equivalent
        
* Methods:
     * void addKit(Kit kit) - add a kit to the kit robot
     * Kit removeKit() - remove a kit from the kit robot
     * -----methods blow are not included in V0----------
     * getPos - returns the kitting stand position of the kit robot.  returns null if the robot is not at the kitting stand.
     * setBroken - sets isBroken
     * getBroken - returns isBroken value

***

###GUIKitRobot
     To view Member Data and Methods in this class please go to docs/GUIKitRobot.html

***

### KitDeliveryStation
      This class contains all the information about the state of the kit delivery station.
* Constructor: KitDeliveryStation()
* Member data:
      * ArrayList<Pallet> pallets
      * ----Data below are not included in V0---------
      * isBroken - boolean variable if conveyor is broken
      * pallets - ArrayList of pallets that are currently visible
      * GUIKitDeliveryStation guiKitDeliveryStation - used to easily link to the GUI equivalent
* Methods:
      * ----Methods below are not included in V0----
      * setBroken - sets isBroken
      * getBroken - returns isBroken value

***

### GUIKitDeliveryStation
      Contains data and methods for drawing and animating the kit delivery station
        
* Constructor:
      * GUIKitDeliveryStation(KitDeliveryStation kitDeliveryStation, GUILane inConveyor, GUILane outConveyor, double x, double y)
      * GUIKitDeliveryStation(KitDeliveryStation kitDeliveryStation, GUILane inConveyor, GUILane outConveyor, Movement movement)
* Member data:
      * public KitDeliveryStation kitDeliveryStation - used to access delivery station data
      * public Movement movement - used to access movement data
      * public GUILane inConveyor, outConveyor - conveyor lanes
* Methods:
      * void checkStatus(long currentTime) - if this conveyor is full, turn off the lane, 
if this conveyor is empty, turn this lane on and move all of the pallets down one space
      * void draw(Graphics2D g, long currentTime) - draws the kit delivery station
      * Point2D.Double getOutConveyorLocation() - return outConveyor's location

***

###Pallet
      This class contains all the information about a pallet.
Constructor: Pallet()       
* Member data:
      * kit- variable holding the kit on the pallet.
      * hasKit - boolean if pallet contains a kit.

***

###GUIPallet
      Contains data and methods for drawing and animating the pallet
        
* Constructor: 
      * GUIPallet(Pallet pallet, GUIKit guiKit, Movement movement)
      * GUIPallet(Pallet pallet, GUIKit guiKit, double x, double y)
* Member data:
      * public Pallet pallet - used to access pallet data
      * public Movement movement - used to access movement data
      * private GUIKit guiKit - used to access the kit on the pallet
        
* Methods:
      * void addKit(GUIKit guiKit) - add a kit to the pallet
      * boolean hasKit() - true if there is a kit in the pallet
      * GUIKit removeKit() - remove a kit from the pallet
      * void draw(Graphics2D g, long currentTime) - draw the pallet

***

###Gantry
      This class defines and controls a gantry robot.

* Member Data:
      * holdingObject - boolean variable that detects if the
      * gantry is currently holding an object
      * broken- boolean variable recording the state of this client
      * binLocations - arraylist of GUIBin objects used to detect available locations to set a bin
        
* Methods:
      * -------------------Methods blow are not used in V0-----------------
      * isHoldingObject - returns value of holdingItem
      * retrieveFullBin(bin, feeder) - acts according to which feeder requests which bin (this method will 
utilize the helper methods moveTo(), pickUp(), dump(), and drop())
      * placeInPurgeStation(feeder) - acts according to which feeder was just fed (this method will utilize 
the helper methods moveTo(), pickUp(), dump(), and drop())
      * placeInTempLoc - acts according to binLocations (this method will utilize the helper methods 
moveTo(), pickUp(), dump(),and drop())
      * isBroken - returns value of broken; affects the physical look of the gantry robot depending on broken state
      * moveTo - either move to the new bin’s location, move to the feeder with the new bin, move empty bin
to purge station, or move purged bin to temporary location depending
      * pickUp - commands gantry to pick up the bin at its current array index (in availableLocations arraylist)
      * dump - feed contents of bin into feeder
      * drop - place bin at gantry’s current array index (in availableLocation arraylist)

***

### GUIGantry
      Contains data and methods for drawing and animating the gantry
        
* Constructor: GUIGantry(Pallet pallet, double x, double y)
        
* Member data:
      * public Gantry gantry - used to access gantry data
      * public GUIEntity guiEntity - used to access movement data
        
* Methods:

      * void tick(long currentTime) - calculates movement
      * void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

***

### Bin(Part part)
      This class contains all the information about bins. The bins will be responsible for a single type of
      part, which will be passed into its constructor upon instantiation.
        
* Member Data:
      * partName- name of part represented by type String
      * partImage - image of part used to identify bin
      * xPos - position of x-coordinate
      * yPos - position of y-coordinate
      * GUIBin guiBin - used to easily link to the GUI equivalent

* Methods:
      * getPartType- returns part type held by bin

***

###GUIBin
       Contains data and methods for drawing and animating a bin
        
* Constructor: GUIBin (Bin bin, double x, double y)
        
* Member data:
       * public Bin bin - used to access bin data
       * public GUIEntity guiEntity - used to access movement data
        
* Methods:
       * void tick(long currentTime) - calculates movement
       * void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

***

### PartCamera:
      This class defines the camera which monitors the state of parts in the nest.

* Member Data:
      * cameraBlocked - boolean for when the robot is blocking the camera
      * previousImage - image of what the nest looked like previously
      * currentImage - image of the picture just taken
      * nestUnchanged - boolean for checking if the new picture matches the previous picture of the nest

* Methods:
      * takePicture() - takes a picture when there is a clear view
      * cameraIsBlocked() - cameraBlocked gets set to true
      * cameraIsClear() - cameraBlocked gets set to false
      * compareImages() - compares previousImage with currentImage, nestUnchanged is true if they are the same, otherwise it is false, previousImage is then set to currentImage.
      * isNestUnchanged() - returns nestUnchanged

***

###GUIPartCamera
      This class defines the graphical representation of PartCamera
        
* Constructor: GUIPartCamera(PartCamera camera, double x, double y)
        
* Member data:
      * public PartCamera camera - used to access PartCamera data
      * public GUIEntity guiEntity - used to access position data
      * long age - how long the flash has been shown (in milliseconds)
      * long expireTime - how long the flash should last (in milliseconds)
        
* Methods:
      * void tick(long currentTime) - updates age
      * boolean isExpired() - returns true if age > expireTime. If true, then this object should be deleted
      * void draw(Graphics g, long elapsedMillis) - calls tick(), draws the flash

***

###KitCamera:
      This class defines the components of the kitting stand camera
* Constructor: KitCamera ()        
* Member Data:
      * boolean takingPicture - if it is taking a picture
* Methods:
      *  void takePicture() - taking picture

***

### GUIKitCamera
      Contains data and methods for drawing the kit camera flash
        
* Constructor: GUIKitCamera ( KitCamera kitCamera, Movement movement, long currentTime, long lifeLength )
* Member Data:
      * KitCamera kitCamera;
      * Movement movement;
      * long birthTime, lifeLength;
* Methods:
      * boolean isExpired(long currentTime) - true if this camera is expired
      * void draw(Graphics2D g, long currentTime) - draw the kit camera 

***

###Feeder
      Feeds parts to a particular lane.
        
* Member Data:
      * diverter - boolean variable for which side of lane items go.
      * partsLow - boolean variable for when parts are low.
      * parts - arraylist of parts in the feeder
        
* Methods:
      * void setPartsLow() - makes partsLow true
      * void setPartsUnlow() - makes partsLow false
      * void checkIfLow() - check if there are parts in feeder, (return partsLow)
      * void changeLane() - changes the lane parts are going. (diverter = !diverter)
      * Lane getLane() - returns which lane parts are going. (Diverter, false is lane 1, true is lane 2 )
      * void loadFeeder( ArrayList<Part> load ) - load the parts into the feeder
      * Part getPart() - return the first part in the list

***

###GUIFeeder
     To view the Member Data and Methods in this class, please go to docs/GUIFeeder.html

***

### Lane
      To view the Member Data and Methods in this class, please go to docs/Lane.html

***

### GUILane
      To view the Member Data and Methods in this class, please go to docs/GUILane.html

***

### Nest
      To view the Member Data and Methods in this class, please go to docs/Nest.html

***

### GUINest
      To view the Member Data and Methods in this class, please go to docs/GUINest.html
***

### WholeLane
      To view the Member Data and Methods in this class, please go to docs/WholeLane.html

***