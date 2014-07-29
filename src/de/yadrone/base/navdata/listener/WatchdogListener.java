package de.yadrone.base.navdata.listener;

import java.util.EventListener;

public interface WatchdogListener extends EventListener {

	public void received(int watchdog);

}
