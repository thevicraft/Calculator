package com.thevicraft.calculator.api;

import org.mariuszgromada.math.mxparser.*;

/**
 * Usage of mxparser lib
 * @see GuiTaschenrechner
 * @author thevicraft
 * @category Util
 * */
public class EvaluationUtil {
	
	/**
	 * returns value from calculation result
	 * @param task - must include fully calculatable task without constant values (eg. pi, e, ans)!
	 * @return v - result
	 * @see GuiTaschenrechner
	 * @author thevicraft
	 * */
	public static double calculate(String task) throws Exception{
		Expression e = new Expression(task);
		double v = e.calculate();
		
		return v;
	}
	/**
	 * returns value from calculation expression
	 * @param task - calculation task in Expression format of mxparser
	 * @return v - result
	 * @see GuiTaschenrechner
	 * @author thevicraft
	 * */
	public static double fromExpression(Expression task) throws Exception{
		double v = task.calculate();
		
		return v;
	}
}
