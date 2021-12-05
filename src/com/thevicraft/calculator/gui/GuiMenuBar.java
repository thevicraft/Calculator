package com.thevicraft.calculator.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.thevicraft.calculator.gui.Images.Pictures;
import java.awt.Color;

@SuppressWarnings("serial")
public class GuiMenuBar extends JMenuBar {

	protected JMenu config;
	protected JMenu window;
	protected JMenu mode;

	static final int MENUS = 3;
	static final int MAX_MENUITEMS = 6;

	public JMenuItem[][] items = new JMenuItem[MENUS][MAX_MENUITEMS]; // index 1 = menu number, index 2 = menu item
																		// number
	private String[][] itemsTextConfig = {
			{ "Zoom In             [Ctrl+]", "Zoom Out          [Ctrl-]", "Toggle dark/light mode" },
			{ "New Window        [Ctrl+W]", "Help                       [Ctrl+H]", "Shortcuts             [Ctrl+S]",
					"Report Bug", "Join Discord Server", "About" },
			{ "Normal", "sin, cos, tan, ...", "asin, acos, atan, ...", "Draw Graph" } };

	// ACTIONLISTENER FOR MENUITEMS IS IN GUITASCHENRECHNER AT THE BOTTON
	// !!!!!!!!!!!!1

	public GuiMenuBar(Color darkLight) {
		initMenus();
		setColorOfComponents(darkLight);
		initMenuItems();
		add(config);
		add(window);
		add(mode);

		setIconOfComponents();
	}

	public GuiMenuBar() {
		initMenus();
		setColorOfComponents(GuiTaschenrechner.dark);
		initMenuItems();
		add(config);
		add(window);
		add(mode);
		setIconOfComponents();
	}

	public void setColorOfComponents(Color d) {
		setBackground(d);

		config.setBackground(d);
		window.setBackground(d);
		mode.setBackground(d);

		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		config.setForeground(d);
		window.setForeground(d);
		mode.setForeground(d);

		setForeground(d);

	}

	private void setIconOfComponents() {
		items[0][0].setIcon(Images.scaleImageIconFromDefault(Pictures.ZOOM_IN, 18, 18));
		items[0][1].setIcon(Images.scaleImageIconFromDefault(Pictures.ZOOM_OUT, 18, 18));
		items[0][2].setIcon(Images.scaleImageIconFromDefault(Pictures.DARK_LIGHT_MODE, 18, 18));

		items[1][1].setIcon(Images.scaleImageIconFromDefault(Pictures.HELP_ICON, 18, 18));
		items[1][2].setIcon(Images.scaleImageIconFromDefault(Pictures.ARROW_RIGHT, 18, 18));
		items[1][3].setIcon(Images.scaleImageIconFromDefault(Pictures.GITHUB_ICON, 18, 18));
		items[1][4].setIcon(Images.scaleImageIconFromDefault(Pictures.DISCORD_ICON, 18, 18));
		items[1][5].setIcon(Images.scaleImageIconFromDefault(Pictures.AUTHOR, 18, 18));

		items[2][3].setIcon(Images.scaleImageIconFromDefault(Pictures.GRAPH_ICON, 18, 18));
		// Log.console(Double.toString(items[0][0].getSize(new
		// Dimension()).getHeight()));
	}

	private void initMenus() {
		config = new JMenu("Config");
		window = new JMenu("Window");
		mode = new JMenu("Mode");
	}

	private void initMenuItems() {
		for (int i = 0; i <= 2; i++) {
			items[0][i] = new JMenuItem(itemsTextConfig[0][i]);
			config.add(items[0][i]);
		}

		for (int i = 0; i <= 5; i++) {
			items[1][i] = new JMenuItem(itemsTextConfig[1][i]);
			window.add(items[1][i]);
		}
		for (int i = 0; i <= 3; i++) {
			items[2][i] = new JMenuItem(itemsTextConfig[2][i]);
			mode.add(items[2][i]);
		}

	}
}
