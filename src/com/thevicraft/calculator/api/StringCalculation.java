package com.thevicraft.calculator.api;

import com.thevicraft.calculator.api.*;
import java.lang.Math;
import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.GuiTaschenrechner;
import com.thevicraft.calculator.gui.Images;
import com.thevicraft.calculator.gui.Images.Pictures;

public class StringCalculation {

	private String result;
	private double storeResult = 0;
	private GuiTaschenrechner window;

	public String calcResultFromString(String calcTask, int mode, int calcMode, String logBase, String logExponent,
			GuiTaschenrechner window) {
		double ergebnis = 0;
		this.window = window;
		calcTask = new StringCalcFunctions().insertConstants(calcTask, storeResult);
		switch (mode) {
		case 1:
			ergebnis = calcTask(calcTask, true);
			break;
		default:
			try {
				ergebnis = calcTask(calcTask, true);
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
					exponent = calcTask(exp, true);
				} catch (Exception e) {
				}
				try {
					basis = calcTask(base, true);
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

	public double calcTask(String calcTask, boolean giveError) {
		double beforeResult = 0;
		// calcTask = new StringCalcFunctions().insertConstants(calcTask, storeResult);

		if (new StringCalcFunctions().detectOperators(calcTask) == true) {
			try {
				beforeResult = Double.parseDouble(CalcTaskUtil.getResultByStrCal(calcTask));
				if (window != null) {
					window.setIconImage(Images.getDefaultImageIcon(Pictures.ICON).getImage());
				}
			} catch (Exception e) {
				if (window != null) {
					window.setIconImage(Images.getDefaultImageIcon(Pictures.ICON_WARNING).getImage());
				}
				if (giveError) {
					Log.errorSyntax();
				}
			}
		} else {
			if (window != null) {
				window.setIconImage(Images.getDefaultImageIcon(Pictures.ICON).getImage());
			}
			return Double.parseDouble(calcTask);
		}
		//System.out.println(calcTask + " = " + beforeResult);

		// storeResult = beforeResult;
		return beforeResult;
	}

}
