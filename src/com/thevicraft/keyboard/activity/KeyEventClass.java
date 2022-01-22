package com.thevicraft.keyboard.activity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.GuiTaschenrechner;

public class KeyEventClass implements KeyListener {
	//private boolean ctrlPressed = false;
	public GuiTaschenrechner tr;
	String[] numPadLabel = new String[13];

	public KeyEventClass(String titel, String mode, JFrame location) {
		tr = new GuiTaschenrechner(titel, mode, location);
		tr.addKeyListener(this);
		for (int i = 0; i <= 12; i++) {
			numPadLabel[i] = tr.numPad[i].getText();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
//		if (key == 17) {
//			ctrlPressed = true;
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		//System.out.println(key);
		switch (key) {
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
			// programm beenden
			tr.dispose();
			break;

		}

//		if (key == 17) {
//			ctrlPressed = false;
//		}
		if ((key == KeyEvent.VK_W) && (e.isControlDown())) {
			tr.menu.items[1][0].doClick();
		}
		if ((key == KeyEvent.VK_F) && (e.isControlDown())) {
			// GeoDraw func =new GeoDraw("Function", 800, 600, 1, GuiTaschenrechner.dark);
			// func.addKeyListener(new WindowCloseEvent(func));
			GeoDrawKeyEvent func = new GeoDrawKeyEvent("Test Window", 800, 600, 1, GuiTaschenrechner.dark,
					"1/x");
		}
//		if ((key == 67) && (ctrlPressed == true)) {
//			tr.buttonCopyResult.doClick();
//			ctrlPressed = false;
//		}
		if ((KeyEvent.VK_C == key) && (e.isControlDown())) {
			tr.buttonCopyResult.doClick();
		}
		if (key == KeyEvent.VK_F1) {
			tr.menu.items[1][1].doClick();
		}
		if ((key == KeyEvent.VK_S) && (e.isControlDown())) {
			tr.menu.items[1][2].doClick();
			//ctrlPressed = false;
		}
//		if (((key == 107) || (key == 521)) && (e.isControlDown())) {
//			tr.menu.items[0][0].doClick();
//			ctrlPressed = false;
//		}
//		if (((key == 109) || (key == 45)) && (e.isControlDown())) {
//			tr.menu.items[0][1].doClick();
//			//ctrlPressed = false;
//		}
		if ((KeyEvent.VK_PLUS == key) && (e.isControlDown())) {
			tr.menu.items[0][0].doClick();
		}
		if ((KeyEvent.VK_MINUS == key) && (e.isControlDown())) {
			tr.menu.items[0][1].doClick();
			//ctrlPressed = false;
		}
		if ((KeyEvent.VK_ADD == key) && (e.isControlDown())) {
			tr.menu.items[0][0].doClick();
		}
		if ((KeyEvent.VK_SUBTRACT == key) && (e.isControlDown())) {
			tr.menu.items[0][1].doClick();
			//ctrlPressed = false;
		}
//		 System.out.println("key :"+key);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		// TODO Auto-generated method stub
		Character key = e.getKeyChar();
		for (int i = 0; i <= 12; i++) {
			if (Character.toString(key).equals(numPadLabel[i])) {
				tr.numPad[i].doClick();
				break;
			} else if (Character.toString(key).equals(",")) {
				tr.numPad[10].doClick();
				break;
			}
		}
		switch (key) {
		case '+':
			if (!e.isControlDown()) {
				tr.buttonPlus.doClick();
				break;
			}
			break;
		case '-':
			if (!e.isControlDown()) {
				tr.buttonMinus.doClick();
				break;
			}
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
		case 'p':
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
		case 'x':
			if (tr.mode == 2) {
				tr.numPad[12].doClick();
			}
		}

	}

}
