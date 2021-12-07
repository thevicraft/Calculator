package com.thevicraft.calculator.api;

import javax.swing.JOptionPane;

import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.Images;
import com.thevicraft.calculator.gui.Images.Pictures;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Calculation extends SimpleMath {
	// GuiTaschenrechner tr = new GuiTaschenrechner("Calculator XX");

	private float op1;
	private float op2;
	private float resultErgebnisStorage;
	private boolean valid_ergebnis;
	private float ans;
	// Images
	// -------------------------------------------------------------------------------------------------------
	// ImageIcon icon = new ImageIcon("src/resources/window-icon.png");
	// ImageIcon iconWarning = new
	// ImageIcon("src/resources/window-icon-warning.png");

	public float tryConvertNumber(JTextField field, JFrame window) throws Exception {
		try {
			Float.parseFloat(field.getText());
		} catch (Exception e) {
			// window.setIconImage(iconWarning.getImage());
			window.setIconImage(Images.getDefaultImageIcon(Pictures.WINDOW_ICON_WARNING).getImage());
			Log.errorSyntax();
			field.setCaretColor(Color.red);
			field.setForeground(Color.red);
			return 0;
		}
		field.setCaretColor(Color.black);
		field.setForeground(Color.black);
		// window.setIconImage(icon.getImage());
		window.setIconImage(Images.getDefaultImageIcon(Pictures.WINDOW_ICON).getImage());
		return Float.parseFloat(field.getText());
	}

	public String calc(JFrame window, JTextField opfield1, JTextField opfield2, int mode, int calcMode) throws Exception {

		switch (mode) {
		// -------------------------------------------------------------------------------------------------------
		case 1:
			if (testForConstants(opfield1) == true) {
				op1 = setConstants(opfield1);
			} else {

				op1 = tryConvertNumber(opfield1, window); // Float.parseFloat(opfield1.getText());
			}

			if (testForConstants(opfield2) == true) {
				op2 = setConstants(opfield2);
			} else {
				op2 = tryConvertNumber(opfield2, window);
				// op2 = Float.parseFloat(opfield2.getText());
			}

			break;
		// -------------------------------------------------------------------------------------------------------

		case 2:
			if (testForConstants(opfield2) == true) {
				op2 = setConstants(opfield2);
			} else {

				op2 = tryConvertNumber(opfield2, window);
				// op2 = Float.parseFloat(opfield2.getText());
			}

			op1 = 0;
			break;
		// -------------------------------------------------------------------------------------------------------
		case 3:
			if (testForConstants(opfield2) == true) {
				op2 = setConstants(opfield2);
			} else {

				op2 = tryConvertNumber(opfield2, window);
				// op2 = Float.parseFloat(opfield2.getText());
			}

			op1 = 0;
			break;
		}
		// -------------------------------------------------------------------------------------------------------

		switch (calcMode) {

		case 1:
			resultErgebnisStorage = (float) (op1 + op2);
			valid_ergebnis = true;// = true;
			break;
		case 2:
			resultErgebnisStorage = (float) (op1 - op2);
			valid_ergebnis = true;
			;
			break;

		case 3:
			resultErgebnisStorage = (float) (op1 * op2);
			valid_ergebnis = true;
			break;
		case 4:
			resultErgebnisStorage = (float) (op1 / op2);
			valid_ergebnis = true;

			if (op2 == 0) {
				valid_ergebnis = false;
				String fehler01;
				fehler01 = "Stell dir vor du verteilst";
				String fehler02;
				fehler02 = "Kekse gleichmäßig auf 0 Freunde. Siehst du, das ergibt keinen Sinn. "
						+ "Und das Krümmelmonster ist traurig weil es nichts abbekommen hat, "
						+ "und du bist traurig weil du Freund*innen hast, die nicht existieren.";
				JOptionPane.showMessageDialog(null, fehler01 + " " + op1 + " " + fehler02, "Infinity",
						JOptionPane.WARNING_MESSAGE);
				Log.console("An error occured whilst trying to calculate.");
				Log.console("Division through number 0 is not defined.");
			}
			break;
		case 5:
			resultErgebnisStorage = (float) power((double) op1, (double) op2);
			break;
		case 6:
			resultErgebnisStorage = (float) Math.log10((double) op2);
			break;
		case 7:
			resultErgebnisStorage = (float) power((double) op2, 0.5);
			break;
		case 8:
			resultErgebnisStorage = (float) sin((double) op2);
			break;
		case 9:
			resultErgebnisStorage = (float) cos((double) op2);
			break;
		case 10:
			resultErgebnisStorage = (float) tan((double) op2);
			break;
		case 11:
			if (testForConstants(opfield1) == true) {
				op1 = setConstants(opfield1);
			} else {
				op1 = tryConvertNumber(opfield1, window);
				// op1 = Float.parseFloat(opfield1.getText());
			}

			if (testForConstants(opfield2) == true) {
				op2 = setConstants(opfield2);
			} else {
				op2 = tryConvertNumber(opfield2, window);
				// op2 = Float.parseFloat(opfield2.getText());
			}
			resultErgebnisStorage = (float) (logarithm((double) op1, (double) op2));
			break;
		case 12:

			break;
		case 13:
			resultErgebnisStorage = (float) asin(op2);
			break;
		case 14:
			resultErgebnisStorage = (float) acos(op2);
			break;
		case 15:
			resultErgebnisStorage = (float) atan(op2);
			break;

		default:
			valid_ergebnis = false;
			resultErgebnisStorage = 0;
			Log.errorSyntax();
			break;
		}
		if (valid_ergebnis != false) {
			// Log.console("Calculation went successfully.");
		}

		// int ergebnisLeere;
		String ergebnisString;
		ergebnisString = "";
		String resultSpaceHolder = "";
		if (Float.toString(resultErgebnisStorage).length()<5) {
			for(int i = 1; i <= (7-Float.toString(resultErgebnisStorage).length()); i ++) {
			resultSpaceHolder = resultSpaceHolder + " ";
			}
		}
		ergebnisString = " = "  + resultSpaceHolder  + Float.toString(resultErgebnisStorage);
		// ------------------------------------------------------------------------------------------------
		ans = resultErgebnisStorage;
		return ergebnisString;

	}

	public float setConstants(JTextField field) {
		switch (field.getText()) {
		case "PI":
			return (float) Math.PI;
		case "E":
			return (float) Math.E;
		case "ANS":
			return ans;
		default:
			return 0;
		}
	}

	public boolean testForConstants(JTextField field) {

		switch (field.getText()) {
		case "PI":
			return true;
		case "E":
			return true;
		case "ANS":
			return true;
		default:
			return false;
		}
	}

}
