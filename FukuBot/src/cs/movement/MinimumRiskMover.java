package cs.movement;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.*;

import cs.Utils.FukuUtils;
import cs.enemy.*;
import cs.FukuBot;
import cs.Utils.*;

import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class MinimumRiskMover {
	public static final int CANDIDATE_COUNT = 36;
	private FukuBot _robot;
	private EnemyManagement _enemyManagement;
	private Destination _currentDestination;
	
	
	public MinimumRiskMover(FukuBot robot){
		this._robot = robot;
		this._enemyManagement = new EnemyManagement();
		this._currentDestination = new Destination(new Point2D.Double(Double.MAX_VALUE, Double.MAX_VALUE), Double.MAX_VALUE, Double.MAX_VALUE);
	}
	public void execute()
	{
		move();
	}
	private void move(){
		if (_enemyManagement.enemyExist()){
			_enemyManagement.updateEnemyDistances(_robot.myLocation);
			Enemy closestEnemy = _enemyManagement.getClosestEnemy();
			List<Destination> candidateDestinations = this.generateCandidateDestinations(closestEnemy, _robot.myLocation);
			_currentDestination = this.getBestDestination(candidateDestinations);
			FukuUtils.setBackAsFront(_robot, _currentDestination.goAngle);
		}
	}
	
	private List<Destination> generateCandidateDestinations(Enemy closestEnemy, Point2D.Double myLocation){
		List<Destination> destinations = new ArrayList<Destination>();
		double radius = Math.min(100 + Math.random() * 100, closestEnemy.getDistance());
		double slideSize = 360 / CANDIDATE_COUNT;
		for(int i = 0; i < CANDIDATE_COUNT; i++){
			double angle = i * slideSize;
			Point2D.Double location = FukuUtils.projectLocation(myLocation, angle, radius);
			destinations.add(new Destination(location, _enemyManagement.evaluateRisk(location, angle, _robot.getEnergy()), angle));
		}
		if (true){
//			destinations.add(_currentDestination);
		}
		return destinations;
	}
	
	private Destination getBestDestination(List<Destination> destinations){
		Destination safest = null;
		double lowestRisk = Double.MAX_VALUE;
		
		for (Destination dest : destinations){
			if (dest.risk < lowestRisk && !WallAvoider.hitWall(dest.localtion, _robot.getBattleFieldWidth(), _robot.getBattleFieldHeight())){
				lowestRisk = dest.risk;
				safest = dest;
			}
		}
		
		return safest;
	}
	
	public void onScannedRobot(ScannedRobotEvent e){
		Point2D.Double enemyLocation = FukuUtils.projectLocation(_robot.myLocation, e.getBearing(), e.getDistance());
		if (_enemyManagement.hasEnemy(e.getName()))
		{
			_enemyManagement.updateEnemy(e, enemyLocation);
		}
		else
		{
			_enemyManagement.newEnemy(e, enemyLocation);
		}
	}
	
	public static class Destination{
		public final Point2D.Double localtion;
		public final double risk;
		public final double goAngle;
		public Destination(Point2D.Double location, double risk, double goAngle){
			this.localtion = location;
			this.risk = risk;
			this.goAngle = goAngle;
		}
	}
	public void onPaint(Graphics2D g){
		g.setColor(new java.awt.Color(0xff, 0x00, 0x00, 0x80));
		g.fillArc((int)_currentDestination.localtion.x, (int)_currentDestination.localtion.y, 20, 20, 0, 360);
	}
}
