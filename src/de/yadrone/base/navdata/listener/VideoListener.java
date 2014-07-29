package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.HDVideoStreamData;
import de.yadrone.base.navdata.data.VideoStreamData;


public interface VideoListener extends EventListener {

	public void receivedHDVideoStreamData(HDVideoStreamData d);

	public void receivedVideoStreamData(VideoStreamData d);

}
