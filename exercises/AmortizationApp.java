package exercises;

import java.io.IOException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;

import exercises.utils.IO;
import exercises.loan.*;
import exercises.factory.*;


class AmortizationApp {

	public static void main(String [] args) {
		
		String[] userPrompts = {
				"Please enter the amount you would like to borrow: ",
				"Please enter the annual percentage rate used to repay the loan: ",
				"Please enter the term, in years, over which the loan is repaid: "
		};
		
		String line;
		Amount amount = null;
		Apr    apr    = null;
		Term   term   = null;
		ScheduleFactory factory = new ScheduleFactoryUS();
		
		for (int step = 0; step < userPrompts.length; ) {
			try {
				line = exercises.utils.IO.readLine(userPrompts[step]);
			} catch (IOException e) {
				exercises.utils.IO.print("An IOException was encountered. Terminating program.\n");
				return;
			}
			
			try {
				switch (step) {
					case 0: amount = factory.createAmount(Double.parseDouble(line)); break;
					case 1: apr    = factory.createApr(Double.parseDouble(line));    break;
					case 2: term   = factory.createTerm(Integer.parseInt(line));     break;
				}
				++step;
			}
			catch (NumberFormatException e) {}
			catch (IllegalArgumentException e) {}
		}
		
		try {
			factory.createSchedule(amount, apr, term).outputAmortizationSchedule();
		} catch (IllegalArgumentException e) {
			exercises.utils.IO.print("Unable to process the values entered. Terminating program.\n");
		}
	}
}

