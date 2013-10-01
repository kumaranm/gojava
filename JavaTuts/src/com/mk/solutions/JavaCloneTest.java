package com.mk.solutions;


/**
 * Simple example of overriding clone() method in Java to understand How Cloning
 * of Object works in Java.
 * 
 * @author
 */
public class JavaCloneTest
{

	public static void main(String args[])
	{

		Rectangle rec = new Rectangle(30, 60);

		Rectangle copy = null;
		try
		{
			System.out.println("Creating Copy of this object using Clone method");
			copy = rec.clone();
			System.out.println("Copy " + copy);

		} catch (CloneNotSupportedException ex)
		{
			System.err.println("Cloning is not supported for this object");
		}

		// testing properties of object returned by clone method in Java
		System.out.println("copy != rec : " + (copy != rec));
		System.out.println("copy.getClass() == rec.getClass() : " + (copy.getClass() == rec.getClass()));
		System.out.println("copy.equals(rec) : " + copy.equals(rec));

		// Updating fields in original object
		rec.setHeight(100);
		rec.setWidth(45);

		System.out.println("Original object :" + rec);
		System.out.println("Clonned object  :" + copy);
	}

}
