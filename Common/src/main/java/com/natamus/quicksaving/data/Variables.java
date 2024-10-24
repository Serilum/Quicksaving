package com.natamus.quicksaving.data;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Variables {
	public static boolean askedServerIfIsInstalled = false;
	public static boolean isInstalledOnServer = false;

	public static Vec3 savedLocation = null;
	public static ResourceKey<Level> savedDimension = null;
}
