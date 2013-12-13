package com.mk.solutions;

class Simple
{
	public native String intern();

	public static void main(String args[])
	{

		/*String s1 = "Sachin".intern();
		String s2 = "sachin";
		// String s2 = new String("Sachin");
		String s3 = "Ratan";
//		Simple s = new Simple();
		// s.intern();

		System.out.println(s1.compareTo(s2));// 0
		System.out.println(s1.compareTo(s3));// 1(because s1>s3)
		System.out.println(s3.compareTo(s1));// -1(because s3 < s1 )
*/
		String s1 = "Rakesh";
		String s2 = "Rakesh";
		String s3 = "Rakesh".intern();
		String s4 = new String("Rakesh");
		String s5 = new String("Rakesh").intern();
		String s6 = new String("Rakesh");

		if ( s1 == s2 ){
		    System.out.println("s1 and s2 are same");  // 1.
		}

		if ( s1 == s3 ){
		    System.out.println("s1 and s3 are same" );  // 2.
		}

		if ( s1 == s4 ){
		    System.out.println("s1 and s4 are same" );  // 3.
		}

		if ( s1 == s5 ){
		    System.out.println("s1 and s5 are same" );  // 4.
		}
		if ( s4 == s5 ){
			System.out.println("s4 and s5 are same" );  // 4.
		}
		if ( s4 == s6 ){
			System.out.println("s4 and s6 are same" );  // 4.
		}
	}
}