package com.natamus.quicksaving;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.quicksaving.forge.config.IntegrateForgeConfig;
import com.natamus.quicksaving.forge.events.ForgeKeyMappingRegister;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import com.natamus.quicksaving.forge.events.ForgeQuicksaveEvents;
import com.natamus.quicksaving.util.Reference;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Reference.MOD_ID)
public class ModForge {
	
	public ModForge(FMLJavaModLoadingContext modLoadingContext) {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		BusGroup busGroup = modLoadingContext.getModBusGroup();

		FMLLoadCompleteEvent.getBus(busGroup).addListener(this::loadComplete);
		RegisterKeyMappingsEvent.getBus(busGroup).addListener(ForgeKeyMappingRegister::registerKeyBinding);

		setGlobalConstants();
		ModCommon.init();

		IntegrateForgeConfig.registerScreen(modLoadingContext);

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		if (FMLEnvironment.dist.equals(Dist.CLIENT)) {
			ForgeQuicksaveEvents.registerEventsInBus();
		}
	}

	private static void setGlobalConstants() {

	}
}