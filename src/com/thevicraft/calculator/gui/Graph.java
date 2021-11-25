package com.thevicraft.calculator.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thevicraft.calculator.api.StringCalcFunctions;
import com.thevicraft.calculator.api.StringCalculation;
import com.thevicraft.calculator.gui.coordinatesystem.BetterPoint;
import com.thevicraft.calculator.gui.coordinatesystem.Coordinates;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {

	private Point origin;
	private String function;

	public Graph(int width, int height, int scaleFactor, int originX, int originY, String function) {
		setPreferredSize(new Dimension(width, height));
		setSize(width, height);
		// setBackground(Color.white);
		this.scaleFactor = scaleFactor;
		origin = new Point(originX, originY);
		this.function = function;

	}

	Graphics2D panel;
	int scaleFactor = 40;

	@Override
	protected void paintComponent(Graphics g) {
		panel = (Graphics2D) g;
		panel.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		panel.setColor(Color.white);
		panel.setBackground(Color.white);
		// panel.drawLine(1, 2, 40, 60);
		// panel.fillOval(10,10,2,2);
		// panel.fillOval(0,0,2,2);
		// Point origin = new Point(40, 100);

		Coordinates cords = new Coordinates(origin, scaleFactor, this, 2, true);
		// cords.addPoint(2, 4);
		for (float x = -7; x <= 7; x += 0.001) {
			panel.setColor(Color.green);
			float xcord = x;
			float ycord; /*= (float) new StringCalculation()
					.calcTask(StringCalcFunctions.insertNumberInFunction(function, "X", Float.toString(x)));*/
			String xToInsert;
			if(x < 0) {
				xToInsert = "( "+xcord+")";
			}else {
				xToInsert = Float.toString(xcord);
			}
			String calcTaskY = StringCalcFunctions.insertNumberInFunction(function, "X", xToInsert);
			calcTaskY = new StringCalcFunctions().insertConstants(calcTaskY, 0);
			ycord = (float)(new StringCalculation().calcTask(calcTaskY,false));
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
