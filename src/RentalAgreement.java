import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Display the final Rental Agreement
 */

/**
 * @author Palmer Cluff
 *
 */
public class RentalAgreement {

	private UserData userData;

	/**
	 * @param userData
	 */
	public RentalAgreement(UserData userData) {
		this.userData = userData;
	}

	/**
	 * Display final Rental Agreement
	 */
	public void displayRentalAgreement() {

		System.out.println("Tool code: " + this.userData.getSelectedTool().getCode().toUpperCase());
		System.out.println("Tool type: " + this.userData.getSelectedTool().getType());
		System.out.println("Tool brand: " + this.userData.getSelectedTool().getBrand());
		System.out.println("Rental days: " + this.userData.getRentalDayCount());

		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yy");

		System.out.println("Checkout date: " + sm.format(this.userData.getCheckoutDate()));
		System.out.println("Due date: " + sm.format(this.userData.getDueDate()));
		System.out.println("Daily rental charge:");

		int chargeDays = 0;
		double totalCharge = 0;

		for (DailyCharge dailyCharge : this.userData.getDailyCharges()) {
			System.out.println("  " + new SimpleDateFormat("EEE").format(dailyCharge.getDate()) + " "
					+ sm.format(dailyCharge.getDate()) + ":  $" + dailyCharge.getCharge());
			if (dailyCharge.getCharge() != 0.0) {
				chargeDays += 1;
			}
			totalCharge += dailyCharge.getCharge();
		}

		System.out.println("Charge days: " + chargeDays);
		System.out
				.println("Pre-discount charge: $" + new BigDecimal(totalCharge).setScale(2, BigDecimal.ROUND_HALF_UP));
		System.out.println("Discount percent: " + this.userData.getDiscountPercent() + "%");
		System.out
				.println("Discount amount: $" + (this.userData.getDiscountPercent() != 0
						? new BigDecimal((this.userData.getDiscountPercent() / 100.0) * totalCharge).setScale(2,
								BigDecimal.ROUND_HALF_UP)
						: new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)));
		System.out.println("Final Charge: $" + (this.userData.getDiscountPercent() != 0
				? new BigDecimal((totalCharge - ((this.userData.getDiscountPercent() / 100.0) * totalCharge)))
						.setScale(2, BigDecimal.ROUND_HALF_UP)
				: new BigDecimal(totalCharge).setScale(2, BigDecimal.ROUND_HALF_UP)));
	}

}
