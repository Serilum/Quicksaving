package com.natamus.quicksaving.neoforge.events;

import com.natamus.quicksaving.data.Constants;
import com.natamus.quicksaving.events.QuicksaveEvents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(Dist.CLIENT)
public class NeoForgeQuicksaveEvents {
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent.Pre e) {
		QuicksaveEvents.onClientTick();
	}

	@SubscribeEvent
	public static void onKey(InputEvent.Key e) {
		if (e.getAction() != 1) {
			return;
		}

		if (Constants.quicksavingKey == null || Constants.quickloadKey == null) {
			return;
		}

		if (e.getKey() == Constants.quicksavingKey.getKey().getValue()) {
			QuicksaveEvents.onQuicksavePress();
		}
		else if (e.getKey() == Constants.quickloadKey.getKey().getValue()) {
			QuicksaveEvents.onQuickloadPress();
		}
	}
}
