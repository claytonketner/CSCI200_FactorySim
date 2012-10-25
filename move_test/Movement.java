import java.awt.geom.*;
import java.io.*;

public class Movement implements Serializable {
  private Point2D.Double startPos;
  private int startTime;
  private Point2D.Double endPos;
  private int endTime;

  public Movement(Point2D.Double newStartPos, int newStartTime, Point2D.Double newEndPos, int newEndTime) {
    startPos = newStartPos;
    startTime = newStartTime;
    endPos = newEndPos;
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
}
