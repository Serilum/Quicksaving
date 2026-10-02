package com.serilum.quicksaving;

import com.natamus.collective.services.Services;
import com.serilum.quicksaving.config.ConfigHandler;
import com.serilum.quicksaving.data.Constants;
import com.serilum.quicksaving.networking.PacketRegistration;
import com.mojang.blaze3d.platform.InputConstants;

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
		Constants.quicksavingKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quicksaving", InputConstants.KEY_F6, "collective.quicksaving.key.quicksaving");
		Constants.quickloadKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quickload", InputConstants.KEY_F8, "collective.quicksaving.key.quicksaving");
	}
}