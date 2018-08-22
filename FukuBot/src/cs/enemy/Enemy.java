package cs.enemy;
import robocode.*;
import cs.FukuBot;
public class Enemy {
	double bearing;
	double distance;
	double pre_energy;
	double energy;
	double heading;
	double velocity;
	String name;
	long time;
	
	private FukuBot _robot;
	
	public double getBearing(){
		return bearing;		
	}
	public double getDistance(){
		return distance;
	}
	public double getEnergy(){
		return energy;
	}
	public double getHeading(){
		return heading;
	}
	public double getVelocity(){
		return velocity;
	}
	public String getName(){
		return name;
	}
	public long getTime(){
		return time;
	}
	public void update(ScannedRobotEvent bot){
		bearing = bot.getBearing();
		distance = bot.getDistance();
		pre_energy = energy;
		energy = bot.getEnergy();
		heading = bot.getHeading();
		velocity = bot.getVelocity();
		name = bot.getName();
		time = bot.getTime();
	}
	public void reset(){
		bearing = 0.0;
		distance =0.0;
		energy= 0.0;
		heading =0.0;
		velocity = 0.0;
		name = null;
	}
	
	public boolean none(){
		if (name == null || name == "")
			return true;
		else
			return false;
	}
	
	public Enemy(FukuBot robot){
		_robot = robot;
		_robot.addCustomEvent(new Condition("enemy-fired"){
			public boolean test(){
				double changeInEnergy = pre_energy - energy;
				return (changeInEnergy > 0 && changeInEnergy <= 3);
			}
		});
		reset();
	}
}
