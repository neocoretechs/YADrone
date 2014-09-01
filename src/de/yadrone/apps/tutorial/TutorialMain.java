package de.yadrone.apps.tutorial;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;




import de.yadrone.base.ARDrone;
import de.yadrone.base.ARDroneLand;
import de.yadrone.base.IARDrone;
import de.yadrone.base.IARDroneLand;
import de.yadrone.base.command.led.LEDAnimation;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.navdata.listener.AttitudeListener;

public class TutorialMain
{

	public static void main(String[] args)
	{
		IARDroneLand drone = null;
		try
		{
			// Tutorial Section 1
			drone = (IARDroneLand) new ARDroneLand();
			drone.addExceptionListener(new IExceptionListener() {
				public void exeptionOccurred(ARDroneException exc)
				{
					exc.printStackTrace();
				}
			});
			drone.start();
			// Tutorial Section 2
			new TutorialAttitudeListener( drone);
			
			// Tutorial Section 3
			new TutorialVideoListener( drone);
			
			//new TutorialStateListener(drone);
			// Tutorial Section 4
//			TutorialCommander commander = new TutorialCommander(drone);
//			commander.animateLEDs();
//			commander.takeOffAndLand();
//			commander.leftRightForwardBackward();
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		//finally
		//{
		//	if (drone != null)
		//		drone.stop();

		//	System.exit(0);
		//}
	}

}
