package com.thevicraft.calculator.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import com.thevicraft.calculator.api.StringCalcFunctions;

public class ButtonActionListener implements ActionListener{
	private GuiTaschenrechner window;
	private JButton buttonPressed;
	
	public ButtonActionListener(GuiTaschenrechner window) {
		this.window = window;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		buttonPressed = (JButton) arg0.getSource();
		executeButtonAction(buttonPressed);
	}
	
	@SuppressWarnings("static-access")
	private void executeButtonAction(JButton d) {
		if(d.equals(window.buttonErgebnis)) {
			try {
				window.labelErgebnis.setText(window.calcString.calcResultFromString(window.labelCalc.getText(), window.mode, window.calcMode,
						window.buttonLogBase.getText(), window.buttonLogExp.getText(),window));
			} catch (Exception er) {

			}
		}
		else if(d.equals(window.buttonDelete)) {
			window.insertTextInField(window.calcLabelEmpty, true);
		} else if(d.equals(window.buttonChangeMode)) {
			window.mode++;
			if (window.mode > window.MODES) {
				window.mode = 1;
			}
			window.setPanel0ToMode(window.mode);

			switch (window.mode) {
			case 1:
				window.funcPadSetVisible(false);
				window.setSizeOfComponents(window.sizeFactor, 0);
				break;
			case 2:
				window.funcPadSetVisible(true);
				window.setSizeOfComponents(window.sizeFactor, 40);
				break;
			case 3:
				window.funcPadSetVisible(true);
				window.setSizeOfComponents(window.sizeFactor, 40);
			}
			for (int i = 0; i <= 4; i++) {
				window.getFuncPad(i).setText(window.textButtons[i][window.mode - 1]);
			}

			window.logWithBaseFocus = 0;
			window.insertTextInField(window.calcLabelEmpty, true);
		} else if(arrayToList(window.funcPad).contains(d)) {
			window.buttonActionOnPressed(d);
		} else if(d.equals(window.buttonSignMinus)){
			window.insertTextInField(" -", false);
		} else if(d.equals(window.buttonMathPi)) {
			window.insertTextInField(window.constants[0], false);
		} else if(d.equals(window.buttonMathE)) {
			window.insertTextInField(window.constants[1], false);
		} else if(d.equals(window.buttonDeleteLast)) {
			// delete all if there is only one character
			if (window.getTextInField().length() == 1) {
				window.setTextInField(window.calcLabelEmpty);
			}

			boolean foundOperator = false;
			String ops[] = new StringCalcFunctions().calcOperator;
			// test for an operator to delete the empty spaces with it
			try {
				for (String o : ops) {
					if (window.getTextInField().substring(window.getTextInField().length() - 3, window.getTextInField().length())
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
					window.setTextInField(window.getTextInField().substring(0, window.getTextInField().length() - 3));

				} else {
					// normal delete of last sign, just inserts same text without last character
					if (window.getTextInField().substring(window.getTextInField().length() - 2, window.getTextInField().length())
							.equals(" -")) {
						// löscht auch ein leerzeichen vom rechenoperator wenn eins da ist
						window.setTextInField(window.getTextInField().substring(0, window.getTextInField().length() - 2));
					} else {
						window.setTextInField(window.getTextInField().substring(0, window.getTextInField().length() - 1));
					}

				}
			} catch (Exception a) {
			}
		} else if(d.equals(window.buttonXPowerReverse)) {
			window.insertTextInField("^( -1)", false);
		} else if(d.equals(window.buttonLogExp)) {
			window.logWithBaseFocus = 1;
			window.buttonLogBase.setBackground(Color.white);
			window.buttonLogExp.setBackground(Color.LIGHT_GRAY);
		} else if(d.equals(window.buttonLogBase)) {
			window.logWithBaseFocus = 2;
			window.buttonLogBase.setBackground(Color.LIGHT_GRAY);
			window.buttonLogExp.setBackground(Color.white);
		}
		
		
		
		
		
		else {
			window.insertTextInField(d.getText(), false);
		}
	}
	
	private List<JButton> arrayToList(JButton[] arr) {
	    List<JButton> result= new ArrayList<JButton>(arr.length);
	    for (JButton i : arr) {
	        result.add(i);
	    }
	    return result;
	}

}