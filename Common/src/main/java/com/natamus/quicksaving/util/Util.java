package com.natamus.quicksaving.util;

import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.implementations.networking.api.Dispatcher;
import com.natamus.quicksaving.config.ConfigHandler;
import com.natamus.quicksaving.data.Variables;
import com.natamus.quicksaving.networking.packets.ToServerTeleportPlayerPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;
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
		Variables.savedDimension = player.level().dimension();

		player.displayClientMessage(Component.translatable("collective.quicksaving.message.quicksaved").withStyle(ChatFormatting.DARK_GREEN), true);
	}

	public static void loadLastLocation(LocalPlayer player) {
		if (player == null) {
			return;
		}

		if (ConfigHandler.mustCrouchForQuickload && !player.isCrouching()) {
			return;
		}

		if (Variables.savedLocation == null || Variables.savedDimension == null) {
			return;
		}

		if (Variables.isInstalledOnServer) {
			Dispatcher.sendToServer(new ToServerTeleportPlayerPacket(Variables.savedLocation, Variables.savedDimension));
		}
		else {
			if (player.permissions().hasPermission(Permissions.COMMANDS_ADMIN)) {
				if (!player.hasEffect(MobEffects.SLOW_FALLING)) {
					player.connection.sendCommand("effect give @p minecraft:slow_falling 1 255 true");
				}

				player.connection.sendCommand("execute in " + Variables.savedDimension.identifier() + " run tp @p " + String.format("%.2f", Variables.savedLocation.x) + " " + String.format("%.2f", Variables.savedLocation.y) + " " + String.format("%.2f", Variables.savedLocation.z));

				player.displayClientMessage(Component.translatable("collective.quicksaving.message.quickloaded").withStyle(ChatFormatting.DARK_GREEN), true);
			}
			else {
				MessageFunctions.sendTranslatableMessage(player, "collective.quicksaving.message.orderquickloadll", ChatFormatting.RED);
			}
		}
	}
}
