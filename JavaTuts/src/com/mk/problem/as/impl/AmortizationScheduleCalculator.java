package com.mk.problem.as.impl;

import java.util.ArrayList;
import java.util.List;

import com.mk.problem.as.ScheduleCalculator;
import com.mk.problem.as.Validator;
import com.mk.problem.as.common.Constants;
import com.mk.problem.as.common.PrintUtility;
import com.mk.problem.as.pojo.Loan;
import com.mk.problem.as.pojo.PaymentSchedule;

/**
 * This class is responsible for calculating of the Amortization Schedule
 * 
 * @author kmuruganandham
 * 
 */
public class AmortizationScheduleCalculator extends ScheduleCalculator
{

	@Override
	public Validator getValidator()
	{
		return AmortizingLoanValidator.getInstance();
	}

	@Override
	public void getSchedule(Loan loan)
	{
		long monthlyPaymentAmount = calculateMonthlyPayment(loan);

		// the following shouldn't happen with the available valid ranges
		// for borrow amount, apr, and term; however, without range validation,
		// monthlyPaymentAmount as calculated by calculateMonthlyPayment()
		// may yield incorrect values with extreme input values
		if (monthlyPaymentAmount > loan.getAmountAsLong())
		{
			throw new IllegalArgumentException();
		}

		List<PaymentSchedule> list = calculateSchedule(loan, monthlyPaymentAmount);
		outputSchedule(list);
	}

	/**
	 * Returns the Monthly Interest rate Represents the J component for
	 * calculating MonthlyPayment
	 * 
	 * @param loan
	 * @return double
	 */
	private double getMonthlyInterest(Loan loan)
	{
		return loan.getPercent() / Constants.MONTHLY_INTEREST_DIVISOR;
	}

	/**
	 * Returns the payment amount to be done monthly
	 * 
	 * @param loan
	 * @return long
	 */
	private long calculateMonthlyPayment(Loan loan)
	{

		// this is 1 / (1 + J)
		double tmp = Math.pow(1d + getMonthlyInterest(loan), -1);

		// this is Math.pow(1/(1 + J), N)
		tmp = Math.pow(tmp, loan.getTermInMonths());

		// this is 1 / (1 - (Math.pow(1/(1 + J), N))))
		tmp = Math.pow(1d - tmp, -1);

		// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
		double rc = loan.getAmountAsLong() * getMonthlyInterest(loan) * tmp;

		return Math.round(rc);
	}

	/**
	 * Returns the PaymentSchedule as a list
	 * 
	 * @param loan
	 * @param monthlyPaymentAmount
	 * @return List<PaymentSchedule>
	 */
	private List<PaymentSchedule> calculateSchedule(Loan loan, long monthlyPaymentAmount)
	{
		List<PaymentSchedule> paymentScheduleList = new ArrayList<>();

		long balance = loan.getAmountAsLong();
		int paymentNumber = 0;
		long totalPayments = 0;
		long totalInterestPaid = 0;

		paymentScheduleList.add(new PaymentSchedule(paymentNumber++, 0d, 0d, ((double) loan.getAmountAsLong()) / 100d,
				((double) totalPayments) / 100d, ((double) totalInterestPaid) / 100d));

		final int maxNumberOfPayments = loan.getTermInMonths() + 1;
		while ((balance > 0) && (paymentNumber <= maxNumberOfPayments))
		{
			// Calculate H = P x J, this is your current monthly interest
			long curMonthlyInterest = Math.round(((double) balance) * getMonthlyInterest(loan));

			// the amount required to payoff the loan
			long curPayoffAmount = balance + curMonthlyInterest;

			// the amount to payoff the remaining balance may be less than the
			// calculated monthlyPaymentAmount
			long curMonthlyPaymentAmount = Math.min(monthlyPaymentAmount, curPayoffAmount);

			// it's possible that the calculated monthlyPaymentAmount is 0,
			// or the monthly payment only covers the interest payment - i.e. no
			// principal
			// so the last payment needs to payoff the loan
			if ((paymentNumber == maxNumberOfPayments)
					&& ((curMonthlyPaymentAmount == 0) || (curMonthlyPaymentAmount == curMonthlyInterest)))
			{
				curMonthlyPaymentAmount = curPayoffAmount;
			}

			// Calculate C = M - H, this is your monthly payment minus your
			// monthly interest,
			// so it is the amount of principal you pay for that month
			long curMonthlyPrincipalPaid = curMonthlyPaymentAmount - curMonthlyInterest;

			// Calculate Q = P - C, this is the new balance of your principal of
			// your loan.
			long curBalance = balance - curMonthlyPrincipalPaid;

			totalPayments += curMonthlyPaymentAmount;
			totalInterestPaid += curMonthlyInterest;

			paymentScheduleList.add(new PaymentSchedule(paymentNumber++, ((double) curMonthlyPaymentAmount) / 100d,
					((double) curMonthlyInterest) / 100d, ((double) curBalance) / 100d,
					((double) totalPayments) / 100d, ((double) totalInterestPaid) / 100d));

			// Set P equal to Q and go back to Step 1: You thusly loop around
			// until the value Q (and hence P) goes to zero.
			balance = curBalance;
		}
		return paymentScheduleList;
	}

	/**
	 * Prints the schedule
	 * 
	 * @param list
	 */
	private void outputSchedule(List<PaymentSchedule> list)
	{
		String formatString = "%1$-20s%2$-20s%3$-20s%4$s,%5$s,%6$s\n";
		PrintUtility.formatAndPrint(formatString, "PaymentNumber", "PaymentAmount", "PaymentInterest",
				"CurrentBalance", "TotalPayments", "TotalInterestPaid");

		for (PaymentSchedule paymentSchedule : list)
		{
			PrintUtility.formatAndPrint(formatString, paymentSchedule.getPaymentNumber(),
					paymentSchedule.getCurrentMonthlyPaymentAmount(), paymentSchedule.getCurrentMonthlyInterest(),
					paymentSchedule.getCurrentBalance(), paymentSchedule.getTotalPayments(),
					paymentSchedule.getTotalInterestPaid());
		}
	}

}
