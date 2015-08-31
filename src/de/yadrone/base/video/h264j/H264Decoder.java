package de.yadrone.base.video.h264j;

import java.io.InputStream;

import com.twilight.h264.player.H264StreamCallback;
import com.twilight.h264.player.RGBListener;

import de.yadrone.base.video.VideoDecoder;
/**
 * Once started, the decode method will wait until
 * a valid RGBListener is set and the mutex waiter is notified.
 * At that point an H264 stream callback is constructed and decoding commences via the playStream method.
 * @author jg
 *
 */
public class H264Decoder implements VideoDecoder
{
	private static final boolean DEBUG = false;
	private RGBListener listener;
	private Object listenerMutex = new Object();

	public void decode(InputStream is) throws Exception
	{
		//H264StreamPlayer hsd = new H264StreamPlayer(is);
		//hsd.playStream();
		if( listener == null ) {
			if( DEBUG )
				System.out.println("VideoDecoder listener null");
			synchronized(listenerMutex) {
				try {
					listenerMutex.wait();
					if( listener == null ) {
						if( DEBUG )
							System.out.println("H264Decoder RGBListener null, returning");
						return; // we may have signaled stop
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		H264StreamCallback hsc = new H264StreamCallback(is, listener);
		hsc.playStream();
	}

	public void stop()
	{
		listener = null;
		synchronized(listenerMutex) {
			listenerMutex.notifyAll();
		}
		if( DEBUG )
			System.out.println("H264Decoder: clean up and close stream ...");
	}
	

	@Override
	public void setImageListener(RGBListener listener) {
		if( DEBUG )
			System.out.println("RGBListener set for "+this);
		synchronized(listenerMutex) {
			this.listener = listener;
			listenerMutex.notifyAll();	
		}		
	}



}
