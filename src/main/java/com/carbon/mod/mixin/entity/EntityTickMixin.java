package com.carbon.mod.mixin.entity;

import com.carbon.mod.optimization.EntityOptimizer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class EntityTickMixin {

    @Inject(at = @At("HEAD"), method = "tickNonPassenger", cancellable = true)
    private void carbon_throttleEntityTick(Entity entity, CallbackInfo ci) {
        ServerLevel level = (ServerLevel)(Object)this;
        if (!EntityOptimizer.shouldTick(entity, level)) {
            ci.cancel(); // skip this tick entirely
        }
    }
}
