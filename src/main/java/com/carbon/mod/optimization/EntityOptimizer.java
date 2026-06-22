package com.carbon.mod.optimization;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;

public class EntityOptimizer {

    private static final double NEAR_DISTANCE     = 24  * 24;
    private static final double MEDIUM_DISTANCE   = 48  * 48;
    private static final double FAR_DISTANCE      = 96  * 96;
    private static final double VERY_FAR_DISTANCE = 128 * 128;

    private static final int NEAR_RATE     = 1;
    private static final int MEDIUM_RATE   = 2;
    private static final int FAR_RATE      = 4;
    private static final int VERY_FAR_RATE = 8;
    private static final int EXTREME_RATE  = 16;

    public static boolean shouldTick(Entity entity, ServerLevel level) {
        if (entity instanceof Player) return true;

        double closestDistance = Double.MAX_VALUE;
        for (Player player : level.players()) {
            double dist = entity.distanceToSqr(player.getX(), player.getY(), player.getZ());
            if (dist < closestDistance) closestDistance = dist;
        }

        if (closestDistance == Double.MAX_VALUE) return (entity.tickCount % EXTREME_RATE == 0);

        int rate;
        if      (closestDistance <= NEAR_DISTANCE)      rate = NEAR_RATE;
        else if (closestDistance <= MEDIUM_DISTANCE)    rate = MEDIUM_RATE;
        else if (closestDistance <= FAR_DISTANCE)       rate = FAR_RATE;
        else if (closestDistance <= VERY_FAR_DISTANCE)  rate = VERY_FAR_RATE;
        else                                            rate = EXTREME_RATE;

        return (entity.tickCount % rate == 0);
    }
}
