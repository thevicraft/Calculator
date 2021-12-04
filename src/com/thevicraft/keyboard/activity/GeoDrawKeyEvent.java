package com.thevicraft.keyboard.activity;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.thevicraft.calculator.gui.GeoDraw;

public class GeoDrawKeyEvent implements KeyListener{
	private GeoDraw geo;
	
	public GeoDrawKeyEvent(String titel, int width, int height, float factor, Color mode, String function) {
		geo = new GeoDraw(titel, width, height, factor, mode,function);
		geo.addKeyListener(this);
		geo.addKeyListener(new WindowCloseEvent(geo));
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key == 17) {
			geo.ctrlPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		switch(key) {
		case 37:
			geo.left.doClick();
			break;
		case 38:
			geo.up.doClick();
			break;
		case 39:
			geo.right.doClick();
			break;
		case 40:
			geo.down.doClick();
			break;
		}
		if (key == 17) {
			geo.ctrlPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char key = e.getKeyChar();
		switch(key) {
		case '+':
			geo.zoomIn.doClick();
			break;
		case '-':
			geo.zoomOut.doClick();
			break;
		}
	}

}
