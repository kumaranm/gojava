package com.mk.problem.as.pojo;

/**
 * A simple pojo class defining the loan elements
 * 
 * @author kmuruganandham
 * 
 */
public class Loan
{
	private double amount;
	private double percent;
	private int term;

	public Loan(double amount, double percent, int term) {
		this.amount = amount;
		this.percent = percent;
		this.term = term;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public double getPercent()
	{
		return percent;
	}

	public void setPercent(double percent)
	{
		this.percent = percent;
	}

	public int getTerm()
	{
		return term;
	}

	public void setTerm(int term)
	{
		this.term = term;
	}

	public long getAmountAsLong()
	{
		return Math.round(getAmount() * 100);
	}

	public int getTermInMonths()
	{
		return getTerm() * 12;
	}
}
