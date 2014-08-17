package de.yadrone.apps.tutorial;

import de.yadrone.base.IARDrone;
import de.yadrone.base.navdata.listener.StateListener;
import de.yadrone.base.navdata.state.ControlState;
import de.yadrone.base.navdata.state.DroneState;

public class TutorialStateListener {
	public TutorialStateListener(IARDrone drone) {
	drone.getNavDataManager().addStateListener(new StateListener() {
		public void stateChanged(DroneState data)
		{
			StringBuffer sb = new StringBuffer();
			//sb.append("IsFlying: " + data.isFlying() + "\n");
			sb.append("IsVideoEnabled: " + data.isVideoEnabled() + "\n");
			sb.append("IsVisionEnabled: " + data.isVisionEnabled() + "\n");
			sb.append("controlAlgo: " + data.getControlAlgorithm() + "\n");
			sb.append("AltitudeControlActive: " + data.isAltitudeControlActive() + "\n");
			//sb.append("IsUserFeedbackOn: " + data.isUserFeedbackOn() + "\n");
			//sb.append("ControlReceived: " + data.isVideoEnabled() + "\n");
			//sb.append("IsTrimReceived: " + data.isTrimReceived() + "\n");
			//sb.append("IsTrimRunning: " + data.isTrimRunning() + "\n");
			//sb.append("IsTrimSucceeded: " + data.isTrimSucceeded() + "\n");
			sb.append("IsNavDataDemoOnly: " + data.isNavDataDemoOnly() + "\n");
			sb.append("IsNavDataBootstrap: " + data.isNavDataBootstrap() + "\n");
			//sb.append("IsMotorsDown: " + data.isMotorsDown() + "\n");
			sb.append("IsGyrometersDown: " + data.isGyrometersDown() + "\n");
			sb.append("IsBatteryLow: " + data.isBatteryTooLow() + "\n");
			sb.append("IsBatteryHigh: " + data.isBatteryTooHigh() + "\n");
			//sb.append("IsTimerElapsed: " + data.isTimerElapsed() + "\n");
			sb.append("isNotEnoughPower: " + data.isNotEnoughPower() + "\n");
			sb.append("isAngelsOutOufRange: " + data.isAngelsOutOufRange() + "\n");
			//sb.append("isTooMuchWind: " + data.isTooMuchWind() + "\n");
			sb.append("isUltrasonicSensorDeaf: " + data.isUltrasonicSensorDeaf() + "\n");
			//sb.append("isCutoutSystemDetected: " + data.isCutoutSystemDetected() + "\n");
			//sb.append("isPICVersionNumberOK: " + data.isPICVersionNumberOK() + "\n");
			sb.append("isATCodedThreadOn: " + data.isATCodedThreadOn() + "\n");
			sb.append("isNavDataThreadOn: " + data.isNavDataThreadOn() + "\n");
			sb.append("isVideoThreadOn: " + data.isVideoThreadOn() + "\n");
			sb.append("isAcquisitionThreadOn: " + data.isAcquisitionThreadOn() + "\n");
			sb.append("isControlWatchdogDelayed: " + data.isControlWatchdogDelayed() + "\n");
			sb.append("isADCWatchdogDelayed: " + data.isADCWatchdogDelayed() + "\n");
			sb.append("isCommunicationProblemOccurred: " + data.isCommunicationProblemOccurred() + "\n");
			//sb.append("IsEmergency: " + data.isEmergency() + "\n");
//			sb.append("CtrlState: " + data.getControlState() + "\n");
//			sb.append("Battery: " + data.getBattery() + "\n");
//			sb.append("Altitude: " + data.getAltitude() + "\n");
//			sb.append("Pitch: " + data.getPitch() + "\n");
//			sb.append("Roll: " + data.getRoll() + "\n");
//			sb.append("Yaw: " + data.getYaw() + "\n");
//			sb.append("X velocity: " + data.getVx() + "\n");
//			sb.append("Y velocity: " + data.getLongitude() + "\n");
//			sb.append("Z velocity: " + data.getVz() + "\n");
//			sb.append("Vision Tags: " + data.getVisionTags() + "\n");

			//System.out.println(sb.toString());
		}
		
		public void controlStateChanged(ControlState state)
		{
			
		}
	});
	}
}
