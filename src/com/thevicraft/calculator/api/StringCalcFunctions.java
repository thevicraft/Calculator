package com.thevicraft.calculator.api;

import com.thevicraft.calculator.gui.GuiTaschenrechner;
import com.thevicraft.calculator.gui.Images;
import com.thevicraft.calculator.gui.Images.Pictures;

/**
 * Class with specific String Calculation functions
 * 
 * @author thevicraft
 * @category Util
 */
public class StringCalcFunctions {
	public static String calcOperator[] = { " ^ ", " / ", " * ", " + ", " - " };
	private static final String constants[] = { GuiTaschenrechner.constants[0], "ANS", GuiTaschenrechner.constants[1],
			"E", " -" };

	private double storeResult = 0;
	private GuiTaschenrechner window;

	public StringCalcFunctions(GuiTaschenrechner frame) {
		this.window = frame;
	}

	/**
	 * Manages Calculation and return of result
	 * 
	 * @param task - Calculation task in String form
	 * @return result - Calculated value from String task
	 * @author thevicraft
	 */
	public double calculate(String task) {
		double result = 0;

		String calculation = insertConstants(task, storeResult);
		try {
			result = EvaluationUtil.calculate(calculation);
			window.setIconImage(Images.getDefaultImageIcon(Pictures.WINDOW_ICON).getImage());
			window.labelErgebnis.setIcon(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		if ((Double.isInfinite(result)) || (Double.isNaN(result))) {
			window.setIconImage(Images.getDefaultImageIcon(Pictures.WINDOW_ICON_WARNING).getImage());
//			window.labelErgebnis.setIcon(Images.scaleImageIconFromDefault(Pictures.WARNING,
//					(int) window.labelErgebnis.getPreferredSize().getHeight(),
//					(int) window.labelErgebnis.getPreferredSize().getHeight()));
			window.labelErgebnis.setIcon(Images.scaleImageIconFromDefault(Pictures.WARNING, 30, 30));
		}
		storeResult = result;
		return result;

	}

	/**
	 * Insert constant numbers such as pi, e, ANS into a calculation, as preparation
	 * before calculation + Changing Syntax (, to .) (; to ,)
	 * 
	 * @param task      - Calculation task in String form
	 * @param oldResult - result of the former calculation in case of "ANS" in task
	 * @author thevicraft
	 */
	public String insertConstants(String task, double oldResult) {

		// Syntax
		task = task.replaceAll(",", ".");
		task = task.replaceAll(";", ",");
		
		task = task.replaceAll(GuiTaschenrechner.constants[0], "pi");
		task = task.replaceAll(GuiTaschenrechner.constants[1], "e");
		task = task.replaceAll("ANS", Double.toString(oldResult));
		
//		char[] power = {'¹','⁻','²','³','⁴','⁵','⁶','⁷','⁸','⁹','⁰','⁺'};
//		char[] normal = {'1','-','2','3','4','5','6','7','8','9','0','+'};
//		for(int c = 0; c < power.length; c++) {
//			for(int cursor = 0; cursor < task.length(); cursor++) {
//				if(task.substring(cursor, cursor+1).equals(Character.toString(power[c]))) {
//					task.replaceFirst(Character.toString(power[c]), Character.toString(normal[c]));
//					for(char p: power) {
//						if(!task.substring(cursor-1,cursor).equals(Character.toString(p))) {
//							task = task.substring(0, cursor-1)+"^"+task.substring(cursor-1,task.length());
//						}
//					}
//				}
//			}
//		}
		return task;
	}

}
