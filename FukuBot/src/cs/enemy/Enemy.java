package cs.enemy;
import robocode.ScannedRobotEvent;
public class Enemy {
	double bearing;
	double distance;
	double energy;
	double heading;
	double velocity;
	String name;
	long time;
	
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
	
	public Enemy(){
		reset();
	}
}
