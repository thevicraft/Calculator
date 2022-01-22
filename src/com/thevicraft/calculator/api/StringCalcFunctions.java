package com.thevicraft.calculator.api;

import java.lang.Math;
import com.thevicraft.calculator.api.*;
import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.GuiTaschenrechner;

public class StringCalcFunctions {
	public static String calcOperator[] = { " ^ ", " / ", " * ", " + ", " - " };
	private static final String constants[] = { GuiTaschenrechner.constants[0], "ANS", GuiTaschenrechner.constants[1], "E", " -" };
	private static String checkAbleForCalc[] = { "^", "/", "*", "+", "-", "n", " -", "(", ")" };

	public String insertConstants(String task, double oldResult) {
		for (int i = 0; i <= task.length(); i++) {
			for (String constant : constants) {
				if (i + constant.length() <= task.length()) {
					if (task.substring(i, i + constant.length()).equals(constant)) {
						String insertedConstant;
						switch (constant) {
						case "\u213c":
							insertedConstant = Double.toString(Math.PI);
							break;
						case "\u212f":
							insertedConstant = Double.toString(Math.E);
							break;
						case "ANS":
							insertedConstant = Double.toString(oldResult);
							break;
						case "E":
							insertedConstant = "*10^";
							break;
						case " -":
							if (task.substring(i + 2, i + 3).equals(" ")) {
								insertedConstant = " -";
							} else {
								insertedConstant = "0-";
							}
							break;
						default:
							insertedConstant = "0";
							break;
						}
						task = task.substring(0, i) + "" + insertedConstant + ""
								+ task.substring(i + constant.length(), task.length());
					}

				}

			}
		}

		return task;
	}
	public static String insertNumberInFunction(String function, String x,String number) {
		String calcFunc = function;
		for(int i = 0; i<=calcFunc.length(); i++) {
			if(i+x.length()<=calcFunc.length()) {
				if(calcFunc.substring(i, i+x.length()).equals(x)) {
					calcFunc = calcFunc.substring(0,i)+number+calcFunc.substring(i+x.length(),calcFunc.length());
				}
			}
		}
		//System.out.println(calcFunc);
		return calcFunc;
	}

	public boolean detectOperators(String calcTask) {
		int countops = 0;
		for (String op : checkAbleForCalc) {
			if (calcTask.indexOf(op) != -1) {
				countops++;
			}
		}
		if (countops == 0) {
			return false;
		} else {
			return true;
		}
	}

}
