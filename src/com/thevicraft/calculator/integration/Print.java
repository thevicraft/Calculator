package com.thevicraft.calculator.integration;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Graphics;

import javax.swing.*;

import java.awt.Dimension;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.*;

public class Print implements Printable {
	final Component comp;

	public Print(Component comp) {
		this.comp = comp;
	}

	@Override
	public int print(Graphics g, PageFormat format, int page_index) throws PrinterException {
		if (page_index > 0) {
			return Printable.NO_SUCH_PAGE;
		}
		// if(format == null) {
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PageFormat preformat = pjob.defaultPage();
		preformat.setOrientation(PageFormat.PORTRAIT);
		format = pjob.pageDialog(preformat);
		// }

		// get the bounds of the component
		Dimension dim = comp.getSize();
		double cHeight = dim.getHeight();
		double cWidth = dim.getWidth();

		// get the bounds of the printable area
		double pHeight = format.getImageableHeight();
		double pWidth = format.getImageableWidth();

		double pXStart = format.getImageableX();
		double pYStart = format.getImageableY();

		double xRatio = pWidth / cWidth;
		double yRatio = pHeight / cHeight;

		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pXStart, pYStart);
		g2.scale(xRatio, yRatio);
		comp.paint(g2);

		return Printable.PAGE_EXISTS;
	}

	public static void printComponent(Component container) {
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PageFormat preformat = pjob.defaultPage();
		preformat.setOrientation(PageFormat.PORTRAIT);
		PageFormat postformat = pjob.pageDialog(preformat);
		// If user does not hit cancel then print.
		if (preformat != postformat) {
			// Set print component
			pjob.setPrintable(new Print(container), postformat);
			if (pjob.printDialog()) {
				try {
					pjob.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//container.print(container.getGraphics());
	}

	// not working, so it is set to private
	private static void printContainer(JFrame frame, Component container) {
		Toolkit tkp = container.getToolkit();
		PrintJob pjp = tkp.getPrintJob(frame, null, null);
		Graphics g = pjp.getGraphics();
		container.print(g);
		g.dispose();
		pjp.end();
	}
}
