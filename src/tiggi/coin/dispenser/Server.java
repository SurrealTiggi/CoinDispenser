package tiggi.coin.dispenser;

import java.io.Console;

public class Server {

	public static void main(String[] args) {
		if(auth()) {
			System.out.println("Authentication successful...continuing...");
			
			BillRandomizer dispenser = new BillRandomizer();
			
		}
		else {
			System.out.println("Authentication failed...exiting!");
			System.exit(0);
		}
	}
	
	public static Boolean auth() {
		
		Console console = System.console();
		boolean yayOrNay;
		
		//String userName = console.readLine("Username (case sensitive)? >> ");
		//String passWord = console.readLine("Password (case sensitive)? >> ");
		
		String userName = "user";
		String passWord = "pass";
		
		if(userName == "user" && passWord == "pass") {
			yayOrNay = true;
		}
		else {
			yayOrNay = false;
		}
		
		return yayOrNay;
	}

}
