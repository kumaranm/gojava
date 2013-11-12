package com.mk.problem.as.pojo;

/**
 * 
 * This class contains method to set ranges for all the loan elements
 * 
 * @author kmuruganandham
 * 
 */
public class LoanRange
{
	private Range<Double> amountRange;
	private Range<Double> percentRange;
	private Range<Integer> termRange;

	public void setAmountRange(double minValue, double maxValue)
	{
		amountRange = new Range<Double>(minValue, maxValue);
	}

	public void setPercentRange(double minValue, double maxValue)
	{
		percentRange = new Range<Double>(minValue, maxValue);
	}

	public void setTermRange(int minValue, int maxValue)
	{
		termRange = new Range<Integer>(minValue, maxValue);
	}

	public Range<Double> getAmountRange()
	{
		return amountRange;
	}

	public Range<Double> getPercentRange()
	{
		return percentRange;
	}

	public Range<Integer> getTermRange()
	{
		return termRange;
	}

}
