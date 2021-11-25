package com.thevicraft.calculator.gui.coordinatesystem;

import java.awt.Point;

@SuppressWarnings("serial")
public class BetterPoint extends Point {
	public BetterPoint(int relativeX, int relativeY) {
		this.x = relativeX;
		this.y = relativeY;
	}

	public BetterPoint relativeTo(Point origin) {
		int x0 =(int) origin.getX();
		int y0 =(int) origin.getY();
		BetterPoint returnValue = new BetterPoint(x+x0, y0-y);
		//if((x+x0 < 0) || (y+y0 < 0)) {
		//	return new BetterPoint(0,0);
		//}
		return returnValue;
	}
}

