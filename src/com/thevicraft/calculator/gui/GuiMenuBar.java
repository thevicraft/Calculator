package com.thevicraft.calculator.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.thevicraft.calculator.gui.Images.Pictures;
import com.thevicraft.calculator.gui.unitsystem.UnitMenu;
import com.thevicraft.calculator.gui.unitsystem.UnitMenuSelect;

import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GuiMenuBar extends JMenuBar {

	protected JMenu config;
	protected JMenu window;
	protected JMenu mode;
	protected JSeparator sep1,sep2,sep3,sep4;
	
	public UnitMenuSelect typeSelect;
	public UnitMenu unit1,unit2;

	static final int MENUS = 3;
	static final int MAX_MENUITEMS = 6;

	public JMenuItem[][] items = new JMenuItem[MENUS][MAX_MENUITEMS]; // index 1 = menu number, index 2 = menu item
																		// number
	private String[][] itemsTextConfig = {
			{ "Zoom In             [Ctrl+]", "Zoom Out          [Ctrl-]", "Toggle dark/light mode" },
			{ "New Window        [Ctrl+W]", "Help                       [Ctrl+H]", "Shortcuts             [Ctrl+S]",
					"Report Bug", "Join Discord Server", "About" },
			{ "Normal", "sin, cos, tan, ...", "asin, acos, atan, ...", "Draw Graph","Unit Calculator" } };

	// ACTIONLISTENER FOR MENUITEMS IS IN GUITASCHENRECHNER AT THE BOTTON
	// !!!!!!!!!!!!1

	public GuiMenuBar(Color darkLight) {
		initMenus();
		setColorOfComponents(darkLight);
		initMenuItems();
		add(config);
		add(window);
		add(mode);
		
		
		add(sep1);
		add(typeSelect);
		add(sep2);
		add(unit1);
		add(sep3);
		add(unit2);
		add(sep4);
		setUnitVisible(false);

		setIconOfComponents();
	}

	/*public GuiMenuBar() {
		initMenus();
		setColorOfComponents(GuiTaschenrechner.dark);
		initMenuItems();
		add(config);
		add(window);
		add(mode);
		add(sep1)
		add(new UnitMenu("area"));
		add(new UnitMenu("area"));
		setIconOfComponents();
	}*/
	public void setUnitVisible(boolean x) {
		sep1.setVisible(x);
		sep2.setVisible(x);
		sep3.setVisible(x);
		sep4.setVisible(x);
		typeSelect.setVisible(x);
		unit1.setVisible(x);
		unit2.setVisible(x);
	}

	public void setColorOfComponents(Color d) {
		setBackground(d);

		config.setBackground(d);
		window.setBackground(d);
		mode.setBackground(d);
		sep1.setForeground(d);
		typeSelect.setBackground(d);
		unit1.setBackground(d);
		unit2.setBackground(d);
		// die anderen dinger

		if (d.equals(GuiTaschenrechner.dark)) {
			d = GuiTaschenrechner.bright;
		} else {
			d = GuiTaschenrechner.dark;
		}
		config.setForeground(d);
		window.setForeground(d);
		mode.setForeground(d);
		
		typeSelect.setForeground(d);
		unit1.setForeground(d);
		unit2.setForeground(d);

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
		items[2][4].setIcon(Images.scaleImageIconFromDefault(Pictures.ICON, 18, 18));
		// Log.console(Double.toString(items[0][0].getSize(new
		// Dimension()).getHeight()));
	}

	private void initMenus() {
		config = new JMenu("Config");
		window = new JMenu("Window");
		mode = new JMenu("Mode");
		
		sep1 = new JSeparator(SwingConstants.VERTICAL);
		sep2 = new JSeparator(SwingConstants.VERTICAL);
		sep3 = new JSeparator(SwingConstants.VERTICAL);
		sep4 = new JSeparator(SwingConstants.VERTICAL);
		unit1 = new UnitMenu("area");
		unit2 = new UnitMenu("area");
		
		typeSelect = new UnitMenuSelect(unit1,unit2);
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
		for (int i = 0; i <= 4; i++) {
			items[2][i] = new JMenuItem(itemsTextConfig[2][i]);
			mode.add(items[2][i]);
		}

	}
}
