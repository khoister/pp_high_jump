// US-specific amortization schedule

package exercises.loan;

import java.lang.Math;
import java.lang.IllegalArgumentException;
import exercises.utils.IO;


public class AmortizationScheduleUS extends AmortizationSchedule {

	private double amountBorrowed = 0d;
	private double apr = 0d;
	private int    initialTermMonths = 0;
	private double monthlyPaymentAmount = 0d;

	private static final String headerFormatString = "%1$-20s%2$-20s%3$-20s%4$-20s%5$-20s%6$-20s%7$-20s\n";
	private static final String bodyFormatString = "%1$-20d%2$-20.2f%3$-20.2f%4$-20.2f%5$-20.2f%6$-20.2f%7$-20.2f\n";

	public double getLoanAmount() {
		return amountBorrowed;
	}

	public double getMonthlyInterestDivisor() {
		return 12d * 100d;
	}

	public double getInterestRate() {
		return apr;
	}

	public int getInitialTermMonths() {
		return initialTermMonths;
	}

	public double getMonthlyPaymentAmount() {
		return monthlyPaymentAmount;
	}


	public AmortizationScheduleUS(Amount amount, Apr interestRate, Term term) throws IllegalArgumentException {

		if (amount == null || interestRate == null || term == null) {
			throw new IllegalArgumentException();
		}

		if (amount.notValid() || interestRate.notValid() || term.notValid()) {
			throw new IllegalArgumentException();
		}

		amountBorrowed = amount.getAmount();
		apr = interestRate.getValue();
		initialTermMonths = Term.yearsToMonths(term.getValue());
		
		monthlyPaymentAmount = calculateMonthlyPayment();
		
		// the following shouldn't happen with the available valid ranges
		// for borrow amount, apr, and term; however, without range validation,
		// monthlyPaymentAmount as calculated by calculateMonthlyPayment()
		// may yield incorrect values with extreme input values
		if (monthlyPaymentAmount > amountBorrowed) {
			throw new IllegalArgumentException();
		}
	}
	
	// The output should include:
	//	The first column identifies the payment number.
	//	The second column contains the amount of the payment.
	//	The third column shows the amount paid to interest.
	//	The fourth column has the current balance.  The total payment amount and the interest paid fields.
	public void printHeader() {
		exercises.utils.IO.printf(headerFormatString,
			"PaymentNumber", "PaymentAmount", "PaymentInterest", "PaymentPrincipal", "CurrentBalance", "TotalPayments", "TotalInterestPaid");
	}

	public void print(
		int  paymentNumber,
		double curMonthlyPayment,
		double curMonthlyInterest,
		double curMonthlyPrincipal,
		double curBalance,
		double totalPayments,
		double totalInterestPaid) {

		// output is in dollars
		exercises.utils.IO.printf(bodyFormatString,
			paymentNumber,
			curMonthlyPayment,
			curMonthlyInterest,
			curMonthlyPrincipal,
			curBalance,
			totalPayments,
			totalInterestPaid);
	}
}

