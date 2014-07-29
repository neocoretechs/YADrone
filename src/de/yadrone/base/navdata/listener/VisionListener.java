package de.yadrone.base.navdata.listener;

import java.util.ArrayList;
import java.util.EventListener;

import de.yadrone.base.navdata.data.TrackerData;
import de.yadrone.base.navdata.vision.VisionData;
import de.yadrone.base.navdata.vision.VisionPerformance;
import de.yadrone.base.navdata.vision.VisionTag;


public interface VisionListener extends EventListener {
	public void tagsDetected(VisionTag[] tags);

	public void trackersSend(TrackerData trackersData);

	public void receivedPerformanceData(VisionPerformance d);

	public void receivedRawData(float[] vision_raw);

	public void receivedData(VisionData d);

	public void receivedVisionOf(float[] of_dx, float[] of_dy);

	public void typeDetected(int detection_camera_type);

}
