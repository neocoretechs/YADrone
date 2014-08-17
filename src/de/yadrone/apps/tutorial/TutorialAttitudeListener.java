package de.yadrone.apps.tutorial;

import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.accel.AcceleroPhysData;
import de.yadrone.base.navdata.accel.AcceleroRawData;
import de.yadrone.base.navdata.data.Altitude;
import de.yadrone.base.navdata.data.GyroPhysData;
import de.yadrone.base.navdata.data.GyroRawData;
import de.yadrone.base.navdata.data.KalmanPressureData;
import de.yadrone.base.navdata.data.MagnetoData;
import de.yadrone.base.navdata.data.Pressure;
import de.yadrone.base.navdata.data.RawData;
import de.yadrone.base.navdata.data.Temperature;
import de.yadrone.base.navdata.data.UltrasoundData;
import de.yadrone.base.navdata.listener.AcceleroListener;
import de.yadrone.base.navdata.listener.AltitudeListener;
import de.yadrone.base.navdata.listener.AttitudeListener;
import de.yadrone.base.navdata.listener.BatteryListener;
import de.yadrone.base.navdata.listener.GyroListener;
import de.yadrone.base.navdata.listener.MagnetoListener;
import de.yadrone.base.navdata.listener.PressureListener;
import de.yadrone.base.navdata.listener.RawDataListener;
import de.yadrone.base.navdata.listener.TemperatureListener;
import de.yadrone.base.navdata.listener.UltrasoundListener;
import de.yadrone.base.navdata.listener.VelocityListener;

public class TutorialAttitudeListener
{

	public TutorialAttitudeListener(IARDrone drone)
	{
		drone.getNavDataManager().addAttitudeListener(new AttitudeListener() {
			
			public void attitudeUpdated(float pitch, float roll, float yaw)
			{
		    	//System.out.println("Pitch: " + pitch + " Roll: " + roll + " Yaw: " + yaw);
			}

			public void attitudeUpdated(float pitch, float roll) { 
		    	//System.out.println("Pitch: " + pitch + " Roll: " + roll);
			}
			public void windCompensation(float pitch, float roll) { }
		});
		/*
		drone.getNavDataManager().addBatteryListener(new BatteryListener() {	
			public void batteryLevelChanged(int percentage)
			{
				System.out.println("Battery: " + percentage + " %");
			}
			public void voltageChanged(int vbat_raw) { }
		});
		*/
		drone.getNavDataManager().addAltitudeListener(new AltitudeListener() {
			public void receivedExtendedAltitude(Altitude ud) {
				//System.out.println("Ext. Alt.:"+ud);
			}
			@Override
			public void receivedAltitude(int altitude) {
				//System.out.println("Altitude: "+altitude);
			}
		});
		
		drone.getNavDataManager().addVelocityListener(new VelocityListener() {
			@Override
			public void velocityChanged(float vx, float vy, float vz) {
				//System.out.println("Velocity delta x:"+vx+" y:"+vy+" z:"+vz);
			}
		});
		
		drone.getNavDataManager().addGyroListener(new GyroListener() {
			@Override
			public void receivedRawData(GyroRawData d) {
				//System.out.println("GyroRaw:"+d);	
			}
			@Override
			public void receivedPhysData(GyroPhysData d) {
				//System.out.println("GyroPhys:"+d);
				
			}
			@Override
			public void receivedOffsets(float[] offset_g) {
				//System.out.print("GyroOffs:");
				//for(float f: offset_g) System.out.print("offs:"+f+" ");
				//System.out.println();
			}
	
		});
		
		drone.getNavDataManager().addAcceleroListener(new AcceleroListener() {
			@Override
			public void receivedRawData(AcceleroRawData d) {
				//System.out.println("Raw Accelero:"+d);
			}

			@Override
			public void receivedPhysData(AcceleroPhysData d) {
				//System.out.println("Phys Accelero:"+d);
				
			}
	
		});
		
		drone.getNavDataManager().addMagnetoListener(new MagnetoListener() {
			@Override
			public void received(MagnetoData d) {
				//System.out.println("Mag:"+d);	
			}

		});
		
		drone.getNavDataManager().addPressureListener(new PressureListener() {
			@Override
			public void receivedKalmanPressure(KalmanPressureData d) {
				//System.out.println("kalman Pressure:"+d);
			}
			@Override
			public void receivedPressure(Pressure d) {
				//System.out.println("Pressure:"+d);		
			}
	
		});
		
		drone.getNavDataManager().addTemperatureListener(new TemperatureListener() {
			@Override
			public void receivedTemperature(Temperature d) {
				//System.out.println("Temp:"+d);
				
			}
		});
		// relatively useless, use altitude - extended
		//drone.getNavDataManager().addUltrasoundListener(new UltrasoundListener() {
			//@Override
			//public void receivedRawData(UltrasoundData ud) {
				//if( ud.getAssociationEcho() > 8 && ud.getAssociationEcho() != 3758)
				//if( ud.getAssociationEcho() == 17 )
				//System.out.println("Sonar:"+ud);
				
			//}
		//});
		
		drone.getNavDataManager().addRawDataListener(new RawDataListener() {
			@Override
			public void receivedRawData(RawData rd) {
				//System.out.println("Raw:"+rd);
			}		
		});
	}

}
