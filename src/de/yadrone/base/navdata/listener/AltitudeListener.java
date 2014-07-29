package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.Altitude;


public interface AltitudeListener extends EventListener {

	public void receivedAltitude(int altitude);

	public void receivedExtendedAltitude(Altitude d);

}
