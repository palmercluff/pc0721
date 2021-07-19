/**
 * Tool of type: Ladder
 */

/**
 * @author Palmer Cluff
 *
 */
public class Ladder extends Tool {

	/**
	 * @param brand
	 * @param code
	 */
	public Ladder(String brand, String code) {
		this.type = "Ladder";
		this.brand = brand;
		this.code = code;
		this.dailyCharge = 1.99;
		this.isWeekdayCharge = true;
		this.isWeekendCharge = true;
		this.isHolidayCharge = false;
	}

}
