import java.awt.geom.*;
import java.io.*;

/** represents the movement of an object that starts at a specified position and rotation,
    moves at constant velocity to a specified end position and rotation, then stops */
public class Movement implements Serializable {
  /** position at beginning of this move */
  private Point2D.Double startPos;
  /** counterclockwise rotation in radians at beginning of this move */
  private double startRot;
  /** time that this move starts, in milliseconds after the simulation started */
  private int startTime;
  /** position at end of this move */
  private Point2D.Double endPos;
  /** counterclockwise rotation in radians at end of this move */
  private double endRot;
  /** time that this move ends, in milliseconds after the simulation started */
  private int endTime;

  /** constructor that sets all instance variables to specified values */
  public Movement(Point2D.Double newStartPos, double newStartRot, int newStartTime,
                  Point2D.Double newEndPos, double newEndRot, int newEndTime) {
    if (newEndTime <= newStartTime) {
      throw new IllegalArgumentException("end time must be later than start time");
    }
    startPos = newStartPos;
    startRot = newStartRot;
    startTime = newStartTime;
    endPos = newEndPos;
    endRot = newEndRot;
    endTime = newEndTime;
  }

  /** returns position at specified time */
  public Point2D.Double getPosWhen(int time) {
    if (time <= startTime) {
      return startPos;
    }
    if (time >= endTime) {
      return endPos;
    }
    return new Point2D.Double(startPos.getX() + (endPos.getX() - startPos.getX()) / (endTime - startTime) * (time - startTime),
                              startPos.getY() + (endPos.getY() - startPos.getY()) / (endTime - startTime) * (time - startTime));
  }

  /** returns rotation at specified time */
  public double getRotWhen(int time) {
    if (time <= startTime) {
      return startRot;
    }
    if (time >= endTime) {
      return endRot;
    }
    return startRot + (endRot - startRot) / (endTime - startTime) * (time - startTime);
  }
}
