import java.io.ByteArrayInputStream;
import org.junit.Test;

/**
 * Attempted Junit test to automate user input...
 */

/**
 * @author Palmer Cluff
 *
 */
public class ToolRentalTest {

	/**
	 * 
	 */
	@Test
	public void ToolRentalTest() {
		String selectedToolInput = "0";
		System.setIn(new ByteArrayInputStream(selectedToolInput.getBytes()));

		ToolRental.main(null);
	}

}
