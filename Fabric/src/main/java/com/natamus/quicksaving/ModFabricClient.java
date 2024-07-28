package com.natamus.quicksaving;

import com.mojang.blaze3d.platform.InputConstants;
import com.natamus.quicksaving.data.Constants;
import com.natamus.quicksaving.events.QuicksaveEvents;
import net.fabricmc.api.ClientModInitializer;
import com.natamus.quicksaving.util.Reference;
import com.natamus.collective.check.ShouldLoadCheck;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		ModCommon.registerPackets();

		ClientTickEvents.START_CLIENT_TICK.register((Minecraft client) -> {
			QuicksaveEvents.onClientTick();
		});

		Constants.quicksavingKey = KeyBindingHelper.registerKeyBinding(new KeyMapping("quicksaving.key.quicksaving", InputConstants.Type.KEYSYM, Constants.F6key, "quicksaving.key.quicksaving"));
		Constants.quickloadKey = KeyBindingHelper.registerKeyBinding(new KeyMapping("quicksaving.key.quickload", InputConstants.Type.KEYSYM, Constants.F8key, "quicksaving.key.quicksaving"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (Constants.quicksavingKey.isDown()) {
				QuicksaveEvents.onQuicksavePress();
				Constants.quicksavingKey.setDown(false);
			}

			while (Constants.quickloadKey.isDown()) {
				QuicksaveEvents.onQuickloadPress();
				Constants.quickloadKey.setDown(false);
			}
		});  	
	}
}
