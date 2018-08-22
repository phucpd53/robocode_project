package cs.movement;

import robocode.util.Utils;
import cs.FukuBot;

public class Dancer {
	private static int MOVE_AMOUNT = 50;
	
	private FukuBot _robot;
	private int moveDirection = 1;
	public Dancer(FukuBot robot)
	{
		_robot = robot;
	}
	public void execute()
	{
		if (_robot.getTime()%20 == 0)
		{
			reserve();
		}
		move();
	}
	private void move(){
		if (_robot.enemy.getDistance() > 100){
			_robot.setTurnRight(Utils.normalRelativeAngleDegrees(_robot.enemy.getBearing() + 90 - (20 * moveDirection)));
			_robot.setAhead(MOVE_AMOUNT * moveDirection);
		}
		else{
			_robot.setTurnRight(Utils.normalRelativeAngleDegrees(_robot.enemy.getBearing() + 90 + (20 * moveDirection)));
			_robot.setAhead(MOVE_AMOUNT * moveDirection);
		}
	}
	public void dodge(){
		_robot.setTurnRight(Utils.normalRelativeAngleDegrees(_robot.enemy.getBearing() + 90 + (20 * moveDirection)));
//		moveDirection *= -1;
		_robot.setAhead(25 * moveDirection);
	}
		
	public void reserve(){
		moveDirection *= -1;
	}
}
