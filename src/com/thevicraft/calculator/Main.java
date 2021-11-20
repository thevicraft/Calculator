package com.thevicraft.calculator;

import com.thevicraft.calculator.gui.GuiTaschenrechner;
import com.thevicraft.keyboard.activity.KeyEventClass;
import com.thevicraft.calculator.api.SimpleMath;
import com.thevicraft.calculator.console.Log;

import java.awt.Component;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GuiTaschenrechner taschenrechner = new GuiTaschenrechner("Calculator", "dark", null);
		new KeyEventClass("Calculator", "dark", null);

	}

}
