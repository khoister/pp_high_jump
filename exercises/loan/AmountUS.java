package exercises.loan;

import java.lang.IllegalArgumentException;

import exercises.loan.Amount;


public class AmountUS extends Amount {

	// Amount in US dollars
	private double amount     = 0d;
	private double min_amount = 1d;
	private double max_amount = 10000000000d;

	public AmountUS(double amount_in_dollars) throws IllegalArgumentException {
		amount = amount_in_dollars;
		if (!validate()) {
			throw new IllegalArgumentException();
		}
	}

	public double getAmount()    { return amount; }
	public double getMinAmount() { return min_amount; }
	public double getMaxAmount() { return max_amount; }
}

