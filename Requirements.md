This piece of software will have one server application which will send and receive information to and from the back end of the kitting cell and will communicate similarly with several GUI clients.  The server will hold the “state” information about each device within the cell.  This information may be updated by the front or back end.  Each of the client application GUIs will allow a kitting cell employee to view the state of the device(s) for which they are responsible.  Some GUIs will also allow the user to set configuration information or simulate non-normative scenarios within the factory.  The GUI clients consist of:

### Parts Manager
* Set available parts ( create, edit, delete )
* Parts will have the following attributes ( number, name, description, image )
* This panel will not have any graphics
* Parts will be serializable

### Kit Manager
* Set available kits ( create, edit, delete )
* Kits will have the following attributes ( number, name, description, static image )
* Assigns location of a part in the kit
* This panel will not have graphics
* Kits will be serializable
   
###Factory Production Manager
* Specific the kit making queue for the factory from available kits list
* *  Which kit
            How many of each
        View current production schedule
        Graphical view of entire factory
    Lane Manager
        Graphics for:
            Feeders
            Lanes
            Nests
            Diverters
            Nest cameras
        Panel for non-normative operation
            Break/fix devices within lane manager’s purview
    Kit Assembly Manager
        Graphics for:
            Part robot
            Kit robot
            Kitting stand
            Kit delivery station
        Panel for non-normative operation
            Break/fix devices within kit manager’s purview
    Gantry Robot Manager
        Graphics for:
            Gantry robot
            Parts bins
            Purge station
        Panel for non-normative operation
            Break/fix devices within gantry robot manager’s purview