package de.yadrone.base.navdata.data;


public class UltrasoundData {

	private int us_debut_echo;
	private int us_fin_echo;
	private int us_association_echo;
	private int us_distance_echo;
	private int us_time_curve;
	private int us_value_curve;
	private int us_courbe_ref;
	private int flag_echo_ini;
	private int nb_echo;
	private long sum_echo;
	private int alt_temp_raw;
	private short gradient;

	public UltrasoundData(int us_debut_echo, int us_fin_echo, int us_association_echo,
			int us_distance_echo, int us_time_curve, int us_value_curve, int us_courbe_ref, int flag_echo_ini,
			int nb_echo, long sum_echo, int alt_temp_raw, short gradient) {
		this.us_debut_echo = us_debut_echo;
		this.us_fin_echo = us_fin_echo;
		this.us_association_echo = us_association_echo;
		this.us_distance_echo = us_distance_echo;
		this.us_time_curve = us_time_curve;
		this.us_value_curve = us_value_curve;
		this.us_courbe_ref = us_courbe_ref;
		this.flag_echo_ini = flag_echo_ini;
		this.nb_echo = nb_echo;
		this.sum_echo = sum_echo;
		this.alt_temp_raw = alt_temp_raw;
		this.gradient = gradient;
	}

	/**
	 * @return the us_debut_echo
	 */
	public int getDebutEcho() {
		return us_debut_echo;
	}

	/**
	 * @return the us_fin_echo
	 */
	public int getFinEcho() {
		return us_fin_echo;
	}

	/**
	 * @return the us_association_echo
	 */
	public int getAssociationEcho() {
		return us_association_echo;
	}

	/**
	 * @return the us_distance_echo
	 */
	public int getDistanceEcho() {
		return us_distance_echo;
	}

	/**
	 * @return the us_time_curve
	 */
	public int getTimeCurve() {
		return us_time_curve;
	}

	/**
	 * @return the us_value_curve
	 */
	public int getValueCurve() {
		return us_value_curve;
	}

	/**
	 * @return the us_curve_ref
	 */
	public int getCurveRef() {
		return us_courbe_ref;
	}

	/**
	 * @return the flag_echo_ini
	 */
	public int getFlagEchoIni() {
		return flag_echo_ini;
	}

	/**
	 * @return the nb_echo
	 */
	public int getNbEcho() {
		return nb_echo;
	}

	/**
	 * @return the sum_echo
	 */
	public long getSumEcho() {
		return sum_echo;
	}

	/**
	 * @return the alt_temp_raw
	 */
	public int getAltTempRaw() {
		return alt_temp_raw;
	}

	/**
	 * @return the gradient
	 */
	public short getGradient() {
		return gradient;
	}
	/*The measured distance from the range 0 to 400 Centimeters*/
	int microsecondsToCentimeters(int duration)
	{
		return duration/29/2;	
	}
	
	/*The measured distance from the range 0 to 157 Inches*/
	int microsecondsToInches(int duration)
	{
		return duration/74/2;	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UltrasoundData [us_debut_echo=");
		builder.append(us_debut_echo);
		builder.append(", us_fin_echo=");
		builder.append(us_fin_echo);
		builder.append(" init-final:");
		builder.append(us_fin_echo-us_debut_echo);
		builder.append(", us_association_echo=");
		builder.append(us_association_echo);
		builder.append(", us_distance_echo=");
		builder.append(us_distance_echo);
		builder.append(", us_time_curve=");
		builder.append(us_time_curve);
		builder.append(", us_value_curve=");
		builder.append(us_value_curve);
		builder.append(", us_curve_ref=");
		builder.append(us_courbe_ref);
		builder.append(", flag_echo_ini=");
		builder.append(flag_echo_ini);
		builder.append(", nb_echo=");
		builder.append(nb_echo);
		builder.append(", sum_echo=");
		builder.append(sum_echo);
		builder.append(", alt_temp_raw=");
		builder.append(alt_temp_raw);
		builder.append(", gradient=");
		builder.append(gradient);
		builder.append("]");
		return builder.toString();
	}

	
}
