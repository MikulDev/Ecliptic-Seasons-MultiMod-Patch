package com.teamtea.eclipticseasons.patch.mixin.modules.ambientsounds;


import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.teamtea.eclipticseasons.api.EclipticSeasonsApi;
import com.teamtea.eclipticseasons.common.core.map.MapChecker;
import com.teamtea.eclipticseasons.patch.modules.ambientsounds.AS6;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import team.creative.ambientsounds.environment.AmbientEnvironment;

@Mixin({AmbientEnvironment.class})
public abstract class MixinAmbientEnvironment {


    @Shadow(remap = false)
    public boolean snowing;

    @Shadow(remap = false)
    public boolean thundering;

    @Shadow(remap = false)
    public boolean raining;

    @WrapOperation(at = {@At(value = "INVOKE", target = "Lteam/creative/ambientsounds/mod/SereneSeasonsCompat;getTemperature(Lnet/minecraft/world/entity/player/Player;)F")},
            method = {"analyzeFast"},
            remap = false)
    private float eclipticseasons$isHalloween(Player player, Operation<Float> original, @Local(argsOnly = true) Level level) {
        if (AS6.Config.enable.get()) {
            BlockPos pos = player.blockPosition();
            Holder<Biome> biomeHolder = EclipticSeasonsApi.getInstance().hasLocalWeather(level) ?
                    MapChecker.getSurfaceBiome(level, pos) : level.getBiome(pos);
            Biome.Precipitation currentPrecipitationAt = EclipticSeasonsApi.getInstance().getCurrentPrecipitationAt(level, pos);
            float baseTemperature = biomeHolder.get().getBaseTemperature();
            if (currentPrecipitationAt == Biome.Precipitation.SNOW && baseTemperature > 0.14f) {
                baseTemperature = 0.14f;
            }
            this.snowing = currentPrecipitationAt == Biome.Precipitation.SNOW;
            this.thundering = EclipticSeasonsApi.getInstance().isThundering(level, pos);
            this.raining = EclipticSeasonsApi.getInstance().isRainingOrSnowing(level, pos);
            return baseTemperature;
        }
        return original.call(player);
    }


}
