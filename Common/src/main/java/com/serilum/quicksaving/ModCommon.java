package com.serilum.quicksaving;

import com.natamus.collective.services.Services;
import com.serilum.quicksaving.config.ConfigHandler;
import com.serilum.quicksaving.data.Constants;
import com.serilum.quicksaving.networking.PacketRegistration;
import com.serilum.quicksaving.util.Reference;
import com.mojang.blaze3d.platform.InputConstants;
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

		Constants.quicksavingKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quicksaving", InputConstants.KEY_F6, keyMappingsCategory);
		Constants.quickloadKey = Services.REGISTERKEYMAPPING.registerKeyMapping("collective.quicksaving.key.quickload", InputConstants.KEY_F8, keyMappingsCategory);
	}
}