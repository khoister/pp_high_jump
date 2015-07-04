//
// Exercise Details:
// Build an amortization schedule program using Java. 
// 
// The program should prompt the user for
//		the amount he or she is borrowing,
//		the annual percentage rate used to repay the loan,
//		the term, in years, over which the loan is repaid.  
// 
// The output should include:
//		The first column identifies the payment number.
//		The second column contains the amount of the payment.
//		The third column shows the amount paid to interest.
//		The fourth column has the current balance.  The total payment amount and the interest paid fields.
// 
// Use appropriate variable names and comments.  You choose how to display the output (i.e. Web, console).  
// Amortization Formula
// This will get you your monthly payment.  Will need to update to Java.
// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
// 
// Where:
// P = Principal
// I = Interest
// J = Monthly Interest in decimal form:  I / (12 * 100)
// N = Number of months of loan
// M = Monthly Payment Amount
// 
// To create the amortization table, create a loop in your program and follow these steps:
// 1.      Calculate H = P x J, this is your current monthly interest
// 2.      Calculate C = M - H, this is your monthly payment minus your monthly interest, so it is the amount of principal you pay for that month
// 3.      Calculate Q = P - C, this is the new balance of your principal of your loan.
// 4.      Set P equal to Q and go back to Step 1: You thusly loop around until the value Q (and hence P) goes to zero.
// 

package exercises.loan;

import java.lang.Math;
import exercises.utils.IO;


abstract public class AmortizationSchedule {

	abstract long   getLoanAmount();
	abstract double getMonthlyInterestDivisor();
	abstract double getInterestRate();
	abstract int    getInitialTermMonths();
	abstract long   getMonthlyPaymentAmount();
	abstract void   printHeader();
	abstract void   print(int paymentNumber,
	                      long curMonthlyPayment,
	                      long curMonthlyInterest,
	                      long curBalance,
	                      long totalPayments,
	                      long totalInterestPaid);

	private double monthlyInterest = 0d;


	protected long calculateMonthlyPayment() {
		// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
		//
		// Where:
		// P = Principal
		// I = Interest
		// J = Monthly Interest in decimal form:  I / (12 * 100)
		// N = Number of months of loan
		// M = Monthly Payment Amount
		// 
		
		// calculate J
		monthlyInterest = getInterestRate() / getMonthlyInterestDivisor();
		
		// this is 1 / (1 + J)
		double tmp = 1d / (1d + monthlyInterest);
		
		// this is Math.pow(1/(1 + J), N)
		tmp = Math.pow(tmp, getInitialTermMonths());
		
		// this is J / (1 - (Math.pow(1/(1 + J), N))))
		tmp = monthlyInterest / (1d - tmp);
		
		// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
		return Math.round(getLoanAmount() * tmp);
	}
	
	// The output should include:
	//	The first column identifies the payment number.
	//	The second column contains the amount of the payment.
	//	The third column shows the amount paid to interest.
	//	The fourth column has the current balance.  The total payment amount and the interest paid fields.
	public void outputAmortizationSchedule() {
		// 
		// To create the amortization table, create a loop in your program and follow these steps:
		// 1.      Calculate H = P x J, this is your current monthly interest
		// 2.      Calculate C = M - H, this is your monthly payment minus your monthly interest, so it is the amount of principal you pay for that month
		// 3.      Calculate Q = P - C, this is the new balance of your principal of your loan.
		// 4.      Set P equal to Q and go back to Step 1: You thusly loop around until the value Q (and hence P) goes to zero.
		// 

		printHeader();
		
		long balance           = getLoanAmount();
		int  paymentNumber     = 0;
		long totalPayments     = 0;
		long totalInterestPaid = 0;
		
		// Output the first row at month 0
		print(paymentNumber++, 0, 0, getLoanAmount(), totalPayments, totalInterestPaid);

		final int maxNumberOfPayments = getInitialTermMonths() + 1;
		while ((balance > 0) && (paymentNumber <= maxNumberOfPayments)) {
			// Calculate H = P x J, this is your current monthly interest
			long curMonthlyInterest = Math.round(((double) balance) * monthlyInterest);

			// the amount required to payoff the loan
			long curPayoffAmount = balance + curMonthlyInterest;
			
			// the amount to payoff the remaining balance may be less than the calculated monthlyPaymentAmount
			long curMonthlyPaymentAmount = Math.min(getMonthlyPaymentAmount(), curPayoffAmount);
			
			// it's possible that the calculated monthlyPaymentAmount is 0,
			// or the monthly payment only covers the interest payment - i.e. no principal
			// so the last payment needs to payoff the loan
			if ((paymentNumber == maxNumberOfPayments) &&
					((curMonthlyPaymentAmount == 0) || (curMonthlyPaymentAmount == curMonthlyInterest))) {
				curMonthlyPaymentAmount = curPayoffAmount;
			}
			
			// Calculate C = M - H, this is your monthly payment minus your monthly interest,
			// so it is the amount of principal you pay for that month
			long curMonthlyPrincipalPaid = curMonthlyPaymentAmount - curMonthlyInterest;
			
			// Calculate Q = P - C, this is the new balance of your principal of your loan.
			long curBalance = balance - curMonthlyPrincipalPaid;
			
			totalPayments += curMonthlyPaymentAmount;
			totalInterestPaid += curMonthlyInterest;

			// Print the rest of the amortization schedule
			print(paymentNumber++, curMonthlyPaymentAmount, curMonthlyInterest, curBalance, totalPayments, totalInterestPaid);

			// Set P equal to Q and go back to Step 1: You thusly loop around until the value Q (and hence P) goes to zero.
			balance = curBalance;
		}
	}
}

