package com.thevicraft.calculator.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class HelpWindow extends JFrame {
	public Font normal; // = new Font("Tahoma", Font.BOLD, 12);

	private static String label = "<html><body>Help Menu:<br>" + "<br>" + "DEL - delete last character<br>"
			+ "AC - delete all<br>" + "<br>"
			+ "E - * 10 ^ (times ten to the power of x; e.g.: 2E3 equals 2 * 10 ^ 3 equals 2*1000 equals 2000)<br>"
			+ "<br>" + "ANS - uses result of last calculation<br>" + "<br>"
			+ "(-) - mathematical sign (not the same as minus operator)<br>" + "<br>"
			+ "M - cycles page 1 and 2 of sin,cos,tan,asin,acos,atan,log,root...<br>" + "<br>" + "<br>" + "<br>";
	// <html><body>Textzeile1<br>Textzeile2</body></html>
	private int FRAME_WIDTH;
	private int FRAME_HEIGHT;

	private JPanel mainPanel;

	private JLabel mainLabel;

	private float sizeFactor;

	private JTable table;
	
	String data[][] = { { "+", "Operator", "Addition" }, { "-", "Operator", "Subtraction" }, { "*", "Operator", "Multiplication" },
						{ "/", "Operator", "Division" }, { "^", "Operator", "Exponentiation" }, { "!", "Operator", "Factorial" },
						{ "#", "Operator", "Modulo Function" },
						{ "sqrt()", "Function", "Square Root" },
						{ "sin()", "Function", "Sinus Function" },
						{ "cos()", "Function", "Cosinus Function" },
						{ "tan()", "Function", "Tangens Function" },
						{ "asin()", "Function", "sis⁻¹" },
						{ "acos()", "Function", "cos⁻¹" },
						{ "atan()", "Function", "tan⁻¹" },
						{ "min(a,b)", "Function", "Returns lower Number" },
						{ "max(a,b)", "Function", "Returns higher Number" },
						{ "gcd(...)", "Function", "Greatest common divisor" },
						{ "int(a,x,b,c)", "Function", "Integral" }};
	
	
	String column[] = { "Key Word", "Category", "Describtion"};

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
//		mainPanel.add(mainLabel);
		add(mainPanel);
		setLocationRelativeTo(null);
		setColorOfComponents(mode);

		JScrollPane scroller = new JScrollPane();
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroller.getViewport().add(table);
		//scroller.setPreferredSize(new Dimension(FRAME_WIDTH - 10, FRAME_HEIGHT-50));
		scroller.getViewport().setBackground(mode);
		scroller.setPreferredSize(new Dimension(FRAME_WIDTH-10, FRAME_HEIGHT-40));
		//viewport.add(mainLabel);
		//viewport.add(table);
		//viewport.setLayout(new GridLayout(2,1));
		//viewport.setPreferredSize(new Dimension(FRAME_WIDTH - 50, FRAME_HEIGHT - 10));
		mainPanel.add(scroller);
		setVisible(true);
	}

	private void initPanel() {
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT-10));

	}

	private void initComponents() {
		normal = new Font("Tahoma", Font.BOLD, (int) (8 * sizeFactor));
		mainLabel = new JLabel(label);
		mainLabel.setPreferredSize(new Dimension(FRAME_WIDTH - 20, 1500));
		mainLabel.setBackground(Color.black);
		mainLabel.setFont(normal);
		// mainLabel.setText(label);

		table = new JTable(data, column);
		table.setFocusable(false);
		table.setEnabled(false);
		table.setFont(normal);
		table.setGridColor(Color.green);
		table.setRowHeight((int)(30*sizeFactor));
		//table.sizeColumnsToFit(100);
		//table.setPreferredSize(new Dimension(FRAME_WIDTH-150, 400));
		
		table.setDragEnabled(false);
	}

	private void setColorOfComponents(Color d) {
		getContentPane().setBackground(d);
		mainPanel.setBackground(d);
		mainLabel.setBackground(d);
		table.setBackground(d);

		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		mainLabel.setForeground(d);
		table.setForeground(d);
	}
}
