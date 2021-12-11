package com.thevicraft.calculator.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.thevicraft.calculator.gui.Images.Pictures;

@SuppressWarnings("serial")
public class Loader extends JFrame{
	
	public JProgressBar loadImages;
	
	public Loader(String typeLoading) {
		setTitle("Loading "+typeLoading+"...");
		setSize(210,60);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		loadImages = new JProgressBar(0,Pictures.values().length*2);
		loadImages.setPreferredSize(new Dimension(200,25));
		loadImages.setStringPainted(true);
		add(loadImages);
		setVisible(true);
	}
}
