package com.thevicraft.calculator.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.thevicraft.calculator.console.Log;
import com.thevicraft.calculator.gui.Images.Pictures;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

@SuppressWarnings("serial")
public class GuiMenuBar extends JMenuBar {

	protected JMenu config;
	protected JMenu window;

	protected JMenuItem[][] items = new JMenuItem[2][4]; // index 1 = menu number, index 2 = menu item number
	private String[][] itemsTextConfig = { { "Zoom In", "Zoom Out", "Toggle dark/light mode" },
			{ "New Window", "Help", "Report Bug","About" } };

	public GuiMenuBar(Color darkLight) {
		initMenus();
		setColorOfComponents(darkLight);
		initMenuItems();
		add(config);
		add(window);

		setIconOfComponents();
	}

	public GuiMenuBar() {
		initMenus();
		setColorOfComponents(GuiTaschenrechner.dark);
		initMenuItems();
		add(config);
		add(window);
		setIconOfComponents();
	}

	public void setColorOfComponents(Color d) {
		setBackground(d);

		config.setBackground(d);
		window.setBackground(d);

		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		config.setForeground(d);
		window.setForeground(d);
		
		setForeground(d);

	}

	private void setIconOfComponents() {
		items[0][0].setIcon(Images.scaleImageIconFromDefault(Pictures.ZOOM_IN, 18, 18));
		items[0][1].setIcon(Images.scaleImageIconFromDefault(Pictures.ZOOM_OUT, 18, 18));
		items[0][2].setIcon(Images.scaleImageIconFromDefault(Pictures.DARK_LIGHT_MODE, 18, 18));

		// Log.console(Double.toString(items[0][0].getSize(new
		// Dimension()).getHeight()));
	}

	private void initMenus() {
		config = new JMenu("Config");
		window = new JMenu("Window");
	}

	private void initMenuItems() {
		for (int i = 0; i <= 2; i++) {
			items[0][i] = new JMenuItem(itemsTextConfig[0][i]);
			config.add(items[0][i]);
		}

		for (int i = 0; i <= 3; i++) {
			items[1][i] = new JMenuItem(itemsTextConfig[1][i]);
			window.add(items[1][i]);
		}

	}
}