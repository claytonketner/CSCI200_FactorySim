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