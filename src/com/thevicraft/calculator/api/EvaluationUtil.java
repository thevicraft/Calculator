package com.thevicraft.calculator.api;

import org.mariuszgromada.math.mxparser.*;

public class EvaluationUtil {
	
	public static double calculate(String task) throws Exception{
		Expression e = new Expression(task);
		double v = e.calculate();
		
		return v;
	}
	
	public static double fromExpression(Expression task) throws Exception{
		double v = task.calculate();
		
		return v;
	}
}
