package com.thevicraft.calculator.gui.unitsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class UnitMenuItem extends JMenuItem {
	Unit unit;
	UnitMenu superComponent;
	public UnitMenuItem(UnitMenu componentSuper,String name, Unit unit) {
		// TODO Auto-generated constructor stub
		setText(name);
		unit.getType();
		this.unit = unit;
		superComponent = componentSuper;
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				superComponent.setText(UnitMenuItem.this.getText());
			}
			
		});
		
	}
}
