package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.Temperature;


public interface TemperatureListener extends EventListener {

	void receivedTemperature(Temperature d);

}
