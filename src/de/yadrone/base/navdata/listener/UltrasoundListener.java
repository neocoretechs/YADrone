package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.UltrasoundData;


public interface UltrasoundListener extends EventListener {
	public void receivedRawData(UltrasoundData ud);
}
