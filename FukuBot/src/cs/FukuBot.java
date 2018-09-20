package cs;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

import cs.scan.Scanner;
import cs.Utils.FukuUtils;
import cs.enemy.*;
import cs.shoot.Shooter;
import cs.movement.*;
import robocode.*;
import robocode.util.Utils;
public class FukuBot extends AdvancedRobot {
	protected Scanner _scanner;
	protected Shooter _shooter;
	protected Dancer _dancer;
	protected WallAvoider _wallAvoider;	
	protected MinimumRiskMover _minimumRiskMover;
	
	public Point2D.Double myLocation;
	public Enemy enemy;
	public Random rand;
	public void run()
	{
		init();
		while(true)
		{
			updateCurrentStatus();
			_minimumRiskMover.execute();
//			_shooter.execute();
//			_dancer.execute();
//			if (getTime() != enemy.getTime()){
				_scanner.execute();
//			}
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
		if (_minimumRiskMover == null){
			_minimumRiskMover = new MinimumRiskMover(this);
		}
	}
	public void onScannedRobot(ScannedRobotEvent e)
	{
		
		Point2D.Double myLocation = new Point2D.Double(getX(),getY());
		Point2D.Double enemyLocation = FukuUtils.projectLocation(myLocation, e.getBearing(), e.getDistance());
		enemy.update(e, enemyLocation);
		_minimumRiskMover.onScannedRobot(e);
		_scanner.tracking_scan(1);
//		_shooter.execute();
//		fire(1);
	}
	public void onCustomEvent(CustomEvent e)
	{
		if (e.getCondition().getName().equals("too_close_to_walls"))
		{
			_dancer.reserve();
		}
		if (e.getCondition().getName().equals("enemy-fired"))
		{
			_dancer.dodge();
		}
	}
	public void onPaint(Graphics2D g){
		_minimumRiskMover.onPaint(g);
	}
	private void updateCurrentStatus(){
		this.myLocation = new Point2D.Double(getX(), getY());
	}
}
