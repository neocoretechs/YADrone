/*
 * Copyright 2010 Cliff L. Biffle.  All Rights Reserved.
 * Use of this source code is governed by a BSD-style license that can be found
 * in the LICENSE file.
 */

package de.yadrone.base.navdata.listener;

import java.util.EventListener;

import de.yadrone.base.navdata.state.ControlState;
import de.yadrone.base.navdata.state.DroneState;

public interface StateListener extends EventListener {
	public void stateChanged(DroneState state);

	public void controlStateChanged(ControlState state);

}
