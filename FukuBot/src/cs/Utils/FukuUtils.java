package cs.Utils;
import java.util.*;
import java.awt.geom.Point2D;

import robocode.util.Utils;
import cs.FukuBot;

public class FukuUtils {
	public static double normalizeBearing(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
	
	public static Point2D.Double projectLocation(Point2D.Double myLocation, double angle, double distance){
		return new Point2D.Double(myLocation.getX() + Math.sin(Math.toRadians(angle)) * distance, myLocation.getY() + Math.cos(Math.toRadians(angle)) * distance);
	}
	
	public static double euclidDistance(Point2D.Double p1, Point2D.Double p2){
		return Math.sqrt(Math.pow(p1.x - p2.x,2) + Math.pow(p1.y - p2.y,2));
	}
	
	public static double absoluteBearing(Point2D.Double sourceLocation, Point2D.Double target) {
		return Math.atan2(target.x - sourceLocation.x, target.y - sourceLocation.y);
	}
	
	public static void setBackAsFront(FukuBot robot, double goAngle) {
	    double angle = Utils.normalRelativeAngleDegrees(goAngle - robot.getHeading());
	    
	    if (Math.abs(angle) > 90) {
	      if (angle < 0) {
	        robot.setTurnRight(180 + angle);
	      } else {
	        robot.setTurnLeft(180 - angle);
	      }
	      robot.setBack(100);
	    } else {
	      if (angle < 0) {
	        robot.setTurnLeft(-1 * angle);
	      } else {
	        robot.setTurnRight(angle);
	      }
	      robot.setAhead(100);
	    }
	  }
}
