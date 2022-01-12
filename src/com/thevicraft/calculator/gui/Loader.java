package com.thevicraft.calculator.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 * Loader with loading Bar to show a loading sequence / working process
 * @author thevicraft
 * @category JFrame
 * @see GuiTaschenrechner
 * 
 * */
@SuppressWarnings("serial")
public class Loader extends JFrame {

	public JProgressBar loadBar;
	/**
	 * Constructor for Loader
	 * @param typeLoading - title of the loader
	 * @param max_value - maximum of loading value steps (e.g.: the loader needs to show how 78 images are loaded, so this number is set to 78)
	 * @author thevicraft
	 * @see Loader
	 * 
	 * */
	public Loader(String typeLoading, int max_value) {
		setTitle("Loading " + typeLoading + "...");
		setSize(210, 60);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		loadBar = new JProgressBar(0, max_value);
		loadBar.setPreferredSize(new Dimension(200, 25));
		loadBar.setStringPainted(true);
		add(loadBar);
		loadBar.setValue(0);
		setVisible(true);
		loadBar.updateUI();
	}
	/**
	 * Method to iterate the loader after a certain loading step
	 * @author thevicraft
	 * @see Loader
	 * 
	 * */
	public void iterate() {
				if (loadBar.getMaximum() >= loadBar.getValue())
					loadBar.setValue(loadBar.getValue() + 1);
	}
}
