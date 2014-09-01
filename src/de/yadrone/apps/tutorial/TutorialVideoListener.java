package de.yadrone.apps.tutorial;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.swing.JFrame;

import com.twilight.h264.decoder.AVFrame;
import com.twilight.h264.player.FrameUtils;
import com.twilight.h264.player.PlayerFrame;
import com.twilight.h264.player.RGBListener;

import de.yadrone.base.IARDrone;
import de.yadrone.base.IDrone;
import de.yadrone.base.command.video.VideoChannel;
import de.yadrone.base.manager.ThreadPoolManager;

/**
 * Mainly demonstrates how we can manipulate the 3 byte BGR buffer to publish to ROS or create
 * a bufferedimage from that payload. Video here is clunky and there are better ways to view the raw video stream using the
 * other H264 classes 
 * @author jg
 *
 */
public class TutorialVideoListener implements Runnable
{
    private BufferedImage image = null;
    PlayerFrame displayPanel;
    IDrone drone;
    boolean shouldRun = true;
	public static ConcurrentLinkedDeque<AVFrame> list = new ConcurrentLinkedDeque<AVFrame>();
    public static int FRAME_THRESHOLD = 10;
    
    public TutorialVideoListener(final IDrone drone)
    {
        this.drone = drone;
		JFrame frame = new JFrame("Player");
		frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e)
            {
            	drone.getCommandManager().setVideoChannel(VideoChannel.NEXT);
            }
        });
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) 
			{
				drone.stop();
			}
		});
        displayPanel = new PlayerFrame();
		frame.getContentPane().add(displayPanel, BorderLayout.CENTER);
		displayPanel.setVisible(true);
		frame.pack();
		frame.setSize(new Dimension(672, 418));
		frame.setVisible(true);
		playStream();
		ThreadPoolManager.getInstance().spin(this);
    }
    
    private void playStream() {
		
        drone.getVideoManager().addImageListener(new RGBListener() {
			@Override
			public void imageUpdated(AVFrame newImage) {
				synchronized(list) {
					list.add(newImage);
					list.notifyAll();
				}
			}
        });
    }
    
    public void run() {
        AVFrame newImage = null;
        while(true) {
        	synchronized(list) {
        		if( list.isEmpty()) {
           			try {
        						list.wait();
        			} catch (InterruptedException e) {
        						e.printStackTrace();
        			}
        		}
        		if( list.size() > FRAME_THRESHOLD ) {
        				for(int i = 0; i < FRAME_THRESHOLD/2; i++)
        					list.remove();
        		}
        		newImage = list.pop();
        	}
			//System.out.println("Image:"+newImage.imageWidth+","+newImage.imageHeight+" queue:"+list.size());
				
			image = new BufferedImage(newImage.imageWidth, newImage.imageHeight, BufferedImage.TYPE_3BYTE_BGR);
			WritableRaster raster = (WritableRaster) image.getRaster();
			//int bufferSize = newImage.imageWidth * newImage.imageHeight;
			int bufferSize = newImage.imageWidth * newImage.imageHeight * 3;
			int[] buffer = new int[bufferSize];
			FrameUtils.YUV2RGB3(newImage, buffer); // RGBA 
		    raster.setPixels(0, 0, newImage.imageWidth, newImage.imageHeight, buffer); 
			displayPanel.lastFrame = image;
			//displayPanel.lastFrame = displayPanel.createImage(new MemoryImageSource(newImage.imageWidth
			//		, newImage.imageHeight, buffer, 0, newImage.imageWidth));
			displayPanel.invalidate();
			displayPanel.updateUI();
				
		}  
    }

}
