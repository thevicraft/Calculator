package com.thevicraft.calculator.gui;

import com.thevicraft.calculator.api.Calculation;
import com.thevicraft.calculator.api.StringCalculation;
import com.thevicraft.calculator.console.Log;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class GuiTaschenrechner extends JFrame {
	private String platz = "                                                 ";

	public static int max_ergebnis_length = 48;
	private int mode = 1; // default mode, do not change
	private static final int MODES = 3;
	protected int calcMode;
	private final String calcLabelEmpty = ""; // " "

	protected JLabel labelErgebnis;
	protected JLabel labelCalc;

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

	protected JButton buttonSignMinus;
	protected JButton buttonMathPi;
	protected JButton buttonMathE;
	protected JButton buttonBracket;
	
	protected JLabel labelFuncOpn;
	protected JLabel labelFuncCls;
	// protected JButton buttonBracketCls;

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

	public int WINDOW_WIDTH = 340;
	public int WINDOW_HEIGHT = 300;

	public int BUTTON_WIDTH = 60;
	public int BUTTON_HEIGHT = 25;

	JButton[] numPad = new JButton[13];
	JPanel[] panels = new JPanel[7];

	private static String constants[] = { "\u03c0", "\u2107" }; // pi, e

	protected float ergebnis;

	boolean bracket = false;

	// Fonts
	// -----------------------------------------------------------------------------------------------------------
	public static Font resultBold = new Font("Tahoma", Font.BOLD, 17); // davor war es groesse 12
	public static Font calcBold = new Font("Tahoma", Font.BOLD, 17);
	public static Font small = new Font("Tahoma", Font.BOLD, 11);
	public static Font normal = new Font("Tahoma", Font.BOLD, 12);
	public static Font xsmall = new Font("Tahoma", Font.BOLD, 10);
	public static Font extremesmall = new Font("Tahoma", Font.PLAIN, 10);
	// -----------------------------------------------------------------------------------------------------------------
	// Calculation calc = new Calculation();
	StringCalculation calcString = new StringCalculation();
	// Dimensions ------------------------------------------------------------------------------------------------------
	Dimension buttonStandartSize = new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT);

	public static String textButtons[][] = { { " + ", "log", "log b x" }, { " - ", " √ ", "x!" },
			{ " * ", "sin", "asin" }, { " / ", "cos", "acos" }, { " ^ ", "tan", "atan" } };

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
		// setLayout(new BorderLayout());

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // current 250, 170
		setResizable(false);

		initComponents();
		initPanels();

		addComponentsToPanels();
		initToolTips();
		addPanels();

		fieldOperator.setEditable(false);
		fieldOperator.setBackground(Color.LIGHT_GRAY);
		labelErgebnis.setFont(resultBold);
		labelCalc.setFont(calcBold);
		labelFuncOpn.setFont(calcBold);
		labelFuncCls.setFont(calcBold);

		for (int i = 0; i <= 8; i++) {
			getNumPad(i).setBackground(Color.white);
		}
		getNumPad(BUTTON_0).setBackground(Color.white);
		buttonDelete.setBackground(Color.orange);
		getNumPad(BUTTON__ANS).setBackground(Color.LIGHT_GRAY);
		getNumPad(BUTTON__DOT).setBackground(Color.white);
		getNumPad(BUTTON__E).setBackground(Color.LIGHT_GRAY);
		buttonErgebnis.setBackground(Color.green);
		buttonChangeMode.setBackground(Color.cyan);
		buttonMathPi.setBackground(Color.white);
		buttonMathE.setBackground(Color.white);
		buttonSignMinus.setBackground(Color.white);
		buttonBracket.setBackground(Color.white);
		// buttonBracketCls.setBackground(Color.white);

		// change size of all buttons
		for (JButton button : numPad) {
			button.setPreferredSize(buttonStandartSize);
		}
		buttonErgebnis.setPreferredSize(buttonStandartSize);
		buttonPlus.setPreferredSize(buttonStandartSize);
		buttonMinus.setPreferredSize(buttonStandartSize);
		buttonTimes.setPreferredSize(buttonStandartSize);
		buttonDivide.setPreferredSize(buttonStandartSize);
		buttonPow.setPreferredSize(buttonStandartSize);
		buttonDelete.setPreferredSize(buttonStandartSize);
		buttonMathE.setPreferredSize(buttonStandartSize);
		buttonMathPi.setPreferredSize(buttonStandartSize);
		buttonSignMinus.setPreferredSize(buttonStandartSize);
		buttonBracket.setPreferredSize(buttonStandartSize);
		// buttonBracketCls.setPreferredSize(new
		// Dimension(BUTTON_WIDTH/2,BUTTON_HEIGHT));
		buttonChangeMode.setPreferredSize(buttonStandartSize);

		panels[0].setPreferredSize(new Dimension(300, 30));
		panels[1].setPreferredSize(new Dimension(300, 30));

		getNumPad(BUTTON__ANS).setFont(small); // korrektur weil die beschriftung sonst nicht auf dem button angezeigt
												// wird

		setLocationRelativeTo(null);

		// this.setIconImage(icon.getImage());
		// this.setIconImage((new Images().imageDefaultInResources(new Images().ICON)));
		this.setIconImage(new Images().imageFromNameInResources("window-icon.png"));
		
		//---------------------------------DER CHANGE BUTTON WIRD DISABLED	----------------------------------------------------------------
		//buttonChangeMode.setEnabled(false);
		

		setVisible(true);
	}

	private void addComponentsToPanels(/* JFrame window */) {

		// panels[0].add(fieldOperand1);
		// panels[0].add(fieldOperator);
		// panels[0].add(fieldOperand2);
		panels[0].add(labelCalc);

		panels[1].add(labelErgebnis);

		for (int i = 0; i <= 2; i++) {
			panels[2].add(getNumPad(i));
		}
		panels[2].add(buttonPow);
		panels[2].add(buttonDelete);

		for (int i = 3; i <= 5; i++) {
			panels[3].add(getNumPad(i));
		}
		panels[3].add(buttonTimes);
		panels[3].add(buttonDivide);

		for (int i = 6; i <= 8; i++) {
			panels[4].add(getNumPad(i));
		}
		panels[4].add(buttonPlus);
		panels[4].add(buttonMinus);

		panels[5].add(getNumPad(BUTTON_0));
		panels[5].add(getNumPad(BUTTON__DOT));
		panels[5].add(getNumPad(BUTTON__E));
		panels[5].add(getNumPad(BUTTON__ANS));
		panels[5].add(buttonErgebnis);

		panels[6].add(buttonMathPi);
		panels[6].add(buttonMathE);
		panels[6].add(buttonSignMinus);
		panels[6].add(buttonBracket);
		// panels[6].add(buttonBracketCls);
		panels[6].add(buttonChangeMode);

	}

	private void addPanels(/* JFrame window */) {
		for (JPanel pane : panels) {
			add(pane);
		}
	}

	private void initPanels() {
		for (int i = 0; i <= 6; i++) {
			panels[i] = new JPanel();
		}
	}

	private void initToolTips() {
		buttonDelete.setToolTipText("Delete Calculation");
		getNumPad(BUTTON__ANS).setToolTipText("Get former result");
		getNumPad(BUTTON__E).setToolTipText("*10^x");
		buttonChangeMode.setToolTipText("Change mode to other Calculations (sin, cos, tan)");
		buttonErgebnis.setToolTipText("Calculate");
	}

	private void initNumPads() {
		String platzhalter = "  ";
		for (int num = 6; num <= 8; num++) {
			numPad[num - 6] = new JButton(Integer.toString(num + 1));
		}
		for (int num = 3; num <= 5; num++) {
			numPad[num] = new JButton(Integer.toString(num + 1));
		}
		for (int num = 0; num <= 2; num++) {
			numPad[num + 6] = new JButton(Integer.toString(num + 1));
		}
		numPad[9] = new JButton("0");
		numPad[10] = new JButton(".");
		numPad[11] = new JButton("E");
		numPad[12] = new JButton("ANS");
	}

	private void addNumPads(JFrame window) {
		for (JButton button : numPad) {
			window.add(button);
		}
		WINDOW_HEIGHT = WINDOW_HEIGHT + 90;
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private void removeNumPads(JFrame window) {
		for (JButton button : numPad) {
			window.remove(button);
		}
		WINDOW_HEIGHT = WINDOW_HEIGHT - 90;
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	private JButton getNumPad(final int d) {
		return numPad[d];
	}

	private void initComponents() {

		labelCalc = new JLabel(calcLabelEmpty);

		labelErgebnis = new JLabel(platz);

		fieldOperand1 = new JTextField(8);
		fieldOperator = new JTextField(3);
		fieldOperand2 = new JTextField(8);

		buttonErgebnis = new JButton("=");
		buttonErgebnis.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// labelErgebnis.setText(calc.calc(GuiTaschenrechner.this, fieldOperand1,
				// fieldOperand2, mode, calcMode));
				try {
					labelErgebnis.setText(calcString.calcResultFromString(labelCalc.getText(),mode, calcMode));
				} catch (Exception er) {

				}
			}
		});
		buttonDelete = new JButton("AC");
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * labelErgebnis.setText(platz); switch (mode) { case 1:
				 * fieldOperand1.setText(""); break; case 2: break; } fieldOperand2.setText("");
				 * fieldOperator.setText("");
				 */
				labelCalc.setText(calcLabelEmpty);
			}
		});
		buttonPlus = new JButton(textButtons[0][0]);
		buttonMinus = new JButton(textButtons[1][0]);
		buttonTimes = new JButton(textButtons[2][0]);
		buttonDivide = new JButton(textButtons[3][0]);
		buttonPow = new JButton(textButtons[4][0]);

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
		//-----------------------------------------------------------------MODE BUTTON------------------------------------
		buttonChangeMode = new JButton("M");
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
					buttonMinus.setEnabled(true);
					buttonPlus.setFont(normal);
					buttonMinus.setFont(normal);
					buttonTimes.setFont(normal);
					buttonDivide.setFont(normal);
					buttonPow.setFont(normal);
					buttonPlus.setToolTipText("");
					break;
				case 2:
					fieldOperand1.setEnabled(false);
					fieldOperator.setEnabled(false);
					buttonMinus.setEnabled(true);
					buttonPlus.setFont(normal);
					buttonMinus.setFont(normal);
					buttonTimes.setFont(normal);
					buttonDivide.setFont(normal);
					buttonPow.setFont(normal);
					buttonPlus.setToolTipText("");
					break;
				case 3:
					fieldOperand1.setEnabled(false);
					fieldOperator.setEnabled(false);
					buttonMinus.setEnabled(false);
					buttonPlus.setFont(extremesmall);
					buttonMinus.setFont(xsmall);
					buttonTimes.setFont(xsmall);
					buttonDivide.setFont(xsmall);
					buttonPow.setFont(xsmall);
					buttonPlus.setToolTipText("Logarithm of base b and x");
					break;
				default:
					break;
				}
				setPanel0ToMode(mode);
				buttonPlus.setText(textButtons[0][mode - 1]);
				buttonMinus.setText(textButtons[1][mode - 1]);
				buttonTimes.setText(textButtons[2][mode - 1]);
				buttonDivide.setText(textButtons[3][mode - 1]);
				buttonPow.setText(textButtons[4][mode - 1]);

				/*
				 * fieldOperand1.setText(""); fieldOperand2.setText("");
				 * fieldOperator.setText("");
				 */
				labelCalc.setText(calcLabelEmpty);
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
		for (JButton button : numPad) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Log.console(button.getText());
					labelCalc.setText(labelCalc.getText() + button.getText());
				}
			});
			buttonSignMinus = new JButton("(-)");
			buttonMathPi = new JButton(constants[0]);
			buttonMathE = new JButton(constants[1]);

			buttonSignMinus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Log.console(button.getText());
					labelCalc.setText(labelCalc.getText() + " -");
				}
			});

			buttonMathPi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Log.console(button.getText());
					labelCalc.setText(labelCalc.getText() + "n");
				}
			});

			buttonMathE.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Log.console(button.getText());
					labelCalc.setText(labelCalc.getText() + "e");
				}
			});
			buttonBracket = new JButton("( )");
			// buttonBracketCls = new JButton(")");
			buttonBracket.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (bracket == false) {
						labelCalc.setText(labelCalc.getText() + "(");
					} else {
						labelCalc.setText(labelCalc.getText() + ")");
					}
					bracket = !bracket;
				}
			});
			
			labelFuncOpn = new JLabel();
			labelFuncCls = new JLabel(")");
			// buttonBracketCls.addActionListener(new ActionListener() {
			// public void actionPerformed(ActionEvent e) {
			// //Log.console(button.getText());
			// buttonActionOnPressed(buttonBracketCls);
			// }
			// });

		}

	}
	
	private void setPanel0ToMode(int modi) {
		switch(modi) {
		case 1:
			labelFuncOpn.setText("");
			labelFuncOpn.setVisible(false);
			labelFuncCls.setVisible(false);
			panels[0].remove(labelFuncOpn);
			panels[0].remove(labelFuncCls);
			panels[0].remove(labelCalc);
			
			panels[0].add(labelCalc);
			break;
		case 2:
			labelFuncOpn.setVisible(true);
			labelFuncCls.setVisible(true);
			panels[0].remove(labelFuncOpn);
			panels[0].remove(labelFuncCls);
			panels[0].remove(labelCalc);
			
			panels[0].add(labelFuncOpn);
			panels[0].add(labelCalc);
			panels[0].add(labelFuncCls);
			break;
		case 3:
			break;
		}
	}

	private void buttonsSetTextOnMode() {
		buttonPlus.setText(textButtons[0][mode - 1]);
		buttonMinus.setText(textButtons[1][mode - 1]);
		buttonTimes.setText(textButtons[2][mode - 1]);
		buttonDivide.setText(textButtons[3][mode - 1]);
		buttonPow.setText(textButtons[4][mode - 1]);
	}

	private void buttonSetSelection(JButton button) {
		if (mode != 1) {
			button.setText("[" + button.getText() + "]");
		}
	}

	private void buttonActionOnPressed(JButton button) {
		// Log.console(GuiTaschenrechner.this.resultBold.getFontName().toString());
		//labelCalc.setText(labelCalc.getText() + button.getText());
		buttonsSetTextOnMode();
		switch (mode) {
		case 1:
			labelCalc.setText(labelCalc.getText() + button.getText());
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
			labelFuncOpn.setText(button.getText()+"(");
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
			labelFuncOpn.setText(button.getText()+"(");
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
			fieldOperand1.setText(button.getText());
			break;
		}
		// buttonSetSelection(button);
	}

}
