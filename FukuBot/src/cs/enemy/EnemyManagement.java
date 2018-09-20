package cs.enemy;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import com.sun.media.sound.EmergencySoundbank;

import cs.Utils.FukuUtils;
import robocode.*;
public class EnemyManagement{
	protected Map<String, Enemy> _enemies;
	public EnemyManagement()
	{
		_enemies = new HashMap<String, Enemy>();
	}
	
	public boolean hasEnemy(String botName){
		return _enemies.containsKey(botName);
	}
	
	public Enemy getEnemy(String botName)
	{
		return _enemies.get(botName);
	}
	
	public void updateEnemy(ScannedRobotEvent e, Point2D.Double enemyLocation)
	{
		Enemy enemy = getEnemy(e.getName());
		enemy.update(e, enemyLocation);
	}
	
	public Enemy newEnemy(ScannedRobotEvent e, Point2D.Double enemyLocation)
	{
		Enemy enemy = new Enemy();
		enemy.update(e, enemyLocation);
		_enemies.put(e.getName(), enemy);
		return enemy;
	}
	public Enemy getClosestEnemy(){
		double closestDistance = Double.MAX_VALUE;
		Enemy closestEnemy = null;
		for (Enemy enemy: _enemies.values()){
			if (enemy.getDistance() < closestDistance){
				closestDistance = enemy.getDistance();
				closestEnemy = enemy;
			} 
		}
		return closestEnemy;
	}
	public double evaluateRisk(Point2D.Double destinationLocation, double goAngle, double myEnergy){
		double risk = 0;
		for (Enemy enemy : _enemies.values()){
			double destDistance = FukuUtils.euclidDistance(destinationLocation, enemy.getLocation());
			risk += (enemy.getEnergy()/myEnergy) * (1 + Math.abs(Math.cos(Math.toRadians(enemy.getBearing() - goAngle))))/destDistance;
		}
		return risk;
	}
	public String[] getBotNames()
	{
		String[] botNames = new String[_enemies.size()];
		_enemies.keySet().toArray(botNames);
		return botNames;
	}
	public void updateEnemyDistances(Point2D.Double myLocation)
	{
		for (Enemy enemy: _enemies.values())
		{
			enemy.setDistance(FukuUtils.euclidDistance(myLocation, enemy.getLocation()));
			enemy.setBearing(FukuUtils.absoluteBearing(myLocation, enemy.getLocation()));
		}
	}
	public boolean enemyExist(){
		if (_enemies.size() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}