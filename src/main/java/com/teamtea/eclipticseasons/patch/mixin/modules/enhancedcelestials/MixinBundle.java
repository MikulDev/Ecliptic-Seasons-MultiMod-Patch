package com.teamtea.eclipticseasons.patch.mixin.modules.enhancedcelestials;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.teamtea.eclipticseasons.api.EclipticSeasonsApi;
import com.teamtea.eclipticseasons.patch.modules.enhancedcelestials.EC;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

public abstract class MixinBundle {

    @Mixin({dev.corgitaco.enhancedcelestials.lunarevent.EnhancedCelestialsLunarForecastWorldData.class})
    public static abstract class EnhancedCelestialsLunarForecastWorldData {
        @WrapOperation(at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isRaining()Z")},
                remap = false, method = {"baseTick"})
        private boolean es_patch$baseTick_isRaining(Level instance, Operation<Boolean> original) {
            if (EC.Config.enable.get() && EclipticSeasonsApi.getInstance().hasLocalWeather(instance)) {
                List<? extends Player> players = instance.players();
                int size = players.size();
                if (size > 0) {
                    for (Player player : players) {
                        if (EclipticSeasonsApi.getInstance().isRainingOrSnowing(instance, player.blockPosition())
                                && (size == 1 || instance.getRandom().nextInt(size) == 0))
                            return true;
                    }
                }
                return EclipticSeasonsApi.getInstance().isRainingOrSnowing(instance, instance.getSharedSpawnPos());
            }
            return original.call(instance);
        }

    }

    @Mixin({dev.corgitaco.enhancedcelestials.server.commands.SetLunarEventCommand.class})
    public static abstract class SetLunarEventCommand {
        @WrapOperation(at = {@At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;isRaining()Z")},
                remap = false, method = {"setLunarEvent"})
        private static boolean es_patch$setLunarEvent_isRaining(ServerLevel instance, Operation<Boolean> original,
                                                                @Local(argsOnly = true) CommandSourceStack source) {
            if (EC.Config.enable.get() && EclipticSeasonsApi.getInstance().hasLocalWeather(instance)) {
                return EclipticSeasonsApi.getInstance().isRainingOrSnowing(instance, BlockPos.containing(source.getPosition()));
            }
            return original.call(instance);
        }

    }

}
