package com.natamus.quicksaving;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.quicksaving.neoforge.config.IntegrateNeoForgeConfig;
import com.natamus.quicksaving.neoforge.events.NeoForgeKeyMappingRegister;
import com.natamus.quicksaving.neoforge.events.NeoForgeQuicksaveEvents;
import com.natamus.quicksaving.util.Reference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(Reference.MOD_ID)
public class ModNeoForge {
	
	public ModNeoForge(IEventBus modEventBus) {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		modEventBus.addListener(this::loadComplete);
		modEventBus.register(NeoForgeKeyMappingRegister.class);

		setGlobalConstants();
		ModCommon.init();

		IntegrateNeoForgeConfig.registerScreen(ModLoadingContext.get());

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		if (FMLEnvironment.dist.equals(Dist.CLIENT)) {
			NeoForge.EVENT_BUS.register(NeoForgeQuicksaveEvents.class);
		}
	}

	private static void setGlobalConstants() {

	}
}