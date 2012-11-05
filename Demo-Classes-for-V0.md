### \*All classes are only for V0 demo

### LaneDemo extends JPanel
* Constructor: LaneDemo()
* Member Data (All the datas are none type):
      * WholeLane lane1 -initialization of a whole lane
      * Part p - initialization of a part
      * GUILane gl - initialization of a GUI lane
      * GUIPart gp1- initialization of the first GUI part
      * GUIPart gp2 - initialization of the second GUI part
      * GUIFeeder gf - initialization of the feeder
      * GUIDiverter gd - initialization of the diverter
      * GUIDiverterArm gda - initialization of the DiverterArm
      * GUIPartsBox gpb - initialization of the PartsBox
      * GUIPart gp3 - initialization of the third GUI part
      * GUIPart gp4 - initialization of the fourth GUI part
      * GUIPart lastPart - this points to the last part in the lane
      * int paintCount = 0 - counter to help calculate what movement the part should do
* Methods:
      * void paint(Graphics gfx) - hard code the animation

*** 

### PartRobotDemo extends JPanel implements ActionListener
* Constructor: PartRobotDemo ()
* Member Data (All the datas are none type):
      * ArrayList<GUINest> nests - arraylist of nest
      * GUIKitStand guiKitStand - initialization of a kit stand
      * GUIPartRobot guiPartRobot - initialization of a part robot
      * int paintCount = 0, timerFireCount = 0 - counters to help hard code animation
      * Timer moveTimer, cameraTimer - timer
      * GUIFlash camFlash
* Methods:
      * void paint(Graphics gfx) - paint the partrobot, kit stand, nests
      * void actionPerformed( ActionEvent ae ) - hard code the animation

***

### KitRobotDemo extends 
* Constructor: KitRobotDemo()

* Member Data:
      * private enum Status {GETKITFROMINCONVEYOR, PUTKITONSTAND, GETKITFROMSTAND, TAKINGPICTURE, PUTKITONOUTCONVEYOR, IDLE}
      * GUIKitDeliveryStation guiKitDeliveryStation - initialization of a kit delivery station
      * GUIKitRobot guiKitRobot - initialization of a gui kit robot 
      * GUIKitStand guiKitStand - initialization of a gui kit stand 
      * private Status status = Status.IDLE 
Methods:
      * void paint(Graphics gfx) - paint kit robot, kit stand, kit delivery station
      * void checkStatus(long currentTime) - hard code the animation