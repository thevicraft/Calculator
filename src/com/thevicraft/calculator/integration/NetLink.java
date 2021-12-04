package com.thevicraft.calculator.integration;

public class NetLink {
	public static void openUrl(String webAddress) {
		try {
			String url = webAddress;
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
