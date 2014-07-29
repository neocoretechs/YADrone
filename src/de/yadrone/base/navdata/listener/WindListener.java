package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.WindEstimationData;


public interface WindListener extends EventListener {

	public void receivedEstimation(WindEstimationData d);

}
