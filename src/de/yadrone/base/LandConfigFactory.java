package de.yadrone.base;

public class LandConfigFactory extends AbstractConfigFactory {

	@Override
	public IDrone createDrone() {
		return new ARDroneLand();
	}

}
