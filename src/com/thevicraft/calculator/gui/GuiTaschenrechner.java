package com.thevicraft.calculator.gui;
import com.thevicraft.calculator.api.Calculation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.thevicraft.calculator.console.Log;

@SuppressWarnings("serial")
public class GuiTaschenrechner extends JFrame {
	private String platz = "                                                 ";
	//private int ergebnisLeere;
	public static int max_ergebnis_length = 48;
	private int mode = 1; // default mode, do not change
	private static final int MODES = 3;
	protected int calcMode;
	/*
	 * private JLabel labelOperand1; private JLabel labelOperand2; private JLabel
	 * labelOperator;
	 */
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

	// private String operator;
	//private boolean valid_ergebnis;
	private boolean valid_operands;
	//private float op1;
	//private float op2;
	protected float ergebnis;
	

	public static String textButtons[][] = { { "+", "log", "log b x" }, { "-", " √ ", "x!" }, { "*", "sin", "asin" },
			{ "/", "cos", "acos" }, { "^", "tan", "atan" } };

	public GuiTaschenrechner(String titel/* , String operator */) {

		setTitle(titel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		setSize(250, 170);
		setResizable(false);

		initComponents();

		/*
		 * add(labelOperand1); add(labelOperator); add(labelOperand2);
		 */

		add(fieldOperand1);
		// add(labelOperator);
		add(fieldOperator); // neu für Operatorfeld
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

		setVisible(true);
		setLocationRelativeTo(null);

	}

	private void initComponents() {
		/*
		 * labelOperand1 = new JLabel("1. Operand"); labelOperand2 = new
		 * JLabel("2. Operand"); labelOperator = new JLabel("Operator");
		 */
		labelErgebnis = new JLabel(platz);

		fieldOperand1 = new JTextField(8);
		fieldOperator = new JTextField(1);
		fieldOperand2 = new JTextField(8);

		buttonErgebnis = new JButton("   =   ");
		buttonErgebnis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*
				 * float op1 = Float.parseFloat(fieldOperand1.getText()); float op2 =
				 * Float.parseFloat(fieldOperand2.getText());
				 */

				//String operator = fieldOperator.getText(); // neu für operatorfeld

				if ((fieldOperand1.getText().length() == 0) || (fieldOperand2.getText().length() == 0)) {
					valid_operands = false;
				} else {
					valid_operands = true;
				}
				if (valid_operands == true) {
					// verarbeiten der eingegebenen operanden mit dem operator, und ggf.
					// fehlerausgabe

					// einspeichern der werte in den operanden textfeldern in separaten variablen
					// und ggf eine konstante wie PI oder E
					/*
					 * if (testForConstants(fieldOperand1) == true) { op1 =
					 * setConstants(fieldOperand1); } else { op1 =
					 * Float.parseFloat(fieldOperand1.getText()); }
					 * 
					 * if (testForConstants(fieldOperand2) == true) { op2 =
					 * setConstants(fieldOperand2); } else { op2 =
					 * Float.parseFloat(fieldOperand2.getText()); }
					 */
					// op1 = Float.parseFloat(fieldOperand1.getText());
					// op2 = Float.parseFloat(fieldOperand2.getText());
					
					
					Calculation calc = new Calculation();

					labelErgebnis.setText(calc.calc(fieldOperand1, fieldOperand2, mode,calcMode));
					
					
					
					
					
					/*
					String ergebnisString;
					ergebnisString = "";
					int ergebnisLaenge = Float.toString(ergebnis).length();
					ergebnisLeere = max_ergebnis_length - ergebnisLaenge;
					for (int i = 1; i <= ergebnisLeere; i++) {
						ergebnisString = ergebnisString + " ";
					}
					if (ergebnisLaenge >= max_ergebnis_length) {
						ergebnisString = Float.toString(ergebnis);
					} else {
						ergebnisString = " = " + ergebnisString + Float.toString(ergebnis) + "  ";
					}
					labelErgebnis.setText(ergebnisString);*/
					
					
					// fieldOperand1.setText("");
					// fieldOperand2.setText("");	
					// ------------------------------------------------------------------------------------------------
					
					
					
					
					
				} else {
					Log.errorLog();
				}

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
				//buttonsRemoveSelection();
				buttonActionOnPressed(buttonPlus);
				//buttonSetSelection(buttonPlus);
			}
		});
		buttonMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//buttonsRemoveSelection();
				buttonActionOnPressed(buttonMinus);
				//buttonSetSelection(buttonMinus);
			}
		});
		buttonTimes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//buttonsRemoveSelection();
				buttonActionOnPressed(buttonTimes);
				//buttonSetSelection(buttonTimes);
			}
		});
		buttonDivide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//buttonsRemoveSelection();
				buttonActionOnPressed(buttonDivide);
				//buttonSetSelection(buttonDivide);
			}
		});
		buttonPow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//buttonsRemoveSelection();
				buttonActionOnPressed(buttonPow);
				//buttonSetSelection(buttonPow);
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
					//op1 = 0;
					//op2 = 0;
					buttonMinus.setEnabled(true);
					break;
				case 2:
					fieldOperand1.setEnabled(false);
					fieldOperator.setEnabled(false);
					buttonMinus.setEnabled(true);
					//op1 = 0;
					//op2 = 0;
					// fieldOperand1.setText("");
					// fieldOperator.setText("");
					break;
				case 3:
					fieldOperand1.setEnabled(false);
					fieldOperator.setEnabled(false);
					buttonMinus.setEnabled(false);
					//op1 = 0;
					//op2 = 0;
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
			// alle aufgelisteten methoden der buttons auf mode 1,
			if (button.equals(buttonPlus)) {
				//fieldOperator.setText("+");
				calcMode = 1;
			} else if (button.equals(buttonMinus)) {
				//fieldOperator.setText("-");
				calcMode = 2;
			} else if (button.equals(buttonTimes)) {
				//fieldOperator.setText("*");
				calcMode = 3;
			} else if (button.equals(buttonDivide)) {
				//fieldOperator.setText("/");
				calcMode = 4;
			} else if (button.equals(buttonPow)) {
				//fieldOperator.setText("^");
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
			if(button.equals(buttonPlus)) {
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
	// ------------------------------------------------------------------------------------------------------------
	/*private float setConstants(JTextField field) {
		if (field.getText().equals("PI")) {
			return (float) Math.PI;
		} else if (field.getText().equals("E")) {
			return (float) Math.E;
		}
		return 0;
	}

	private boolean testForConstants(JTextField field) {
		if (field.getText().equals("PI")) {
			return true;
		} else if (field.getText().equals("E")) {
			return true;
		}
		return false;
	}

	private float logWithBase(float b, float result) {
		return (float) ((float) Math.log10((double) result) / Math.log10((double) b));
	}*/
	// --------------------------------------------------------------------------------------------------------

	
	
	

	
	
}
