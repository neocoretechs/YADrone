package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.GyroPhysData;
import de.yadrone.base.navdata.data.GyroRawData;


public interface GyroListener extends EventListener {
	public void receivedRawData(GyroRawData d);

	public void receivedPhysData(GyroPhysData d);

	public void receivedOffsets(float[] offset_g);
}
