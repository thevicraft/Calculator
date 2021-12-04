package com.thevicraft.calculator.api;

public class Numbers {
	public static String optimizeNumber(String number, boolean replacePowerTen) {
		String num;
		try {
			num = number;
			if (num.substring(num.length() - 2, num.length()).equals(".0")) {
				num = num.substring(0, num.length() - 2);
			}
			if (replacePowerTen) {
				for (int i = 0; i <= num.length(); i++) {
					try {
						if (num.substring(i, i + 1).equals("E")) {
							num = num.substring(0, i) + " * 10 ^ " + num.substring(i + 1, num.length());
						}
					} catch (Exception e) {
					}
				}
				return num;
			}
			return num;
		} catch (Exception e) {
			return Double.toString(0);
		}
	}
}
