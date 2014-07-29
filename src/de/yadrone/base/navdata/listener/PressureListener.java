package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.KalmanPressureData;
import de.yadrone.base.navdata.data.Pressure;


public interface PressureListener extends EventListener {

	public void receivedKalmanPressure(KalmanPressureData d);

	public void receivedPressure(Pressure d);

}
