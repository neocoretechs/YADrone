package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.data.ReferencesData;


public interface ReferencesListener extends EventListener {

	public void receivedRcReferences(int[] rc_ref);

	public void receivedReferences(ReferencesData d);

}
