package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.RawData;

public interface RawDataListener extends EventListener {
	public void receivedRawData(RawData rd);
}
