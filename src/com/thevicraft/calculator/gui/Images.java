package com.thevicraft.calculator.gui;

import javax.swing.ImageIcon;

import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.integration.FileResourcesUtils;

import static java.util.Collections.sort;

import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Images {
	// IMPORTANT FOR ADDING NEW IMAGES: THE ENUM VARIABLE HAS TO BE NAMED THE SAME
	// WAY THE IMAGE FILE IS NAMED (does not matter if capital letters or not)!!!!!
	public static enum Pictures {
		ARROW_DOWN, ARROW_LEFT, ARROW_RIGHT, ARROW_UP, AUTHOR, COPY, DISCORD_ICON, GITHUB_ICON, GRAPH_ICON, LAMP,
		MODE_ICON, UNITS, WARNING, WINDOW_ICON, WINDOW_ICON_WARNING, ZOOM_MINUS, ZOOM_PLUS
	}

	private static List<ImageIcon> imageList = new ArrayList<ImageIcon>();

	private static List<String> picsValues = new ArrayList<String>();

	public static final String imageFileFormat = "png";
	
	private static boolean imagesLoaded = false;

	public static void initImages() {
		Exception error = null;
		Log.console("Loading Images ...");
		try {
			/*
			 * for (Pictures image : Pictures.values()) {
			 * listFiles.add(image.toString().toLowerCase());
			 * //System.out.println(image.toString().toLowerCase()+".png"); } //listFiles =
			 * Arrays.asList(Pictures.values().toString().toLowerCase()); //String[] format
			 * = {"png","jpg"}; //filterFileFormat(listFiles, format);
			 * listFiles.forEach(iterator ->{
			 * listFileImages.add(Images.class.getClassLoader().getResource(iterator+".png")
			 * .toString()); //System.out.println(iterator+".png"); });
			 * listFileImages.forEach(iterator ->{ //System.out.println(iterator); });
			 */

			for (Pictures d : Pictures.values()) {
				picsValues.add(d.toString().toLowerCase() + "." + imageFileFormat);
				System.out.println("-- " + d.toString().toLowerCase() + "." + imageFileFormat);
			}

			picsValues.forEach(iterator -> {
				ImageIcon dummy = new ImageIcon(new Images().getClass().getClassLoader().getResource(iterator));
				imageList.add(dummy);
			});

		} catch (

		Exception e) {
			error = e;
			Log.console("Failed to load images.");
			Log.console("An error occured whilst trying to load images.");
		}
		if (error == null) {
			Log.console("Successfully loaded " + imageList.size() + " images.");
			imagesLoaded = true;
		}

	}
	
	public static boolean isImagesLoaded() {
		return imagesLoaded;
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
		/*
		 * int counter = 0; for (Pictures image : Pictures.values()) { if
		 * (d.equals(image)) { return new Images().imageDefaultInResources(counter); }
		 * counter++;
		 * 
		 * }
		 */
		String picture = d.toString().toLowerCase();
		// picsValues.contains(picture.substring(0,
		// picture.length()-imageFileFormat.length()));
		int index = picsValues.indexOf(picture + "." + imageFileFormat);

		return new Images().imageDefaultInResources(index);

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
		// System.out.println("used: "+listFileImages.get(imageId).toString());
		// return new
		// ImageIcon(getClass().getClassLoader().getResource(listFileImages.get(imageId).toString()));
		// imageList.get(imageId);

		/*
		 * imageList.forEach(iterator -> { System.out.println(iterator.toString()); });
		 * System.out.println(imageList.size());
		 */
		return new ImageIcon(imageList.get(imageId).getImage());
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
