package de.yadrone.base;

import de.yadrone.base.ARDrone.ISpeedListener;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.manager.CommandManager;
import de.yadrone.base.manager.ConfigurationManager;
import de.yadrone.base.manager.NavDataManager;
import de.yadrone.base.manager.VideoManager;

public interface IDrone {
	public CommandManager getCommandManager();
	public NavDataManager getNavDataManager();
	public VideoManager getVideoManager();
	public ConfigurationManager getConfigurationManager();
	
	public void start();
	public void stop();
	
	//camera
	public void setHorizontalCamera();//setFrontCameraStreaming()
	public void setVerticalCamera();//setBellyCameraStreaming()
	public void setHorizontalCameraWithVertical();//setFrontCameraWithSmallBellyStreaming()
	public void setVerticalCameraWithHorizontal();//setBellyCameraWithSmallFrontStreaming()
	public void toggleCamera();
	
	//control command
	
	public void reset();
	public void forward();
	public void backward();
	public void spinRight();
	public void spinLeft();
	public void goRight();
	public void goLeft();
	public void freeze();
	
	//getter
	public int getSpeed();
	public void setSpeed(int speed);
	public void addSpeedListener(ISpeedListener speedListener);
	public void removeSpeedListener(ISpeedListener speedListener);
	
	public void addExceptionListener(IExceptionListener exceptionListener);
	public void removeExceptionListener(IExceptionListener exceptionListener);
	

}
