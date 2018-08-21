package cs.scan;
import robocode.util.Utils;
import cs.FukuBot_test;
public class Scanner {
	private FukuBot_test _robot;
	public static double MAX_SCAN_TRACKING_AMOUNT = 45;
	private static double RADAR_TURN_GAIN = 1.5;
	public Scanner(FukuBot_test robot)
	{
		_robot = robot;
	}
	public void execute()
	{
		_robot.setTurnRadarRight(360);
	}
	public void tracking_scan(int magnitude)
	{
		double radarTurnAmount = magnitude * RADAR_TURN_GAIN * Utils.normalRelativeAngleDegrees(_robot.getHeading() - _robot.getRadarHeading() + _robot.enemy.getBearing());
		if (Math.abs(radarTurnAmount) > MAX_SCAN_TRACKING_AMOUNT)
		{
			radarTurnAmount = (radarTurnAmount > 0) ? MAX_SCAN_TRACKING_AMOUNT : -1 * MAX_SCAN_TRACKING_AMOUNT;
		}
		_robot.setTurnRadarRight(radarTurnAmount);
	}
}