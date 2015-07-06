package tiggi.coin.dispenser;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/rest")
public class ServerRest {
	
	private static int mAuthToken = 0;
	
	//-------------------------
	// REST functions
	//-------------------------
	//	Authentication
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
	
	
	// Random bill generator
	@Path("/bill")
	@GET
	@Produces("application/xml")
	public String spitOutRandomBill() {
		String bill;
		System.out.println("Generating your bill...");
		bill = "R250.00";
		return "<bill>" + "<billoutput>" + bill + "</billoutput>" + "</bill>";
	}
	
	// Denomination break down
	@Path("/payment/{paid}")
	@GET
	@Produces("application/xml")
	public String getTheChange(@PathParam("paid") String paid) {
		// Pass bill and payment through to Change class
		// Make bill a class variable that this GET changes
		String userPayment = paid;
		
		return "<change>" + "<changeoutput>" + userPayment + "</changeoutput>" + "</change>";
	}
	
	// No input
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
			mAuthToken = 1;
		}
		else {
			yayOrNay = false;
		}
		
		return yayOrNay;
	}

	/*public Response login(
	        //@Context HttpHeaders httpHeaders,
	        @FormParam( "username" ) String username,
	        @FormParam( "password" ) String password ) {
	 
	        //DemoAuthenticator demoAuthenticator = DemoAuthenticator.getInstance();
	        //String serviceKey = httpHeaders.getHeaderString( DemoHTTPHeaderNames.SERVICE_KEY );
	 
	        try {
	            String authToken = demoAuthenticator.login( serviceKey, username, password );
	 
	            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
	            jsonObjBuilder.add( "auth_token", authToken );
	            JsonObject jsonObj = jsonObjBuilder.build();
	 
	            return getNoCacheResponseBuilder( Response.Status.OK ).entity( jsonObj.toString() ).build();
	 
	        } catch ( final LoginException ex ) {
	            JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
	            jsonObjBuilder.add( "message", "Problem matching service key, username and password" );
	            JsonObject jsonObj = jsonObjBuilder.build();
	 
	            return getNoCacheResponseBuilder( Response.Status.UNAUTHORIZED ).entity( jsonObj.toString() ).build();
	        }
	}*/
}
