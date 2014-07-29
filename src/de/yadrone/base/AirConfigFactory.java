package de.yadrone.base;
/**
 * Create an ARDrone and return as IDroneinterface. ARDrone is the default, aerial drone.
 * @author jg
 *
 */
public class AirConfigFactory extends AbstractConfigFactory {

	@Override
	public IDrone createDrone() {
		return new ARDrone();
	}

}
