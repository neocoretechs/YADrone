package de.yadrone.base.navdata.data;

public class RawData {
	final float accs_offset[]                    = { 2048, 2048, 2048 };
	final float accs_gains[]               = { 1024, 1024, 1024 };
	final float gyros_offset[]                   = { (float) (1350/0.806), (float) (1350/0.806), (float) (1350/0.806) };
	final float gyros_gains[]              = { (float) 6.9786031e-03, (float) 7.1979444e-03, (float) 3.8175473e-03 }; 
	final float gyros110_offset[]                = { (float) (1350/0.806), (float) (1350/0.806) };
	final float gyros110_gains[]           = { (float) 1.5517747e-03, (float) 1.5981209e-03 };

	//const float accs_offset[]                    = { -2.2074473e+03, 1.9672749e+03, 1.9423679e+03 };
	//const float accs_gains[]                     = {  9.6180552e-01,  2.5897421e-02,  5.7041653e-02,  
//	                                                 -1.2127322e-02, -9.9142015e-01,  2.2923036e-02,  
//													    4.8988122e-02, -4.6047132e-02, -9.6375960e-01 };
	//const float gyros_offset[]                   = { 1.6654420e+03, 1.6706140e+03, 1.6645740e+03 };
	//const float gyros_gains[]                    = { 6.9786031e-03, -7.1979444e-03, -3.8175473e-03 };
	//const float gyros110_offset[]                = { 1.6567020e+03, 1.6819180e+03 };
	//const float gyros110_gains[]                 = { 1.5517747e-03, -1.5981209e-03 };

	//get a sample from the nav board (non blocking)
	//returns 0 on success
	double ts; //timestamp in seconds with microsecond resolution
	float dt; //time since last sample
	float ax;   // acceleration x-axis in [G] front facing up is positive         
	float ay;   // acceleration y-axis in [G] left facing up is positive                
	float az;   // acceleration z-axis in [G] top facing up is positive             
	float gx;   // gyro value x-axis in [rad/sec] right turn, i.e. roll right is positive           
	float gy;   // gyro value y-axis in [rad/sec] right turn, i.e. pitch down is positive                     
	float gz;   // gyro value z-axis in [rad/sec] right turn, i.e. yaw left is positive                           
	float t;    // temperature in [C] 
	float h;    // height above ground in [cm] 
	boolean h_meas;// true if this is a new h measurement, false otherwise
	float ta;   // temperature acc
	float tg;   // temperature gyro
	public double getTimestamp() {
		return ts;
	}
	public void setTimestamp(double ts) {
		this.ts = ts;
	}
	public float getDt() {
		return dt;
	}

	public float getAx() {
		return ax;
	}

	public float getAy() {
		return ay;
	}

	public float getAz() {
		return az;
	}

	public float getGx() {
		return gx;
	}
	
	public float getGy() {
		return gy;
	}

	public float getGz() {
		return gz;
	}

	public float getT() {
		return t;
	}

	public float getDistance() {
		return h;
	}

	public boolean isNewMeasure() {
		return h_meas;
	}
	
	public float getHeightCM() {
		return h;
	}
	
	public float getTa() {
		return ta;
	}

	public float getTg() {
		return tg;
	}


	public RawData(int[] acc, short[] gyro, short[] gyro_110, int us_echo, int gyro_temp, int acc_temp) {
		//store converted sensor data in nav structure. 
		ax = (((float)acc[0]) - accs_offset[0]) / accs_gains[0];
		ay = (((float)acc[1]) - accs_offset[1]) / accs_gains[1];
		az = (((float)acc[2]) - accs_offset[2]) / accs_gains[2];
		gx = (((float)gyro[0]) - gyros_offset[0]) * gyros_gains[0];
		gy = (((float)gyro[1]) - gyros_offset[1]) * gyros_gains[1];
		gz = (((float)gyro[2]) - gyros_offset[2]) * gyros_gains[2];	
		if( gx > Math.toRadians(-100)  && gx < Math.toRadians(100) ) 
			gx = ((float)gyro_110[0] - gyros110_offset[0]) * gyros110_gains[0];
		if(gy > Math.toRadians(-100) && gy < Math.toRadians(100)) 
			gy = ((float)gyro_110[1] - gyros110_offset[1]) * gyros110_gains[1];
		h  = (float) ((float)((us_echo & 0x7fff)) * 0.0340);
		h_meas = (us_echo >> 15) != 0;
		tg  = (float) ((( (float)gyro_temp * 0.806 /*mv/lsb*/ ) - 1250 /*Offset 1250mV at room temperature*/) / 4.0 /*Sensitivity 4mV/°C*/ + 20 /*room temperature*/);
		ta  = (float) (((float)acc_temp) * 0.5 /*C/lsb*/ - 30 /*Offset is -30C*/);
	}  
	
	public String toString() {
		return String.format("a=%6.3f,%6.3f,%6.3f (G's) g=%4.0f,%4.0f,%4.0f (deg/s) h=%3.0f (cm) ta=%4.1f (C) tg=%4.1f (C) dt=%2.0f (ms)"
				,ax,ay,az
				,Math.toDegrees(gx),Math.toDegrees(gy),Math.toDegrees(gz)
				,h
				,ta
				,tg
				,dt*1000
			);
	}

}
