package exercises.factory;

import exercises.loan.*;


public interface ScheduleFactory {
	Amount createAmount(double loanAmount);
	Apr createApr(double interestRate);
	Term createTerm(int years);
	AmortizationSchedule createSchedule(Amount amount, Apr apr, Term term);
}

