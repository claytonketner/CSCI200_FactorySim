### \*All classes implement Serializable

### GUIEntity
* Constructor(double x, double y)
* Constructor(double x, double y, double rotation)

* Member data:
      * String imagePath - the path/filename of the image
      * ImageIcon image - the image for the entity
      * speed - speed in pixels per second
      * x_last - x location at last setDesired method call
      * y_last - y location at last setDesired method call
      * x_current - current x location (double)
      * y_current - current y location (double)
      * x_desired - desired x location (double)
      * y_desired - desired y location (double)
      * rotation_current - current rotation (double)
      * rotation_desired - desired rotation (double)

* Methods:
      * void setCurrentX(double x)
      * void setCurrentY(double y)
      * void setCurrentRotation(double angle)
      * void setCurrentLocation(Point2D.Double location)
      * void setDesiredX(double x)
      * void setDesiredY(double y)
      * void setDesiredRotation(double angle)
      * void setDesiredLocation(Point2D.Double location)
      * double getCurrentX()
      * double getCurrentY()
      * double getCurrentRotation()
      * Point2D.Double getCurrentLocation()
      * double getDesiredX()
      * double getDesiredY()
      * double getDesiredRotation()
      * Point2D.Double getDesiredLocation()
      * void tick(long currentTime)

***

### Part
> This class defines a parts and its attributes.
        
* Member Data:
      * number – int part number
      * name – string part name
      * description – string part description

* Methods:
      * setPartName() – set the name of the part
      * setPartDescription() – return the description of the part
      * setPartNumber() – set the number of the part(it has to be a specific one)
      * getPartImagePath() - return image path of the part
      * getPartsName() – return the name of the part
      * getPartsNumber() – return the number of the part
      * getPartsDescription() – return the description of the part
      * deletePart() – delete this kind of part and delete this kind part from the arrayList

***

### GUIPart
> Contains data and methods for drawing and animating a part

* Constructor: GUIPart(Part part, double x, double y)
        
* Member data:
      * public Part part - used to access part data
      * public GUIEntity guiEntity - used to access movement data
      * image - static ImageIcon of the part
      
* Methods:
      * void tick(long currentTime) - calculates movement
      * void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

***

### Kit
> This class defines a kit and its attributes, use gridLayout to define the part location.
        
* Member Data:
      * number – int kit number.
      * name – string kit name
      * description – string kit description
      * partsNeeded - list of what parts does this kit need
      * kitStatus - is it completed, incomplete, or a part’s location is wrong
        
* Methods:
      * setKitName() – set the name of the kit
      * getKitStatus() - return the kit status
      * setKitStatus() - set the kit status
      * setKitDescription() – return the description of the kit
      * setKitNumber() – set the number of the kit (it has to be a specific one)
      * setPartsInKit(ArrayList<Kit> kits) - set the parts in a kit
      * getPartsInKit() - get the parts in a kit

***

### GUIKit
> Contains data and methods for drawing and animating a kit
        
* Constructor: GUIKit(Kit kit, double x, double y)
        
* Member data:
      * public Kit kit - used to access kit data
      * public GUIEntity guiEntity - used to access movement data
      * kitImagePath - string path to the kit’s image

* Methods:
      * void tick(long currentTime) - calculates movement
      * void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

***

### PartRobot
> This class defines and controls a part robot.
        
* Member Data:
      * partsInGripper - ArrayList of Part type of what is in the grippers
      * isBroken - boolean variable if robot is broken
      * GUIPartRobot guiPartRobot - used to easily link to the GUI equivalent

* Methods:
      * getKitStandPos - returns the kitting position of the part robot, returns null if the part robot is 
not at the kitting stand.
      * setBroken - sets isBroken
      * getBroken - returns isBroken value

***

    GUIPartRobot

        Contains data and methods for drawing and animating a part robot
        Constructor: GUIPartRobot(PartRobot partRobot, double x, double y)
        Member data:
            public PartRobot partRobot - used to access part robot data
            public GUIEntity guiEntity - used to access movement data
        Methods:
            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    KitRobot   
        This class defines and controls a part robot.
        Member Data:
            kit - a Kit variable of the kit in its hands/grippers
            isBroken - boolean variable if robot is broken
            GUIKitRobot guiKitRobot - used to easily link to the GUI equivalent
        Methods:
            getPos - returns the kitting stand position of the kit robot.  returns null if the robot is not at the kitting stand.
            setBroken - sets isBroken
            getBroken - returns isBroken value

    GUIKitRobot

        Contains data and methods for drawing and animating a kit robot
        Constructor: GUIKitRobot(KitRobot kitRobot, double x, double y)
        Member data:
            public KitRobot kitRobot - used to access kit robot data
            public GUIEntity guiEntity - used to access movement data
        Methods:
            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    KitDeliveryStation
        This class contains all the information about the state of the kit delivery station.
        Member data:
            isBroken - boolean variable if conveyor is broken
            pallets - ArrayList of pallets that are currently visible
            GUIKitDeliveryStation guiKitDeliveryStation - used to easily link to the GUI equivalent
        Methods:
            setBroken - sets isBroken
            getBroken - returns isBroken value

    GUIKitDeliveryStation

        Contains data and methods for drawing and animating the kit delivery station
        Constructor: GUIKitDeliveryStation (KitDeliveryStation kitDeliveryStation, double x, double y)
        Member data:
            public KitDeliveryStation kitDeliveryStation - used to access delivery station data
            public GUIEntity guiEntity - used to access movement data
        Methods:
            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    Pallet
        This class contains all the information about a pallet.
        Member data:
            kit- variable holding the kit on the pallet.
            hasKit - boolean if pallet contains a kit.
        Methods:

    GUIPallet

        Contains data and methods for drawing and animating the pallet
        Constructor: GUIPallet (Pallet pallet, double x, double y)
        Member data:
            public Pallet pallet - used to access pallet data
            public GUIEntity guiEntity - used to access movement data
        Methods:

    void tick(long currentTime) - calculates movement
    void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    Gantry
        This class defines and controls a gantry robot.
        Member Data:
            holdingObject - boolean variable that detects if the
                gantry is currently holding an object
            broken- boolean variable recording the state of this client
            binLocations - arraylist of GUIBin objects used to detect available locations to set a bin
        Methods:
            isHoldingObject - returns value of holdingItem
            retrieveFullBin(bin, feeder) - acts according to which
                feeder requests which bin (this method will utilize the helper
                methods moveTo(), pickUp(), dump(), and drop())
            placeInPurgeStation(feeder) - acts according to which
                feeder was just fed (this method will utilize the helper methods
                moveTo(), pickUp(), dump(), and drop())
            placeInTempLoc - acts according to
                binLocations (this method will utilize the helper methods
                moveTo(), pickUp(), dump(),and drop())
            isBroken - returns value of broken; affects the physical look of the gantry
                robot depending on broken state
            moveTo - either move to the new bin’s location, move to the feeder with the new bin, move empty bin to purge station, or move purged bin to temporary location depending
            pickUp - commands gantry to pick up the bin at its current array index (in availableLocations arraylist)
            dump - feed contents of bin into feeder
            drop - place bin at gantry’s current array index (in availableLocation arraylist)


    GUIGantry

        Contains data and methods for drawing and animating the gantry
        Constructor: GUIGantry(Pallet pallet, double x, double y)
        Member data:
            public Gantry gantry - used to access gantry data
            public GUIEntity guiEntity - used to access movement data
        Methods:

            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    Bin(Part part)
        This class contains all the information about bins. The bins will be responsible for
            a single type of part, which will be passed into its constructor upon
            instantiation
        Member Data:
            partName- name of part represented by type String
            partImage - image of part used to identify bin
            xPos - position of x-coordinate
            yPos - position of y-coordinate

            GUIBin guiBin - used to easily link to the GUI equivalent

        Methods:
            getPartType- returns part type held by bin

   

    GUIBin

        Contains data and methods for drawing and animating a bin
        Constructor: GUIBin (Bin bin, double x, double y)
        Member data:
            public Bin bin - used to access bin data
            public GUIEntity guiEntity - used to access movement data
        Methods:

            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    PartCamera:
        Member Data:
            cameraBlocked - boolean for when the robot is blocking the camera
            previousImage - image of what the nest looked like previously
            currentImage - image of the picture just taken
            nestUnchanged - boolean for checking if the new picture matches the

previous picture of the nest

        Methods:
            takePicture() - takes a picture when there is a clear view
            cameraIsBlocked() - cameraBlocked gets set to true
            cameraIsClear() - cameraBlocked gets set to false
            compareImages() - compares previousImage with currentImage,

nestUnchanged is true if they are the same, otherwise it is false, previousImage is then set to currentImage.

            isNestUnchanged() - returns nestUnchanged

    GUIPartCamera

        Contains data and methods for drawing the partcamera flash
        Constructor: GUIPartCamera(PartCamera camera, double x, double y)
        Member data:
            public PartCamera camera - used to access partcamera data
            public GUIEntity guiEntity - used to access position data
            long age - how long the flash has been shown for
            long expireTime - how long the flash should last (in milliseconds)
        Methods:

            void tick(long currentTime) - updates age
            boolean isExpired() - returns true if age > expireTime. If true, then this object should be deleted
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the flash

       

    KitCamera:
        This class defines the components of the kitting stand camera
        Member Data:
            currentKit - Kit that is supposed to be created
        Methods:
            getCurrentKit - gets currently queued kit for comparison
            takePicture - triggers picture flash animation and compares currentKit

to the Kit at the inspection station, sets kitStatus.

    GUIKitCamera

        Contains data and methods for drawing the kitcamera flash
        Constructor: GUIKitCamera(KitCamera camera, double x, double y)
        Member data:
            public KitCamera camera - used to access kitcamera data
            public GUIEntity guiEntity - used to access position data
            long age - how long the flash has been shown for
            long expireTime - how long the flash should last (in milliseconds)
        Methods:

            void tick(long currentTime) - updates age
            boolean isExpired() - returns true if age > expireTime. If true, then this object should be deleted
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the flash

    Feeder
        A feeder object.
        Member Data:
            diverter - boolean variable for which side of lane items go.
            partsLow - boolean variable for when parts are low.
            amount - int variable for amount of parts in feeder.
        Methods:
            setPartsLow() - makes partsLow true
            setPartsUnlow() - makes partsLow false
            checkIfLow() - check if there are parts in feeder, (return partsLow )
            changeLane() - changes the lane parts are going. (diverter = !diverter)
            getLane() - returns what lane parts are going. (Diverter, false is lane 1, true is lane 2 )

    GUIFeeder

        Contains data and methods for drawing and animating a feeder
        Constructor: GUIFeeder(Feeder feeder, double x, double y)
        Member data:
            public Feeder feeder - used to access feeder data
            public GUIEntity guiEntity - used to access movement data
        Methods:

            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    Lane
        A lane object.
        Member Data:
            velocity - int variable for speed of the lane.
            laneOn - boolean variable for when the lane is on.
            parts - ArrayList of X parts.
            amplitude - double variable for how much lane is vibrating.
        Methods:
            isLaneOn() - checks if lane is on, returns LaneOn
            setAmplitude() - sets the amplitude of the lane vibration
            removeItem() - remove a part from MovingItems
            addPart() - add a part to parts
            getAmplitude() - return amplitude of the lane

    GUILane

        Contains data and methods for drawing and animating a lane
        Constructor: GUILane(Lane lane, double x, double y)
        Member data:
            public Lane lane - used to access lane data
            public GUIEntity guiEntity - used to access movement data
        Methods:

            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    Nest
        A Nest object.
        Member Data:
            nestedItems - ArrayList of X parts.
            nestFull - boolean variable for when nest is full.
            limit - int variable for number of parts the nest can hold.
            switch - boolean for when nest is up or down
        Methods:
            isNestFull() - checks if the nest is full, return NestFull
            addPart() - adds a part to the NestedItems
            removePart() - removes a part from NestedItems
            dumpNest() - removes all parts from NestedItems
            flipSwitch() - flips the switch ( switch = !switch )

    GUINest

        Contains data and methods for drawing and animating a nest
        Constructor: GUILane(Nest nest, double x, double y)
        Member data:
            public Nest nest - used to access nest data
            public GUIEntity guiEntity - used to access movement data
        Methods:

            void tick(long currentTime) - calculates movement
            void draw(Graphics g, long elapsedMillis) - calls tick(), draws the part

    WholeLane
        Class with a feeder, lane, and nest as a set.
        Member Data:
            myFeeder - Feeder object for the lane.
            myLane - Lane object for the lane.
            myTopNest - Top nest object for lane.
            myBottomNest - Bottom nest object for lane.
        Methods:
            turnOnLane() - turns its lane on
            isFeederLow() - checks if feeder is low
            isTopNestFull() - checks if top nest is full
            isBottomNestFull() - checks if bottom nest is full
            vibrateLane() - increases vibration of the lane
            unvibrateLane() - decreases vibration of the lane
            takePicOfNests() - takes a picture of both nests
            flipNestOneSwitch() - flips Nest 1’s switch
            flipNestTwoSwitch() - flips Nest 2’s switch

    GUIWholeLane

        Contains data and methods for drawing the whole lane
        Constructor: GUIWholeLane(WholeLane wholeLane, double x, double y)
        Member data:
            public WholeLane wholeLane - used to access lane data
            public GUIEntity guiEntity - used to access movement data
        Methods:

            void draw(Graphics g, long elapsedMillis) - draws all components of the lane