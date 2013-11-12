package com.mk.problem.as.pojo;

/**
 * A simple pojo class defining the payment
 * 
 * @author kmuruganandham
 * 
 */
public class PaymentSchedule
{
	private long paymentNumber;
	private double currentMonthlyPaymentAmount;
	private double currentMonthlyInterest;
	private double currentBalance;
	private double totalPayments;
	private double totalInterestPaid;

	public PaymentSchedule(long paymentNumber, double currentMonthlyPaymentAmount, double currentMonthlyInterest,
			double currentBalance, double totalPayments, double totalInterestPaid) {
		this.paymentNumber = paymentNumber;
		this.currentMonthlyPaymentAmount = currentMonthlyPaymentAmount;
		this.currentMonthlyInterest = currentMonthlyInterest;
		this.currentBalance = currentBalance;
		this.totalPayments = totalPayments;
		this.totalInterestPaid = totalInterestPaid;
	}

	public long getPaymentNumber()
	{
		return paymentNumber;
	}

	public void setPaymentNumber(long paymentNumber)
	{
		this.paymentNumber = paymentNumber;
	}

	public double getCurrentMonthlyPaymentAmount()
	{
		return currentMonthlyPaymentAmount;
	}

	public void setCurrentMonthlyPaymentAmount(double currentMonthlyPaymentAmount)
	{
		this.currentMonthlyPaymentAmount = currentMonthlyPaymentAmount;
	}

	public double getCurrentMonthlyInterest()
	{
		return currentMonthlyInterest;
	}

	public void setCurrentMonthlyInterest(double currentMonthlyInterest)
	{
		this.currentMonthlyInterest = currentMonthlyInterest;
	}

	public double getCurrentBalance()
	{
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance)
	{
		this.currentBalance = currentBalance;
	}

	public double getTotalPayments()
	{
		return totalPayments;
	}

	public void setTotalPayments(double totalPayments)
	{
		this.totalPayments = totalPayments;
	}

	public double getTotalInterestPaid()
	{
		return totalInterestPaid;
	}

	public void setTotalInterestPaid(double totalInterestPaid)
	{
		this.totalInterestPaid = totalInterestPaid;
	}

}
