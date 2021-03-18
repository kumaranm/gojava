package com.mk.programiz;

public class LongestPalindrome {

	/**
	 * Longest Palindrome Substring
	 * 
	 * Submitted
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {

		String longestPalindrome = "";
		for (int i = 0; i < s.length(); i++) {

			if(longestPalindrome.length() > s.length() - i){
				//if longestpalindrome already found quit loop
				break;
			}
			
			for (int j = s.length() - 1; j >= i; j--) {
				String temp = s.substring(i, j + 1);
				if(longestPalindrome.length() > temp.length()){
					//if longestpalindrome already found quit loop
					break;
				}
				if (temp.charAt(0) == (temp.charAt(temp.length()-1)) && temp.length() > longestPalindrome.length() && isPalindrome(temp)) {
					longestPalindrome = temp;
				}
			}
		}
		return longestPalindrome;
	}

	public boolean isPalindrome(String s) {
		for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
			if (!(s.charAt(i) == (s.charAt(j)))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		LongestPalindrome lp = new LongestPalindrome();
		String s = "babad";
		System.out.printf("%s -> %s", s, lp.longestPalindrome(s));

		s = "cbbdd";
		System.out.printf("\n%s -> %s", s, lp.longestPalindrome(s));

		s = "a";
		System.out.printf("\n%s -> %s", s, lp.longestPalindrome(s));

		s = "ac";
		System.out.printf("\n%s -> %s", s, lp.longestPalindrome(s));
		
		s = "uwqrvqslistiezghcxaocjbhtktayupazvowjrgexqobeymperyxtfkchujjkeefmdngfabycqzlslocjqipksz"
				+ "mihaarekosdkwvsirzxpauzqgnftcuflzyqwftwdeizwjhloqwkhevfovqwyvwcrosexhflkcudycvuelvvqlbzxo"
				+ "ajisqgwgzhioomucfmkmyaqufqggimzpvggdohgxheielsqucemxrkmmagozxhvxlwvtbbcegkvvdrgkqszgajebbobxno"
				+ "ssfrafglxvryhvyfcibfkgpbsorqprfujfgbmbctsenvbzcvypcjubsnjrjvyznbswqawodghmigdwgijfytxbgpxreyevup"
				+ "rpztmjejkaqyhppchuuytkdsteroptkouuvmkvejfunmawyuezxvxlrjulzdikvhgxajohpzrshrnngesarimyopgqydcmsaci"
				+ "egqlpqnclpwcjqmhtmtwwtbkmtnntdllqbyyhfxsjyhugnjbebtxeljytoxvqvrxygmtogndrhlcmbmgiueliyfkkcuypvvzkomjr"
				+ "fhuhhnfbxeuvssvvllgukdolffukzwqaimxkngnjnmsbvwkajyxqntsqjkjqvwxnlxwjfiaofejtjcveqstqhdzgqistxwsgrovvwgo"
				+ "rjaoosremqbzllgbgrwtqdggxnyvkivlcvnv";
		System.out.printf("\n%s -> %s", s, lp.longestPalindrome(s));
		
		s = "ibawpzhrunsgfobmenlqlxnprtgijgbeicsuoihnmcekzmvtffmlpzuwlimuuzjhkzppmpqqrfwyrjrsltkypjpc"
				+ "jffpvhtdiwjdonutobpecsiqubiusvwsyhrddqjeqqpgofifmwvmcdjixjvjxrvyabqaqumfqiiqxizmhzevh"
				+ "xutsbgzcfggyyvolwaxfcpjhfpksxvgyxhddcssnxhygzvmyxrxqizzhpluxkautjmieximoskcffimctsfzgmiht"
				+ "oxkltopwobtfjvjymtuknxmsgevkeklprcaudidywwkfuhtatpeeiewczpwiegmpjquayfleczrvzekikbaeocpcurtx"
				+ "hcsysbbsyschxtrucpcoeabkikezvrzcelfyauqjpmgeiwpzcweieeptathufkwwydiduacrplkekvegsmxnkutmyjvjft"
				+ "bowpotlkxothimgzfstcmiffcksomixeimjtuakxulphzziqxrxymvzgyhxnsscddhxygvxskpfhjpcfxawlovyyggfczgbstu"
				+ "xhvezhmzixqiiqfmuqaqbayvrxjvjxijdcmvwmfifogpqqejqddrhyswvsuibuqiscepbotunodjwidthvpffjcpjpyktlsrjryw"
				+ "frqqpmppzkhjzuumilwuzplmfftvmzkecmnhiousciebgjigtrpnxlqlnembofgsnurhzpwabi";
		System.out.printf("\n%s -> %s", s, lp.longestPalindrome(s));
	}

}
