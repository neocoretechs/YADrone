package de.yadrone.base.command.flight;

import de.yadrone.base.command.config.ConfigureCommand;

public class FlightAnimationCommand extends ConfigureCommand {

	public FlightAnimationCommand(FlightAnimation anim, int duration) {
		super("control:flight_anim", String.valueOf(anim.ordinal()) + "," + String.valueOf(duration));
	}

	public FlightAnimationCommand(FlightAnimation anim) {
		this(anim, anim.getDefaultDuration());
	}
}
