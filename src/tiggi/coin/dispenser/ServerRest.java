package tiggi.coin.dispenser;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/rest")
public class ServerRest {
	
	private static int mAuthToken;
	private static String mBill;
	private static String mPayment;
	
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
		System.out.println("Generating your bill...");
		BillRandomizer bill = new BillRandomizer();
		mBill = bill.getBill();
		return "<bill>" + "<billoutput>" + mBill + "</billoutput>" + "</bill>";
	}
	
	// Denomination break down
	@Path("/payment/{paid}")
	@GET
	@Produces("application/xml")
	public String getTheChange(@PathParam("paid") String paid) {
		
		mPayment = paid;
		Change toPay = new Change(mBill, mPayment);
		Double changeFull = toPay.sumList(toPay.getChangeList());
		Set<Double> looper = toPay.getChangeSet();
		List<Double> change = toPay.getChangeList();
		StringBuilder sb = new StringBuilder();
		StringBuilder result = null;
		
		// Loop through return above and display in xml
		int i = 1;
		for (Double money: looper) {
			String str = (Collections.frequency(change, money) + "x R" + money).toString();
			result = sb.append("<change" + i + ">" + str + "</change" + i + ">");
			i++;
		}
		String resultstr = "@Produces(\"application/xml\") Your change is:\n" + changeFull;
		return "<change>" + "<changeoutput>" + result.toString() + "</changeoutput>" + "</change>";
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
		
		if(usr.equals("user") && pwd.equals("pass")) {
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
