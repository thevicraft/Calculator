package com.thevicraft.calculator.api;
import com.thevicraft.calculator.api.*;
import com.thevicraft.calculator.console.Log;

public class StringCalculation {
	
	private String result;
	private double storeResult = 0;
	//private StringObject[] operators; // = new StringObject[2];
	//private StringObject[] operands; // = new StringObject[2];
	//private static String calcOperator[][] = { { " + ", "log", "log b x" }, { " - ", " √ ", "x!" }, { " x ", "sin", "asin" },
	//		{ " / ", "cos", "acos" }, { " ^ ", "tan", "atan" } };
	//private static String calcOperator[] = {" / "," * "," + "," - "};
	
	public String calcResultFromString(String calcTask) {
		
		calcTask = new StringCalcFunctions().insertConstants(calcTask, storeResult);
		try {
		result = CalcTaskUtil.getResultByStrCal(calcTask);
		} catch(Exception e) {
			Log.errorSyntax();
		}
		
		storeResult = Double.parseDouble(result);
		return result;

	}

}
