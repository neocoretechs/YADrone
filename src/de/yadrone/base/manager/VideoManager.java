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
package de.yadrone.base.manager;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import com.twilight.h264.decoder.AVFrame;
import com.twilight.h264.player.RGBListener;

import de.yadrone.base.exception.IExceptionListener;
import de.yadrone.base.exception.VideoException;
import de.yadrone.base.utils.ARDroneUtils;
import de.yadrone.base.video.VideoDecoder;

public class VideoManager extends AbstractTCPManager implements RGBListener 
{
	private static final boolean DEBUG = true;
	private IExceptionListener excListener;
	
	private VideoDecoder decoder;

	private ArrayList<RGBListener> listener = new ArrayList<RGBListener>();

	public VideoManager(InetAddress inetaddr, VideoDecoder decoder, IExceptionListener excListener) 
	{
		super(inetaddr);
		this.decoder = decoder;
		this.excListener = excListener;
	}

	public void addImageListener(RGBListener listener) {
		this.listener.add(listener);
		if (this.listener.size() == 1)
			decoder.setImageListener(this);
	}
	
	public void removeImageListener(RGBListener listener) {
		this.listener.remove(listener);
		if (this.listener.size() == 0)
			decoder.setImageListener(null);
	}

	/** Called only by decoder to inform all the other listener */
	public void imageUpdated(AVFrame image)
	{
		for (int i=0; i < listener.size(); i++)
		{
			listener.get(i).imageUpdated(image);
		}
	}
	
	public boolean connect(int port) throws IOException
	{
		if (decoder == null) {
			if( DEBUG )
				System.out.println("Drone video manager connect failed, decoder null");
			return false;
		}

		return super.connect(port);
	}

	public void reinitialize()
	{
		if( DEBUG )
			System.out.println("VideoManager: reinitialize video stream ...");
		close();
		if( DEBUG )
			System.out.println("VideoManager: previous stream closed ...");
		try
		{
			if( DEBUG)
				System.out.println("VideoManager: create new decoder");
			decoder.stop();
			decoder = (VideoDecoder)decoder.getClass().newInstance();
			decoder.setImageListener(this);
			Thread.sleep(1000);
		}
		catch (Exception e)
		{
			System.out.println("Exception re-initializing video stream");
			e.printStackTrace();
		}
		if(DEBUG)
			System.out.println("VideoManager: start connecting again ...");
		
		ThreadPoolManager.getInstance().spin(this);
	}
	
	@Override
	public void run() {
		if (decoder == null) {
			if( DEBUG )
				System.out.println("Drone video manager: decoder null, return from runnable");
			return;
		}
		while(true) {
			try
			{
				if( DEBUG )
					System.out.println("VideoManager: connect ");
				connect(ARDroneUtils.VIDEO_PORT);
				if( DEBUG )
					System.out.println("VideoManager: tickle ");
				ticklePort(ARDroneUtils.VIDEO_PORT);
			
				//manager.setVideoBitrateControl(VideoBitRateMode.DISABLED); // bitrate set to maximum
				if( DEBUG )
					System.out.println("VideoManager: decode ");
				decoder.decode(getInputStream());
			} catch(Exception exc) {
				if( DEBUG ) {
					exc.printStackTrace();
					excListener.exeptionOccurred(new VideoException(exc));
				}
			}
		
			close();
		}
	}

	@Override
	public void close() {
		if( DEBUG )
			System.out.println("Drone video manager invoking close");
		if (decoder == null)
			return;

		super.close();
	}

}
