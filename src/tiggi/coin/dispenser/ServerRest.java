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
	//@Produces("application/xml")
	public String letMeInWithInput(@PathParam("user") String userName, @PathParam("pass") String passWord) {
		String allowed;
		System.out.println("Authenticating with user: " + userName + " and pass: " + passWord);
		if(auth(userName, passWord)) {
			allowed = "1";			
		}
		else {
			allowed = "0";
		} 
		//String result = "@Produces(\"application/xml\") Your results:\n" + allowed;
		//return "<auth>" + "<authoutput>" + allowed + "</authoutput>" + "</auth>";
		return allowed;
		
	}
	
	// Random bill generator
	@Path("/bill")
	@GET
	//@Produces("application/xml")
	public String spitOutRandomBill() {
		System.out.println("Generating your bill...");
		BillRandomizer bill = new BillRandomizer();
		mBill = bill.getBill();
		//return "<bill>" + "<billoutput>" + mBill + "</billoutput>" + "</bill>";
		return mBill;
	}
	
	// Denomination break down
	@Path("/payment/{bill}&{paid}")
	@GET
	//@Produces("application/xml")
	public String getTheChange(@PathParam("bill") String bill, @PathParam("paid") String paid) {
		
		//mPayment = paid;
		Change toPay = new Change(bill, paid);
		Double changeFull = toPay.sumList(toPay.getChangeList());
		Set<Double> looper = toPay.getChangeSet();
		List<Double> change = toPay.getChangeList();
		StringBuilder sb = new StringBuilder();
		StringBuilder result = null;
		System.out.println("Done with change, now formatting...");
		
		// Loop through return above and display in xml
		int i = 1;
		for (Double money: looper) {
			String str = (Collections.frequency(change, money) + "x R" + money).toString();
			//result = sb.append("<change" + i + ">" + str + "</change" + i + ">");
			result = sb.append(str + ",");
			i++;
		}
		String resultstr = "@Produces(\"application/xml\") Your change is:\n" + changeFull;
		//return "<change>" + "<changeoutput>" + result.toString() + "</changeoutput>" + "</change>";
		return result.toString();
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

}
