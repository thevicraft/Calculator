package com.thevicraft.calculator.gui;

import javax.swing.JPanel;

import com.thevicraft.calculator.api.StringCalcFunctions;
import com.thevicraft.calculator.api.StringCalculation;
import com.thevicraft.calculator.gui.coordinatesystem.BetterPoint;
import com.thevicraft.calculator.gui.coordinatesystem.Coordinates;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	private Point origin;
	private String function;
	private int scaleFactor;

	private int drawFrom;
	private int drawTo;

	private Color mode;

	public Graph(int width, int height, int scaleFactor, int originX, int originY, String function, Color mode) {
		setPreferredSize(new Dimension(width, height));
		setSize(width, height);
		this.scaleFactor = scaleFactor;
		origin = new Point(originX, originY);
		this.function = function;
		this.mode = mode;
	}

	Graphics2D panel;
	// int scaleFactor = 40;

	@Override
	protected void paintComponent(Graphics g) {
		panel = (Graphics2D) g;
		panel.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (mode.equals(GuiTaschenrechner.dark)) {
			panel.setColor(Color.white);
		} else {
			panel.setColor(Color.black);
		}
		// panel.setBackground(Color.white);

		Coordinates cords = new Coordinates(origin, scaleFactor, this, 2, true);
		//cords.addPoint(2, 4);
		//cords.addPoint(2, 2);
		for (float x = drawFrom; x <= drawTo; x += 0.001) { // 0,001
			panel.setColor(Color.green);
			float xcord = x;
			float ycord; /*
							 * = (float) new StringCalculation()
							 * .calcTask(StringCalcFunctions.insertNumberInFunction(function, "X",
							 * Float.toString(x)));
							 */
			String xToInsert;
			if (x < 0) {
				xToInsert = "( " + xcord + ")";
			} else {
				xToInsert = Float.toString(xcord);
			}
			String calcTaskY = StringCalcFunctions.insertNumberInFunction(function, GuiTaschenrechner.X, xToInsert);
			calcTaskY = new StringCalcFunctions().insertConstants(calcTaskY, 0);
			ycord = (float) (new StringCalculation().calcTask(calcTaskY, false));
			cords.addPoint(xcord, ycord);
		}

	}

	public void makeAxes(Point o) {
		int markerLength = scaleFactor / 5;
		// X axis
		panel.drawLine((int) 0, (int) o.getY(), (int) this.getWidth(), (int) o.getY());
		// Y axis
		panel.drawLine((int) o.getX(), (int) 0, (int) o.getX(), (int) this.getHeight());

		// marker für eine einheit auf positiv X
		int counter = 0;
		for (int i = (int) o.getX(); i < this.getWidth(); i += scaleFactor) {
			Point center = new Point(i, (int) o.getY());
			Coordinates axisLine = new Coordinates(center, 1, this, 2, false);
			drawLine(new BetterPoint(0, -markerLength).relativeTo(center), new BetterPoint(0, 0).relativeTo(center));
			panel.drawString(Integer.toString(counter), (float) center.getX(),
					(float) ((float) center.getY() + markerLength * 2.5));
			counter++;
		}
		drawTo = counter;
		// System.out.println("x positiv bis "+counter);
		counter = 0;
		// marker für eine einheit auf negativ X
		for (int i = (int) o.getX(); i > 0; i -= scaleFactor) {
			Point center = new Point(i, (int) o.getY());
			Coordinates axisLine = new Coordinates(center, 1, this, 2, false);
			drawLine(new BetterPoint(0, -markerLength).relativeTo(center), new BetterPoint(0, 0).relativeTo(center));
			if (counter != 0) {
				panel.drawString("-" + Integer.toString(counter), (float) center.getX(),
						(float) (center.getY() + markerLength * 2.5));
			}
			counter++;
		}
		drawFrom = -counter;
		// System.out.println("x negativ bis "+counter);
		counter = 0;
		// marker für eine einheit auf negativ Y
		for (int i = (int) o.getY(); i < this.getHeight(); i += scaleFactor) {
			Point center = new Point((int) o.getX(), i);
			Coordinates axisLine = new Coordinates(center, 1, this, 2, false);
			drawLine(new BetterPoint(0, 0).relativeTo(center), new BetterPoint(-markerLength, 0).relativeTo(center));
			if (counter != 0) {
				panel.drawString("-" + Integer.toString(counter), (float) (center.getX() - markerLength * 2.5),
						(float) center.getY());
			}
			counter++;
		}
		counter = 0;
		// marker für eine einheit auf positiv Y
		for (int i = (int) o.getY(); i > 0; i -= scaleFactor) {
			Point center = new Point((int) o.getX(), i);
			Coordinates axisLine = new Coordinates(center, 1, this, 2, false);
			drawLine(new BetterPoint(0, 0).relativeTo(center), new BetterPoint(-markerLength, 0).relativeTo(center));
			if (counter != 0) {
				panel.drawString(Integer.toString(counter), (float) (center.getX() - markerLength * 2.5),
						(float) center.getY());
			}
			counter++;
		}
		counter = 0;
	}

	public void drawPoint(Point d, int size) {
		panel.fillOval((int) d.getX(), (int) d.getY(), size, size);
	}

	public void drawLine(Point p1, Point p2) {
		panel.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
	}

}
