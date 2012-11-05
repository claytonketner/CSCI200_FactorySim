***

### Helper Classes:


### Painter 
           to paint the GUI stuffs
* Member Data:
      * private static TreeMap<ImageEnum, ImageIcon> allImages;
      
* Member Data:
      * static void draw(Graphics2D g, ImageEnum partType, long currentTime, Movement movement, boolean useCenterPoint) - draw the GUI stuffs
      * public static enum ImageEnum - store the images
      * static void draw(Graphics2D g, ImageEnum partType, int desiredWidth, int desiredHeight, long currentTime, Movement movement, boolean useCenterPoint) - draw and scale. Set either desiredWidth or desiredHeight to -1 to set automatic scaling for that dimension
     * public static int getScaledWidth(ImageEnum partType, int desiredHeight) - convert the ImageIcon to BufferedImage to rotate and scale
     * public static int getScaledHeight(ImageEnum partType, int desiredWidth) - convert the ImageIcon to BufferedImage to rotate and scale
     * public static void loadImages() - load the images from the file
***

### NetComm
           encapsulates networked communication with a single client or server
* Constructor: NetComm(Socket newSocket, Networked networkNode) - constructor that initializes communication with given node (that implements Networked) and given Socket; throws IOException if fails
* Member Data (all private):
      * node - object that is notified when a new message is received
      * socket - client socket that is endpoint for network communication
      * in - ObjectInputStream that reads messages related to this client over the network
      * out - ObjectOutputStream that writes messages related to this client over the network
* Methods:
      * write - write specified object to output stream
      * close - close connection

### NetCommReader
           inner class of NetComm that runs on a separate thread to receive messages
* Methods:
      * run - keep polling for new messages, and forward received messages to network node (this is automatically called when thread starts)
      * read - returns object read from input stream, or null if no new message, or a CloseConnectionMsg if either client or server disconnected from network

### ConnectPanel
           panel that all client (manager) classes display to let user connect to server;
           this class is based on SignInPanel.java in Andrew's HW3 submission

Mockup (labeled components are described in Member Data section):

![fsdfsd](https://github.com/usc-csci200-fall2012/team11/diff_blob/2ad6e9034a993c18607b54abbe4d23667f3f4627/design/images/image05.png?raw=true)

* Member Data (all private):
      * txtServerAddress - text box to enter server domain name or IP address
      * btnConnect - button to connect to server
      * lblActionMsg - label for displaying message or error
      * action - reference to action listener to notify when user presses connect to server button
* Methods:
      * ConnectPanel - constructor that sets up the panel GUI and stores reference to action listener to notify to connect to server
      * reset - reset text field and action message text
      * setActionMsg - display specified action message, allowing HTML formatting
      * setActionError - display specified action message in red text, allowing HTML formatting
      * actionPerformed - when button clicked, validate sign in info and notify parent action listener