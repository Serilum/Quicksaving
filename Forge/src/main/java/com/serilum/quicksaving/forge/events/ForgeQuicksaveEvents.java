package com.serilum.quicksaving.forge.events;

import com.serilum.quicksaving.data.Constants;
import com.serilum.quicksaving.events.QuicksaveEvents;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeQuicksaveEvents {
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent e) {
		if (!e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

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
