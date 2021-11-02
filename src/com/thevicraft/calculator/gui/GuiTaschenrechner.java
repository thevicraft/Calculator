package com.thevicraft.calculator.gui;

import com.thevicraft.calculator.api.Calculation;
import com.thevicraft.calculator.console.Log;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

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

	public static final int BUTTON_0 = 9;
	public static final int BUTTON_1 = 6;
	public static final int BUTTON_2 = 7;
	public static final int BUTTON_3 = 8;
	public static final int BUTTON_4 = 3;
	public static final int BUTTON_5 = 4;
	public static final int BUTTON_6 = 5;
	public static final int BUTTON_7 = 0;
	public static final int BUTTON_8 = 1;
	public static final int BUTTON_9 = 2;
	public static final int BUTTON__ANS = 12;
	public static final int BUTTON__DOT = 10;
	public static final int BUTTON__E = 11;
	
	public int WINDOW_WIDTH = 250;
	public int WINDOW_HEIGHT = 170;

	// JButton[] numPad = new JButton[3];
	JButton[] numPad = new JButton[13];


	protected float ergebnis;

	// Fonts
	// -----------------------------------------------------------------------------------------------------------
	public static Font bold = new Font("Tahoma", Font.BOLD, 12);

	// -----------------------------------------------------------------------------------------------------------------
	Calculation calc = new Calculation();

	public static String textButtons[][] = { { "+", "log", "log b x" }, { "-", " √ ", "x!" }, { "*", "sin", "asin" },
			{ "/", "cos", "acos" }, { "^", "tan", "atan" } };

	public GuiTaschenrechner(String titel/* , String operator */) {
		/*
		 * { boolean lang1; boolean lang2; do {
		 * setLanguage(Log.input("Select a language: [en; de]")); lang1 =
		 * getLanguage().equals("de"); lang2 = getLanguage().equals("en"); } while
		 * ((lang1 == false) & (lang2 == false)); }
		 */
		setTitle(titel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		//setLayout(new BorderLayout());

		setSize(250, 280);		// current 250, 170
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
		//-----------------------------------------------------CHECKBOX MACHEN, MIT DER MAN DAS NUMPAD AKTIVIEREN KANN 
		//-----------------------------------------------------(dann wird entweder addNumpads/ removeNumpads)
		addNumPads(GuiTaschenrechner.this);

		fieldOperator.setEditable(false);
		fieldOperator.setBackground(Color.LIGHT_GRAY);
		labelErgebnis.setFont(bold);

		setLocationRelativeTo(null);

		// this.setIconImage(icon.getImage());
		this.setIconImage(Images.image(Images.ICON));

		setVisible(true);
	}

	private void initNumPads() {
		String platzhalter = "   ";
		for (int num = 6; num <= 8; num++) {
			String buttonTitle = platzhalter+(num+1)+platzhalter;
			numPad[num-6] = new JButton(buttonTitle);
		}
		for (int num = 3; num <= 5; num++) {
			String buttonTitle = platzhalter+(num+1)+platzhalter;
			numPad[num] = new JButton(buttonTitle);
		}
		for (int num = 0; num <= 2; num++) {
			String buttonTitle = platzhalter+(num+1)+platzhalter;
			numPad[num+6] = new JButton(buttonTitle);
		}
		numPad[9] = new JButton(platzhalter+"0"+platzhalter);
		numPad[10] = new JButton(".");
		numPad[11] = new JButton("E");
		numPad[12] = new JButton("ANS");
	}
	private void addNumPads(JFrame window) {
		for(JButton button: numPad) {
			window.add(button);
		}
		WINDOW_HEIGHT = WINDOW_HEIGHT + 90;	
		window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
	}
	private void removeNumPads(JFrame window) {
		for(JButton button: numPad) {
			window.remove(button);
		}
		WINDOW_HEIGHT = WINDOW_HEIGHT - 90;
		window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
	}
	
	private JButton getNumPad(final int d) {
		return numPad[d];
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
		buttonDelete = new JButton("AC");
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
				fieldOperator.setText("");
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
					WINDOW_HEIGHT -= 30;
					GuiTaschenrechner.this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
					break;
				case 2:
					fieldOperand1.setEnabled(false);
					fieldOperator.setEnabled(false);
					buttonMinus.setEnabled(true);
					// op1 = 0;
					// op2 = 0;
					// fieldOperand1.setText("");
					// fieldOperator.setText("");
					WINDOW_HEIGHT += 30;
					GuiTaschenrechner.this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
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
		// hier foreachschleife nutzen
		/*
		 * BUTTON_0 = new JButton("0"); BUTTON_0.addActionListener(new ActionListener()
		 * {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * buttonActionOnPressed(BUTTON_0); } });
		 */
		initNumPads();
		for(JButton button: numPad) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Log.console(button.getText());
				}
			});
		}

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

}
