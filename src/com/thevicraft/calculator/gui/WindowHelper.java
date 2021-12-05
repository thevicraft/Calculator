package com.thevicraft.calculator.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public interface WindowHelper {
	Font normal = null; // = new Font("Tahoma", Font.BOLD, 12);

	static String label = "<html><body>Help Menu:<br>"
			+ "<br>"
			+ "DEL - delete last character<br>"
			+ "AC - delete all<br>"
			+ "<br>"
			+ "+ - addition<br>"
			+ "- - subtraction<br>"
			+ "* - multiplication<br>"
			+ "/ - dividation<br>"
			+ "<br>"
			+ "^ - power<br>"
			+ "E - * 10 ^ (times ten to the power of x; e.g.: 2E3 equals 2*10^3 equals 2*1000 equals 2000)<br>"
			+ "<br>"
			+ "ANS - uses result of last calculation<br>"
			+ "<br>"
			+ "= - calculates result<br>"
			+ "<br>"
			+ "\u03c0 - mathematical constant 3.14...<br>"
			+ "\u2107 - mathematical constant 2.71...<br>"
			+ "<br>"
			+ "(-) - mathematical sign (not the same as minus operator)<br>"
			+ "<br>"
			+ "( - bracket open<br>"
			+ ") - bracket close<br>"
			+ "<br>"
			+ "M - changes calculation mode to types like sin, cos, tan, asin, acos, atan, log, root, ...<br>"
			+ "<br>"
			+ "^2 - to the power of 2<br>"
			+ "^3 - to the power of 3<br>"
			+ "^(-1) - to the power of -1<br>"
			+ "<br>";
	// <html><body>Textzeile1<br>Textzeile2</body></html>
	int FRAME_WIDTH = 0;
	int FRAME_HEIGHT = 0;

	JPanel mainPanel = null;

	JLabel mainLabel = null;

	float sizeFactor = 0;

	void initPanel();

	void initComponents();
	void setColorOfComponents(Color d);
}
