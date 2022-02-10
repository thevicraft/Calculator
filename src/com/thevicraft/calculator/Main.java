package com.thevicraft.calculator;

import com.thevicraft.calculator.gui.GuiTaschenrechner;
import com.thevicraft.keyboard.activity.KeyEventClass;
import club.minnced.discord.rpc.*;

public class Main {
	/**
	 * discord event handler field
	 * 
	 * 
	 * @category field
	 * @author thevicraft
	 */
	public static DiscordEventHandlers handlers;
	/**
	 * discord rpc field, can be updated after changing with
	 * lib.Discord_UpdatePresence(presence);
	 * @category field
	 * @author thevicraft
	 */
	public static DiscordRichPresence presence;
	/**
	 * discord rpc lib field, can be updated after changing presence
	 * lib.Discord_UpdatePresence(presence);
	 * @category field
	 * @author thevicraft
	 */
	public static DiscordRPC lib;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// GuiTaschenrechner taschenrechner = new GuiTaschenrechner("Calculator",
		// "dark", null);
		KeyEventClass calculator = new KeyEventClass("Simple Calculator", "dark", null);

		lib = DiscordRPC.INSTANCE;
		String applicationId = "941422927671332904";
		String steamId = "";
		handlers = new DiscordEventHandlers();
		handlers.ready = (user) -> System.out.println("Rich Presence Integration displaying current activity on discord");
		lib.Discord_Initialize(applicationId, handlers, true, steamId);
		presence = new DiscordRichPresence();
		presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
		presence.details = "Version "+GuiTaschenrechner.VERSION;
		presence.state = "Mode Normal";
		presence.largeImageKey = "simplecalculator_icon";
		presence.smallImageKey = "thevicraft";
		presence.largeImageText = "Simple Calculator "+GuiTaschenrechner.VERSION;
		presence.smallImageText = "Coded by thevicraft";
		lib.Discord_UpdatePresence(presence);
		// in a worker thread
		new Thread(() -> {
			while (calculator.tr.isDisplayable()) {
				lib.Discord_RunCallbacks();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ignored) {
				}
			}
		}, "RPC-Callback-Handler").start();
	}

}
