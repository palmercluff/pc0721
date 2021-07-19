import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Prompt clerk for data which will be used to later generate the Rental Agreement
 */

/**
 * @author Palmer Cluff
 *
 */
public class UserData {

	private Tool selectedTool;
	private Date checkoutDate;
	private Date dueDate;
	private int rentalDayCount;
	private List<DailyCharge> dailyCharges;
	private int discountPercent;

	/**
	 * @param tools
	 */
	public UserData(List<Tool> tools) {
		promptRentalToolDetails(tools);
		promptRentalDayDetails();
		promptDiscountPercent();
	}

	/**
	 * @return the selectedTool
	 */
	public Tool getSelectedTool() {
		return selectedTool;
	}

	/**
	 * @param selectedTool the selectedTool to set
	 */
	public void setSelectedTool(Tool selectedTool) {
		this.selectedTool = selectedTool;
	}

	/**
	 * @return the checkoutDate
	 */
	public Date getCheckoutDate() {
		return checkoutDate;
	}

	/**
	 * @param checkoutDate the checkoutDate to set
	 */
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the rentalDayCount
	 */
	public int getRentalDayCount() {
		return rentalDayCount;
	}

	/**
	 * @param rentalDayCount the rentalDayCount to set
	 */
	public void setRentalDayCount(int rentalDayCount) {
		this.rentalDayCount = rentalDayCount;
	}

	/**
	 * @return the dailyCharges
	 */
	public List<DailyCharge> getDailyCharges() {
		return dailyCharges;
	}

	/**
	 * @param dailyCharges the dailyCharges to set
	 */
	public void setDailyCharges(List<DailyCharge> dailyCharges) {
		this.dailyCharges = dailyCharges;
	}

	/**
	 * @return the discountPercent
	 */
	public int getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * @param discountPercent the discountPercent to set
	 */
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	/**
	 * @param tools
	 */
	public void promptRentalToolDetails(List<Tool> tools) {

		boolean isValidSelectedTool = false;

		// Output table of all available tools
		System.out.printf("%-15s %-15s %-15s %n", "Tool Type", "Brand", "Tool Code");
		for (Tool tool : tools) {
			System.out.printf("%-15s %-15s %-15s %n", tool.getType(), tool.getBrand(), tool.getCode());
		}

		// Prompt for tool selection
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Please select which tool that you would like to use based off of tool code: ");
			String selectedToolInput = scanner.nextLine();
			selectedToolInput = selectedToolInput.toUpperCase();

			for (Tool tool : tools) {
				if (selectedToolInput.equals(tool.code.toUpperCase())) {
					isValidSelectedTool = true;
					this.selectedTool = tool;
					break;
				}
			}

			if (!isValidSelectedTool) {
				System.out.println("Please select a valid tool code");
			}

		} while (!isValidSelectedTool);

		// System.out.println(this.selectedTool.getType());
	}

	/**
	 * 
	 */
	public void promptRentalDayDetails() {

		boolean isValidDate = true;
		Scanner scanner = new Scanner(System.in);
		this.checkoutDate = null;
		this.rentalDayCount = 0;

		// Prompt for checkout date
		do {
			System.out.print("Please enter the date (MM/DD/YYYY) when you will CHECKOUT the item(s): ");
			String checkoutDateInput = scanner.nextLine();

			try {
				this.checkoutDate = new SimpleDateFormat("MM/dd/yyyy").parse(checkoutDateInput);
				isValidDate = true;
			} catch (ParseException e) {
				// e.printStackTrace();
				System.out.println("Unsupported date entered. Please enter a valid date in the format of MM/DD/YYYY)");
				isValidDate = false;
			}
		} while (!isValidDate);

		do {
			System.out.print("Please enter how many days you will checkout the item(s) for: ");
			String rentalDayCountInput = scanner.nextLine();
			try {
				this.rentalDayCount = Integer.parseInt(rentalDayCountInput);
				isValidDate = true;

				if (this.rentalDayCount < 1) {
					System.out.println("Rental day period is too small. Rental day period must be at least 1 day");
					isValidDate = false;
				}
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("Please enter a valid number");
				isValidDate = false;
			}

		} while (!isValidDate);

		// Prepare date range for Date for-looping
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.checkoutDate);
		calendar.add(Calendar.DATE, this.rentalDayCount);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dueDateOutput = sdf.format(calendar.getTime());

		this.dueDate = null;

		try {
			this.dueDate = new SimpleDateFormat("MM/dd/yyyy").parse(dueDateOutput);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// System.out.println("Due date is: " + this.dueDate);

		Calendar start = Calendar.getInstance();
		start.setTime(this.checkoutDate);
		Calendar end = Calendar.getInstance();
		end.setTime(this.dueDate);
		end.add(Calendar.DATE, 1);

		// Per requirements, first chargable date is the day AFTER checkout
		Date date = start.getTime();
		start.add(Calendar.DATE, 1);
		date = start.getTime();

		this.dailyCharges = new ArrayList<DailyCharge>();

		for (date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			// System.out.println(date);
			Calendar c = Calendar.getInstance();
			c.setTime(date);

			// Check 4th of July
			if (c.get(Calendar.MONTH) == Calendar.JULY && c.get(Calendar.DAY_OF_MONTH) == 4) {

				// July 4th is celebrated on both Friday and Saturday
				if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
					if (this.selectedTool.isHolidayCharge()) {
						Date friday = this.dailyCharges.get(this.dailyCharges.size() - 1).getDate();
						DailyCharge dailyCharge1 = new DailyCharge(friday, selectedTool.getDailyCharge());
						this.dailyCharges.remove(this.dailyCharges.size() - 1);
						this.dailyCharges.add(dailyCharge1);
						DailyCharge dailyCharge2 = new DailyCharge(date, selectedTool.getDailyCharge());
						this.dailyCharges.add(dailyCharge2);
					} else {
						Date friday = this.dailyCharges.get(this.dailyCharges.size() - 1).getDate();
						DailyCharge dailyCharge1 = new DailyCharge(friday, 0.0);
						this.dailyCharges.remove(this.dailyCharges.size() - 1);
						this.dailyCharges.add(dailyCharge1);
						DailyCharge dailyCharge2 = new DailyCharge(date, 0.0);
						this.dailyCharges.add(dailyCharge2);
					}
				}

				// July 4th is celebrated on both Sunday and Monday
				else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					if (this.selectedTool.isHolidayCharge()) {
						DailyCharge dailyCharge1 = new DailyCharge(date, selectedTool.getDailyCharge());
						this.dailyCharges.add(dailyCharge1);
						start.add(Calendar.DATE, 1);
						date = start.getTime();
						DailyCharge dailyCharge2 = new DailyCharge(date, selectedTool.getDailyCharge());
						this.dailyCharges.add(dailyCharge2);
					} else {
						DailyCharge dailyCharge1 = new DailyCharge(date, 0.0);
						this.dailyCharges.add(dailyCharge1);
						start.add(Calendar.DATE, 1);
						date = start.getTime();
						DailyCharge dailyCharge2 = new DailyCharge(date, 0.0);
						this.dailyCharges.add(dailyCharge2);
					}
				}

				// July 4th is celebrated on the one weekday it falls on
				else {
					if (this.selectedTool.isHolidayCharge()) {
						DailyCharge dailyCharge = new DailyCharge(date, selectedTool.getDailyCharge());
						this.dailyCharges.add(dailyCharge);
					} else {
						DailyCharge dailyCharge = new DailyCharge(date, 0.0);
						this.dailyCharges.add(dailyCharge);
					}
				}

			}

			// Check Labor Day
			else if (c.get(Calendar.MONTH) == Calendar.SEPTEMBER && c.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1
					&& c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				if (this.selectedTool.isHolidayCharge()) {
					DailyCharge dailyCharge = new DailyCharge(date, selectedTool.getDailyCharge());
					this.dailyCharges.add(dailyCharge);
				} else {
					DailyCharge dailyCharge = new DailyCharge(date, 0.0);
					this.dailyCharges.add(dailyCharge);
				}

			}

			// Check Weekends
			else if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if (this.selectedTool.isWeekendCharge()) {
					DailyCharge dailyCharge = new DailyCharge(date, selectedTool.getDailyCharge());
					this.dailyCharges.add(dailyCharge);
				} else {
					DailyCharge dailyCharge = new DailyCharge(date, 0.0);
					this.dailyCharges.add(dailyCharge);
				}
			}

			// Check Weekdays
			else if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
					|| c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)) {
				if (this.selectedTool.isWeekdayCharge()) {
					DailyCharge dailyCharge = new DailyCharge(date, selectedTool.getDailyCharge());
					this.dailyCharges.add(dailyCharge);
				} else {
					DailyCharge dailyCharge = new DailyCharge(date, 0.0);
					this.dailyCharges.add(dailyCharge);
				}
			}
		}

		/*
		 * for (DailyCharge dailyCharge : this.dailyCharges) {
		 * System.out.println(dailyCharge.getDate() + "   " + dailyCharge.getCharge());
		 * }
		 */

	}

	/**
	 * 
	 */
	public void promptDiscountPercent() {
		boolean isValidDiscountPercent = true;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("Please enter an optional discount percent (0 for no discount percent): ");
			String discountPercentInput = scanner.nextLine();
			try {
				this.discountPercent = Integer.parseInt(discountPercentInput);

				if (this.discountPercent < 0 || this.discountPercent > 100) {
					System.out.println("Discount percent must be within 0 and 100");
					isValidDiscountPercent = false;
				}

				else {
					isValidDiscountPercent = true;
				}
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("Please enter a valid number");
				isValidDiscountPercent = false;
			}
		} while (!isValidDiscountPercent);
		scanner.close();
	}

}
