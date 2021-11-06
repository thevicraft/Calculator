package com.thevicraft.calculator.api;
import com.thevicraft.calculator.api.StringObject;
import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.GuiTaschenrechner;
public class StringCalculation {
	
	private double result;
	private StringObject[] operators; // = new StringObject[2];
	private StringObject[] operands; // = new StringObject[2];
	//private static String calcOperator[][] = { { " + ", "log", "log b x" }, { " - ", " √ ", "x!" }, { " x ", "sin", "asin" },
	//		{ " / ", "cos", "acos" }, { " ^ ", "tan", "atan" } };
	private static String calcOperator[] = {" / "," * "," + "," - "};
	
	public double calcResultFromString(String calcTask) {
		//String calcTask = "log(3) + 3 x 34 + 32 / df - adf";

		for (int i = 0; i <= calcTask.length(); i++) {
			for (int ops = 0; ops <= 3; ops++) {

				if (i + calcOperator[ops].length() <= calcTask.length()) {
					if (calcTask.substring(i, i + calcOperator[ops].length()).equals(calcOperator[ops])) {
						operators[i] = new StringObject("operator");
						operators[i].setType("operator");
						operators[i].setText(calcOperator[ops]);
						operators[i].strPosFrom = i;// calcTask.substring(i).indexOf(calcOperator[ops]);
						operators[i].strPosTo = i + operators[i].getText().length();
						System.out.println(
								operators[i].strPosFrom + " |" + operators[i].getText() + " |" + operators[i].strPosTo);
					}
				}
			}
		}
		System.out.println();
		
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

			}

		}
		
		return 0/*result*/;
	}

}
