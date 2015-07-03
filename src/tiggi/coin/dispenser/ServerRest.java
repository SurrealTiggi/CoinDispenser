package tiggi.coin.dispenser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/auth")
public class ServerRest {
	
	@Path("{c}")
	@GET
	@Produces("application/xml")
	public String letMeInWithInput(@PathParam("c") String c) {
		String allowed;
		
		if(auth(c)) {
			allowed = "Well done!";			
		}
		else {
			allowed = "Booooooo";
		} 
		String result = "@Produces(\"application/xml\") Your results:\n" + allowed;
		return "<auth>" + "<authoutput>" + result + "</authoutput>" + "</auth>";
	}
	
	@GET
	@Produces("application/xml")
	public String letMeInWithoutInput() {
		String allowed = "Hey! Some variables please...";
		String result = "@Produces(\"application/xml\") Your results:\n" + allowed;
		return "<auth>" + "<authoutput>" + result + "</authoutput>" + "</auth>";
	}
	
	public static Boolean auth(String creds) {
		boolean yayOrNay;
		
		
		if(creds != "user") {
			yayOrNay = true;
		}
		else {
			yayOrNay = false;
		}
		System.out.println("yay or nay is " + yayOrNay);
		
		return yayOrNay;
	}
	
}
