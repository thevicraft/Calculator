package com.thevicraft.calculator.gui;

import com.thevicraft.calculator.api.StringCalcFunctions;
import com.thevicraft.calculator.gui.Images.Pictures;
import com.thevicraft.calculator.gui.component.RoundedBorder;
import com.thevicraft.calculator.integration.NetLink;
import com.thevicraft.keyboard.activity.KeyEventClass;
import com.thevicraft.keyboard.activity.WindowCloseEvent;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.mariuszgromada.math.mxparser.mXparser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Constructs the default Simple Calculator GUI
 * 
 * @author thevicraft
 * @version 5.0.pre2
 * @category JFrame
 * 
 */

@SuppressWarnings("serial")
public class GuiTaschenrechner extends JFrame {
	private String platz = "                                                 ";
	/**
	 * Current mode of calculator
	 * 
	 * @see GuiTaschenrechner
	 * @author thevicraft
	 */
	public int mode = 1; // default mode, do not change
	public int funcMode = 0;

	/**
	 * If set to 0 all available unicode characters will be used as button text,
	 * else if set to 1, unicode characters will not be used
	 * 
	 * @author thevicraft
	 * @see GuiTaschenrechner
	 */
	public int unicode = 0;
	/**
	 * Number of modes that are implemented in Simple Calculator
	 * 
	 * @category constant values
	 * @see GuiTaschenrechner
	 * @author thevicraft
	 */
	static final int MODES = 3;
	protected int calcMode;
	final String calcLabelEmpty = ""; // " "

	public JLabel labelErgebnis;
	// ----------------------------------------------------------------------------------
	public JTextField labelCalc;
	// ----------------------------------------------------------------------------------

	public JButton buttonErgebnis;
	public JButton buttonDelete;
	public JButton buttonDeleteLast;
	public JButton buttonPlus;
	public JButton buttonMinus;

	public JButton buttonTimes;
	public JButton buttonDivide;
	public JButton buttonPow;
	public JButton buttonChangeMode;

	public JButton buttonCopyResult;

	public JButton buttonSignMinus;
	public JButton buttonMathPi;
	public JButton buttonMathE;
	public JButton buttonBracketOpn;
	public JButton buttonBracketCls;

	public JButton buttonXPower2;
	public JButton buttonXPower3;
	public JButton buttonXPowerReverse;

	protected JLabel labelFuncOpn;

	public GuiMenuBar menu;

	public Loader loader;

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
	public int WINDOW_HEIGHT = 400; // 400
	public int PANEL_WIDTH = 340;
	public int PANEL_HEIGHT = 390; // 390
	public int BUTTON_WIDTH = 60;
	public int BUTTON_HEIGHT = 25;
	private final int PANELS = 8;

	public JButton[] numPad = new JButton[13];
	public JButton[] funcPad = new JButton[5];
	JPanel[] panels = new JPanel[PANELS + 1];
	protected JPanel panelMaster;

	/**
	 * Unicode constants
	 * 
	 * @category constant values
	 * @author thevicraft
	 */
	public static final String constants[] = { "\u213c", "\u212f", "\u03c0", "\u2107" }; // pi, e
	String[] minusSign = { "\u00B1", "(-)" };
	/**
	 * X string constant for function declaration
	 * 
	 * @category constant values
	 * @author thevicraft
	 */
	public static final String X = "x";// = "\uD835\uDC65";
	/**
	 * unicode constant for f of f(x)
	 * 
	 * @category constant values
	 * @author thevicraft
	 */
	public static final String f[] = { "\u2a0d", "f" }; // "\u2a0d";
	protected float ergebnis;
	/**
	 * Default scale factor of Calculator on startup
	 * 
	 * @category constant values
	 * @see GuiTaschenrechner
	 * @author thevicraft
	 */
	float sizeFactor = 1.5f;

	public Color appearanceMode;

	private int labelCalcLength = 300;

	// Fonts
	// -----------------------------------------------------------------------------------------------------------
	public static Font resultBold = new Font("System", Font.BOLD, 17); // davor war es groesse 12
	public static Font calcBold = new Font("System", Font.BOLD, 17);
	public static Font small = new Font("System", Font.BOLD, 11);
	public static Font normal = new Font("System", Font.BOLD, 12);
	public static Font xsmall = new Font("System", Font.BOLD, 10);
	public static Font extremesmall = new Font("System", Font.PLAIN, 10);
	// -----------------------------------------------------------------------------------------------------------------
	// Calculation calc = new Calculation();
	StringCalcFunctions calcString = new StringCalcFunctions(GuiTaschenrechner.this);
	// Dimensions
	// ------------------------------------------------------------------------------------------------------
	Dimension buttonStandartSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

	// Images--------------------------------------------------------------------------------------------------
	Images icon = new Images();

	/**
	 * Color constant for dark mode
	 * 
	 * @category constant values
	 * @author thevicraft
	 */
	public static final Color dark = Color.DARK_GRAY;
	/**
	 * Color constant for white mode
	 * 
	 * @category constant values
	 * @author thevicraft
	 */
	public static final Color bright = Color.white;

	public static String operator[][] = { { "+", "-", "\u00D7", "\u00F7", "^" }, { "+", "-", "*", "/", "^" } };

	public static String textButtons[][] = { { "log10", "log" }, { " √ ", "!" }, { "sin", "asin" }, { "cos", "acos" },
			{ "tan", "atan" } };

	public GuiTaschenrechner(String titel, String darkLight, JFrame location) {

		mXparser.setDegreesMode();
		loader = new Loader("Calculator", Pictures.values().length * 2 + 30);
		loader.setVisible(true);
		switch (darkLight) {
		case "dark":
			appearanceMode = dark;
			break;
		case "light":
			appearanceMode = bright;
		default:
			appearanceMode = bright;
		}
		if (!Images.isLoaded()) {
			Images.initImages(GuiTaschenrechner.this, loader);
		}

		setTitle(titel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setMinimumSize(new Dimension(250, 250));
		setResizable(true);
		loader.iterate();

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

		this.setIconImage(Images.getDefaultImageIcon(Pictures.WINDOW_ICON).getImage());

		changeSizeWindow(sizeFactor);

		funcPadSetVisible(true);

		menu = new GuiMenuBar(appearanceMode);
		initMenuBar(titel);

		setJMenuBar(menu);

		for (int i = 0; i <= PANELS; i++) {
			removeFocusFromComponent(panels[i].getComponents());
			setActionListenerToComponent(panels[i].getComponents());
		}
		loader.iterate();
		labelCalc.setEditable(true);
		labelCalc.setFocusable(true);

		loader.dispose();

		setUnicode(true);

		setVisible(true);

		Thread rgb = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Color[] rgb = { Color.red, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue, Color.pink,
						Color.magenta };
				List<Color> colors = new ArrayList<Color>();
				for (int r = 0; r < 100; r++)
					colors.add(new Color(r * 255 / 100, 255, 0));
				for (int g = 100; g > 0; g--)
					colors.add(new Color(255, g * 255 / 100, 0));
				for (int b = 0; b < 100; b++)
					colors.add(new Color(255, 0, b * 255 / 100));
				for (int r = 100; r > 0; r--)
					colors.add(new Color(r * 255 / 100, 0, 255));
				for (int g = 0; g < 100; g++)
					colors.add(new Color(0, g * 255 / 100, 255));
				for (int b = 100; b > 0; b--)
					colors.add(new Color(0, 255, b * 255 / 100));
				colors.add(new Color(0, 255, 0));

				Color[] c = colors.toArray(new Color[colors.size()]);

				while (isDisplayable()) {
					for (Color d : c) {
						buttonChangeMode.setForeground(d);
						// labelCalc.setCaretColor(d);
						try {
							Thread.sleep(3);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		rgb.start();
	}

	private void removeFocusFromComponent(Component[] container) {
		for (Component child : container) {
			if (child instanceof JButton) {
				child.setFocusable(false);
			}
		}
		loader.iterate();
	}

	private void setActionListenerToComponent(Component[] buttons) {
		for (Component child : buttons) {
			if (child instanceof JButton) {
				((JButton) child).addActionListener(new ButtonActionListener(this));
			}
		}
		loader.iterate();
	}

	public void changeSizeWindow(float factor) {
		setSizeOfComponents(factor, labelCalcLength);
		setIconOfComponents(factor);
		setFontOfComponents(factor);
		loader.iterate();

	}

	private void setIconOfComponents(float factor) {
		Images.setButtonIcon(buttonCopyResult, Pictures.COPY);
		// buttonAppearMode.setIcon(
		// Images.scaleImageIconFromDefault(Pictures.DARK_LIGHT_MODE, (int) (30 *
		// factor), ((int) (30 * factor))));
		// buttonZoomIn.setIcon(
		// Images.scaleImageIconFromDefault(Pictures.ZOOM_IN, (int) (25 * factor),
		// ((int) (25 * factor))));
		// buttonZoomOut.setIcon(
		// Images.scaleImageIconFromDefault(Pictures.ZOOM_OUT, (int) (25 * factor),
		// ((int) (25 * factor))));
		loader.iterate();
	}

	/**
	 * Used to set unicode characters or use normal ascii letters
	 * 
	 * @param yes - If set to true unicode characters will be used, if set to false
	 *            ascii letters will be used
	 * @author thevicraft
	 * @see GuiTaschenrechner
	 */
	public void setUnicode(boolean yes) {
		if (yes)
			unicode = 0;
		else
			unicode = 1;

		buttonPlus.setText(operator[unicode][0]);
		buttonMinus.setText(operator[unicode][1]);
		buttonTimes.setText(operator[unicode][2]);
		buttonDivide.setText(operator[unicode][3]);
		buttonPow.setText(operator[unicode][4]);

		buttonSignMinus.setText(minusSign[unicode]);
	}

	void setSizeOfComponents(float factor, int labelCalcLength) {
		buttonStandartSize = new Dimension((int) (factor * BUTTON_WIDTH), (int) (factor * BUTTON_HEIGHT));

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

		buttonChangeMode.setPreferredSize(buttonStandartSize);
		buttonXPower2.setPreferredSize(buttonStandartSize);
		buttonXPower3.setPreferredSize(buttonStandartSize);
		buttonXPowerReverse.setPreferredSize(buttonStandartSize);

		buttonCopyResult.setPreferredSize(
				new Dimension((int) ((BUTTON_HEIGHT - 5) * factor), (int) ((BUTTON_HEIGHT - 5) * factor)));

		for (JButton c : funcPad) {
			c.setPreferredSize(buttonStandartSize);
		}

		labelCalc.setPreferredSize(new Dimension((int) (labelCalcLength * factor), (int) (25 * factor)));

		// panels default size 300, 30
		panels[0].setPreferredSize(panelSize);
		panels[1].setPreferredSize(panelSize);
		setSize((int) (WINDOW_WIDTH * factor), (int) ((WINDOW_HEIGHT) * (factor * 0.88)));
		panelMaster.setPreferredSize(
				new Dimension((int) (PANEL_WIDTH * factor), (int) ((PANEL_HEIGHT) * (factor * 0.88))));

		loader.iterate();
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

		buttonCopyResult.setBackground(bright);

		for (JPanel p : panels) {
			p.setBackground(mode);
		}
		panelMaster.setBackground(mode);
		getContentPane().setBackground(mode);

		if (mode.equals(dark)) {
			labelCalc.setBackground(dark);
			labelCalc.setForeground(bright);
			labelErgebnis.setForeground(bright);
			labelFuncOpn.setForeground(bright);
			labelCalc.setCaretColor(Color.white);
			buttonChangeMode.setForeground(Color.magenta);
			for (JButton x : funcPad) {
				x.setForeground(bright);
				x.setBackground(dark);
			}

		} else if (mode.equals(bright)) {
			labelCalc.setBackground(bright);
			labelCalc.setForeground(dark);
			labelErgebnis.setForeground(dark);
			labelFuncOpn.setForeground(dark);
			labelCalc.setCaretColor(Color.black);
			buttonChangeMode.setForeground(Color.blue);
			for (JButton x : funcPad) {
				x.setForeground(dark);
				x.setBackground(bright);
			}

		} else {
			labelCalc.setForeground(dark);
			labelErgebnis.setForeground(dark);
			labelFuncOpn.setForeground(dark);
			labelCalc.setCaretColor(Color.white);
			buttonChangeMode.setForeground(Color.magenta);
			for (JButton x : funcPad) {
				x.setForeground(bright);
				x.setBackground(dark);
			}

		}
		loader.iterate();

	}

	private void setFontOfComponents(float factor) {
		resultBold = new Font("System", Font.BOLD, (int) (17 * factor)); // davor war es groesse 12
		calcBold = new Font("System", Font.BOLD, (int) (17 * factor));
		small = new Font("System", Font.BOLD, (int) (11 * factor));
		normal = new Font("System", Font.BOLD, (int) (12 * factor));
		xsmall = new Font("System", Font.BOLD, (int) (10 * factor));
		extremesmall = new Font("System", Font.PLAIN, (int) (10 * factor));

		labelErgebnis.setFont(resultBold);
		labelCalc.setFont(calcBold);
		labelFuncOpn.setFont(calcBold);

		buttonXPowerReverse.setFont(extremesmall);

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

		loader.iterate();
	}

	private void addComponentsToPanels(/* JFrame window */) {

		panels[0].add(labelFuncOpn);

		panels[0].add(labelCalc);

		labelFuncOpn.setVisible(false);

		panels[1].add(labelErgebnis);
		panels[1].add(buttonCopyResult);

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
		loader.iterate();
	}

	private void addPanels() {
		for (JPanel pane : panels) {
			panelMaster.add(pane);
		}
		loader.iterate();
	}

	private void initPanels() {
		for (int i = 0; i <= PANELS; i++) {
			panels[i] = new JPanel();
		}
		loader.iterate();
	}

	private void initToolTips() {
		buttonDelete.setToolTipText("Delete Calculation");
		getNumPad(BUTTON__ANS).setToolTipText("Get former result");
		getNumPad(BUTTON__E).setToolTipText("*10^x");
		buttonChangeMode.setToolTipText("Change mode to other Calculations (sin, cos, tan)");
		buttonErgebnis.setToolTipText("Calculate");
		buttonCopyResult.setToolTipText("Copy to Clipboard");

		loader.iterate();
	}

	private void initNumPads() {
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
		loader.iterate();
	}

//-----------------------------------------------------------------------------------------------------------
	private void initFuncPad() {
		for (int c = 0; c <= 4; c++) {
			funcPad[c] = new JButton(textButtons[c][0]);
		}
		loader.iterate();
	}

	void funcPadSetVisible(boolean visible) {
		panels[8].setVisible(visible);
	}

	JButton getFuncPad(int d) {
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
		// --------------------------------------------------------------------------------
		labelCalc = new JTextField();
		// -------------------------------------------------------------------------------

		labelErgebnis = new JLabel("");

		buttonErgebnis = new JButton("=");

		buttonDelete = new JButton("AC");

		buttonPlus = new JButton(operator[unicode][0]);
		buttonMinus = new JButton(operator[unicode][1]);
		buttonTimes = new JButton(operator[unicode][2]);
		buttonDivide = new JButton(operator[unicode][3]);
		buttonPow = new JButton(operator[unicode][4]);

		buttonChangeMode = new JButton("M");
		buttonChangeMode.setBorder(new RoundedBorder(30));
		buttonChangeMode.setOpaque(false);

		initNumPads();
		for (JButton button : numPad) {

		}

		initFuncPad();
		for (JButton pad : funcPad) {
			pad.setBorder(new RoundedBorder(10));
			pad.setOpaque(false);
		}
		buttonSignMinus = new JButton(minusSign[unicode]);
		buttonMathPi = new JButton(constants[0]);
		buttonMathE = new JButton(constants[1]);

		buttonBracketOpn = new JButton("(");
		buttonBracketCls = new JButton(")");

		buttonDeleteLast = new JButton("DEL");

		buttonXPower2 = new JButton("^2");
		buttonXPower3 = new JButton("^3");
		buttonXPowerReverse = new JButton("^(-1)");

		labelFuncOpn = new JLabel();

		buttonCopyResult = new JButton();
		buttonCopyResult.setBorder(new RoundedBorder(10));
		buttonCopyResult.setOpaque(false);

		loader.iterate();

// last bracket
	}

	void changeMode() {
		this.setPanel0ToMode(this.mode);
		final int normal = 300;
		final int depressed = 240;

		switch (this.mode) {
		case 1:
			if (menu.deg.getState() == true) {
				mXparser.setDegreesMode();
			} else if (menu.rad.getState() == true) {
				mXparser.setRadiansMode();
			}
			numPad[BUTTON__ANS].setText("ANS");
			buttonErgebnis.setText("=");
			buttonErgebnis.setIcon(null);
			buttonErgebnis.setBackground(Color.green);
			this.funcPadSetVisible(true);
			labelCalcLength = normal;
			this.setSizeOfComponents(this.sizeFactor, labelCalcLength);
			break;
		case 2:
			mXparser.setRadiansMode();
			numPad[BUTTON__ANS].setText(X);
			buttonErgebnis.setText("");
			buttonErgebnis.setIcon(Images.scaleImageIconFromDefault(Pictures.GRAPH_ICON,
					(int) ((BUTTON_HEIGHT * 1.28) * sizeFactor), (int) (BUTTON_HEIGHT * sizeFactor)));
			buttonErgebnis.setBackground(Color.white);
			this.funcPadSetVisible(true);
			labelCalcLength = depressed - 10;
			this.setSizeOfComponents(this.sizeFactor, labelCalcLength);
			break;
		case 3:
			if (menu.deg.getState() == true) {
				mXparser.setDegreesMode();
			} else if (menu.rad.getState() == true) {
				mXparser.setRadiansMode();
			}
			numPad[BUTTON__ANS].setText("ANS");
			buttonErgebnis.setText("");

			Images.setButtonIcon(buttonErgebnis, Pictures.UNITS);
			buttonErgebnis.setBackground(Color.white);
			this.funcPadSetVisible(false);
			labelCalcLength = normal;
			this.setSizeOfComponents(this.sizeFactor, labelCalcLength);
			break;
		}
	}

	void setPanel0ToMode(int modi) {
		switch (modi) {
		case 1:
			labelFuncOpn.setText("");
			labelFuncOpn.setVisible(false);

			menu.setUnitVisible(false);
			break;
		case 2:
			labelFuncOpn.setText(f[unicode] + "(" + X + ") = ");
			labelFuncOpn.setVisible(true);

			menu.setUnitVisible(false);
			break;
		case 3:
			labelFuncOpn.setText("");
			labelFuncOpn.setVisible(false);

			menu.setUnitVisible(true);
			break;
		}
	}

	void insertTextInField(String text, boolean deleteall) {
		if (deleteall == false) {

			labelCalc.setText(labelCalc.getText() + text);

		} else {

			labelCalc.setText(calcLabelEmpty);

		}
	}

	String getTextInField() {

		return labelCalc.getText();

	}

	void setTextInField(String text) {

		labelCalc.setText(text);

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
		loader.iterate();
		menu.items[0][1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sizeFactor >= 1.3) {
					sizeFactor -= 0.1;
					changeSizeWindow(sizeFactor);
				}
			}
		});
		loader.iterate();

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
			}
		});
		menu.items[0][3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (unicode == 0) {
					unicode = 1;
					setUnicode(false);
				} else if (unicode == 1) {
					unicode = 0;
					setUnicode(true);
				}
			}
		});
		loader.iterate();
		menu.items[1][0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mode;
				if (appearanceMode.equals(dark)) {
					mode = "dark";
				} else {
					mode = "light";
				}
				KeyEventClass extraWin;
				extraWin = new KeyEventClass(titel + " (copy)", mode, GuiTaschenrechner.this);
				extraWin.tr.sizeFactor = GuiTaschenrechner.this.sizeFactor;
				extraWin.tr.changeSizeWindow(extraWin.tr.sizeFactor);
				extraWin.tr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // dispose on close
				extraWin.tr.setColorOfComponents(appearanceMode);
				extraWin.tr.menu.setColorOfComponents(appearanceMode);
			}
		});
		loader.iterate();
		menu.items[1][1].addActionListener(new ActionListener() {
			// opens help
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpWindow help = new HelpWindow("Help", 400, 520, 1.5f, appearanceMode);
				help.addKeyListener(new WindowCloseEvent(help));
			}
		});
		loader.iterate();
		menu.items[1][2].addActionListener(new ActionListener() {
			// opens help
			@Override
			public void actionPerformed(ActionEvent e) {
				ShortcutWindow shorts = new ShortcutWindow("Shortcuts", 400, 380, 1.5f, appearanceMode);
				shorts.addKeyListener(new WindowCloseEvent(shorts));
			}
		});
		loader.iterate();
		menu.items[1][3].addActionListener(new ActionListener() {
			// opens github
			@Override
			public void actionPerformed(ActionEvent e) {
				NetLink.openUrl("https://github.com/thevicraft/Calculator/issues");
			}
		});
		loader.iterate();
		menu.items[1][4].addActionListener(new ActionListener() {
			// join discord server
			@Override
			public void actionPerformed(ActionEvent e) {
				NetLink.openUrl("https://discord.com/invite/fhAdBZEQ2J");
			}
		});
		loader.iterate();
		menu.items[1][5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreditsWindow credit = new CreditsWindow("About", 340, 200, 1.5f, appearanceMode);
				credit.addKeyListener(new WindowCloseEvent(credit));
			}
		});
		loader.iterate();
		menu.items[2][0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 1;
				changeMode();
			}
		});
		loader.iterate();
		menu.items[2][1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 2;
				changeMode();
			}
		});
		loader.iterate();
		menu.items[2][2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 3;
				changeMode();
			}
		});
		loader.iterate();
	}

}
