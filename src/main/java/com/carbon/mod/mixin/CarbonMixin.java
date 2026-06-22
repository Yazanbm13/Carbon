package com.carbon.mod.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class CarbonMixin {
	@Inject(at = @At("HEAD"), method = "loadLevel")
	private void init(CallbackInfo info) {
		com.carbon.mod.Carbon.LOGGER.info("Carbon mixin loaded into MinecraftServer!");
	}
}