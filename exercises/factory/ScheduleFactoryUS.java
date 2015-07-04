package exercises.factory;

import exercises.factory.ScheduleFactory;
import exercises.loan.*;


public class ScheduleFactoryUS implements ScheduleFactory {

	public Amount createAmount(double loanAmount) {
		return new AmountUS(loanAmount);
	}

	public Apr createApr(double interestRate) {
		return new Apr(interestRate);
	}

	public Term createTerm(int years) {
		return new Term(years);
	}

	public AmortizationSchedule createSchedule(Amount amount, Apr apr, Term term) {
		return new AmortizationScheduleUS(amount, apr, term);
	}
}

