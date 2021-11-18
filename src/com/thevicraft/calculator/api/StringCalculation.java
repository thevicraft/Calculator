package com.thevicraft.calculator.api;

import com.thevicraft.calculator.api.*;
import java.lang.Math;
import com.thevicraft.calculator.console.Log;

public class StringCalculation {

	private String result;
	private double storeResult = 0;

	public String calcResultFromString(String calcTask, int mode, int calcMode, String logBase, String logExponent) {
		double ergebnis = 0;
		calcTask = new StringCalcFunctions().insertConstants(calcTask, storeResult);
		switch (mode) {
		case 1:
			ergebnis = calcTask(calcTask);
			break;
		default:
			try {
				ergebnis = calcTask(calcTask);
			} catch (Exception e) {
			}

			switch (calcMode) {
			case 6:
				ergebnis = Math.log10(ergebnis);
				break;
			case 7:
				ergebnis = SimpleMath.power(ergebnis, 0.5);
				break;
			case 8:
				ergebnis = SimpleMath.sin(ergebnis);
				break;
			case 9:
				ergebnis = SimpleMath.cos(ergebnis);
				break;
			case 10:
				ergebnis = SimpleMath.tan(ergebnis);
				break;
			case 11:
				String exp = new StringCalcFunctions().insertConstants(logExponent, storeResult);
				String base = new StringCalcFunctions().insertConstants(logBase, storeResult);
				double exponent = 0;
				double basis = 0;
				try {
					exponent = calcTask(exp);
				} catch (Exception e) {
				}
				try {
					basis = calcTask(base);
				} catch (Exception e) {
				}
				ergebnis = SimpleMath.logarithm(exponent, basis);
				break;
			case 12:
				break;
			case 13:
				ergebnis = SimpleMath.asin(ergebnis);
				break;
			case 14:
				ergebnis = SimpleMath.acos(ergebnis);
				break;
			case 15:
				ergebnis = SimpleMath.atan(ergebnis);
				break;
			}
			break;
		}
		storeResult = ergebnis;
		result = Double.toString(ergebnis);
		return result;
	}

	private double calcTask(String calcTask) {
		double beforeResult = 0;
		// calcTask = new StringCalcFunctions().insertConstants(calcTask, storeResult);

		if (new StringCalcFunctions().detectOperators(calcTask)==true) {
			try {
				beforeResult = Double.parseDouble(CalcTaskUtil.getResultByStrCal(calcTask));
			} catch (Exception e) {
				Log.errorSyntax();
			}
		} else {
			return Double.parseDouble(calcTask);
		}

		// storeResult = beforeResult;
		return beforeResult;
	}

}