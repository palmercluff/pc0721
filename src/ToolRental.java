import java.util.ArrayList;
import java.util.List;

/**
 * Root of Tool Rental program.
 */

/**
 * @author Palmer Cluff
 *
 */
public class ToolRental {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Declare tools

		// Ladders
		Ladder wernerLadder = new Ladder("Werner", "LADW");

		// Chainsaws
		Tool stihlChainsaw = new Chainsaw("Stihl", "CHNS");

		// Jackhammers
		Tool ridgidJackhammer = new Jackhammer("Ridgid", "JAKR");
		Tool deWaltJackhammer = new Jackhammer("DeWalt", "JAKD");

		// Add available tools to list
		List<Tool> tools = new ArrayList<Tool>();
		tools.add(wernerLadder);
		tools.add(stihlChainsaw);
		tools.add(ridgidJackhammer);
		tools.add(deWaltJackhammer);

		// Start gathering data from user
		UserData userData = new UserData(tools);

		// Print Rental Agreement
		RentalAgreement rentalAgreement = new RentalAgreement(userData);
		rentalAgreement.displayRentalAgreement();

	}
}
