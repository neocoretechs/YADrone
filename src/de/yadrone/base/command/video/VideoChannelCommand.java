package de.yadrone.base.command.video;

import de.yadrone.base.command.config.ConfigureCommand;

public class VideoChannelCommand extends ConfigureCommand {

	public VideoChannelCommand(VideoChannel c) {
		super("video:video_channel", String.valueOf(c.ordinal()));
	}

}
