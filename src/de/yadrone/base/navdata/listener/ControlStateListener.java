package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.state.ControlState;


public interface ControlStateListener  extends EventListener {
	public void controlStateChanged(ControlState state);
}
