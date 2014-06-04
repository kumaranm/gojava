package com.mk.solutions;

public class Singleton
{
	private volatile static Singleton instance;

	private static final Singleton sinstance = new Singleton();
	
	private Singleton() {

	}

	/**
	 * Static instance
	 * 
	 * @return
	 */
	public static Singleton getInstanceStatic()
	{
		return sinstance;
	}
	
	/**
	 * Creates multiple instances if 2 threads access this method simultaneously
	 * 
	 * @return
	 */
	public static Singleton getInstance()
	{
		if (instance == null)
		{
			instance = new Singleton();
		}
		return instance;
	}

	/**
	 * Thread safe and only creates one instance on concurrent env but expensive
	 * due to cost of sync in every call
	 * 
	 * @return
	 */
	public static synchronized Singleton getInstanceTS()
	{

		if (instance == null)
		{
			instance = new Singleton();
		}
		return instance;
	}

	/**
	 * Impl of double checked locking of singleton Intention is to minimize cost
	 * of synchronization and improve performance, by only locking critical
	 * section of code, the code which creates instance of Singleton class. By
	 * the way this is still broken, if we don't make _instance volatile, as
	 * another thread can see a half initialized instance of Singleton.
	 * 
	 * Volatile - HAPPENS BEFORE guarantee
	 * 
	 * @return
	 */
	public static Singleton getInstanceDLS()
	{
		if (instance == null)
		{
			synchronized (Singleton.class)
			{
				if (instance == null)
				{
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Enum based Singleton thread ssafe
	 *
	 */
	public enum EasySingleton{
	    INSTANCE;
	}
}


