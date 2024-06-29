package com.natamus.quicksaving;

import com.natamus.quicksaving.config.ConfigHandler;
import com.natamus.quicksaving.networking.PacketRegistration;

public class ModCommon {

	public static void init() {
		ConfigHandler.initConfig();

		registerPackets();

		load();
	}

	private static void load() {

	}

	public static void registerPackets() {
		new PacketRegistration().init();
	}
}