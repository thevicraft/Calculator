package com.thevicraft.calculator.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class CalcTaskUtil {
	static final String symbol = "+-*/^()"; // Operator
	static final String[] priority = { "+-", "*/", "^%", "()" }; // Operator priority

	/**
	 * Operator comparator
	 */
	static Comparator<String> comp = new Comparator<String>() {
		public int compare(String s1, String s2) {
			int n1 = 0, n2 = 0;
			for (int i = 0; i < priority.length; i++) {
				if (priority[i].indexOf(s1) >= 0) {
					n1 = i;
				}
				if (priority[i].indexOf(s2) >= 0) {
					n2 = i;
				}
			}
			return (n1 - n2);
		}
	};

	/**
	 * Enter string formula, return results
	 * 
	 * @param exp
	 * @return
	 * @throws Exception
	 */
	public static String getResultByStrCal(String exp) throws Exception {
		List<String> list = analyze(exp); // Infixed suffix
		double result = cacl(list); // Calculation results
		//return String.format("%.8g", result);// % .2f \ n explanation:% F - floating point .2 - two decimal points \ n -
		return Double.toString(result);										// wrap
	}

	/**
	 * Analysis Algorite
	 * 
	 * @param exp
	 * @return
	 * @throws Exception
	 */
	public static List<String> analyze(String exp) throws Exception {
		if (exp == null) {
			throw new Exception("illegal parameter.");
		}
		exp = exp.replaceAll("\\s*", ""); // Remove all spaces (in order to facilitate intermediate space calculation

		List<String> list = new ArrayList<String>();
		Stack<String> sym = new Stack<String>();

		StringBuilder buf = new StringBuilder();
		for (char c : exp.toCharArray()) {
			if (symbol.indexOf(c) >= 0) { // If it is an operator
				if (buf.length() > 0) { // If there is an operand
					String v = buf.toString();
					if (!v.matches("\\d+([.]\\d+)?")) {
						throw new Exception("illegal varaible(" + v + ").");
					}
					list.add(v);
					buf.delete(0, buf.length());
				}

				if (c == '(') {
					sym.push(String.valueOf(c));
				} else if (c == ')') {
					String last = "";
					while (sym.size() > 0) {
						last = sym.pop();
						if (last.equals("(")) {
							break;
						} else {
							list.add(last);
						}
					}
					if (!"(".equals(last)) {
						throw new Exception("illigal express.");
					}
				} else if (sym.size() > 0) {
					String s = String.valueOf(c);
					String last = sym.peek();
					if (last.equals("(") || comp.compare(s, last) > 0) {
						sym.push(s);
					} else {
						last = sym.pop();
						list.add(last);
						sym.push(s);
					}
				} else {
					sym.push(String.valueOf(c));
				}
			} else { // Not an operator as an operand (because all spaces have been removed, this no
						// longer need to judge the space)
				buf.append(c);
			}
		}

		if (buf.length() > 0) {
			list.add(buf.toString());
		}

		while (sym.size() > 0) {
			String last = sym.pop();
			if ("()".indexOf(last) >= 0) {
				throw new Exception("illigal express.");
			}
			list.add(last);
		}

		return list;
	}

	/**
	 * Calculation
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static double cacl(List<String> list) throws Exception {
		Stack<Double> val = new Stack<Double>();
		double result = 0;
		while (list.size() > 0) {
			String s = list.remove(0);
			
			boolean negative = false;
			
			if (symbol.indexOf(s) >= 0) {
				double d1 = val.pop();
				//System.out.println("op2 :"+d1);
				double d2;
				//try {
					d2 = val.pop();
				//	System.out.println("op1: "+d2);
				//} catch (Exception e) {
				//	System.out.println("minus entdeckt vor op1 "+d1);
				//	d2 = 0;
				//	negative = true;
				//}
				if ("+".equals(s)) {
					result = d2 + d1;
				} else if ("-".equals(s)) {
					//if (negative == true) {
					//	result = -d1;
					//} else {
						result = d2 - d1;
					//}
				} else if ("*".equals(s)) {
					result = d2 * d1;
				} else if ("/".equals(s)) {
					result = d2 / d1;
				} else if ("^".equals(s)) {
					result = Math.pow(d2, d1);
				} else {
					throw new Exception("illigal symbol(" + s + ").");
				}
				val.push(result);
			} else {
				if (!s.matches("\\d+([.]\\d+)?")) {
					throw new Exception("illigal variable(" + s + ").");
				}
				//if((s.equals("-")) || (negative == true)) {
					
				//}
				val.push(Double.valueOf(s));
				//System.out.println(val.lastElement());
			}
		}

		return result;
	}
}
