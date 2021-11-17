package com.thevicraft.calculator.gui;

import com.thevicraft.calculator.api.Calculation;
import com.thevicraft.calculator.api.StringCalcFunctions;
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
	protected JButton buttonDeleteLast;
	protected JButton buttonPlus;
	protected JButton buttonMinus;

	protected JButton buttonTimes;
	protected JButton buttonDivide;
	protected JButton buttonPow;
	protected JButton buttonChangeMode;

	protected JButton buttonSignMinus;
	protected JButton buttonMathPi;
	protected JButton buttonMathE;
	protected JButton buttonBracketOpn;
	protected JButton buttonBracketCls;

	protected JButton buttonLogBase;
	protected JButton buttonLogExp;

	protected JButton buttonXPower2;
	protected JButton buttonXPower3;
	protected JButton buttonXPowerReverse;

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
	public int WINDOW_HEIGHT = 340;	//300

	public int BUTTON_WIDTH = 60;
	public int BUTTON_HEIGHT = 25;
	private final int PANELS = 7;

	JButton[] numPad = new JButton[13];
	JPanel[] panels = new JPanel[PANELS + 1];

	private static String constants[] = { "\u03c0", "\u2107" }; // pi, e

	protected float ergebnis;

	boolean bracket = false;
	int logWithBaseFocus = 0;

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
	// Dimensions
	// ------------------------------------------------------------------------------------------------------
	Dimension buttonStandartSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

	// Images--------------------------------------------------------------------------------------------------
	Images icon = new Images();
	Color dark = Color.DARK_GRAY;
	Color bright = Color.white;

	public static String textButtons[][] = { { " + ", "log", "log" }, { " - ", " √ ", "x!" }, { " * ", "sin", "asin" },
			{ " / ", "cos", "acos" }, { " ^ ", "tan", "atan" } };

	public GuiTaschenrechner(String titel) {

		setTitle(titel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		// setLayout(new BorderLayout());

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
		buttonXPowerReverse.setFont(extremesmall);

		for (int i = 0; i <= 8; i++) {
			getNumPad(i).setBackground(bright);
		}
		getNumPad(BUTTON_0).setBackground(bright);
		buttonDelete.setBackground(Color.orange);
		buttonDeleteLast.setBackground(Color.orange);
		getNumPad(BUTTON__ANS).setBackground(Color.LIGHT_GRAY);
		getNumPad(BUTTON__DOT).setBackground(bright);
		getNumPad(BUTTON__E).setBackground(Color.LIGHT_GRAY);
		buttonErgebnis.setBackground(Color.green);
		buttonChangeMode.setBackground(Color.cyan);
		buttonMathPi.setBackground(dark);
		buttonMathE.setBackground(dark);
		buttonSignMinus.setBackground(dark);
		buttonBracketOpn.setBackground(dark);
		buttonBracketCls.setBackground(dark);
		buttonXPower2.setBackground(dark);
		buttonXPower3.setBackground(dark);
		buttonXPowerReverse.setBackground(dark);
		buttonPow.setBackground(dark);
		
		buttonMathPi.setForeground(bright);
		buttonMathE.setForeground(bright);
		buttonSignMinus.setForeground(bright);
		buttonBracketOpn.setForeground(bright);
		buttonBracketCls.setForeground(bright);
		buttonXPower2.setForeground(bright);
		buttonXPower3.setForeground(bright);
		buttonXPowerReverse.setForeground(bright);
		buttonPow.setForeground(bright);

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
		buttonBracketOpn.setPreferredSize(buttonStandartSize);
		buttonBracketCls.setPreferredSize(buttonStandartSize);
		// buttonLogBase.setPreferredSize(buttonStandartSize);
		// buttonLogExp.setPreferredSize(buttonStandartSize);
		buttonChangeMode.setPreferredSize(buttonStandartSize);
		buttonXPower2.setPreferredSize(buttonStandartSize);
		buttonXPower3.setPreferredSize(buttonStandartSize);
		buttonXPowerReverse.setPreferredSize(buttonStandartSize);

		panels[0].setPreferredSize(new Dimension(300, 30));
		panels[1].setPreferredSize(new Dimension(300, 30));

		getNumPad(BUTTON__ANS).setFont(small); // korrektur weil die beschriftung sonst nicht auf dem button angezeigt
												// wird

		setLocationRelativeTo(null);

		// this.setIconImage(icon.getImage());
		// this.setIconImage((new Images().imageDefaultInResources(new Images().ICON)));
		// this.setIconImage(new Images().imageFromNameInResources("window-icon.png"));
		this.setIconImage(icon.imageDefaultInResources(icon.ICON));

		// ---------------------------------DER CHANGE BUTTON WIRD DISABLED
		// ----------------------------------------------------------------
		// buttonChangeMode.setEnabled(false);

		setVisible(true);
	}

	private void addComponentsToPanels(/* JFrame window */) {

		// panels[0].add(fieldOperand1);
		// panels[0].add(fieldOperator);
		// panels[0].add(fieldOperand2);
		panels[0].add(labelFuncOpn);
		panels[0].add(buttonLogExp);
		panels[0].add(labelCalc);
		panels[0].add(buttonLogBase);
		panels[0].add(labelFuncCls);
		labelFuncOpn.setVisible(false);
		labelFuncCls.setVisible(false);
		buttonLogExp.setVisible(false);
		buttonLogBase.setVisible(false);

		panels[1].add(labelErgebnis);

		for (int i = 0; i <= 2; i++) {
			panels[2].add(getNumPad(i));
		}
		panels[2].add(buttonDeleteLast);
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

		panels[6].add(buttonSignMinus);
		panels[6].add(buttonBracketOpn);
		panels[6].add(buttonBracketCls);
		panels[6].add(buttonChangeMode);

		panels[7].add(buttonMathE);
		panels[7].add(buttonXPower2);
		panels[7].add(buttonPow);
		panels[7].add(buttonXPower3);
		panels[7].add(buttonXPowerReverse);

	}

	private void addPanels(/* JFrame window */) {
		for (JPanel pane : panels) {
			add(pane);
		}
	}

	private void initPanels() {
		for (int i = 0; i <= PANELS; i++) {
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
					labelErgebnis.setText(calcString.calcResultFromString(labelCalc.getText(), mode, calcMode,
							buttonLogBase.getText(), buttonLogExp.getText()));
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
				// labelCalc.setText(calcLabelEmpty);
				insertTextInField(calcLabelEmpty, true);
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
		// -----------------------------------------------------------------MODE
		// BUTTON------------------------------------
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
				logWithBaseFocus = 0;
				insertTextInField(calcLabelEmpty, true);
			}
		});

		initNumPads();
		for (JButton button : numPad) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					insertTextInField(button.getText(), false);
				}
			});
			buttonSignMinus = new JButton("(-)");
			buttonMathPi = new JButton(constants[0]);
			buttonMathE = new JButton(constants[1]);

			buttonSignMinus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField(" -", false);
				}
			});

			buttonMathPi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField("n", false);
				}
			});

			buttonMathE.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField("e", false);
				}
			});

			buttonBracketOpn = new JButton("(");
			buttonBracketCls = new JButton(")");
			buttonBracketOpn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField("(", false);

				}
			});
			buttonBracketCls.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField(")", false);

				}
			});
			buttonDeleteLast = new JButton("DEL");
			buttonDeleteLast.addActionListener(new ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {
					
					// delete all if there is only one character
					if (getTextInField().length() == 1) {
						setTextInField(calcLabelEmpty);
					}

					boolean foundOperator = false;
					String ops[] = new StringCalcFunctions().calcOperator;
					// test for an operator to delete the empty spaces with it
					try {
						for (String o : ops) {
							if (getTextInField().substring(getTextInField().length() - 3, getTextInField().length())
									.equals(o)) {
								foundOperator = true;
								break;
							} else {
								foundOperator = false;
							}
						}
					} catch (Exception in) {
					}
					try {
						// test for a special sign minus, to delete the empty space with it
						if (foundOperator) {
							setTextInField(getTextInField().substring(0, getTextInField().length() - 3));

						} else {
							// normal delete of last sign, just inserts same text without last character
							if (getTextInField().substring(getTextInField().length() - 2, getTextInField().length())
									.equals(" -")) {
								// löscht auch ein leerzeichen vom rechenoperator wenn eins da ist
								setTextInField(getTextInField().substring(0, getTextInField().length() - 2));
							} else {
								setTextInField(getTextInField().substring(0, getTextInField().length() - 1));
							}

						}
					} catch (Exception a) {
					}

				}
			});

			buttonXPower2 = new JButton("^2");
			buttonXPower3 = new JButton("^3");
			buttonXPowerReverse = new JButton("^(-1)");
			buttonXPower2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField(buttonXPower2.getText(), false);
				}
			});
			buttonXPower3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField(buttonXPower3.getText(), false);
				}
			});
			buttonXPowerReverse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertTextInField("^( -1)", false);
				}
			});

			labelFuncOpn = new JLabel();
			labelFuncCls = new JLabel(")");
			buttonLogBase = new JButton("");
			buttonLogExp = new JButton("");
			buttonLogBase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					logWithBaseFocus = 2;
					buttonLogBase.setBackground(Color.LIGHT_GRAY);
					buttonLogExp.setBackground(Color.white);
				}
			});
			buttonLogExp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					logWithBaseFocus = 1;
					buttonLogBase.setBackground(Color.white);
					buttonLogExp.setBackground(Color.LIGHT_GRAY);
				}
			});

		}

	}

	private void setPanel0ToMode(int modi) {
		switch (modi) {
		case 1:
			labelFuncOpn.setText("");
			labelFuncOpn.setVisible(false);
			labelFuncCls.setVisible(false);

			labelCalc.setVisible(true);
			logWithBaseFocus = 0;
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			break;
		case 2:
			labelFuncOpn.setVisible(true);
			labelFuncCls.setVisible(true);
			labelCalc.setVisible(true);
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			logWithBaseFocus = 0;
			break;
		case 3:
			labelCalc.setVisible(true);
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			logWithBaseFocus = 0;
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
		buttonsSetTextOnMode();
		boolean log = false;
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
			labelFuncOpn.setText(button.getText() + "(");
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
			labelFuncOpn.setText(button.getText() + "(");
			if (button.equals(buttonPlus)) {
				fieldOperand1.setEnabled(true);
				fieldOperand1.setText("");
				calcMode = 11;
				labelFuncOpn.setText("log(");
				log = true;

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
		if (log == true) {
			buttonLogBase.setVisible(true);
			buttonLogExp.setVisible(true);
			labelCalc.setVisible(false);

			buttonLogBase.setVisible(true);
			buttonLogExp.setVisible(true);

		} else {
			labelCalc.setVisible(true);
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			logWithBaseFocus = 0;
		}
	}

	private void insertTextInField(String text, boolean deleteall) {
		if (deleteall == false) {
			switch (logWithBaseFocus) {
			case 0:
				labelCalc.setText(labelCalc.getText() + text);
				break;
			case 1:
				buttonLogExp.setText(buttonLogExp.getText() + text);
				break;
			case 2:
				buttonLogBase.setText(buttonLogBase.getText() + text);
				break;
			}

		} else {
			switch (logWithBaseFocus) {
			case 0:
				labelCalc.setText(calcLabelEmpty);
				break;
			case 1:
				buttonLogExp.setText(calcLabelEmpty);
				break;
			case 2:
				buttonLogBase.setText(calcLabelEmpty);
				break;
			}
		}
	}

	private String getTextInField() {
		switch (logWithBaseFocus) {
		case 0:
			return labelCalc.getText();
		case 1:
			return buttonLogExp.getText();
		case 2:
			return buttonLogBase.getText();
		default:
			return null;
		}

	}

	private void setTextInField(String text) {

		switch (logWithBaseFocus) {
		case 0:
			labelCalc.setText(text);
			break;
		case 1:
			buttonLogExp.setText(text);
			break;
		case 2:
			buttonLogBase.setText(text);
			break;
		}
	}

}
