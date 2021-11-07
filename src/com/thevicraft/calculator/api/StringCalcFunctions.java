package com.thevicraft.calculator.api;

import java.lang.Math;
import com.thevicraft.calculator.api.*;

public class StringCalcFunctions {
	private StringObject[] operators = new StringObject[100]; // = new StringObject[2];
	private StringObject[] operatorsRaw = new StringObject[100]; // = new StringObject[2];
	private StringObject[] operands = new StringObject[100]; // = new StringObject[2];
	private static String calcOperator[] = { " ^ ", " / ", " x ", " + ", " - " };
	private static String constants[] = { "PI", "ANS", "E" };
	private StringObject lastStore;

	public String insertConstants(String task, double oldResult) {
		for (int i = 0; i <= task.length(); i++) {
			for (String constant : constants) {
				if (i + constant.length() <= task.length()) {
					if (task.substring(i, i + constant.length()).equals(constant)) {
						double insertedConstant;
						switch (constant) {
						case "PI":
							insertedConstant = Math.PI;
							break;
						case "E":
							insertedConstant = Math.E;
							break;
						case "ANS":
							insertedConstant = oldResult;
							break;
						default:
							insertedConstant = 0;
							break;
						}
						task = task.substring(0, i) + "" + Double.toString(insertedConstant) + ""
								+ task.substring(i + constant.length(), task.length());

					}

				}

			}
		}

		return task;
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
