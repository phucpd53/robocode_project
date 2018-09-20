package cs.enemy;
import java.awt.geom.Point2D;

import robocode.*;
import cs.FukuBot;
import cs.Utils.*;
public class Enemy {
	private double bearing;
	private double distance;
	private double pre_energy;
	private double energy;
	private double heading;
	private double velocity;
	private String name;
	private long time;
	private Point2D.Double location;
	
	private FukuBot _robot;
	public void setDistance(double distance){
		this.distance = distance;
	}
	public void setBearing(double bearing){
		this.bearing = bearing;
	}
	public double getBearing(){
		return this.bearing;		
	}
	public double getDistance(){
		return this.distance;
	}
	public double getEnergy(){
		return this.energy;
	}
	public double getHeading(){
		return this.heading;
	}
	public double getVelocity(){
		return this.velocity;
	}
	public String getName(){
		return this.name;
	}
	public long getTime(){
		return this.time;
	}
	public Point2D.Double getLocation(){
		return this.location;
	}
	public void update(ScannedRobotEvent bot, Point2D.Double enemyLocation){
		this.bearing = bot.getBearing();
		this.distance = bot.getDistance();
		this.pre_energy = energy;
		this.energy = bot.getEnergy();
		this.heading = bot.getHeading();
		this.velocity = bot.getVelocity();
		this.name = bot.getName();
		this.time = bot.getTime();
		this.location = enemyLocation;
	}
	public void reset(){
		this.bearing = 0.0;
		this.distance =0.0;
		this.energy= 100.0;
		this.heading =0.0;
		this.velocity = 0.0;
		this.name = null;
		this.location = new Point2D.Double();
	}
	
	public boolean none(){
		if (name == null || name == "")
			return true;
		else
			return false;
	}
	
//	public Enemy(FukuBot robot){
//		_robot = robot;
//		_robot.addCustomEvent(new Condition("enemy-fired"){
//			public boolean test(){
//				double changeInEnergy = pre_energy - energy;
//				return (changeInEnergy > 0 && changeInEnergy <= 3);
//			}
//		});
//		reset();
//	}
	public Enemy(){
		reset();
	}
}
