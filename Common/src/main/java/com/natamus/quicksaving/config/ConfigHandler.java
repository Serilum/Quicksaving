package com.natamus.quicksaving.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.quicksaving.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean mustCrouchForQuicksave = true;
	@Entry public static boolean mustCrouchForQuickload = false;
	@Entry public static boolean musthaveCheatAccessForQuickloadOnServer = true;

	public static void initConfig() {
		configMetaData.put("mustCrouchForQuicksave", Arrays.asList(
			"Whether the player has to crouch in order to execute a quicksaving."
		));
		configMetaData.put("mustCrouchForQuickload", Arrays.asList(
			"Whether the player has to crouch in order to execute a quickload."
		));
		configMetaData.put("musthaveCheatAccessForQuickloadOnServer", Arrays.asList(
			"Whether a player requires cheat access if they want to quick load when the mod is installed on both the client and the server. NOTE: if the mod is only installed on the client, cheat access is always needed."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}