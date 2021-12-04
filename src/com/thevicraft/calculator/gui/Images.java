package com.thevicraft.calculator.gui;

import javax.swing.ImageIcon;

import com.thevicraft.calculator.console.Log;

import static java.util.Collections.sort;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;

public class Images {

	public static enum Pictures {
		ARROW_DOWN, ARROW_LEFT, ARROW_RIGHT, ARROW_UP, AUTHOR, COPY,DISCORD_ICON, GITHUB_ICON, GRAPH_ICON, HELP_ICON,
		DARK_LIGHT_MODE, WARNING_SIGN, ICON_WARNING, ICON, ZOOM_OUT, ZOOM_IN
	}

	private static List<String> listFileImages = null;
	private static File fileDir;

	@SuppressWarnings("unchecked")
	public static void initImages() {
		Log.console("Loading Images ...");
		try {
			fileDir = new File(new Images().getClass().getClassLoader().getResource("").getFile());
			if (fileDir.isDirectory()) {
				listFileImages = Arrays.asList(fileDir.list());
			}
			sort(listFileImages, Collections.reverseOrder().reversed());
			String[] acceptedFormat = { "png", "jpg" };
			listFileImages = filterFileFormat(listFileImages, acceptedFormat);
			listFileImages.forEach(images -> System.out.println(images.toString()));
			// listFileImages.size();
			Log.console("Successfully loaded all images.");
		} catch (Exception e) {
			Log.console("Failed to load images.");
			Log.console("An error occured whilst trying to load images.");
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public static List filterFileFormat(List<String> d, String[] format) {
		List<String> dummy = new ArrayList<String>(d);
		d.forEach(files -> {
			int removeFile = 0;
			for (String data : format) {
				if (files.toString().indexOf("." + data) == -1) {
					removeFile++;
					// removeIndex.add(d.indexOf(files));
					// Log.console(files.toString()+" lies in "+dummy.indexOf(files)+" and was
					// removed, it was not "+data);
				}
			}
			if (removeFile == format.length) {
				dummy.remove(files);
				// System.out.println(files.toString()+" was removed it did not belong to the
				// current filter setting");
			}
		});
		return dummy;
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

	public static void setButtonIcon(JButton container, Pictures image) {
		container.setIcon(Images.scaleImageIconFromDefault(image, (int) container.getPreferredSize().getHeight(),
				(int) container.getPreferredSize().getHeight()));
	}
	/*
	 * public ImageIcon imageIconFromNameInResources(String imageName) { ImageIcon
	 * picture = new ImageIcon(getClass().getClassLoader().getResource(imageName));
	 * return picture; }
	 */

}
