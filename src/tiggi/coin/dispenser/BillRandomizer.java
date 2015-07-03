package tiggi.coin.dispenser;

import java.util.Random;
import java.math.*;
import java.text.DecimalFormat;

public class BillRandomizer {
	
	public static double minValue = 10;
	public static double maxValue = 200;
	
	public static String roundoff(double bill) {
		double num = Math.round(bill*100.0)/100.0;
		double result = 0.05*Math.round(num/0.05);
		
		// Use BigDecimal for calculations, DecimalFormat for displaying
		// Maybe offload the above to difference classes...
		
		//BigDecimal bd = new BigDecimal(result);
		//bd = bd.setScale(3, RoundingMode.HALF_UP);
		
		DecimalFormat df = new DecimalFormat("#.00");
		
		String resultAsStr = df.format(result);
		//double resultAsDbl = Double.parseDouble(df.format(result));
		
		//return resultAsDbl;
		return resultAsStr;
		
	}
	
	
	public BillRandomizer() {
		Random r = new Random();
		double f = minValue + (maxValue - minValue) * r.nextDouble();
		String bill = roundoff(f);
		System.out.println("Please pay " + bill);
		
		String payment = "250.00";
		
		Change toPay = new Change(bill, payment);
		
	}

}
