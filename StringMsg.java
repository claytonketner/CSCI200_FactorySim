import java.io.*;

/** networking message containing a string;
    this class is modified from StringMsg.java in Andrew's HW3 submission */
public class StringMsg implements Serializable {
  /** categories that can be associated with a StringMsg */
  public enum MsgType {
    NEW_PART, CHANGE_PART, DELETE_PART, NEW_KIT, CHANGE_KIT, DELETE_KIT, PRODUCE_KITS, NON_NORMATIVE
  }

  public MsgType type; /**< type of string message */
  public String message; /**< content of string message */

  /** constructor for string message */
  public StringMsg(MsgType newType, String newMessage) {
    type = newType;
    message = newMessage;
  }
}
