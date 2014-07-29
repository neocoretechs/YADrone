package de.yadrone.base.command.flight;

import de.yadrone.base.command.RefCommand;

public class LandCommand extends RefCommand {
	public LandCommand() {
		super(false, false);
		// 9th bit set to 0
	}

}
