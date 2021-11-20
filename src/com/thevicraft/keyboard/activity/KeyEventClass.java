package com.thevicraft.keyboard.activity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.thevicraft.calculator.gui.GuiTaschenrechner;

public class KeyEventClass implements KeyListener {
	public GuiTaschenrechner tr;
	String[] numPadLabel = new String[13];
	public KeyEventClass(String titel, String mode, JFrame location) {
		tr = new GuiTaschenrechner(titel, mode, location);
		tr.addKeyListener(this);
		for(int i = 0; i <= 12; i++){
			numPadLabel[i] = tr.numPad[i].getText();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch(key) {
		case 10:
			tr.buttonErgebnis.doClick();
			break;
		case 8:
			tr.buttonDeleteLast.doClick();
			break;
		case 127:
			tr.buttonDelete.doClick();
			break;
		case 27:
			//programm beenden
			tr.dispose();
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		// TODO Auto-generated method stub
		Character key = e.getKeyChar();
		for(int i = 0; i <= 12; i++) {
			if(Character.toString(key).equals(numPadLabel[i])) {
				tr.numPad[i].doClick();
				break;
			}else if(Character.toString(key).equals(",")) {
				tr.numPad[10].doClick();
				break;
			}
		}
		switch(key) {
		case '+':
			tr.buttonPlus.doClick();
			break;
		case '-':
			tr.buttonMinus.doClick();
			break;
		case '*':
			tr.buttonTimes.doClick();
			break;
		case '/':
			tr.buttonDivide.doClick();
			break;
		case '^':
			tr.buttonPow.doClick();
			break;
		case 'n':
			tr.buttonMathPi.doClick();
			break;
		case 'e':
			tr.buttonMathE.doClick();
			break;
		case '(':
			tr.buttonBracketOpn.doClick();
			break;
		case ')':
			tr.buttonBracketCls.doClick();
			break;
		case 'm':
			tr.buttonChangeMode.doClick();
			break;
		case 'M':
			tr.buttonChangeMode.doClick();
			break;
		}
		

	}

}
