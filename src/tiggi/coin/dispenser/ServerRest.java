package tiggi.coin.dispenser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/rest")
public class ServerRest {
	
	// REST functions
	
	@Path("/auth/{user}&{pass}")
	@GET
	@Produces("application/xml")
	public String letMeInWithInput(@PathParam("user") String userName, @PathParam("pass") String passWord) {
		String allowed;
		
		System.out.println("Authenticating with user: " + userName + " and pass: " + passWord);
		
		if(auth(userName, passWord)) {
			allowed = "Well done!";			
		}
		else {
			allowed = "Booooooo";
		} 
		//String result = "@Produces(\"application/xml\") Your results:\n" + allowed;
		return "<auth>" + "<authoutput>" + allowed + "</authoutput>" + "</auth>";
	}
	
	@Path("/bill")
	@GET
	@Produces("application/xml")
	public String spitOutRandomBill() {
		String bill;
		System.out.println("Generating your bill...");
		bill = "R250.00";
		return "<bill>" + "<billoutput>" + bill + "</billoutput>" + "</bill>";
	}
	
	@Path("/payment/{paid}")
	@GET
	@Produces("application/xml")
	public String getTheChange(@PathParam("paid") String paid) {
		// Pass bill and payment through to Change class
		// Make bill a class variable that this GET changes
		String userPayment = paid;
		
		return "<change>" + "<changeoutput>" + userPayment + "</changeoutput>" + "</change>";
	}
	
	@GET
	@Produces("application/xml")
	public String letMeInWithoutInput() {
		String allowed = "Hey! Some variables please...";
		//String result = "@Produces(\"application/xml\") Your results:\n" + allowed;
		return "<auth>" + "<authoutput>" + allowed + "</authoutput>" + "</auth>";
	}
	
	
	public static Boolean auth(String usr, String pwd) {
		boolean yayOrNay;
		
		
		if(usr == "user" && pwd == "pass") {
			yayOrNay = true;
		}
		else {
			yayOrNay = false;
		}
		
		return yayOrNay;
	}
	
}
