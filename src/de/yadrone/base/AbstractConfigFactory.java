package de.yadrone.base;
/**
 * @author jg
 */

import java.io.NotActiveException;

	/**
	 * Abstract factory pattern to create the proper type based on deployment
	 * @author jg
	 *
	 */
	public abstract class AbstractConfigFactory
	{
		public abstract IDrone createDrone();
		/**
		 * Create a drone of the proper deployment
		 * @param droneType Land, Sea, Air, Space
		 * @return
		 * @throws NotSupportedException
		 */
		public static AbstractConfigFactory createFactory(String droneType) throws NotActiveException {
			switch(droneType) {
				case "Land":
					return new LandConfigFactory();
				case "Sea":
					return new SeaConfigFactory();
				case "Air":
					return new AirConfigFactory(); 
				case "Space":
					return new SpaceConfigFactory(); 
        	    default:
                    throw new NotActiveException("The Drone type "+droneType+" is not supported.");
			}
		}
		
	}