package com.natamus.quicksaving.forge.events;

import com.natamus.quicksaving.data.Constants;
import com.natamus.quicksaving.events.QuicksaveEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeQuicksaveEvents {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeQuicksaveEvents.class);
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent.Pre e) {
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
