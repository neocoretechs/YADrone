/*
 *
  Copyright (c) <2011>, <Shigeo Yoshida>
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
The names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.yadrone.base;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.neocoretechs.robocore.MotorControl;

import de.yadrone.base.command.video.VideoChannel;
import de.yadrone.base.exception.ARDroneException;
import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.manager.CommandManager;
import de.yadrone.base.manager.ConfigurationManager;
import de.yadrone.base.manager.NavDataManager;
import de.yadrone.base.manager.ThreadPoolManager;
import de.yadrone.base.manager.VideoManager;
import de.yadrone.base.video.VideoDecoder;
import de.yadrone.base.video.h264j.H264Decoder;


public class ARDroneLand implements IARDroneLand, IExceptionListener {

	/** default ip address */
	private static final String IP_ADDRESS = "192.168.1.1";

	private String ipaddr = null;
	private InetAddress inetaddr = null;
	private VideoDecoder videoDecoder = null;

	// managers
	private CommandManager commandManager = null;
	private VideoManager videoManager = null;
	private NavDataManager navdataManager = null;
	private ConfigurationManager configurationManager = null;
	
	private List<IExceptionListener> excListenerList = null;

	// The speed setting has been moved into this class to allow the commandmanager to stay simple and to be able to do
	// more advanced speed calculations for example based on the actual velocity
	// Should we refactor this into a separate API?
	private int speed = 25;
	private Set<ISpeedListener> speedListener = null;

	/** constructor */
	public ARDroneLand() {
		this(IP_ADDRESS);
	}

	public ARDroneLand(String ipaddr) {
		this(ipaddr, new H264Decoder());
	}
	
	/**
	 * Create a new instance of a drone's virtual counterpart.
	 * @param ipaddr  The address of the drone, e.g. 192.168.1.1
	 * @param videoDecoder  A decoder instance, e.g. 'new H264Decoder' or null, if video shall not be used (e.g. on Android devices)
	 */
	public ARDroneLand(String ipaddr, VideoDecoder videoDecoder) {
		this.ipaddr = ipaddr;
		this.videoDecoder = videoDecoder;
		this.speedListener = new HashSet<ISpeedListener>();
		this.excListenerList = new ArrayList<IExceptionListener>();
	}

	public synchronized CommandManager getCommandManager() {
		if (commandManager == null) {
			InetAddress ia = getInetAddress();
			commandManager = new CommandManager(ia, this);
			commandManager.start();
		}
		return commandManager;
	}

	public synchronized NavDataManager getNavDataManager() {
		if (navdataManager == null) {
			InetAddress ia = getInetAddress();
			CommandManager cm = getCommandManager();
			navdataManager = new NavDataManager(ia, cm, this);
			navdataManager.start();
		}
		return navdataManager;
	}

	public synchronized VideoManager getVideoManager() {
		// videoDecoder may only be null if the corresponding constructor of this class has been called with null
		// this can should be done when working e.g. with Android devices
		if (videoDecoder == null) {
			System.out.println("No Video Decoder!");
			return null;
		}
		
		if (videoManager == null) {
			InetAddress ia = getInetAddress();
			videoManager = new VideoManager(ia, videoDecoder, this);
			videoManager.start();
		}
		return videoManager;
	}

	public synchronized ConfigurationManager getConfigurationManager() {
		if (configurationManager == null) {
			InetAddress ia = getInetAddress();
			CommandManager cm = getCommandManager();
			configurationManager = new ConfigurationManager(ia, cm, this);
			configurationManager.start();
		}
		return configurationManager;
	}

	@Override
	public void stop() {
		freeze();
		//CommandManager cm = getCommandManager();
		//cm.stop();
		ConfigurationManager cfgm = getConfigurationManager();
		cfgm.close();
		//NavDataManager nm = getNavDataManager();
		//nm.stop();
		VideoManager vm = getVideoManager();
		if (vm != null)
			vm.close();
		ThreadPoolManager.getInstance().shutdown();
	}

	@Override
	public void start() {
		// call get to init and start manager
		getCommandManager();
		getConfigurationManager();
		getNavDataManager();
		getVideoManager();
	}

	@Override
	public void setHorizontalCamera() {
		if (commandManager != null)
			commandManager.setVideoChannel(VideoChannel.HORI);
	}

	@Override
	public void setVerticalCamera() {
		if (commandManager != null)
			commandManager.setVideoChannel(VideoChannel.VERT);
	}

	@Override
	public void setHorizontalCameraWithVertical() {
		if (commandManager != null)
			commandManager.setVideoChannel(VideoChannel.LARGE_HORI_SMALL_VERT);
	}

	@Override
	public void setVerticalCameraWithHorizontal() {
		if (commandManager != null)
			commandManager.setVideoChannel(VideoChannel.LARGE_VERT_SMALL_HORI);
	}

	@Override
	public void toggleCamera() {
		if (commandManager != null)
			commandManager.setVideoChannel(VideoChannel.NEXT);
	}


	@Override
	public void reset() {
		if (commandManager != null)
			commandManager.emergency();
	}

	@Override
	public void forward() {
		if (commandManager != null)
			commandManager.forward(speed);
	}

	@Override
	public void backward() {
		if (commandManager != null)
			commandManager.backward(speed);
	}

	@Override
	public void spinRight() {
		if (commandManager != null)
			commandManager.spinRight(speed);
	}

	@Override
	public void spinLeft() {
		if (commandManager != null)
			commandManager.spinLeft(speed);
	}


	@Override
	public void goRight() {
		if (commandManager != null)
			commandManager.goRight(speed);
	}

	@Override
	public void goLeft() {
		if (commandManager != null)
			commandManager.goLeft(speed);
	}

	@Override
	public void freeze() {
		if (commandManager != null)
			commandManager.freeze();
	}

	public void hover() {
		if (commandManager != null)
			commandManager.hover();
	}
	

	@Override
	public void setSpeed(int speed) {
		if (this.speed != speed)
		{
			this.speed = speed;
			
			// inform listener
			Iterator<ISpeedListener> iter = speedListener.iterator();
			while(iter.hasNext())
			{
				iter.next().speedUpdated(speed);
			}
		}
		else
		{
			this.speed = speed;
		}
	}

	/**
	 * 0.01-1.0 -> 1-100%
	 * 
	 * @return 1-100%
	 */
	@Override
	public int getSpeed() {
		return speed;
	}
	
	public void addSpeedListener(ARDrone.ISpeedListener speedListener)
	{
		this.speedListener.add((ISpeedListener) speedListener);
	}
	
	public void removeSpeedListener(ARDrone.ISpeedListener speedListener)
	{
		this.speedListener.remove(speedListener);
	}
	
	public interface ISpeedListener
	{
		public void speedUpdated(int speed);		
	}
	
	public void addExceptionListener(IExceptionListener exceptionListener)
	{
		this.excListenerList.add(exceptionListener);
	}
	
	public void removeExceptionListener(IExceptionListener exceptionListener)
	{
		this.excListenerList.remove(exceptionListener);
	}
	
	private void informExceptionListener(ARDroneException exception)
	{
		for (int i=0; i < excListenerList.size(); i++)
			excListenerList.get(i).exeptionOccurred(exception);
	}
	
	/**
	 * Call upon an exception occurred in one of the managers
	 */
	public void exeptionOccurred(ARDroneException exc)
	{
		informExceptionListener(exc);
	}
	
	/**
	 * print error message
	 * 
	 * @param message
	 * @param obj
	 */
	public static void error(String message, Object obj) {
		System.err.println("[" + obj.getClass() + "] " + message);
	}

	private InetAddress getInetAddress() {
		if (inetaddr == null) {
			StringTokenizer st = new StringTokenizer(ipaddr, ".");
			byte[] ipBytes = new byte[4];
			if (st.countTokens() == 4) {
				for (int i = 0; i < 4; i++) {
					ipBytes[i] = (byte) Integer.parseInt(st.nextToken());
				}
			} else {
				error("Incorrect IP address format: " + ipaddr, this);
				return null;
			}
			try {
				inetaddr = InetAddress.getByAddress(ipBytes);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return inetaddr;
	}

	/**
	 * Move relative with some rudimentary checks for excessive angular momentum and objects too close
	 * and reduce motor rate by intermittent commandStop until values are acceptable.
	 * The ARDrone US seems to flatten everything to 0 when objects 200mm closer or less
	 */
	@Override
	public void move2DRelative(float yawIMURads, int yawTargetDegrees, int targetDistance, int targetTime, float[] accelDeltas, int[] ranges) {
		MotorControl mc = new MotorControl();
		// if any deltas > about 100, there is lateral force sufficient to raise wheels
		// if any ranges close than about 200mm
		if( ranges[0] < 225 || ranges[1] < 200 || 
				accelDeltas[0] > 100.0f || accelDeltas[1] > 100.0f || accelDeltas[2] > 100.0f) {
			mc.commandStop(); // pick up further motion on subsequent commands when accel deltas are nicer
		} else
			mc.moveRobotRelative(yawIMURads, yawTargetDegrees, targetDistance, targetTime);
	}
	
	@Override
	public void move2DAbsolute(float yawIMURads, int yawTargetDegrees, int targetDistance, int targetTime, float[] accelDeltas, int[] ranges) {
		MotorControl mc = new MotorControl();
		// if any deltas > about 100, there is lateral force sufficient to raise wheels
		// if any ranges close than about 200mm
		if( ranges[0] < 225 || ranges[1] < 200 || 
				accelDeltas[0] > 100.0f || accelDeltas[1] > 100.0f || accelDeltas[2] > 100.0f) {
			mc.commandStop(); // pick up further motion on subsequent commands when accel deltas are nicer
		} else
			mc.moveRobotAbsolute(yawIMURads, yawTargetDegrees, targetDistance, targetTime);
	}
}