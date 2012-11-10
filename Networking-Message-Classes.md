### \*All classes implement Serializable

### CloseConnectionMsg
          networking message indicating to close connection
          class is empty (no member data or methods) because the requested command is self-evident from the data type

***

### StringMsg
    networking message containing a string
* Member Data (all public):
      * type - instance of an enum indicating the type of message (NEW_PART, CHANGE_PART, DELETE_PART, NEW_KIT, CHANGE_KIT, DELETE_KIT, PRODUCE_KITS, NON_NORMATIVE)
      * message - content of string message (generally empty strings indicate success and non-empty strings are an error description)
* Methods:
      * StringMsg - constructor to set up StringMsg with specified type and message

***

### NewPartMsg
    networking message indicating to add a new part 
    (is a separate class even though it only contains 1 instance 
    variable because it specifies that the command is to add a new part)
* Member Data (all public):
      * part - Part instance to add

***

### ChangePartMsg
    networking message indicating to change a part
* Member Data (all public):
      * oldNumber - old number of part to change
      * part - replacement Part

***

### DeletePartMsg
    networking message indicating to delete a part
* Member Data (all public):
      * number - part number of part to delete

***

### PartListMsg
    networking message listing all available parts
* Member Data (all public):
      * parts - ArrayList of available parts

***

### NewKitMsg
    networking message indicating to add a new kit
    (is a separate class even though it only contains 1 instance variable because it specifies that the       
    command is to add a new kit)
* Member Data (all public):
      * kit - Kit instance to add

***

### ChangeKitMsg
    networking message indicating to change a kit
* Member Data (all public):
      * oldNumber - old number of kit to change
      * kit - replacement Kit

***

### DeleteKitMsg
    networking message indicating to delete a Kit
* Member Data (all public):
     * number - kit number of kit to delete

***

### KitListMsg
    networking message listing all available kits
* Member Data (all public):
     * kits - ArrayList of available kits

***

### ProduceKitsMsg
    networking message specifying kits to produce in factory
* Member Data (all public):
     * kitNumber - kit number corresponding to type of kit to produce
     * howMany - how many new kits to produce

***

### ProduceStatusMsg
    networking message listing status of all kits in production
* Member Data (all public):
     * kitCmds - ArrayList of ProduceKitsMsg’s that have been sent to server
     * kitStatus - ArrayList of instances of an enum indicating whether each kit command is queued, in production, or completed

***

### ItemUpdateMsg&lt;T&gt;
    generic class updating the state of all items of specified type T
* Member Data (all public):
     * putItems - TreeMap in which key is ID of new or updated item and entry is new or updated item
     * removeItems - ArrayList<Integer> containing IDs of deleted items

***

### FactoryStateMsg
    networking message containing all information needed to generate factory state
    note that if a client sends an empty FactoryStateMsg, it means they are requesting to be kept up-to-date                
    with the factory state as long as it is connected to the server
* Member Data (all public):
    These are all TreeMaps in which the keys are integers (the item IDs) and the entries are the GUI     
    versions of the specified classes.
     * parts
     * kits
     * partRobots
     * kitRobots
     * kitDeliveryStations
     * pallets
     * gantries
     * bins
     * partCameras
     * kitCameras
     * feeders
     * lanes
     * nests
     * wholeLanes
* Methods:
     * update - updates the factory state given a FactoryUpdateMsg

***
### FactoryUpdateMsg
    networking message updating factory state
* Member Data (all public):

    The fields below are ArrayLists of ItemUpdateMsgs of the GUI versions of the specified classes.
     * parts
     * kits
     * partRobots
     * kitRobots
     * kitDeliveryStations
     * pallets
     * gantries
     * bins
     * partCameras
     * kitCameras
     * feeders
     * lanes
     * nests
     * wholeLanes

    The fields below are TreeMaps in which the keys are integer IDs and the entries are updated Movement's of the specified classes.
     * partMoves
     * kitMoves
     * partRobotMoves
     * kitRobotMoves
     * kitDeliveryStationMoves
     * palletMoves
     * gantrieMoves
     * binMoves
     * partCameraMoves
     * kitCameraMoves
     * feederMoves
     * laneMoves
     * nestMoves
     * wholeLaneMoves

***

### NonNormativeItemMsg
    networking message indicating to break or fix an item
* Member Data (all public):
     * fix - true if should fix item, false if should break item
     * type - instance of an enum indicating the type of item to break or fix (tpKitRobot, tpPartRobot, tpKitDeliveryStation, tpNest, tpLane, tpFeeder, tpGantry)
     * id - integer ID of item to break or fix

***