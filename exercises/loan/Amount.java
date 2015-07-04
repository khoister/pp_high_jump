package exercises.loan;

import exercises.loan.DataObject;
import exercises.utils.IO;


abstract public class Amount extends DataObject{

	abstract double getAmount();
	abstract double getMinAmount();
	abstract double getMaxAmount();

	/**
	 * Checks if the loan amount is between the minimum and maximum range
	 */
	public boolean isValid() {
		double amount = getAmount();
		return ( (amount >= getMinAmount()) && (amount <= getMaxAmount()) );
	}

	public void printErrorMsg() {
		exercises.utils.IO.print("ERROR: Please enter a positive amount value between " + getMinAmount() + " and " + getMaxAmount() + ".\n");
	}
}

