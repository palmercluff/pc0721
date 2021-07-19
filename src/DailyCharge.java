import java.util.Date;

/**
 * Object to hold Date and charge mappings
 */

/**
 * @author Palmer Cluff
 *
 */
public class DailyCharge {

	private Date date;
	private double charge;

	/**
	 * @param date
	 * @param charge
	 */
	public DailyCharge(Date date, double charge) {
		this.date = date;
		this.charge = charge;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the charge
	 */
	public double getCharge() {
		return charge;
	}

	/**
	 * @param charge the charge to set
	 */
	public void setCharge(double charge) {
		this.charge = charge;
	}

}
