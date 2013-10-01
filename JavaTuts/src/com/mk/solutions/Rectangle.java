package com.mk.solutions;

/*
 * 1. class must implement java.lang.Cloneable interface else
 * CloneNotSupportedException 
 * 2. override protected clone() method from
 * java.lang.Object 
 * 3. No constructor is called during cloning of Object 
 * 4. Default implementation of clone() method in Java provides "shallow copy" of object,
 * "shallow copy"
 * because it creates copy of Object by creating new instance and then copying
 * content by assignment, which means if your Class contains a mutable field,
 * then both original object and clone will refer to same internal object
 * "deep copy"
 *  Even mutable field must be different
 * 
 */
public class Rectangle implements Cloneable
{
	private int width;
	private int height;

	public Rectangle(int w, int h) {
		width = w;
		height = h;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int area()
	{
		return width * height;
	}

	@Override
	public String toString()
	{
		return String.format("Rectangle [width: %d, height: %d, area: %d]", width, height, area());
	}

	@Override
	protected Rectangle clone() throws CloneNotSupportedException
	{
		return (Rectangle) super.clone();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final Rectangle other = (Rectangle) obj;
		if (this.width != other.width)
		{
			return false;
		}
		if (this.height != other.height)
		{
			return false;
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = hash + this.width;
		hash = hash + this.height;
		return hash;
	}

}
