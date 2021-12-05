package com.thevicraft.calculator.gui.unitsystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class UnitMenuSelect extends JMenu {
	
	private String[] itemText = {"length","area","volume","mass","time"};
	private UnitMenu unit1,unit2;
	
	private JMenuItem[] item = new JMenuItem[itemText.length];
	public UnitMenuSelect(UnitMenu m, UnitMenu n) {
		unit1 = m;
		unit2 = n;
		setText("Select Unit Type");
		initComponents();
		
		for(JMenuItem i: item) {
			i.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					unit1.changeType(i.getText());
					unit2.changeType(i.getText());
					setText(i.getText());
				}
				
			});
			add(i);
		}
	}
	private void initComponents() {
		for(int i = 0; i <= itemText.length-1; i++) {
			item[i] = new JMenuItem(itemText[i]);
		}
	}
}
