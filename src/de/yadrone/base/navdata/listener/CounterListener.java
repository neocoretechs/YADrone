package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.Counters;


public interface CounterListener extends EventListener {

	public void update(Counters d);

}
