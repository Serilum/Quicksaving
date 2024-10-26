package com.natamus.quicksaving.neoforge.events;

import com.natamus.quicksaving.data.Constants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeKeyMappingRegister {
	@SubscribeEvent
	public static void registerKeyBinding(RegisterKeyMappingsEvent e) {
		Constants.quicksavingKey = new KeyMapping("quicksaving.key.quicksaving", Constants.F6key, "key.categories.quicksaving");
		Constants.quickloadKey = new KeyMapping("quicksaving.key.quickload", Constants.F8key, "key.categories.quicksaving");
		e.register(Constants.quicksavingKey);
		e.register(Constants.quickloadKey);
	}
}