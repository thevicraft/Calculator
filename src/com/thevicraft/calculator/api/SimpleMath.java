package com.thevicraft.calculator.api;

import java.lang.Math;

import javax.swing.JTextField;

public class SimpleMath {

	/*public static float setConstants(JTextField field) {
		if (field.getText().equals("PI")) {
			return (float) Math.PI;
		} else if (field.getText().equals("E")) {
			return (float) Math.E;
		} else if (field.getText().equals("ANS")) {
			return (float) Calculation.getANS();
		}
		return 0;
	}

	public static boolean testForConstants(JTextField field) {
		if (field.getText().equals("PI")) {
			return true;
		} else if (field.getText().equals("E")) {
			return true;
		} else if (field.getText().equals("ANS")) {
			return true;
		}
		return false;
	}*/

	public static double logarithm(double a, double b) {
		double op1;
		double op2;
		op1 = (Math.log10(b));
		op2 = (Math.log10(a));
		return (op1 / op2);
	}

	public static double sin(double a) {
		return Math.sin(Math.toRadians(a));
	}

	public static double cos(double a) {
		return Math.cos(Math.toRadians(a));
	}

	public static double tan(double a) {
		return Math.tan(Math.toRadians(a));
	}

	public static double asin(double a) {
		return Math.toDegrees(Math.asin(a));
	}

	public static double acos(double a) {
		return Math.toDegrees(Math.acos(a));
	}

	public static double atan(double a) {
		return Math.toDegrees(Math.atan(a));
	}

	public static double power(double b, double e) {
		if (e == 0) {
			return 1;
		}
		return Math.pow(b, e);
	}

}
