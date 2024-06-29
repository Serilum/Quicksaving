package com.natamus.quicksaving.events;

import com.natamus.collective.implementations.networking.api.Dispatcher;
import com.natamus.collective.implementations.networking.exceptions.RegistrationException;
import com.natamus.quicksaving.data.Variables;
import com.natamus.quicksaving.networking.packets.ToServerAskIfModIsInstalled;
import com.natamus.quicksaving.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;

public class QuicksaveEvents {
	private static final Minecraft mc = Minecraft.getInstance();

	public static void onClientTick() {
		// Workaround because getConnection() is null for NeoForge on EntityJoinLevelEvent.
		if (!Variables.askedServerIfIsInstalled) {
			if (mc.player != null && mc.getConnection() != null) {
				Variables.askedServerIfIsInstalled = true;

				try {
					Dispatcher.sendToServer(new ToServerAskIfModIsInstalled());
				}
				catch (RegistrationException ex) {
					Variables.isInstalledOnServer = false;
				}
			}
		}
		else if (mc.player == null) {
			Variables.savedLocation = null;
			Variables.askedServerIfIsInstalled = false;
		}
	}

	public static void onQuicksavePress() {
		if (mc.screen instanceof ChatScreen) {
			return;
		}

		Util.saveCurrentLocation(mc.player);
	}

	public static void onQuickloadPress() {
		if (mc.screen instanceof ChatScreen) {
			return;
		}

		Util.loadLastLocation(mc.player);
	}
}
