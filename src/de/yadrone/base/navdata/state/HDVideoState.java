package de.yadrone.base.navdata.state;

/**
 * @brief Values for the detection type on drone cameras.
 */
public enum HDVideoState {
	STORAGE_FIFO_IS_FULL(1 << 0), USBKEY_IS_PRESENT(1 << 8), USBKEY_IS_RECORDING(1 << 9), USBKEY_IS_FULL(1 << 10);

	public final int value;

	private HDVideoState(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static HDVideoState fromInt(int v) {
		switch (v) {
		case 1 << 0:
			return STORAGE_FIFO_IS_FULL;
		case 1 << 8:
			return USBKEY_IS_PRESENT;
		case 1 << 9:
			return USBKEY_IS_RECORDING;
		case 1 << 10:
			return USBKEY_IS_FULL;
		}
		return null;
	}
	
	public static String toString(HDVideoState hdv) {
		switch(hdv) {
		case STORAGE_FIFO_IS_FULL:
			return "Storage FIFO is full";
		case USBKEY_IS_PRESENT:
			return "USB Key Present";
		case USBKEY_IS_RECORDING:
			return "USB Key recording";
		case USBKEY_IS_FULL:
			return "USB Key Full";
		}
		return "Unknown state";
	}
}