package com.mk.problem.as.common;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * This class defines all the constants used
 * 
 * @author kmuruganandham
 * 
 */
public class Constants
{
	public static final double MONTHLY_INTEREST_DIVISOR = 12d * 100d;
	public static final int INITIAL_TERM_MONTHS = 0;

	public static final double[] BORROW_AMOUNT_RANGE = new double[] { 0.01d, 1000000000000d };
	public static final double[] APR_RANGE = new double[] { 0.000001d, 100d };
	public static final int[] TERM_RANGE = new int[] { 1, 1000000 };

	public static final double APR_MIN_RANGE = 0.000001d;
	public static final double APR_MAX_RANGE = 100d;

	public static final double BORROW_AMOUNT_MIN_RANGE = 0.01d;
	public static final double BORROW_AMOUNT_MAX_RANGE = 1000000000000d;

	public static final int TERM_MIN_RANGE = 1;
	public static final int TERM_MAX_RANGE = 1000000;

	public static final String BORROW_AMOUNT = "BorrowAmount";
	public static final String APR = "Apr";
	public static final String TERM = "Term";

	public static HashMap<String, String> INPUT_QUESTIONS = new LinkedHashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2054296645436380813L;

		{
			put(BORROW_AMOUNT, "Please enter the amount you would like to borrow: ");
			put(APR, "Please enter the annual percentage rate used to repay the loan: ");
			put(TERM, "Please enter the term, in years, over which the loan is repaid: ");
		}
	};
}
