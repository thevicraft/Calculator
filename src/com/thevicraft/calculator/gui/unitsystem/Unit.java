package com.thevicraft.calculator.gui.unitsystem;

public class Unit {
	private String type;
	private double toMainUnit;
	private String category;
	public Unit(UnitArea area) {
		category = "area";
		switch(area) {
		case a:
			toMainUnit = 100;
			type = area.name();
			break;
		case cm2:
			toMainUnit = 1/10000;
			type = area.name();
			break;
		case dm2:
			toMainUnit = 1/100;
			type = area.name();
			break;
		case ha:
			toMainUnit = 10000;
			type = area.name();
			break;
		case km2:
			toMainUnit = 1000000;
			type = area.name();
			break;
		case m2:
			toMainUnit = 1;
			type = area.name();
			break;
		case mm2:
			toMainUnit = 1/1000000;
			type = area.name();
			break;
		case foot2:
			toMainUnit = 0.092903;
			type = area.name();
			break;
		case inch2:
			toMainUnit = 0.00064516;
			type = area.name();
			break;
		case mile2:
			toMainUnit = 2.59e+6;
			type = area.name();
			break;
		case yard2:
			toMainUnit = 0.836127;
			type = area.name();
			break;
		default:
			toMainUnit = 0;
			break;
		
		}
	}
	public Unit(UnitLength length) {
		category = "length";
		switch(length) {
		case cm:
			toMainUnit = 1/100;
			type = length.name();
			break;
		case dm:
			toMainUnit = 1/10;
			type = length.name();
			break;
		case foot:
			toMainUnit = 0.3048;
			type = length.name();
			break;
		case inch:
			toMainUnit = 0.0254;
			type = length.name();
			break;
		case km:
			toMainUnit = 1000;
			type = length.name();
			break;
		case m:
			toMainUnit = 1;
			type = length.name();
			break;
		case mm:
			toMainUnit = 1/1000;
			type = length.name();
			break;
		case nm:
			toMainUnit = 1e-9;
			type = length.name();
			break;
		case pm:
			toMainUnit = 1e-12;
			type = length.name();
			break;
		case yard:
			toMainUnit = 0.9144;
			type = length.name();
			break;
		case mile:
			toMainUnit = 1609.34;
			type = length.name();
			break;
		case sea_mile:
			toMainUnit = 1852;
			type = length.name();
			break;
		case micro_meter:
			toMainUnit = 1e-6;
			type = length.name();
			break;
		case light_year:
			toMainUnit = 9.461e15;
			type = length.name();
			break;
		default:
			toMainUnit = 0;
			type = length.name();
			break;
		
		}
	}
	
	public Unit(UnitCubic volume) {
		category = "volume";
		switch(volume) {
		case cm3:
			toMainUnit = 1/1000000;
			type = volume.name();
			break;
		case ml:
			toMainUnit = 1/1000000;
			type = volume.name();
			break;
		case dm3:
			toMainUnit = 1/1000;
			type = volume.name();
			break;
		case inch3:
			toMainUnit = 1.6387e-5;
			type = volume.name();
			break;
		case km3:
			toMainUnit = 1e9;
			type = volume.name();
			break;
		case m3:
			toMainUnit = 1;
			type = volume.name();
			break;
		case mm3:
			toMainUnit = 1e-9;
			type = volume.name();
			break;
		case l:
			toMainUnit = 1000;
			type = volume.name();
			break;
		case foot3:
			toMainUnit = 0.0283168;
			type = volume.name();
			break;
		case gallon:
			toMainUnit = 0.00454609;
			type = volume.name();
			break;
		case cup:
			toMainUnit = 0.000284131;
			type = volume.name();
			break;
		default:
			toMainUnit = 0;
			type = volume.name();
			break;
		}
	}
	
	public Unit(UnitMass mass) {
		category = "mass";
		switch(mass) {
		case u:
			toMainUnit = 6.022e23;
			type = mass.name();
			break;
		case g:
			toMainUnit = 1;
			type = mass.name();
			break;
		case kg:
			toMainUnit = 1000;
			type = mass.name();
			break;
		case mg:
			toMainUnit = 1/1000;
			type = mass.name();
			break;
		case t:
			toMainUnit = 1e6;
			type = mass.name();
			break;
		case micro_gram:
			toMainUnit = 1e-6;
			type = mass.name();
			break;
		case pound:
			toMainUnit = 453.592;
			type = mass.name();
			break;
		default:
			toMainUnit = 0;
			type = mass.name();
			break;
		}
	}
	
	public Unit(UnitTime time) {
		category = "time";
		switch(time) {
		case min:
			toMainUnit = 1/60;
			type = time.name();
			break;
		case d:
			toMainUnit = 24;
			type = time.name();
			break;
		case sec:
			toMainUnit = 1/3600;
			type = time.name();
			break;
		case h:
			toMainUnit = 1;
			type = time.name();
			break;
		case w:
			toMainUnit = 24*7;
			type = time.name();
			break;
		case micro_second:
			toMainUnit = 2.7778e-10;
			type = time.name();
			break;
		case ns:
			toMainUnit = 2.7778e-13;
			type = time.name();
			break;
		case milli_second:
			toMainUnit = 2.7778e-7;
			type = time.name();
			break;
		case y:
			toMainUnit = 24*365.25;
			type = time.name();
			break;
		default:
			toMainUnit = 0;
			type = time.name();
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public String getType() {
		return type;
	}
	public String getCategory() {
		return category;
	}
	public double getToMainUnit() {
		return toMainUnit;
	}
}
