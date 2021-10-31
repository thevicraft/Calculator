package com.thevicraft.calculator.console;

import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;


public class Log {
	

	public static void console(String text) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.println("["+dtf.format(now)+"]   "+text);
	}
	
	
	public static void errorLog() {
		JOptionPane.showMessageDialog(null, "Huch! Bei der Berechnung trat ein Fehler auf!", "Error",
				JOptionPane.ERROR_MESSAGE);
		Log.console("An Error occured whilst trying to calculate.");
		Log.console("Try to type in correct operands or operators.");
	}
}
