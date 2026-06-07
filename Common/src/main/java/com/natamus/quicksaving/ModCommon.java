package com.natamus.quicksaving;

import com.natamus.collective.services.Services;
import com.natamus.quicksaving.config.ConfigHandler;
import com.natamus.quicksaving.data.Constants;
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

	public static void registerHotkeys() {
		Constants.quicksavingKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quicksaving", Constants.F6key, "collective.quicksaving.key.quicksaving");
		Constants.quickloadKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quickload", Constants.F8key, "collective.quicksaving.key.quicksaving");
	}
}