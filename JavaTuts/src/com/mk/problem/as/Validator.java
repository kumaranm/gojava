package com.mk.problem.as;

import com.mk.problem.as.pojo.LoanRange;

/**
 * Defines the validations for the loan elements
 * 
 * @author kmuruganandham
 * 
 */
public interface Validator
{
	public boolean isValidAmount(double amount);

	public boolean isValidPercent(double percent);

	public boolean isValidTerm(int term);

	public LoanRange getRanges();
}
