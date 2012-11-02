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