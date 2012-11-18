### \*All classes implement Serializable

### CloseConnectionMsg
          To view the Member Data and Methods in this class, please go to docs/CloseConnectionMsg.html

***

### StringMsg
          To view the Member Data and Methods in this class, please go to docs/StringMsg.html

***

### NewPartMsg
          To view the Member Data and Methods in this class, please go to docs/NewPartMsg.html

***

### ChangePartMsg
          To view the Member Data and Methods in this class, please go to docs/ChangePartMsg.html

***

### DeletePartMsg
          To view the Member Data and Methods in this class, please go to docs/DeletePartMsg.html

***

### PartListMsg
          To view the Member Data and Methods in this class, please go to docs/PartListMsg.html

***

### NewKitMsg
          To view the Member Data and Methods in this class, please go to docs/NewKitMsg.html

***

### ChangeKitMsg
          To view the Member Data and Methods in this class, please go to docs/ChangeKitMsg.html

***

### DeleteKitMsg
          To view the Member Data and Methods in this class, please go to docs/DeleteKitMsg.html

***

### KitListMsg
          To view the Member Data and Methods in this class, please go to docs/KitListMsg.html

***

### ProduceKitsMsg
          To view the Member Data and Methods in this class, please go to docs/ProduceKitsMsg.html

***

### ProduceStatusMsg
          To view the Member Data and Methods in this class, please go to docs/ProduceStatusMsg.html

***

### ItemUpdateMsg< T >;
          To view the Member Data and Methods in this class, please go to docs/ItemUpdateMsg.html

***

### FactoryStateMsg
          To view the Member Data and Methods in this class, please go to docs/FactoryStateMsg.html

***

### FactoryUpdateMsg
          To view the Member Data and Methods in this class, please go to docs/FactoryUpdateMsg.html

***

### NonNormativeItemMsg (Not needed in V1, so we have not actually added yet)
    networking message indicating to break or fix an item
* Member Data (all public):
     * fix - true if should fix item, false if should break item
     * type - instance of an enum indicating the type of item to break or fix (tpKitRobot, tpPartRobot, tpKitDeliveryStation, tpNest, tpLane, tpFeeder, tpGantry)
     * id - integer ID of item to break or fix

***