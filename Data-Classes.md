### \*All data classes implement Serializable

### Movement 
      represents the movement of an object that starts at a specified position and rotation,
      moves at constant velocity to a specified end position and rotation, then stops
* Constructor(Point2D.Double currentPos, double rotation)
* Constructor(Point2D.Double newStartPos, double newStartRot, long newStartTime,
			Point2D.Double newEndPos, double newEndRot, long newEndTime)

* Member data:
      * private Point2D.Double startPos - position at beginning of this move
      * private double startRot - counterclockwise rotation in radians at beginning of this move
      * private long startTime - time that this move starts, in milliseconds after the simulation started 
      * private Point2D.Double endPos - position at end of this move
      * private double endRot - counterclockwise rotation in radians at end of this move
      * private long endTime - time that this move ends, in milliseconds after the simulation started
      * private boolean paused = false;
      * private long pauseStartTime = 0;

* Methods:
      * Point2D.Double calcPos(long time)- returns position at specified time
      * double calcRot(long time) - returns rotation at specified time
      * boolean arrived(long time) - returns whether specified time is past end time
      * Point2D.Double getStartPos() - return start position
      * Point2D.Double getEndPos() - return end position
      * double getEndRot() - getter for endRot
      * long getEndTime() - getter for endTime
      * void pause(long currentTime) - to pause the movement
      * void unPause(long currentTime) - to continue the movement
      * void slaveTranslation(Movement master, double xOffset, double yOffset, long currentTime) - make the movement follow the master
      * void slaveRotation(Movement master, double angleOffset, long currentTime) - make the rotation follow the master
      * Movement fromSpeed(Point2D.Double newStartPos, double newStartRot, long newStartTime,
			Point2D.Double newEndPos, double newEndRot, double speed) - alternate method to create Movement object that asks for speed (in position units per second) instead of end time
    

***

### Part
      This class defines a parts and its attributes.
        
* Member Data:
     * private String name, description - name and description of the part
     * private int number - the part number
Constructor:
      * Part() - define a part without any name, description, or number
      * Part(String name, String description, int partNumber) - define a part with specific name, description and part number
* Methods:
      * void setPartName() – set the name of the part
      * void setPartDescription() – return the description of the part
      * void setPartNumber() – set the number of the part(it has to be a specific one)
      *  ---Methods below are not included in V0-----
      * getPartImagePath() - return image path of the part
      * getPartsName() – return the name of the part
      * getPartsNumber() – return the number of the part
      * getPartsDescription() – return the description of the part
      * deletePart() – delete this kind of part and delete this kind part from the arrayList

***

### GUIPart
      Contains data and methods for drawing and animating a part
* Constructor: 
      * GUIPart(Part part, Painter.ImageEnum partType, double x, double y )
      * GUIPart(Part part, Painter.ImageEnum partType, double x, double y, double rotation)
      * GUIPart(Part part, Painter.ImageEnum partType, Movement movement)
      * GUIPart(Part part, Painter.ImageEnum partType, Movement movement)
* Member data:
      * public Part part - used to access part data
      * public Movement movement - used to access movement data
      * private Painter.ImageEnum partType - different part has a different image
* Methods:
      * void draw(Graphics2D g, long currentTime)

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
      * removePart( Integer index ) - remove the part from the 
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
      void draw( Graphics2D g, long currentTime )

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
     * private Lane myBotLane - used to access the bot lane
* Methods:
     * void turnOn() - turn on both lanes
     * void turnOff() - turn off both lanes
     * boolean isLaneOn() - return if this pair of lane is on
     * void setAmplitude(double amplitude) - set the amplitude of this pair of lane
     * double getAmplitude() - get the amplitude of this pair of lane
     * void addPartTopLane(Part p) - add a part to top lane
     * void addPartBotLane(Part p) - add a part to bot lane
     * Part removePartTopLane() - remove end part from top lane
     * Part removePartBotLane() - remove end part from bot lane
     * double getSpeed() - return speed for both lanes

***

###GUIPartRobot
      Contains data and methods for drawing and animating a part robot
        
* Constructor: GUIPartRobot(PartRobot partRobot)
        
* Member data:
     * public PartRobot partRobot - used to access part robot data
     * public Movement movement - set the start positiion
     * private Movement baseMove, armMove, handMove - robot movement
     * private final int baseStartX = 400 - baseX locaiton
     * private final int baseStartY = 330 - baseY locaiton

        
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
     * ----methods blow are not included in V0----------------
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
     Contains data and methods for drawing and animating a kit robot

* Constructor: GUIKitRobot(KitRobot kitRobot)
        
* Member data:
     * public KitRobot kitRobot - used to access kit robot data
     * public Movement movement - The kit robot doesn't use this for movement - only to access its goal and desired end time because of the complex calculations required
     * private GUIKit kit - the one on the robot's hand
     * private Movement baseMove, armMove, handMove - basic robot's move 
     * private final int baseStartX = 300 - base location
     * private final int baseStartY = 270 - base location

* Methods:
      * void doCalculations(long currentTime) - calculates the rotations and movement of robots
      * void draw(Graphics2D g, long currentTime) - draws the kit robot
      * boolean arrived(long currentTime) - return true if baseMove, armMove, handMove are all past end time
      * void setKit(GUIKit kit) - set the current kit in the robot
      * GUIKit removeKit() - remove the kit in the robot
      * void park() - make the robot stops at the base

***

### KitDeliveryStation
      This class contains all the information about the state of the kit delivery station.
* Constructor: KitDeliveryStation()
* Member data:
      * ArrayList<Pallet> pallets
      * ----Datas blow are not included in V0---------
      * isBroken - boolean variable if conveyor is broken
      * pallets - ArrayList of pallets that are currently visible
      * GUIKitDeliveryStation guiKitDeliveryStation - used to easily link to the GUI equivalent
* Methods:
      * ----Methods blow are not included in V0----
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
if this conveyor is empty, turn this lane on and move all of the pallet down one space
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
      * void addKit(GUIKit guiKit) - add a kit into the pallet
      * boolean hasKit() - true if there is the kit in the pallet
      * GUIKit removeKit() - remove the kit from the pallet
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
      Contains data and methods for drawing the partcamera flash
        
* Constructor: GUIPartCamera(PartCamera camera, double x, double y)
        
* Member data:
      * public PartCamera camera - used to access partcamera data
      * public GUIEntity guiEntity - used to access position data
      * long age - how long the flash has been shown for long expireTime - how long the flash should last (in milliseconds)
        
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
      A feeder object.
        
* Member Data:
      * diverter - boolean variable for which side of lane items go.
      * partsLow - boolean variable for when parts are low.
      * parts - arraylist of parts in the feeder
        
* Methods:
      * void setPartsLow() - makes partsLow true
      * void setPartsUnlow() - makes partsLow false
      * void checkIfLow() - check if there are parts in feeder, (return partsLow )
      * void changeLane() - changes the lane parts are going. (diverter = !diverter)
      * Lane getLane() - returns what lane parts are going. (Diverter, false is lane 1, true is lane 2 )
      * void loadFeeder( ArrayList<Part> load ) - load the parts into the feeder
      * Part getPart() - return the first part in the list
***

###GUIFeeder
      Contains data and methods for drawing and animating a feeder
        
* Constructor: GUIFeeder(Feeder feeder, double x, double y)
        
* Member data:
      * public Feeder feeder - used to access feeder data
      * public Movement movement - used to access movement data
        
* Methods:
      void draw( Graphics2D g, long currentTime ) - draws the feeder

***

### Lane
      A lane object.
* Constructor: Lane()
* Member Data:
      * private double speed = 80
      * private boolean laneOn - true if lane is on
      * private ArrayList<Part> parts - parts on the lane
      * private double amplitude - amplitude of the lane
* Methods:
      * boolean isLaneOn() - checks if lane is on, returns LaneOn
      * void turnOff() - turn off the lane
      * void turnOn() - turn on the lane
      * void addPart(Part p) - add part to the lane
      * void setAmplitude(double amplitude) - set the amplitude
      * double getAmplitude() - return the amplitude
      * Part removePart() - remove the end part from the lane
      * double getSpeed() - return the speed of the lane
***

### GUILane
      Contains data and methods for drawing and animating a lane
        
* Constructor: GUILane(ComboLane lane, boolean isForParts, int laneLength, double x, double y)
        
* Member data:
      * public ComboLane lane - used to access combolane data
      * public Movement movement - used to access movement data
      * boolean isForParts - true if we are doing some animation about parts
      * private ArrayList<GUIPallet> pallets - pallets in the conveyor
      * private ArrayList<GUIPart> topParts, bottomParts - parts on the top and on the bottom of the lane
      * private int laneLength - lane length
      * private ArrayList<GUILaneSegment> guiLaneSegments - arraylist of gui lane segments
      * private final int conveyorEndPadding = 30 - final value
* Methods:
      * void draw(Graphics2D g, long currentTime) - draws the GUILane
      * void checkMotion(long currentTime) - if lane is on, unpause it, if lane if off, pause it
      * void addPallet() - add pallet to the pallets
      * void addPallet(GUIPallet pallet) - overwrite of the above the function
      * void GUIPallet removeEndPallet() - remove the end pallet and move everything down one space
      * void GUIKit removeEndPalletKit() - remove kit from the end pallet
      * boolean hasEmptyPalletAtEnd(long currentTime) - return true if last pallet is empty
      * boolean hasFullPalletAtEnd(long currentTime) - return true if last pallet is full
      * boolean containsPallets() - return true if pallets in on the lane
      * Point2D.Double getLocationOfEndPallet(long currentTime) - return the location of the end pallet
      * int getLaneLength() - return lane length


***

### GUILaneSegment 

* Constructor: GUILaneSegment(Movement movement)

* Member data:
      * public Movement movement - used to access movement data
* Methods:
      * void draw(Graphics2D g, long currentTime) - draw the lane segment

***


### Nest
      A Nest object.
      Not sure if it is need to be serializable
* Member Data:
      * private final int limit = 10 - instructions say 1-10 parts per nest
      * public ArrayList<Part> nestedItems - items in the nest
      * private boolean nestFull - true if nest is full
      * private boolean mySwitch - switch to control the up and down of the nest

        
* Methods:
      * boolean isNestFull() - checks if the nest is full, return NestFull
      * void addPart( Part p ) - adds a part to the NestedItems
      * Part removePart() - removes the last part from NestedItems
      * void dumpNest() - removes all parts from NestedItems
      * void flipSwitch() - flips the switch ( switch = !switch )

***

### GUINest
      Contains data and methods for drawing and animating a nest
        
* Constructor: GUINest( Nest nest, double x, double y)
        
* Member data:
      * public ArrayList<GUIPart> parts - parts in the nest
      * public Nest nest - used to access nest data
      * public Movement movement - used to access movement data
        
* Methods:
      * void addPart( GUIPart part ) - add the part into a nest
      * void draw( Graphics2D g, long currentTime ), draws the nest

***

### WholeLane
      Class with a feeder, lane, and nest as a set.
      Not sure if it should be Serializable  
* Constructor: WholeLane()
* Member Data (all of the data are private):
      * Feeder myFeeder - Feeder object for the lane.
      * ComboLane myLane - Lane object for the lane.
      * Nest myTopNest - Top nest object for lane.
      * Nest myBotNest - Bottom nest object for lane.
        
* Methods:
      * void turnOffLane() - turn off the combo lane
      * void turnOnLane() - turns on the combo lane
      * boolean areLanesOn() - true if combo lane is on
      * void divert() - feeder feeds into the other lane
      * boolean isTopNestFull() - true if the upper nest is full
      * boolean isBotNestFull() - true if the bottom nest is full
      * void feedToLane() - feed the parts into the lanes according to the number being assigned to the feeder
      * boolean topLaneToNest() - true if top lane is feeding to top nest
      * boolean botLaneToNest() - true if bot lane is feeding to bot nest
      * boolean isFeederLow() - checks if feeder is low
      * boolean isTopNestFull() - checks if top nest is full
      * boolean isBottomNestFull() - checks if bottom nest is full
      * void vibrateLane() - increases vibration of the lane
      * void unvibrateLane() - decreases vibration of the lane
      * void takePicOfNests() - takes a picture of both nests
      * void flipTopNestSwitch() - flips top Nests switch
      * void flipBotNestSwitch() - flips bottom Nests switch
      * int getLane() - return the lane
      * void fillFeeder( ArrayList<Part> load ) - fill the feeder with parts
      * Feeder getFeeder() - return the feeder
      * ComboLane getComboLane() - return the combo lane

***

### GUIWholeLane
      Contains data and methods for drawing the whole lane
        
* Constructor: GUIWholeLane(WholeLane wholeLane, double x, double y)
        
* Member data:
      * public WholeLane wholeLane - used to access lane data
      * public GUIEntity guiEntity - used to access movement data
        
* Methods:
       * void draw(Graphics g, long elapsedMillis) - draws all components of the lane