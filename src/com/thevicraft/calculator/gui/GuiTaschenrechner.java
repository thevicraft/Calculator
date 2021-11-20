package com.thevicraft.calculator.gui;

import com.thevicraft.calculator.api.StringCalcFunctions;
import com.thevicraft.calculator.api.StringCalculation;
import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.Images.Pictures;
import com.thevicraft.keyboard.activity.KeyEventClass;
import com.thevicraft.keyboard.activity.WindowCloseEvent;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class GuiTaschenrechner extends JFrame {
	private String platz = "                                                 ";

	// public static int max_ergebnis_length = 48;
	private int mode = 1; // default mode, do not change
	private static final int MODES = 3;
	protected int calcMode;
	private final String calcLabelEmpty = ""; // " "

	protected JLabel labelErgebnis;
	protected JLabel labelCalc;

	public JButton buttonErgebnis;
	public JButton buttonDelete;
	public JButton buttonDeleteLast;
	public JButton buttonPlus;
	public JButton buttonMinus;

	public JButton buttonTimes;
	public JButton buttonDivide;
	public JButton buttonPow;
	public JButton buttonChangeMode;

	public JButton buttonSignMinus;
	public JButton buttonMathPi;
	public JButton buttonMathE;
	public JButton buttonBracketOpn;
	public JButton buttonBracketCls;

	public JButton buttonLogBase;
	public JButton buttonLogExp;

	public JButton buttonXPower2;
	public JButton buttonXPower3;
	public JButton buttonXPowerReverse;

	// protected JButton buttonAppearMode;
	// protected JButton buttonZoomIn;
	// protected JButton buttonZoomOut;

	protected JLabel labelFuncOpn;
	protected JLabel labelFuncMid;
	protected JLabel labelFuncCls;
	// protected JButton buttonBracketCls;

	public GuiMenuBar menu;

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
	public int WINDOW_HEIGHT = 380; // 400
	public int PANEL_WIDTH = 340;
	public int PANEL_HEIGHT = 370; // 390
	public int BUTTON_WIDTH = 60;
	public int BUTTON_HEIGHT = 25;
	private final int PANELS = 8;

	public JButton[] numPad = new JButton[13];
	public JButton[] funcPad = new JButton[5];
	JPanel[] panels = new JPanel[PANELS + 1];
	protected JPanel panelMaster;

	private static String constants[] = { "\u03c0", "\u2107" }; // pi, e

	protected float ergebnis;

	boolean bracket = false;
	int logWithBaseFocus = 0;

	private float sizeFactor = 1.5f;

	private Color appearanceMode;

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
	public static Color dark = Color.DARK_GRAY;
	public static Color bright = Color.white;

	public static String textButtons[][] = { { " + ", "log", "log" }, { " - ", " √ ", "x!" }, { " * ", "sin", "asin" },
			{ " / ", "cos", "acos" }, { " ^ ", "tan", "atan" } };

	public GuiTaschenrechner(String titel, String darkLight, JFrame location) {
		switch (darkLight) {
		case "dark":
			appearanceMode = dark;
			break;
		case "light":
			appearanceMode = bright;
		default:
			appearanceMode = bright;
		}

		setTitle(titel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setResizable(true);

		initComponents();
		initPanels();
		addComponentsToPanels();
		initToolTips();
		addPanels();

		// master panel
		add(panelMaster);
		panelMaster.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

		setColorOfComponents(appearanceMode);

		setLocationRelativeTo(location);

		this.setIconImage(Images.getDefaultImageIcon(Pictures.ICON).getImage());

		changeSizeWindow(sizeFactor);

		funcPadSetVisible(false);

		menu = new GuiMenuBar(appearanceMode);
		initMenuBar(titel);

		setJMenuBar(menu);

		for (JPanel p : panels) {
			removeFocusFromAllObjects(p.getComponents());
		}

		//addKeyListener(new KeyEventClass());

		setVisible(true);
	}

	private void removeFocusFromAllObjects(Component[] container) {
		for (Component child : container) {
			if (child instanceof JButton) {
				child.setFocusable(false);
			}
		}
	}

	public void changeSizeWindow(float factor) {
		setSizeOfComponents(factor, 0);
		setIconOfComponents(factor);
		setFontOfComponents(factor);

	}

	private void setIconOfComponents(float factor) {

		// buttonAppearMode.setIcon(
		// Images.scaleImageIconFromDefault(Pictures.DARK_LIGHT_MODE, (int) (30 *
		// factor), ((int) (30 * factor))));
		// buttonZoomIn.setIcon(
		// Images.scaleImageIconFromDefault(Pictures.ZOOM_IN, (int) (25 * factor),
		// ((int) (25 * factor))));
		// buttonZoomOut.setIcon(
		// Images.scaleImageIconFromDefault(Pictures.ZOOM_OUT, (int) (25 * factor),
		// ((int) (25 * factor))));
	}

	private void setSizeOfComponents(float factor, int winPanel) {
		buttonStandartSize = new Dimension((int) (factor * BUTTON_WIDTH), (int) (factor * BUTTON_HEIGHT));
		Dimension buttonAppearModeSize = new Dimension((int) (factor * 30), (int) (factor * 30));
		Dimension panelSize = new Dimension((int) (factor * 300), (int) (factor * 30));

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
		buttonDeleteLast.setPreferredSize(buttonStandartSize);
		// buttonLogBase.setPreferredSize(buttonStandartSize);
		// buttonLogExp.setPreferredSize(buttonStandartSize);
		buttonChangeMode.setPreferredSize(buttonStandartSize);
		buttonXPower2.setPreferredSize(buttonStandartSize);
		buttonXPower3.setPreferredSize(buttonStandartSize);
		buttonXPowerReverse.setPreferredSize(buttonStandartSize);

		// buttonAppearMode.setPreferredSize(buttonAppearModeSize);
		// buttonZoomIn.setPreferredSize(buttonAppearModeSize);
		// buttonZoomOut.setPreferredSize(buttonAppearModeSize);

		for (JButton c : funcPad) {
			c.setPreferredSize(buttonStandartSize);
		}

		// panels default size 300, 30
		panels[0].setPreferredSize(panelSize);
		panels[1].setPreferredSize(panelSize);
		setSize((int) (WINDOW_WIDTH * factor), (int) ((WINDOW_HEIGHT + winPanel) * (factor * 0.88)));
		panelMaster.setPreferredSize(
				new Dimension((int) (PANEL_WIDTH * factor), (int) ((PANEL_HEIGHT + winPanel) * (factor * 0.88))));
	}

	private void setColorOfComponents(Color mode) {
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
		buttonChangeMode.setBackground(Color.LIGHT_GRAY);
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

		// buttonZoomIn.setBackground(bright);
		// buttonZoomOut.setBackground(bright);

		for (JButton x : funcPad) {
			x.setForeground(bright);
			x.setBackground(dark);
		}

		for (JPanel p : panels) {
			p.setBackground(mode);
		}
		panelMaster.setBackground(mode);
		getContentPane().setBackground(mode);

		if (mode.equals(dark)) {
			labelCalc.setForeground(bright);
			labelErgebnis.setForeground(bright);
			labelFuncOpn.setForeground(bright);
			labelFuncMid.setForeground(bright);
			labelFuncCls.setForeground(bright);
		} else if (mode.equals(bright)) {
			labelCalc.setForeground(dark);
			labelErgebnis.setForeground(dark);
			labelFuncOpn.setForeground(dark);
			labelFuncMid.setForeground(dark);
			labelFuncCls.setForeground(dark);
		} else {
			labelCalc.setForeground(dark);
			labelErgebnis.setForeground(dark);
			labelFuncOpn.setForeground(dark);
			labelFuncMid.setForeground(dark);
			labelFuncCls.setForeground(dark);
		}

	}

	private void setFontOfComponents(float factor) {
		resultBold = new Font("Tahoma", Font.BOLD, (int) (17 * factor)); // davor war es groesse 12
		calcBold = new Font("Tahoma", Font.BOLD, (int) (17 * factor));
		small = new Font("Tahoma", Font.BOLD, (int) (11 * factor));
		normal = new Font("Tahoma", Font.BOLD, (int) (12 * factor));
		xsmall = new Font("Tahoma", Font.BOLD, (int) (10 * factor));
		extremesmall = new Font("Tahoma", Font.PLAIN, (int) (10 * factor));

		labelErgebnis.setFont(resultBold);
		labelCalc.setFont(calcBold);
		labelFuncOpn.setFont(calcBold);
		labelFuncCls.setFont(calcBold);
		labelFuncMid.setFont(calcBold);
		buttonXPowerReverse.setFont(extremesmall);
		buttonLogExp.setFont(extremesmall);
		buttonLogBase.setFont(calcBold);
		getNumPad(BUTTON__ANS).setFont(small); // korrektur weil die beschriftung sonst nicht auf dem button angezeigt
		// wird
		for (JButton x : funcPad) {
			x.setFont(xsmall);
		}
		for (JButton p : numPad) {
			p.setFont(normal);
		}
		buttonPlus.setFont(normal);
		buttonMinus.setFont(normal);
		buttonTimes.setFont(normal);
		buttonDivide.setFont(normal);
		buttonPow.setFont(normal);

		buttonChangeMode.setFont(normal);
		buttonErgebnis.setFont(normal);

		buttonBracketOpn.setFont(normal);
		buttonBracketCls.setFont(normal);
		buttonSignMinus.setFont(normal);
		buttonMathPi.setFont(normal);
		buttonMathE.setFont(normal);
		buttonXPower2.setFont(normal);
		buttonXPower3.setFont(normal);

		buttonDelete.setFont(normal);
		buttonDeleteLast.setFont(normal);
	}

	private void addComponentsToPanels(/* JFrame window */) {

		// panels[0].add(fieldOperand1);
		// panels[0].add(fieldOperator);
		// panels[0].add(fieldOperand2);
		panels[0].add(labelFuncOpn);
		panels[0].add(buttonLogExp);
		panels[0].add(labelFuncMid);
		panels[0].add(labelCalc);
		panels[0].add(buttonLogBase);
		panels[0].add(labelFuncCls);
		labelFuncOpn.setVisible(false);
		labelFuncMid.setVisible(false);
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

		for (JButton pad : funcPad) {
			panels[8].add(pad);
		}
		// panels[9].add(buttonZoomOut);
		// panels[9].add(buttonZoomIn);
	}

	private void addPanels(/* JFrame window */) {
		for (JPanel pane : panels) {
			// add(pane);
			panelMaster.add(pane);
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

		// buttonZoomIn.setToolTipText("Zoom in");
		// buttonZoomOut.setToolTipText("Zoom out");
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

//-----------------------------------------------------------------------------------------------------------
	private void initFuncPad() {
		for (int c = 0; c <= 4; c++) {
			funcPad[c] = new JButton(textButtons[c][1]);
		}
	}

	private void funcPadSetVisible(boolean visible) {
		panels[8].setVisible(visible);
		// for (JButton pad : funcPad) {
		// pad.setVisible(visible);
		// }
	}

	private JButton getFuncPad(int d) {
		return funcPad[d];
	}

	private JButton getNumPad(final int d) {
		return numPad[d];
	}

	// ----------------------------------------------------------------------------------------------------------------------------
	// -----------------------------------------INIT
	// COMPONENTS--------------------------------------------------------------------
	private void initComponents() {

		panelMaster = new JPanel();

		labelCalc = new JLabel(calcLabelEmpty);

		labelErgebnis = new JLabel(platz);

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
				insertTextInField(buttonPlus.getText(), false);
			}
		});
		buttonMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertTextInField(buttonMinus.getText(), false);
			}
		});
		buttonTimes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertTextInField(buttonTimes.getText(), false);
			}
		});
		buttonDivide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertTextInField(buttonDivide.getText(), false);
			}
		});
		buttonPow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertTextInField(buttonPow.getText(), false);
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
				setPanel0ToMode(mode);

				switch (mode) {
				case 1:
					funcPadSetVisible(false);
					// GuiTaschenrechner.this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
					// panelMaster.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
					setSizeOfComponents(sizeFactor, 0);
					break;
				case 2:
					funcPadSetVisible(true);
					// GuiTaschenrechner.this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT + 40);
					// panelMaster.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT + 40));
					setSizeOfComponents(sizeFactor, 40);
					break;
				case 3:
					funcPadSetVisible(true);
					// GuiTaschenrechner.this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT + 40);
					// panelMaster.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT + 40));
					setSizeOfComponents(sizeFactor, 40);
				}
				for (int i = 0; i <= 4; i++) {
					getFuncPad(i).setText(textButtons[i][mode - 1]);
				}

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
		}

		initFuncPad();
		for (JButton pad : funcPad) {
			pad.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					buttonActionOnPressed(pad);
				}
			});
		}
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
		labelFuncMid = new JLabel("(");
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

		// buttonZoomIn = new JButton();
		// buttonZoomOut = new JButton();
		// buttonZoomIn.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// if (sizeFactor <= 2.5) {
		// sizeFactor += 0.1;
		// changeSizeWindow(sizeFactor);
		// }
		// }
		// });
		// buttonZoomOut.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// if (sizeFactor >= 1.3) {
		// sizeFactor -= 0.1;
		// changeSizeWindow(sizeFactor);
		// }
		// }
		// });

// last bracket
	}

	private void setPanel0ToMode(int modi) {
		switch (modi) {
		case 1:
			labelFuncOpn.setText("");
			labelFuncOpn.setVisible(false);
			labelFuncMid.setVisible(false);
			labelFuncCls.setVisible(false);

			labelCalc.setVisible(true);
			logWithBaseFocus = 0;
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			break;
		case 2:
			labelFuncOpn.setVisible(true);
			labelFuncMid.setVisible(false);
			labelFuncCls.setVisible(true);
			labelCalc.setVisible(true);
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			logWithBaseFocus = 0;
			break;
		case 3:
			labelCalc.setVisible(true);
			labelFuncMid.setVisible(false);
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			logWithBaseFocus = 0;
			break;
		}
	}

	private void buttonActionOnPressed(JButton button) {
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
			break;
		case 2:
			labelFuncOpn.setText(button.getText() + "(");
			if (button.equals(getFuncPad(0))) {
				calcMode = 6;
			} else if (button.equals(getFuncPad(1))) {
				calcMode = 7;
			} else if (button.equals(getFuncPad(2))) {
				calcMode = 8;
			} else if (button.equals(getFuncPad(3))) {
				calcMode = 9;
			} else if (button.equals(getFuncPad(4))) {
				calcMode = 10;
			}
			break;
		case 3:
			labelFuncOpn.setText(button.getText() + "(");
			if (button.equals(getFuncPad(0))) {
				calcMode = 11;
				labelFuncOpn.setText("log");
				log = true;

			} else if (button.equals(getFuncPad(1))) {
				calcMode = 12;
			} else if (button.equals(getFuncPad(2))) {
				calcMode = 13;
			} else if (button.equals(getFuncPad(3))) {
				calcMode = 14;
			} else if (button.equals(getFuncPad(4))) {
				calcMode = 15;
			}

			break;
		}
		if (log == true) {
			labelFuncMid.setVisible(true);
			buttonLogBase.setVisible(true);
			buttonLogExp.setVisible(true);
			labelCalc.setVisible(false);

			buttonLogBase.setVisible(true);
			buttonLogExp.setVisible(true);

		} else {
			labelCalc.setVisible(true);
			buttonLogBase.setVisible(false);
			buttonLogExp.setVisible(false);
			labelFuncMid.setVisible(false);
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

	private void initMenuBar(String titel) {
		menu.items[0][0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sizeFactor <= 2.5) {
					sizeFactor += 0.1;
					changeSizeWindow(sizeFactor);
				}
			}
		});
		menu.items[0][1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sizeFactor >= 1.3) {
					sizeFactor -= 0.1;
					changeSizeWindow(sizeFactor);
				}
			}
		});

		menu.items[0][2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (appearanceMode.equals(bright)) {
					appearanceMode = dark;
				} else if (appearanceMode.equals(dark)) {
					appearanceMode = bright;
				}
				setColorOfComponents(appearanceMode);
				menu.setColorOfComponents(appearanceMode);

				// Log.console(menu.items[0][2].getSize(new Dimension()).toString());
			}
		});
		menu.items[1][0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mode;
				if (appearanceMode.equals(dark)) {
					mode = "dark";
				} else {
					mode = "light";
				}
				KeyEventClass extraWin = new KeyEventClass(titel + " (copy)", mode, GuiTaschenrechner.this);
				extraWin.tr.sizeFactor = GuiTaschenrechner.this.sizeFactor;
				extraWin.tr.changeSizeWindow(extraWin.tr.sizeFactor);
				extraWin.tr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dispose on close
				extraWin.tr.setColorOfComponents(appearanceMode);
				extraWin.tr.menu.setColorOfComponents(appearanceMode);
				// GuiTaschenrechner.this.setTitle(titelText + " (main)");
			}
		});
		menu.items[1][1].addActionListener(new ActionListener() {
			// opens help
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpWindow help = new HelpWindow("Help", 400, 520, 1.5f, appearanceMode);
				help.addKeyListener(new WindowCloseEvent(help));
			}
		});

		menu.items[1][2].addActionListener(new ActionListener() {
			// opens github
			@Override
			public void actionPerformed(ActionEvent e) {
				NetLink.openUrl("https://github.com/thevicraft/Calculator/issues");
			}
		});
		menu.items[1][3].addActionListener(new ActionListener() {
			// join discord server
			@Override
			public void actionPerformed(ActionEvent e) {
				NetLink.openUrl("https://discord.com/invite/fhAdBZEQ2J");
			}
		});
		menu.items[1][4].addActionListener(new ActionListener() {
			// opens github
			@Override
			public void actionPerformed(ActionEvent e) {
				CreditsWindow credit = new CreditsWindow("About", 340, 200, 1.5f, appearanceMode);
				credit.addKeyListener(new WindowCloseEvent(credit));
			}
		});

	}

}
