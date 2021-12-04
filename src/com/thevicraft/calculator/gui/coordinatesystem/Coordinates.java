package com.thevicraft.calculator.gui.coordinatesystem;

import java.awt.Point;

import com.thevicraft.calculator.gui.Graph;

public class Coordinates {
	private Point origin;
	private int scaleFactor;
	private Graph panel;
	private int size;

	public Coordinates(Point origin, int scale, Graph d, int size, boolean makeAxis) {
		this.origin = origin;
		this.scaleFactor = scale;
		panel = d;
		this.size = size;
		if (makeAxis == true) {
			panel.makeAxes(origin);
		}
	}

	public void addPoint(float x, float y) {
		// mit scale faktor
		BetterPoint add = new BetterPoint((int)(x * scaleFactor),(int)( y * scaleFactor));
		panel.drawPoint(add.relativeTo(origin), size);
	}

}
