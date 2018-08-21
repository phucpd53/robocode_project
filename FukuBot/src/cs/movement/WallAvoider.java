package cs.movement;

import cs.FukuBot;
import robocode.*;

public class WallAvoider{
	public int wallMargin = 10;
	private FukuBot _robot;
	public WallAvoider(FukuBot robot)
	{
		_robot = robot;
		_robot.addCustomEvent(new Condition("too_close_to_walls") {
			public boolean test() {
				return (
					// we're too close to the left wall
						(_robot.getX() <= wallMargin ||
					 // or we're too close to the right wall
						_robot.getX() >= _robot.getBattleFieldWidth() - wallMargin ||
					 // or we're too close to the bottom wall
						_robot.getY() <= wallMargin ||
					 // or we're too close to the top wall
						_robot.getY() >= _robot.getBattleFieldHeight() - wallMargin )
					 // or we're too close to enemy
//						Math.abs(_robot.enemy.getDistance()) >= wallMargin)
				);
			}
		});
	}
}
