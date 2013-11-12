/**
 * 
 */
package com.mk.problem.as.impl;

import com.mk.problem.as.Validator;
import com.mk.problem.as.common.Constants;
import com.mk.problem.as.pojo.LoanRange;

/**
 * This class defines the Min and Max values for the loan elements and provides
 * methods to validate the input against the ranges
 * 
 * @author kmuruganandham
 * 
 */
public class AmortizingLoanValidator implements Validator
{

	private LoanRange ranges;
	private static AmortizingLoanValidator loanRange;

	private AmortizingLoanValidator() {
		init();
	}

	public static AmortizingLoanValidator getInstance()
	{
		if (loanRange == null)
		{
			loanRange = new AmortizingLoanValidator();
		}
		return loanRange;
	}

	private void init()
	{
		ranges = new LoanRange();
		ranges.setAmountRange(Constants.BORROW_AMOUNT_MIN_RANGE, Constants.BORROW_AMOUNT_MAX_RANGE);
		ranges.setPercentRange(Constants.APR_MIN_RANGE, Constants.APR_MAX_RANGE);
		ranges.setTermRange(Constants.TERM_MIN_RANGE, Constants.TERM_MAX_RANGE);
	}

	@Override
	public boolean isValidAmount(double amount)
	{
		return ((ranges.getAmountRange().getMinValue() <= amount) && (amount <= ranges.getAmountRange().getMaxValue()));
	}

	@Override
	public boolean isValidPercent(double percent)
	{
		return ((ranges.getPercentRange().getMinValue() <= percent) && (percent <= ranges.getPercentRange()
				.getMaxValue()));
	}

	@Override
	public boolean isValidTerm(int term)
	{
		return ((ranges.getTermRange().getMinValue() <= term) && (term <= ranges.getTermRange().getMaxValue()));
	}

	@Override
	public LoanRange getRanges()
	{
		return ranges;
	}
}
