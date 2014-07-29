package de.yadrone.base;
/**
 * Land-based Drone. Basically, adds a move2D using speed in X and angular rotation to IDrone
 * @author jg
 *
 */
public interface IARDroneLand extends IDrone {
	
	public void move2D(int speedX, double angular);
}
