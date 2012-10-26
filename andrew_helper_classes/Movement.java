import java.awt.geom.*;
import java.io.*;

public class Movement implements Serializable {
  private Point2D.Double startPos;
  private double startRot;
  private int startTime;
  private Point2D.Double endPos;
  private double endRot;
  private int endTime;

  public Movement(Point2D.Double newStartPos, double newStartRot, int newStartTime, Point2D.Double newEndPos, double newEndRot, int newEndTime) {
    startPos = newStartPos;
    startRot = newStartRot;
    startTime = newStartTime;
    endPos = newEndPos;
    endRot = newEndRot;
    endTime = newEndTime;
  }

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
