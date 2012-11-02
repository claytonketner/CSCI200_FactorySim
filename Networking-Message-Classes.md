### \*All classes implement Serializable

### CloseConnectionMsg
          networking message indicating to close connection
          class is empty (no member data or methods) because the requested command is self-evident from the data type

***

### StringMsg
    networking message containing a string
* Member Data (all public):
      * type - instance of an enum indicating the type of message (NewPart, ChangePart, DeletePart, NewKit, ChangeKit, DeleteKit, ProduceKits, NonNormative)
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
      * oldName - old name of part to change
      * part - replacement Part

***

### DeletePartMsg
    networking message indicating to delete a part
* Member Data (all public):
      * name - name of part to delete

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
      * oldName - old name of kit to change
      * kit - replacement Kit

***

### DeleteKitMsg
    networking message indicating to delete a Kit
* Member Data (all public):
     * name - name of kit to delete

***

### ProduceStatusMsg
    networking message listing status of all kits in production
* Member Data (all public):
     * kitCmds - ArrayList of ProduceKitsMsg’s that have been sent to server
     * kitStatus - ArrayList of instances of an enum indicating whether each kit command is queued, in                      * production, or completed

***

### ItemUpdateMsg<T>
    generic class updating the state of all items of specified type T
* Member Data (all public):
     * add - TreeMap<Integer, T> in which key is ID of new item and entry is new item
     * change - TreeMap<Integer, T> in which key is ID of changed items and entry is changed item
     * delete - ArrayList<Integer> containing IDs of deleted items

***

