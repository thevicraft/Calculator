package com.thevicraft.calculator.gui;

import com.thevicraft.calculator.api.Calculation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class GuiTaschenrechner extends JFrame {
	private String platz = "                                                 ";

	public static int max_ergebnis_length = 48;
	private int mode = 1; // default mode, do not change
	private static final int MODES = 3;
	protected int calcMode;

	protected JLabel labelErgebnis;

	protected JTextField fieldOperand1;
	protected JTextField fieldOperator; // neu für operatorfeld
	protected JTextField fieldOperand2;

	protected JButton buttonErgebnis;
	protected JButton buttonDelete;
	protected JButton buttonPlus;
	protected JButton buttonMinus;

	protected JButton buttonTimes;
	protected JButton buttonDivide;
	protected JButton buttonPow;
	protected JButton buttonChangeMode;



	private String language;

	protected float ergebnis;
	
	// Fonts -----------------------------------------------------------------------------------------------------------
	public static Font bold = new Font("Tahoma", Font.BOLD,12);
	
	
	// -----------------------------------------------------------------------------------------------------------------
	Calculation calc = new Calculation();

	public static String textButtons[][] = { { "+", "log", "log b x" }, { "-", " √ ", "x!" }, { "*", "sin", "asin" },
			{ "/", "cos", "acos" }, { "^", "tan", "atan" } };

	public GuiTaschenrechner(String titel/* , String operator */) {
		/*{
			boolean lang1;
			boolean lang2;
			do {
				setLanguage(Log.input("Select a language: [en; de]"));
				lang1 = getLanguage().equals("de");
				lang2 = getLanguage().equals("en");
			} while ((lang1 == false) & (lang2 == false));
		}*/
		setTitle(titel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		setSize(250, 170);
		setResizable(false);

		initComponents();

		add(fieldOperand1);
		// add(labelOperator);
		add(fieldOperator);
		add(fieldOperand2);

		add(buttonPlus);
		add(buttonMinus);
		add(buttonTimes);
		add(buttonDivide);
		add(buttonPow);

		add(labelErgebnis);
		add(buttonErgebnis);
		add(buttonDelete);
		add(buttonChangeMode);
		
		fieldOperator.setEditable(false);
		fieldOperator.setBackground(Color.LIGHT_GRAY);
		labelErgebnis.setFont(bold);
		

		setLocationRelativeTo(null);

		//this.setIconImage(icon.getImage());
		this.setIconImage(Images.image(Images.ICON));

		setVisible(true);
	}

	private void initComponents() {

		labelErgebnis = new JLabel(platz);

		fieldOperand1 = new JTextField(8);
		fieldOperator = new JTextField(1);
		fieldOperand2 = new JTextField(8);

		buttonErgebnis = new JButton("   =   ");
		buttonErgebnis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				labelErgebnis.setText(calc.calc(GuiTaschenrechner.this, fieldOperand1, fieldOperand2, mode, calcMode));
				

			}
		});
		buttonDelete = new JButton("C");
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				labelErgebnis.setText(platz);
				switch (mode) {
				case 1:
					fieldOperand1.setText("");
					break;
				case 2:
					break;
				}
				fieldOperand2.setText("");
			}
		});
		buttonPlus = new JButton("+");
		buttonMinus = new JButton("-");
		buttonTimes = new JButton("*");
		buttonDivide = new JButton("/");
		buttonPow = new JButton("^");

		buttonPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActionOnPressed(buttonPlus);
			}
		});
		buttonMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActionOnPressed(buttonMinus);
			}
		});
		buttonTimes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActionOnPressed(buttonTimes);
			}
		});
		buttonDivide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActionOnPressed(buttonDivide);
			}
		});
		buttonPow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonActionOnPressed(buttonPow);
			}
		});

		buttonChangeMode = new JButton("MODE");
		buttonChangeMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode++;
				if (mode > MODES) {
					mode = 1;
				}
				switch (mode) {
				case 1:
					// fieldOperand1.setText("");
					fieldOperand1.setEnabled(true);
					fieldOperator.setEnabled(true);
					// op1 = 0;
					// op2 = 0;
					buttonMinus.setEnabled(true);
					break;
				case 2:
					fieldOperand1.setEnabled(false);
					fieldOperator.setEnabled(false);
					buttonMinus.setEnabled(true);
					// op1 = 0;
					// op2 = 0;
					// fieldOperand1.setText("");
					// fieldOperator.setText("");
					break;
				case 3:
					fieldOperand1.setEnabled(false);
					fieldOperator.setEnabled(false);
					buttonMinus.setEnabled(false);
					// op1 = 0;
					// op2 = 0;
					// fieldOperand1.setText("");
					// fieldOperator.setText("");
					break;
				default:
					break;
				}
				buttonPlus.setText(textButtons[0][mode - 1]);
				buttonMinus.setText(textButtons[1][mode - 1]);
				buttonTimes.setText(textButtons[2][mode - 1]);
				buttonDivide.setText(textButtons[3][mode - 1]);
				buttonPow.setText(textButtons[4][mode - 1]);

				fieldOperand1.setText("");
				fieldOperand2.setText("");
				fieldOperator.setText("");
			}
		});

	}

	public void buttonsRemoveSelection() {
		buttonPlus.setText(textButtons[0][mode - 1]);
		buttonMinus.setText(textButtons[1][mode - 1]);
		buttonTimes.setText(textButtons[2][mode - 1]);
		buttonDivide.setText(textButtons[3][mode - 1]);
		buttonPow.setText(textButtons[4][mode - 1]);
	}

	public void buttonSetSelection(JButton button) {
		if (mode != 1) {
			button.setText("[" + button.getText() + "]");
		}
	}

	public void buttonActionOnPressed(JButton button) {
		buttonsRemoveSelection();
		switch (mode) {
		case 1:
			if (button.equals(buttonPlus)) {
				calcMode = 1;
			} else if (button.equals(buttonMinus)) {
				calcMode = 2;
			} else if (button.equals(buttonTimes)) {
				calcMode = 3;
			} else if (button.equals(buttonDivide)) {
				calcMode = 4;
			} else if (button.equals(buttonPow)) {
				calcMode = 5;
			}
			fieldOperator.setText(button.getText());
			break;
		case 2:

			if (button.equals(buttonPlus)) {
				calcMode = 6;
			} else if (button.equals(buttonMinus)) {
				calcMode = 7;
			} else if (button.equals(buttonTimes)) {
				calcMode = 8;
			} else if (button.equals(buttonDivide)) {
				calcMode = 9;
			} else if (button.equals(buttonPow)) {
				calcMode = 10;
			}
			fieldOperand1.setText(button.getText());
			break;
		case 3:
			fieldOperand1.setText(button.getText());
			if (button.equals(buttonPlus)) {
				fieldOperand1.setEnabled(true);
				fieldOperand1.setText("");
				calcMode = 11;
			} else if (button.equals(buttonMinus)) {
				calcMode = 12;
				fieldOperand1.setEnabled(false);
			} else if (button.equals(buttonTimes)) {
				calcMode = 13;
				fieldOperand1.setEnabled(false);
			} else if (button.equals(buttonDivide)) {
				calcMode = 14;
				fieldOperand1.setEnabled(false);
			} else if (button.equals(buttonPow)) {
				calcMode = 15;
				fieldOperand1.setEnabled(false);
			}

			break;
		}
		buttonSetSelection(button);
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
