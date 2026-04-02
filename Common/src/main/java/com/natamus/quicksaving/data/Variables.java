package com.natamus.quicksaving.data;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class Variables {
	public static boolean askedServerIfIsInstalled = false;
	public static boolean isInstalledOnServer = false;

	public static Vector3f savedLocation = null;
	public static ResourceKey<Level> savedDimension = null;
}
