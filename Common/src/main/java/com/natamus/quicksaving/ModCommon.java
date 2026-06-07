package com.natamus.quicksaving;

import com.natamus.collective.services.Services;
import com.natamus.quicksaving.config.ConfigHandler;
import com.natamus.quicksaving.data.Constants;
import com.natamus.quicksaving.networking.PacketRegistration;
import com.natamus.quicksaving.util.Reference;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

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
		KeyMapping.Category keyMappingsCategory = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(Reference.MOD_ID, "quicksaving"));

		Constants.quicksavingKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quicksaving", Constants.F6key, keyMappingsCategory);
		Constants.quickloadKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quickload", Constants.F8key, keyMappingsCategory);
	}
}