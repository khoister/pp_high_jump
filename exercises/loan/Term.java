package exercises.loan;

import java.lang.IllegalArgumentException;

import exercises.loan.DataObject;
import exercises.utils.IO;


public class Term extends DataObject {

	// Term in years
	private int term = 0;
	private static final int min_term = 1;
	private static final int max_term = 1000000;
	private static final int MONTHS_IN_YEAR = 12;

	public Term(int years) throws IllegalArgumentException {
		term = years;
		if (!validate()) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Checks if the term of the loan is between the minimum and maximum range
	 */
	public boolean isValid() {
		return ((term >= min_term) && (term <= max_term));
	}

	public int getValue() {
		return term;
	}

	public void printErrorMsg() {
		exercises.utils.IO.print("ERROR: Please enter a positive integer value between " + min_term + " and " + max_term + ".\n");
	}

	/*
	 * Converts from years to months
	 */
	public static int yearsToMonths(int years) {
		int months = 0;
		if (years >= 0) {
			months = years * MONTHS_IN_YEAR;
		}
		return months;
	}
}

