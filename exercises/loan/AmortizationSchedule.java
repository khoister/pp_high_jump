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

	abstract double getLoanAmount();
	abstract double getMonthlyInterestDivisor();
	abstract double getInterestRate();
	abstract int    getInitialTermMonths();
	abstract double getMonthlyPaymentAmount();
	abstract void   printHeader();
	abstract void   print(int paymentNumber,
	                      double curMonthlyPayment,
	                      double curMonthlyInterest,
	                      double curMonthlyPrincipal,
	                      double curBalance,
	                      double totalPayments,
	                      double totalInterestPaid);


	protected double calculateMonthlyPayment() {
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
		double monthlyInterestRate = getInterestRate() / getMonthlyInterestDivisor();
		
		// this is 1 / (1 + J)
		double tmp = 1d / (1d + monthlyInterestRate);
		
		// this is Math.pow(1/(1 + J), N)
		tmp = Math.pow(tmp, getInitialTermMonths());
		
		// this is J / (1 - (Math.pow(1/(1 + J), N))))
		tmp = monthlyInterestRate / (1d - tmp);
		
		// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
		return getLoanAmount() * tmp;
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
		
		double balance             = getLoanAmount();
		double monthlyPayment      = getMonthlyPaymentAmount();
		double monthlyInterestRate = getInterestRate() / getMonthlyInterestDivisor();
		int    months              = getInitialTermMonths();
		double totalPayments       = 0d;
		double totalInterestPaid   = 0d;

		printHeader();
		for (int paymentNumber = 1; paymentNumber <= months; ++paymentNumber) {
			double monthlyInterest      = balance * monthlyInterestRate;
			double monthlyPrincipalPaid = monthlyPayment - monthlyInterest;
			double newBalance           = Math.abs(balance - monthlyPrincipalPaid);
			totalPayments              += monthlyPayment;
			totalInterestPaid          += monthlyInterest;

			print(paymentNumber, monthlyPayment, monthlyInterest, monthlyPrincipalPaid, newBalance, totalPayments, totalInterestPaid);
			balance = newBalance;
		}
	}
}

