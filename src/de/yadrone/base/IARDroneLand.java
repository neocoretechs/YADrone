package de.yadrone.base;
/**
 * Land-based Drone. Basically, adds a move2D, both relative and absolute, extending IDrone.
 * Speed in X and angular rotation yaw. Supply a target distance to move in the target time
 * @author jg
 *
 */
public interface IARDroneLand extends IDrone, MotorControlInterface2D {
	
	public void move2DRelative(float yawIMURads, int yawTargetDegrees, int targetDistance, int targetTime, float[] accelDeltas, int[] ranges);
	public void move2DAbsolute(float yawIMURads, int yawTargetDegrees, int targetDistance, int targetTime, float[] accelDeltas, int[] ranges);
}
