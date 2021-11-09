package com.thevicraft.calculator.api;
import com.thevicraft.calculator.api.*;
import java.lang.Math;
import com.thevicraft.calculator.console.Log;

public class StringCalculation {
	
	private String result;
	private double storeResult = 0;
	//private StringObject[] operators; // = new StringObject[2];
	//private StringObject[] operands; // = new StringObject[2];
	//private static String calcOperator[][] = { { " + ", "log", "log b x" }, { " - ", " √ ", "x!" }, { " x ", "sin", "asin" },
	//		{ " / ", "cos", "acos" }, { " ^ ", "tan", "atan" } };
	//private static String calcOperator[] = {" / "," * "," + "," - "};
	
	public String calcResultFromString(String calcTask, int mode, int calcMode) {
		double ergebnis;
		switch(mode) {
		case 1:
			ergebnis = calcTask(calcTask);
			ergebnis = ergebnis + 0;	// dummer befehl ohne sinn xD
			break;
		default:
			ergebnis = Double.parseDouble(calcTask);
			switch(calcMode) {
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
				Log.errorLog("feature not ready yet");
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
		result = Double.toString(ergebnis);
		return result;
	}
	
	private double calcTask(String calcTask) {
		double beforeResult = 0;
		calcTask = new StringCalcFunctions().insertConstants(calcTask, storeResult);
		try {
		beforeResult = Double.parseDouble(CalcTaskUtil.getResultByStrCal(calcTask));
		} catch(Exception e) {
			Log.errorSyntax();
		}
		storeResult = beforeResult;
		return beforeResult;
	}

}
