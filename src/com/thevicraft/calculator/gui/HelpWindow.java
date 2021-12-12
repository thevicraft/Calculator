package com.thevicraft.calculator.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class HelpWindow extends JFrame {
	public Font normal; // = new Font("Tahoma", Font.BOLD, 12);

	private static String label = "<html><body>Help Menu:<br>"
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
			+ "\u212f - mathematical constant 2.71...<br>"
			+ "<br>"
			+ "(-) - mathematical sign (not the same as minus operator)<br>"
			+ "<br>"
			+ "( - bracket open<br>"
			+ ") - bracket close<br>"
			+ "<br>"
			+ "M - cycles page 1 and 2 of sin,cos,tan,asin,acos,atan,log,root...<br>"
			+ "<br>"
			+ "^2 - to the power of 2<br>"
			+ "^3 - to the power of 3<br>"
			+ "^(-1) - to the power of -1<br>"
			+ "<br>";
	// <html><body>Textzeile1<br>Textzeile2</body></html>
	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;

	private JPanel mainPanel;

	private JLabel mainLabel;

	private float sizeFactor;

	public HelpWindow(String titel, int width, int height, float factor, Color mode) {
		FRAME_HEIGHT = (int) (height * factor);
		FRAME_WIDTH = (int) (width * factor);
		sizeFactor = factor;
		setTitle(titel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		initPanel();
		initComponents();
		mainPanel.add(mainLabel);
		add(mainPanel);
		setLocationRelativeTo(null);
		setColorOfComponents(mode);
		setVisible(true);
	}

	private void initPanel() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

	}

	private void initComponents() {
		normal = new Font("Tahoma", Font.BOLD, (int) (12 * sizeFactor));
		mainLabel = new JLabel();
		mainLabel.setPreferredSize(new Dimension(FRAME_WIDTH - 10, FRAME_HEIGHT - 10));
		mainLabel.setBackground(Color.black);
		mainLabel.setFont(normal);
		mainLabel.setText(label);
	}
	private void setColorOfComponents(Color d){
		getContentPane().setBackground(d);
		mainPanel.setBackground(d);
		mainLabel.setBackground(d);
		
		if(d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		}else {
			d = GuiTaschenrechner.dark;
		}
		mainLabel.setForeground(d);
		
	}
}
