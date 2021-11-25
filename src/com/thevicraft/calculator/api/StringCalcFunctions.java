package com.thevicraft.calculator.api;

import java.lang.Math;
import com.thevicraft.calculator.api.*;
import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.GuiTaschenrechner;

public class StringCalcFunctions {
	private StringObject[] operators = new StringObject[100]; // = new StringObject[2];
	private StringObject[] operatorsRaw = new StringObject[100]; // = new StringObject[2];
	private StringObject[] operands = new StringObject[100]; // = new StringObject[2];
	public static String calcOperator[] = { " ^ ", " / ", " * ", " + ", " - " };
	private static final String constants[] = { GuiTaschenrechner.constants[0], "ANS", GuiTaschenrechner.constants[1], "E", " -" };
	private static String checkAbleForCalc[] = { "^", "/", "*", "+", "-", "n", " -", "(", ")" };
	private StringObject lastStore;

	public String insertConstants(String task, double oldResult) {
		for (int i = 0; i <= task.length(); i++) {
			for (String constant : constants) {
				if (i + constant.length() <= task.length()) {
					if (task.substring(i, i + constant.length()).equals(constant)) {
						String insertedConstant;
						switch (constant) {
						case "\u03c0":
							insertedConstant = Double.toString(Math.PI);
							break;
						case "\u2107":
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
			if(i+1<=calcFunc.length()) {
				if(calcFunc.substring(i, i+1).equals(x)) {
					calcFunc = calcFunc.substring(0,i)+number+calcFunc.substring(i+1,calcFunc.length());
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

	public void extractOperatorsFromString(String calcTask) {
		for (int i = 0; i <= calcTask.length(); i++) {
			for (int ops = 0; ops <= 4; ops++) {

				if (i + calcOperator[ops].length() <= calcTask.length()) {
					if (calcTask.substring(i, i + calcOperator[ops].length()).equals(calcOperator[ops])) {
						operatorsRaw[i] = new StringObject("operator");
						operatorsRaw[i].setType("operator");
						operatorsRaw[i].setText(calcOperator[ops]);
						operatorsRaw[i].strPosFrom = i;// calcTask.substring(i).indexOf(calcOperator[ops]);
						operatorsRaw[i].strPosTo = i + operatorsRaw[i].getText().length();
						// System.out.println(operatorsRaw[i].strPosFrom + "| " +
						// operatorsRaw[i].getText() + " |"
						// + operatorsRaw[i].strPosTo + " with index of " + i);
					}
				}
			}
		}
		int countOperators = 0;
		for (StringObject w : operatorsRaw) {
			if (w != null) {
				operators[countOperators] = w;
				operators[countOperators].setType(w.getType());
				operators[countOperators].setText(w.getText());
				operators[countOperators].strPosFrom = w.strPosFrom;
				operators[countOperators].strPosTo = w.strPosTo;
				// System.out.println(w.strPosFrom+" "+w.strPosTo);
				System.out.println(operators[countOperators].strPosFrom + "| " + operators[countOperators].getText()
						+ " |" + operators[countOperators].strPosTo + " with index of " + countOperators);
				countOperators++;
			}
		}
	}

	public void extractOperandsFromString(String calcTask) {
		int countOperands = 0;
		int firstPos = 0;
		for (StringObject e : operators) {
			if (e != null) {
				operands[countOperands] = new StringObject("operand");
				operands[countOperands].strPosFrom = firstPos;
				operands[countOperands].strPosTo = e.strPosFrom;
				operands[countOperands].setText(
						calcTask.substring(operands[countOperands].strPosFrom, operands[countOperands].strPosTo));
				firstPos = e.strPosTo;
				System.out.println(operands[countOperands].strPosFrom + "| " + operands[countOperands].getText() + " |"
						+ operands[countOperands].strPosTo);
				countOperands++;
				lastStore = e;

			} else if (e == null) {
				operands[countOperands].strPosFrom = lastStore.strPosTo;
				operands[countOperands].strPosTo = operands[countOperands].strPosFrom + calcTask.length();
			}

		}
	}

}
