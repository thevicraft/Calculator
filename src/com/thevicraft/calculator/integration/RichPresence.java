package com.thevicraft.calculator.integration;

import com.thevicraft.calculator.Main;
/**
 * RichPresence Util class with various methods for updating/setting Discord RPC
 * @category class
 * @author thevicraft
 */
public class RichPresence {
	
	/**
	 * method that updates the rpc with the field of the method
	 * @param state - String that will be displayed at this category
	 * @author thevicraft
	 */
	public static void updateState(String state) {
		Main.presence.state = state;
		Main.lib.Discord_UpdatePresence(Main.presence);
	}
	/**
	 * method that updates the rpc with the field of the method
	 * @param largeImageKey - String that will be displayed at this category
	 * @author thevicraft
	 */
	public static void updateLargeImageKey(String largeImageKey) {
		Main.presence.largeImageKey = largeImageKey;
		Main.lib.Discord_UpdatePresence(Main.presence);
	}
	/**
	 * method that updates the rpc with the field of the method
	 * @param smallImageKey - String that will be displayed at this category
	 * @author thevicraft
	 */
	public static void updateSmallImageKey(String smallImageKey) {
		Main.presence.smallImageKey = smallImageKey;
		Main.lib.Discord_UpdatePresence(Main.presence);
	}
	/**
	 * method that updates the rpc with the field of the method
	 * @param largeImageText - String that will be displayed at this category
	 * @author thevicraft
	 */
	public static void updateLargeImageText(String largeImageText) {
		Main.presence.largeImageText = largeImageText;
		Main.lib.Discord_UpdatePresence(Main.presence);
	}
	/**
	 * method that updates the rpc with the field of the method
	 * @param smallImageText - String that will be displayed at this category
	 * @author thevicraft
	 */
	public static void updateSmallImageText(String smallImageText) {
		Main.presence.smallImageText = smallImageText;
		Main.lib.Discord_UpdatePresence(Main.presence);
	}
	/**
	 * method that updates the rpc with the field of the method
	 * @param details - String that will be displayed at this category
	 * @author thevicraft
	 */
	public static void updateDetails(String details) {
		Main.presence.details = details;
		Main.lib.Discord_UpdatePresence(Main.presence);
	}
	/**
	 * method that updates the rpc with ALL fields of the discord rpc
	 * @author thevicraft
	 */
	public static void updateAll(String mode, String largeImageKey,String smallImageKey,String largeImageText,String smallImageText) {
		//Main.presence.details = "Version "+GuiTaschenrechner.VERSION;
		Main.presence.state = mode;
		Main.presence.largeImageKey = largeImageKey;
		Main.presence.smallImageKey = smallImageKey;
		Main.presence.largeImageText = largeImageText;
		Main.presence.smallImageText = smallImageText;
		Main.lib.Discord_UpdatePresence(Main.presence);
	}
}
