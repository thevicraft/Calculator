package com.thevicraft.calculator.gui;

import javax.swing.ImageIcon;

import static java.util.Collections.sort;

import java.awt.Image;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Images {

	public static enum Pictures {
		AUTHOR, d, DISCORD_ICON, GITHUB_ICON, HELP_ICON, DARK_LIGHT_MODE, WARNING_SIGN, ICON_WARNING, ICON, ZOOM_OUT,
		ZOOM_IN
	}

	private static List listFileImages = null;
	private static File fileDir;

	@SuppressWarnings("unchecked")
	public static void initImages() {
		fileDir = new File(new Images().getClass().getClassLoader().getResource("").getFile());
		if (fileDir.isDirectory()) {
			listFileImages = Arrays.asList(fileDir.list());
		}
		sort(listFileImages, Collections.reverseOrder().reversed());
		// listFileImages.forEach(images -> System.out.println(images.toString()));
	}

	public static ImageIcon getDefaultImageIcon(Pictures d) {
		int counter = 0;
		for (Pictures image : Pictures.values()) {
			if (d.equals(image)) {
				return new Images().imageDefaultInResources(counter);
			}
			counter++;

		}
		return null;
	}

	// ---------------------SCALE
	// PICTURES--------------------------------------------------
	public static ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}

	// ---------------------------SCALE DEFAULT
	// PICTURES-------------------------------------
	public static ImageIcon scaleImageIconFromDefault(Pictures icon, int width, int height) {
		Image img = getDefaultImageIcon(icon).getImage();
		Image newimg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}

	// private String[] pictures = { "window-icon.png", "window-icon-warning.png",
	// "mode-icon.png", "zoom-plus.png",
	// "zoom-minus.png", "author.png", "discord-icon.png", "github-icon.png",
	// "lamp.png" };

	@SuppressWarnings("unchecked")
	private ImageIcon imageDefaultInResources(int imageId) {
		// @SuppressWarnings("rawtypes")
		// List listFile = null;
		// File fileDir = new
		// File(getClass().getClassLoader().getResource("").getFile());
		// if (fileDir.isDirectory()) {
		// listFile = Arrays.asList(fileDir.list());
		// }
		// sort(listFile, Collections.reverseOrder().reversed());
		// listFile.forEach(file -> System.out.println(file.toString()));
		// return new
		// ImageIcon(getClass().getClassLoader().getResource(listFile.get(imageId).toString()));
		return new ImageIcon(getClass().getClassLoader().getResource(listFileImages.get(imageId).toString()));
	}

	/*
	 * public ImageIcon imageIconFromNameInResources(String imageName) { ImageIcon
	 * picture = new ImageIcon(getClass().getClassLoader().getResource(imageName));
	 * return picture; }
	 */

}
