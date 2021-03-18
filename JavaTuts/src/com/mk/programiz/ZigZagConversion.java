package com.mk.programiz;

public class ZigZagConversion {

	public String convert(String s, int numRows) {

		char[] output = new char[s.length()];

		if (numRows == 0) {
			return "";
		}
		if (numRows == 1) {
			return s;
		}

		int factor = 2 * numRows - 2;
		output[0] = s.charAt(0);
		
		for (int index = 0; index < s.length(); index++) {
			int loc = index + factor;
			if(index == 0){
				index = loc = 0;
			}
			output[index] = s.charAt(loc);
		}

		return String.valueOf(output);
	}
	
	public String convert1(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

	public static void main(String[] args) {

		ZigZagConversion c = new ZigZagConversion();

		String s = "PAYPALISHIRING";
		System.out.printf("%s -> %s", s, c.convert(s, 2));
		System.out.printf("\n%s -> %s", s, c.convert(s, 3));
	}

}
