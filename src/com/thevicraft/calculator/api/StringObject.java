package com.thevicraft.calculator.api;

public class StringObject extends Object{
	public int strPosFrom= Integer.MAX_VALUE;
	public int strPosTo = Integer.MAX_VALUE;
	private String type = "";
	private String text= "";
	
	public StringObject(String type) {
		this.type = type;
	}
	public StringObject() {
		
	}

	
	
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
