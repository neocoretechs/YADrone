package de.yadrone.base;

import de.yadrone.base.ARDrone.ISpeedListener;
import de.yadrone.base.exception.IExceptionListener;

public interface IARDrone extends IDrone {
	
	//control command
	public void landing();
	public void takeOff();
	public void up();
	public void down();
	public void hover();
	
	//getter
	public int getSpeed();
	public void setSpeed(int speed);
	public void addSpeedListener(ISpeedListener speedListener);
	public void removeSpeedListener(ISpeedListener speedListener);
	
	public void addExceptionListener(IExceptionListener exceptionListener);
	public void removeExceptionListener(IExceptionListener exceptionListener);
	
	//set max/min altitude
	public void setMaxAltitude(int altitude);
	public void setMinAltitude(int altitude);
	
	public void move3D(int speedX, int speedY, int speedZ, int speedSpin);
}
