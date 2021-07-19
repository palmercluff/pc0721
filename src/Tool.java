/**
 * Tool base class. Can derive tools of all varieties from this one.
 */

/**
 * @author Palmer Cluff
 *
 */
public class Tool {

	protected String type;
	protected String brand;
	protected String code;
	protected double dailyCharge;
	protected boolean isWeekdayCharge;
	protected boolean isWeekendCharge;
	protected boolean isHolidayCharge;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the dailyCharge
	 */
	public double getDailyCharge() {
		return dailyCharge;
	}

	/**
	 * @param dailyCharge the dailyCharge to set
	 */
	public void setDailyCharge(float dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	/**
	 * @return the isWeekdayCharge
	 */
	public boolean isWeekdayCharge() {
		return isWeekdayCharge;
	}

	/**
	 * @param isWeekdayCharge the isWeekdayCharge to set
	 */
	public void setWeekdayCharge(boolean isWeekdayCharge) {
		this.isWeekdayCharge = isWeekdayCharge;
	}

	/**
	 * @return the isWeekendCharge
	 */
	public boolean isWeekendCharge() {
		return isWeekendCharge;
	}

	/**
	 * @param isWeekendCharge the isWeekendCharge to set
	 */
	public void setWeekendCharge(boolean isWeekendCharge) {
		this.isWeekendCharge = isWeekendCharge;
	}

	/**
	 * @return the isHolidayCharge
	 */
	public boolean isHolidayCharge() {
		return isHolidayCharge;
	}

	/**
	 * @param isHolidayCharge the isHolidayCharge to set
	 */
	public void setHolidayCharge(boolean isHolidayCharge) {
		this.isHolidayCharge = isHolidayCharge;
	}

}
