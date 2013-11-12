/**
 * 
 */
package com.mk.problem.as.pojo;

/**
 * This class is used to represent the min and max values values
 * 
 * @author kmuruganandham
 * @param <T>
 */
public class Range<T>
{

	private T minValue;
	private T maxValue;

	public Range(T minValue, T maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public T getMinValue()
	{
		return minValue;
	}

	public T getMaxValue()
	{
		return maxValue;
	}

}
