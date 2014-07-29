package de.yadrone.base.command.vision;

import de.yadrone.base.command.ATCommand;

public class VisionOptionCommand extends ATCommand {

	private int option;

	public VisionOptionCommand(int option) {
		super();
		this.option = option;
	}

	@Override
	protected String getID() {
		return "VISO";
	}

	@Override
	protected Object[] getParameters() {
		return new Object[] { option };
	}

}
