package com.teamtea.eclipticseasons.patch.modules.particlerain;

import com.teamtea.eclipticseasons.api.EclipticSeasonsApi;
import com.teamtea.eclipticseasons.common.core.biome.WeatherManager;
import com.teamtea.eclipticseasons.compat.vanilla.VanillaWeather;
import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.Tags;

@ESPatch(mods = "particlerain")
public class PR implements IESModPatch {

    @Override
    public void client(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;
        public static ForgeConfigSpec.BooleanValue fixSand;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("Particle Rain").push("particlerain");
            enable = builder
                    .define("Enable", true);
            fixSand = builder
                    .comment("When it rains in desert biomes, replace it with a sandstorm.")
                    .define("FixSand", true);
            builder.pop();
        }
    }

    public static class Hook {

        public static Biome.Precipitation getPrecipitation(Biome instance, BlockPos pos, ClientLevel level, Holder<Biome> biomeHolder) {
            boolean hasLocalWeather = EclipticSeasonsApi.getInstance().hasLocalWeather(level);
            Biome.Precipitation precipitationAt = hasLocalWeather ?
                    WeatherManager.getPrecipitationAt(level, instance, pos) :
                    VanillaWeather.handlePrecipitationAt(level, instance, pos);
            if (Config.fixSand.get() && precipitationAt == Biome.Precipitation.RAIN && hasLocalWeather) {
                if (instance.getModifiedClimateSettings().downfall() == 0 && (biomeHolder.is(Tags.Biomes.IS_DESERT) || biomeHolder.is(BiomeTags.IS_BADLANDS)))
                    precipitationAt = Biome.Precipitation.NONE;
            }
            return precipitationAt;
        }
    }

}
