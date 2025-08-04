package com.teamtea.eclipticseasons.patch.mixin.modules.simpleclouds;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.teamtea.eclipticseasons.api.util.EclipticUtil;
import com.teamtea.eclipticseasons.common.core.map.MapChecker;
import com.teamtea.eclipticseasons.compat.vanilla.VanillaWeather;
import com.teamtea.eclipticseasons.patch.modules.simpleclouds.SC;
import dev.nonamecrackers2.simpleclouds.client.renderer.WorldEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({WorldEffects.class})
public class MixinSimpleCloudWorldEffects {
    @Shadow(remap = false)
    @Final
    private Minecraft mc;

    @WrapOperation(
            method = {"tick"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getBiome(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;")
    )
    private Holder<Biome> eclipticseasons$tick_getBiome(ClientLevel instance, BlockPos pos, Operation<Holder<Biome>> original) {
        return SC.Config.enable.get() && EclipticUtil.hasLocalWeather(instance) ?
                MapChecker.getSurfaceBiome(instance, pos) :
                original.call(instance, pos);
    }

    @WrapOperation(
            method = {"tick"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;getPrecipitationAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/biome/Biome$Precipitation;")
    )
    private Biome.Precipitation eclipticseasons$tick_getPrecipitationAt(Biome biome, BlockPos pos, Operation<Biome.Precipitation> original) {
        if (SC.Config.enable.get()) {
            if (EclipticUtil.hasLocalWeather(mc.level))
                return mc != null && mc.level != null ?
                        EclipticUtil.getRainOrSnow(mc.level, biome, pos) : Biome.Precipitation.NONE;
            else return VanillaWeather.handlePrecipitationAt(mc.level != null ?
                    mc.level :
                    VanillaWeather.getValidLevel(biome), biome, pos);
        }
        return original.call(biome, pos);
    }

    @ModifyExpressionValue(
            remap = false,
            method = {"tick"},
            at = @At(value = "INVOKE", target = "Ljava/util/Map;containsKey(Ljava/lang/Object;)Z")
    )
    private boolean eclipticseasons$tick_cancelNone(boolean original, @Local Biome.Precipitation precipitation) {
        if (SC.Config.enable.get() && precipitation == Biome.Precipitation.NONE)
            return true;
        return original;
    }
}
