package com.natamus.quicksaving.forge.events;

import com.natamus.quicksaving.data.Constants;
import com.natamus.quicksaving.events.QuicksaveEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(Dist.CLIENT)
public class ForgeQuicksaveEvents {
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent e) {
		if (!e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		QuicksaveEvents.onClientTick();
	}

	@SubscribeEvent
	public void onKey(InputEvent.Key e) {
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
