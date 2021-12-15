package com.thevicraft.calculator.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class Loader extends JFrame {

	public JProgressBar loadBar;

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

	public void iterate() {
				if (loadBar.getMaximum() >= loadBar.getValue())
					loadBar.setValue(loadBar.getValue() + 1);
	}
}
