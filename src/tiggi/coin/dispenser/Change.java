package tiggi.coin.dispenser;

import java.util.*;


public class Change {
	
	private static double mTotal;
	private static double mPayment;
	
	public static double sumList(List<Double> gotList) {
		double result = 0;
		for (double i: gotList)
			result = result + i;
		return result;
	}

	public static List<Double> breakItDown(double bill, double cash) {
		
		// Create and fill both hashes (easier way to do this?)
		LinkedHashMap<String, Double> HmMoney = new LinkedHashMap<String, Double>();

		HmMoney.put("R100", new Double(100.00));
		HmMoney.put("R50", new Double(50.00));
		HmMoney.put("R20", new Double(20.00));
		HmMoney.put("R10", new Double(10.00));
		HmMoney.put("R5", new Double(5.00));
		HmMoney.put("R2", new Double(2.00));
		HmMoney.put("R1", new Double(1.00));
		HmMoney.put("50c", new Double(0.50));
		HmMoney.put("25c", new Double(0.20));
		HmMoney.put("10c", new Double(0.10));
		HmMoney.put("5c", new Double(0.05));
		
		// Calculate the change
		double change = cash - bill;
		double runningTotal = 0.00;
		
		// Create empty list of doubles to store denominations;
		List<Double> changeList = new ArrayList<Double>();

		// Create simple list of denomination values to loop through and sum
		List<Double> moneyList = new ArrayList<Double>(HmMoney.values());
		
		// Loop until sum of all items in changeList == change		
		for(int x = 0; x < moneyList.size(); x++) {
			changeList.add(moneyList.get(x));
			runningTotal = sumList(changeList);
			if(runningTotal > change) {
				changeList.remove(changeList.size()-1);
			}
			else if(runningTotal < change) {
				x--;
			}
			else if(runningTotal == change) {
				break;
			}
		}
		return changeList;
	}

	public static double starter(double total, double payment) {
		List<Double> change = breakItDown(total, payment);

		// HashSet creates a set with only unique values
		Set<Double> uniqueSet = new HashSet<Double>(change);
		//System.out.println(uniqueSet);
		
		System.out.println("You paid >> " + payment);
		System.out.println("Your change is " + sumList(change) + " broken down as follows:");
		double finalSum = sumList(change);
		
		// Loop through set, pass to Collections to count occurences in original list
		for (Double money: uniqueSet) {
			System.out.println(Collections.frequency(change, money) + "x R" + money);
		}
		
		return finalSum;
	}
	
	public Change(String total, String payment) {
		mTotal = Double.parseDouble(total);
		mPayment = Double.parseDouble(payment);
		
		starter(mTotal, mPayment);
	}
}
