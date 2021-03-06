package com.thevicraft.calculator.console;

import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Log {

	// console = output on console with system time, errorLog = opens a JOptionPane
	// window with a certain message
	// input = opens a window with an input method

	public static void console(String text) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.println("[" + dtf.format(now) + "]   " + text);
	}

	public static void errorWin(String error, String log) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
		Log.console(log);
	}

	public static void errorWin(String error) {
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void errorSyntax() {
		JOptionPane.showMessageDialog(null, "Syntax Error!", "Error", JOptionPane.ERROR_MESSAGE);
		Log.console("An Error occured whilst trying to calculate." + "Try to type in correct operands or operators.");
	}

	public static String input(String message) {
		return JOptionPane.showInputDialog(null, message);
	}
}
