package com.natamus.quicksaving;

import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.quicksaving.data.Constants;
import com.natamus.quicksaving.events.QuicksaveEvents;
import com.natamus.quicksaving.util.Reference;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
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

		ModCommon.registerHotkeys();

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
