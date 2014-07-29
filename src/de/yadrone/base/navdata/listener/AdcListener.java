package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.AdcFrame;


public interface AdcListener extends EventListener {

	public void receivedFrame(AdcFrame d);

}
