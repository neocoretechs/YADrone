package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.MagnetoData;


public interface MagnetoListener extends EventListener {
	public void received(MagnetoData d);
}
