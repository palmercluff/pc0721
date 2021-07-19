/**
 * Tool of type: Chainsaw
 */

/**
 * @author Palmer Cluff
 *
 */
public class Chainsaw extends Tool {

	/**
	 * @param brand
	 * @param code
	 */
	public Chainsaw(String brand, String code) {
		this.type = "Chainsaw";
		this.brand = brand;
		this.code = code;
		this.dailyCharge = 1.49;
		this.isWeekdayCharge = true;
		this.isWeekendCharge = false;
		this.isHolidayCharge = true;
	}

}
