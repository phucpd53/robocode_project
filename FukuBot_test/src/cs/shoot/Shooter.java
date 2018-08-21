package cs.shoot;
import robocode.util.Utils;
import cs.FukuBot_test;
import cs.shoot.*;
import cs.Utils.*;
public class Shooter {
	private FukuBot_test _robot;
	private static double EPSILON = 3;
	private Intercept intercept;
	public Shooter(FukuBot_test robot)
	{
		intercept = new Intercept();
		_robot = robot;
	}
	public void execute()
	{
		straight_shoot(3);
//		intercept_shoot(3);
	}
	private void fire(double bulletPower)
	{
		_robot.setFireBullet(bulletPower);
	}
	private void straight_shoot(double bulletPower){
		if (_robot.getTime() == _robot.enemy.getTime()){
			double gunTurnAmount = Utils.normalRelativeAngleDegrees(_robot.getHeading() - _robot.getGunHeading() + _robot.enemy.getBearing());
			_robot.setTurnGunRight(gunTurnAmount);
			fire(bulletPower);
		}
	}
	private void intercept_shoot(double bulletPower){
		double rad = Math.toRadians(_robot.getHeading() + _robot.enemy.getBearing());
		
		int scannedX = (int)(_robot.getX() + Math.sin(rad) * _robot.enemy.getDistance());
		int scannedY = (int)(_robot.getY() + Math.cos(rad) * _robot.enemy.getDistance());
		
		intercept.calculate(_robot.getX(), _robot.getY(), scannedX, scannedY, _robot.enemy.getHeading(), _robot.enemy.getVelocity(), bulletPower, 0);
		double turnAmount = FukuUtils.normalizeBearing(intercept.bulletHeading_deg - _robot.getGunHeading());
		_robot.setTurnGunRight(turnAmount);
		if (Math.abs(turnAmount) <= intercept.angleThreshold) {
            if ((intercept.impactPoint.x > 0) && (intercept.impactPoint.x < _robot.getBattleFieldWidth()) && (intercept.impactPoint.y > 0) && (intercept.impactPoint.y < _robot.getBattleFieldHeight())) {
                fire(bulletPower);
            }
        }
	}
}
