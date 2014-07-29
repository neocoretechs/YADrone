package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.accel.AcceleroPhysData;
import de.yadrone.base.navdata.accel.AcceleroRawData;


public interface AcceleroListener extends EventListener {
	public void receivedRawData(AcceleroRawData d);

	public void receivedPhysData(AcceleroPhysData d);
}
