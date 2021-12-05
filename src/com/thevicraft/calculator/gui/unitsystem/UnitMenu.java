package com.thevicraft.calculator.gui.unitsystem;

import javax.swing.JMenu;
import java.awt.Component;

@SuppressWarnings("serial")
public class UnitMenu extends JMenu {

	private UnitMenuItem[] items;

	/*
	 * public UnitMenu(UnitMenu m, UnitMenu n) { setText("Select Unit Type");
	 * add(new UnitMenuItem(UnitMenu.this, "length", new Unit(UnitArea.a)));//third
	 * parameter unnecessary for use add(new UnitMenuItem(UnitMenu.this, "area", new
	 * Unit(UnitArea.a)));//third parameter unnecessary for use add(new
	 * UnitMenuItem(UnitMenu.this, "volume", new Unit(UnitArea.a)));//third
	 * parameter unnecessary for use add(new UnitMenuItem(UnitMenu.this, "mass", new
	 * Unit(UnitArea.a)));//third parameter unnecessary for use add(new
	 * UnitMenuItem(UnitMenu.this, "time", new Unit(UnitArea.a)));//third parameter
	 * unnecessary for use }
	 */
	public UnitMenu(String type) {
		construct(type);
	}

	private void construct(String type) {
		setText("Select Unit");
		switch (type) {
		case "area":
			// items = new UnitMenuItem[UnitArea.values().length];
			items = initMenuItems(UnitArea.values(), type);
			break;
		case "length":
			// items = new UnitMenuItem[UnitLength.values().length];
			items = initMenuItems(UnitLength.values(), type);
			break;
		case "volume":
			// items = new UnitMenuItem[UnitCubic.values().length];
			items = initMenuItems(UnitCubic.values(), type);
			break;
		case "mass":
			// items = new UnitMenuItem[UnitMass.values().length];
			items = initMenuItems(UnitMass.values(), type);
			break;
		case "time":
			// items = new UnitMenuItem[UnitTime.values().length];
			items = initMenuItems(UnitTime.values(), type);
			break;
		default:
			items = new UnitMenuItem[0];
		}
		for (UnitMenuItem s : items) {
			add(s);
		}
		setVisible(true);
	}

	public void changeType(String type) {
		/*
		 * for(Component d: this.getComponents()) { if(d instanceof UnitMenuItem) {
		 * remove(d); updateUI(); System.out.println(d.toString()); } }
		 */
		for (UnitMenuItem s : items) {
			remove(s);
		}
		construct(type);
	}

	@SuppressWarnings("rawtypes")
	private UnitMenuItem[] initMenuItems(Enum[] test, String type) {
		UnitMenuItem[] menuitem;
		menuitem = new UnitMenuItem[test.length];
		for (int i = 0; i <= test.length - 1; i++) {
			if (type.equals("area")) {
				menuitem[i] = new UnitMenuItem(UnitMenu.this, test[i].name(),
						new Unit(UnitArea.valueOf(test[i].toString())));
			} else if (type.equals("volume")) {
				menuitem[i] = new UnitMenuItem(UnitMenu.this, test[i].name(),
						new Unit(UnitCubic.valueOf(test[i].toString())));
			} else if (type.equals("length")) {
				menuitem[i] = new UnitMenuItem(UnitMenu.this, test[i].name(),
						new Unit(UnitLength.valueOf(test[i].toString())));
			} else if (type.equals("mass")) {
				menuitem[i] = new UnitMenuItem(UnitMenu.this, test[i].name(),
						new Unit(UnitMass.valueOf(test[i].toString())));
			} else if (type.equals("time")) {
				menuitem[i] = new UnitMenuItem(UnitMenu.this, test[i].name(),
						new Unit(UnitTime.valueOf(test[i].toString())));
			}
		}
		// for(UnitMenuItem d: menuitem) {
		// System.out.println(d.toString());
		// }
		return menuitem;
	}
}
