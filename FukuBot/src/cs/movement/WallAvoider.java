package cs.movement;

import java.awt.geom.Point2D;

import cs.FukuBot;
import robocode.*;

public class WallAvoider{
	private static final int WALL_MARGIN = 10;
	private FukuBot _robot;
	private double _upboundWidth, _upboundHeight, _lowboundWidth, _lowboundHeight;
	public WallAvoider(FukuBot robot)
	{
		_robot = robot;
		_upboundWidth = _robot.getBattleFieldWidth() - WALL_MARGIN;
		_upboundHeight = _robot.getBattleFieldHeight() - WALL_MARGIN;
		_lowboundWidth = WALL_MARGIN;
		_lowboundHeight = WALL_MARGIN;
		_robot.addCustomEvent(new Condition("too_close_to_walls") {
			public boolean test() {
				return (
					// we're too close to the left wall
						(_robot.getX() <= _lowboundWidth ||
					 // or we're too close to the right wall
						_robot.getX() >= _upboundWidth ||
					 // or we're too close to the bottom wall
						_robot.getY() <= _lowboundHeight ||
					 // or we're too close to the top wall
						_robot.getY() >= _upboundHeight )
					 // or we're too close to enemy
//						Math.abs(_robot.enemy.getDistance()) >= wallMargin)
				);
			}
		});
	}
	public static boolean hitWall(Point2D.Double location, double max_width, double max_height){
		if (location.x <= WALL_MARGIN || location.x >= max_width - WALL_MARGIN || location.y <= WALL_MARGIN || location.y >= max_height - WALL_MARGIN)
		{
			return true;
		}
		else{
			return false;
		}
	}
}
