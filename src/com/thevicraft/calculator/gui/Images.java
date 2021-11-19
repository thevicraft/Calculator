package com.thevicraft.calculator.gui;

import javax.swing.ImageIcon;

import com.thevicraft.calculator.console.Log;

import java.awt.Image;
import java.io.File;

public class Images {

	private String[] pictures = { "window-icon.png", "window-icon-warning.png","mode-icon.png","zoom-plus.png","zoom-minus.png" };

	public static enum Pictures {
		ICON, ICON_WARNING, DARK_LIGHT_MODE, ZOOM_IN, ZOOM_OUT
	}

	public ImageIcon imageIconFromNameInResources(String imageName) {
		ImageIcon picture = new ImageIcon(getClass().getClassLoader().getResource(imageName));
		// Log.console(getClass().getClassLoader().toString());
		// Log.console(getClass().getClassLoader().getResource(imageName).toString());
		return picture;
	}

	private Image imageDefaultInResources(int imageId) {
		ImageIcon picture = new ImageIcon(getClass().getClassLoader().getResource(pictures[imageId]));
		return picture.getImage();
	}

	private ImageIcon imageIconDefaultInResources(int imageId) {
		ImageIcon picture = new ImageIcon(getClass().getClassLoader().getResource(pictures[imageId]));
		return picture;
	}

	public static ImageIcon getDefaultImageIcon(Pictures d) {
		switch (d) {
		case ICON:
			return new Images().imageIconDefaultInResources(0);
		case ICON_WARNING:
			return new Images().imageIconDefaultInResources(1);
		case DARK_LIGHT_MODE:
			return new Images().imageIconDefaultInResources(2);
		case ZOOM_IN:
			return new Images().imageIconDefaultInResources(3);
		case ZOOM_OUT:
			return new Images().imageIconDefaultInResources(4);
		default:
			return null;

		}
	}

	public static ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}
	
	public static ImageIcon scaleImageIconFromDefault(Pictures icon, int width, int height) {
		Image img = getDefaultImageIcon(icon).getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}

}
