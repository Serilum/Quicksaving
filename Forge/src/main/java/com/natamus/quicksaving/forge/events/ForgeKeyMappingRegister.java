package com.natamus.quicksaving.forge.events;

import com.natamus.quicksaving.data.Constants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(value = Dist.CLIENT)
public class ForgeKeyMappingRegister {
    @SubscribeEvent
	public void registerKeyBinding(RegisterKeyMappingsEvent e) {
    	Constants.quicksavingKey = new KeyMapping("quicksaving.key.quicksaving", Constants.F6key, "quicksaving.key.quicksaving");
		Constants.quickloadKey = new KeyMapping("quicksaving.key.quickload", Constants.F8key, "quicksaving.key.quicksaving");
    	e.register(Constants.quicksavingKey);
		e.register(Constants.quickloadKey);
    }
}