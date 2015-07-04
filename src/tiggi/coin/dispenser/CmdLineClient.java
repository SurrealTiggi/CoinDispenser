package tiggi.coin.dispenser;

public class CmdLineClient {

	public static void main(String[] args) {
		/* 1) Prompt for login details (do local exception handling)
		 * 2) HTTP GET from backend, reprompt for login for all exceptions
		 * 3) After successful login, display amount owed (HTTP GET), ensure persistent storing of amount on backend(?)
		 * 4) Capture amount to pay (local exception handling), HTTP GET a list of change(store locally?)
		 * 5) Loop through local changeList to display denomination break down
		 * 6) Prompt user for exit.
		*/

	}

}
