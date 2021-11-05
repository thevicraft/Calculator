package com.thevicraft.calculator.gui;

import javax.swing.ImageIcon;

import com.thevicraft.calculator.console.Log;

import java.awt.Image;
import java.io.File;

public class Images {
	
	//public Images(String imageName) {
	//	imageFromNameInResources(imageName);
		
	//}
	//private static File here = new File(Images.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	//private static File main = here.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
	//String [] paths = {""};
	// add overlay images with "public static final ImageIcon <name> = new ImageIcon(<path>)"
	//File path = new File("");
	//public static final ImageIcon ICON = new ImageIcon(imageFromNameInResources("window-icon.png"));
	//public static final ImageIcon ICON_WARNING = new ImageIcon(imageFromNameInResources("window-icon-warning.png"));
	//public static final ImageIcon ICON_WARNING = new ImageIcon("src/resources/window-icon-warning.png");

	//public static Image image(final ImageIcon PIC) {
	//
	//	return PIC.getImage();
	//}
	private String [] pictures = {"window-icon.png","window-icon-warning.png"};
	public final int ICON = 0;
	public final int ICON_WARNING = 1;
	
	public Image imageFromNameInResources(String imageName) {
		ImageIcon picture = new ImageIcon(getClass().getClassLoader().getResource(imageName));
		//Log.console(getClass().getClassLoader().toString());
		//Log.console(getClass().getClassLoader().getResource(imageName).toString());
		return picture.getImage();
	}
	public Image imageDefaultInResources(final int imageId) {
		ImageIcon picture = new ImageIcon(getClass().getClassLoader().getResource(pictures[imageId]));
		return picture.getImage();
	}

}
