package exercises.loan;

import java.lang.IllegalArgumentException;

import exercises.loan.DataObject;
import exercises.utils.IO;


public class Apr extends DataObject {

	private double rate     = 0d;
	private double min_rate = 0.000001d;
	private double max_rate = 100d;

	public Apr(double apr) throws IllegalArgumentException {
		rate = apr;
		if (!validate()) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks if the APR is between the minimum and maximum range
	 */
	public boolean isValid() {
		return ((rate >= min_rate) && (rate <= max_rate));
	}

	public double getValue() {
		return rate;
	}

	public void printErrorMsg() {
		exercises.utils.IO.print("ERROR: Please enter a positive value between " + min_rate + " and " + max_rate + ".\n");
	}
}

