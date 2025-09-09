package com.teamtea.eclipticseasons.patch.mixin.modules.goety;


import com.Polarice3.Goety.compat.serene_seasons.SSeasonsLoaded;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.teamtea.eclipticseasons.api.EclipticSeasonsApi;
import com.teamtea.eclipticseasons.patch.modules.goety.GOETY;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

public abstract class MixinSeasonBundle {

    // Forge not support
    // @Mixin({com.Polarice3.Goety.api.magic.ISpell.class})
    // public interface ISpell {
    //     @WrapOperation(at = {@At(value = "INVOKE", target = "Lcom/Polarice3/Goety/compat/serene_seasons/SSeasonsLoaded;isLoaded()Z")},
    //             remap = false,
    //             method = {"SoulCalculation"})
    //     private boolean es_patch$SoulCalculation_isLoaded(SSeasonsLoaded instance, Operation<Boolean> original) {
    //         if (GOETY.Config.enable.get()) {
    //             return true;
    //         }
    //         return original.call(instance);
    //     }
    //
    //     @WrapOperation(at = {@At(value = "INVOKE", target = "Lcom/Polarice3/Goety/compat/serene_seasons/SSeasonsIntegration;summonSnowVariant(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z")},
    //             remap = false,method = {"SoulCalculation"})
    //     private boolean es_patch$SoulCalculation_summonSnowVariant(Level level, BlockPos pos, Operation<Boolean> original) {
    //         if (GOETY.Config.enable.get()) {
    //             return EclipticSeasonsApi.getInstance().getPrecipitationAt(level, pos) == Biome.Precipitation.SNOW;
    //         }
    //         return original.call(level, pos);
    //     }
    // }

    @Mixin({com.Polarice3.Goety.common.entities.ally.undead.zombie.ZombieServant.class,
            com.Polarice3.Goety.common.entities.ally.undead.skeleton.AbstractSkeletonServant.class})
    public static abstract class ZombieServant {
        @WrapOperation(at = {@At(value = "INVOKE", target = "Lcom/Polarice3/Goety/compat/serene_seasons/SSeasonsLoaded;isLoaded()Z")},
                remap = false,method = {"getVariant"})
        private boolean es_patch$getVariant_season(SSeasonsLoaded instance, Operation<Boolean> original) {
            if (GOETY.Config.enable.get()) {
                return true;
            }
            return original.call(instance);
        }

        @WrapOperation(at = {@At(value = "INVOKE", target = "Lcom/Polarice3/Goety/compat/serene_seasons/SSeasonsIntegration;summonSnowVariant(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z")},
                remap = false,method = {"getVariant"})
        private boolean es_patch$getVariant_summonSnowVariant(Level level, BlockPos pos, Operation<Boolean> original) {
            if (GOETY.Config.enable.get()) {
                return EclipticSeasonsApi.getInstance().getPrecipitationAt(level, pos) == Biome.Precipitation.SNOW;
            }
            return original.call(level, pos);
        }
    }


}
