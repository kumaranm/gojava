/**
 * 
 */
package com.mk.problem.as;

import java.io.IOException;
import java.util.Map.Entry;

import com.mk.problem.as.common.Constants;
import com.mk.problem.as.common.PrintUtility;
import com.mk.problem.as.pojo.Loan;

/**
 * This class defines the template to use for getting the schedule
 * 
 * First a Validator is defined
 * The input is got from the user, and using the validator the validations are done
 * The schedule is calculated
 * 
 * @author kmuruganandham
 * 
 */
public abstract class ScheduleCalculator implements Schedule
{
	@Override
	public void printSchedule() throws IOException
	{
		Validator validator = getValidator();
		Loan loan = getInput(validator);
		getSchedule(loan);
	}

	/**
	 * Returns the validator to be used for validating the input from the user
	 * 
	 * @return Validator
	 */
	abstract protected Validator getValidator();

	/**
	 * Calculates and prints the schedule
	 * 
	 * @param loan
	 */
	abstract protected void getSchedule(Loan loan);

	/**
	 * Gets the input from the user and validates the input and returns a Loan
	 * object
	 * 
	 * @param validator
	 * @return Loan
	 * @throws IOException
	 */
	protected Loan getInput(Validator validator) throws IOException
	{
		boolean validate = true;
		if (null == validator)
		{
			validate = false;
		}

		double amount = 0;
		double apr = 0;
		int years = 0;

		for (Entry<String, String> set : Constants.INPUT_QUESTIONS.entrySet())
		{
			boolean isValidValue = false;
			while (!isValidValue)
			{
				isValidValue = true;
				try
				{
					String input = PrintUtility.readLine(set.getValue());
					switch (set.getKey()) {
					case Constants.BORROW_AMOUNT:
						amount = Double.parseDouble(input);
						if (validate && !validator.isValidAmount(amount))
						{
							isValidValue = false;
							PrintUtility.print("Please enter a positive value between "
									+ validator.getRanges().getAmountRange().getMinValue() + " and "
									+ validator.getRanges().getAmountRange().getMaxValue() + ". ");
						}
						break;
					case Constants.APR:
						apr = Double.parseDouble(input);
						if (validate && !validator.isValidPercent(apr))
						{
							isValidValue = false;
							PrintUtility.print("Please enter a positive value between "
									+ validator.getRanges().getPercentRange().getMinValue() + " and "
									+ validator.getRanges().getPercentRange().getMaxValue() + ". ");
						}
						break;
					case Constants.TERM:
						years = Integer.parseInt(input);
						if (validate && !validator.isValidTerm(years))
						{
							isValidValue = false;
							PrintUtility.print("Please enter a positive value between "
									+ validator.getRanges().getTermRange().getMinValue() + " and "
									+ validator.getRanges().getTermRange().getMaxValue() + ". ");
						}
						break;
					default:
						break;
					}
				} catch (NumberFormatException e)
				{
					isValidValue = false;
				}
				if (!isValidValue)
				{
					PrintUtility.print("An invalid value was entered.\n");
				}
			}
		}

		return new Loan(amount, apr, years);
	}

}
