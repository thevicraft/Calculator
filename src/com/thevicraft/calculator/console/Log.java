package com.thevicraft.calculator.console;

import javax.swing.JOptionPane;


public class Log {
	public static void console(String text) {
		System.out.println(text);
	}
	
	
	public static void errorLog() {
		JOptionPane.showMessageDialog(null, "Huch! Bei der Berechnung trat ein Fehler auf!", "Error",
				JOptionPane.ERROR_MESSAGE);
		Log.console("An Error occured whilst trying to calculate.");
		Log.console("Try to type in correct operands or operators.");
	}
}
