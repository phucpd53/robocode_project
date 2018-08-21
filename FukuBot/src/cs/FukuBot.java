package cs;

import java.util.Random;

import cs.scan.Scanner;
import cs.enemy.Enemy;
import cs.shoot.Shooter;
import cs.movement.*;
import robocode.*;
import robocode.util.Utils;
public class FukuBot extends AdvancedRobot {
	protected Scanner _scanner;
	protected Shooter _shooter;
	protected Dancer _dancer;
	protected WallAvoider _wallAvoider;
	
	public Enemy enemy;
	public Random rand;
	public void run()
	{
		init();
		while(true)
		{
//			_shooter.execute();
			_dancer.execute();
			if (getTime() != enemy.getTime()){
				_scanner.execute();
			}
			execute();
		}
	}
	private void init()
	{
		setAdjustGunForRobotTurn(true);
	    setAdjustRadarForGunTurn(true);
		if (_scanner == null)
		{
			_scanner = new Scanner(this);
		}
		if (_shooter == null)
		{
			_shooter = new Shooter(this);
		}
		if (_dancer == null)
		{
			_dancer = new Dancer(this);
		}
		if (_wallAvoider == null)
		{
			_wallAvoider = new WallAvoider(this);
		}
		if (rand == null)
		{
			rand = Utils.getRandom();
		}
		if (enemy == null)
		{
			enemy = new Enemy();
		}
	}
	public void onScannedRobot(ScannedRobotEvent e)
	{
		enemy.update(e);
		_scanner.tracking_scan(1);
		_shooter.execute();
//		fire(1);
	}
	public void onCustomEvent(CustomEvent e)
	{
		if (e.getCondition().getName().equals("too_close_to_walls"))
		{
			_dancer.reserve();
			setAhead(50);
		}
	}
}
