package de.yadrone.base.navdata.listener;

import java.util.EventListener;

public interface TimeListener extends EventListener {

	public void timeReceived(int seconds, int useconds);

}
