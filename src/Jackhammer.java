/**
 * Tool of type: Jackhammer
 */

/**
 * @author Palmer Cluff
 *
 */
public class Jackhammer extends Tool {

	/**
	 * @param brand
	 * @param code
	 */
	public Jackhammer(String brand, String code) {
		this.type = "Jackhammer";
		this.brand = brand;
		this.code = code;
		this.dailyCharge = 2.99;
		this.isWeekdayCharge = true;
		this.isWeekendCharge = false;
		this.isHolidayCharge = false;
	}

}
