package com.natamus.quicksaving.util;

import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.implementations.networking.api.Dispatcher;
import com.natamus.quicksaving.config.ConfigHandler;
import com.natamus.quicksaving.data.Variables;
import com.natamus.quicksaving.networking.packets.ToServerTeleportPlayerPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;

public class Util {
	public static void saveCurrentLocation(LocalPlayer player) {
		if (player == null) {
			return;
		}

		if (ConfigHandler.mustCrouchForQuicksave && !player.isCrouching()) {
			return;
		}

		Variables.savedLocation = new Vec3(player.position().x, player.position().y, player.position().z);
		player.displayClientMessage(Component.literal("Quicksaved.").withStyle(ChatFormatting.DARK_GREEN), true);
	}

	public static void loadLastLocation(LocalPlayer player) {
		if (player == null) {
			return;
		}

		if (ConfigHandler.mustCrouchForQuickload && !player.isCrouching()) {
			return;
		}

		if (Variables.savedLocation == null) {
			return;
		}

		if (Variables.isInstalledOnServer) {
			Dispatcher.sendToServer(new ToServerTeleportPlayerPacket(Variables.savedLocation));
		}
		else {
			if (player.hasPermissions(2)) {
				if (!player.hasEffect(MobEffects.SLOW_FALLING)) {
					player.connection.sendCommand("effect give @p minecraft:slow_falling 1 255 true");
				}

				player.connection.sendCommand("tp @p " + String.format("%.2f", Variables.savedLocation.x) + " " + String.format("%.2f", Variables.savedLocation.y) + " " + String.format("%.2f", Variables.savedLocation.z));

				player.displayClientMessage(Component.literal("Quickloaded.").withStyle(ChatFormatting.DARK_GREEN), true);
			}
			else {
				MessageFunctions.sendMessage(player, "In order to quickload, you'll need to install the mod on the server or have cheat access.", ChatFormatting.RED);
			}
		}
	}
}
