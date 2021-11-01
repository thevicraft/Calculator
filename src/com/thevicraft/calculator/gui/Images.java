package com.thevicraft.calculator.gui;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Images {

	public static final ImageIcon ICON = new ImageIcon("src/resources/window-icon.png");
	public static final ImageIcon ICON_WARNING = new ImageIcon("src/resources/window-icon-warning.png");

	public static Image image(final ImageIcon PIC) {

		return PIC.getImage();
	}
	
	public static Image imageFromPath(String path) {
		ImageIcon picture = new ImageIcon(path);
		return picture.getImage();
	}
}
