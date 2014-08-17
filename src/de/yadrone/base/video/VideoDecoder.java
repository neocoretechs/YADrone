package de.yadrone.base.video;

import java.io.InputStream;

import com.twilight.h264.player.RGBListener;

public interface VideoDecoder {

    public void decode(InputStream is);
    public void stop();
	public void setImageListener(RGBListener listener);

}
