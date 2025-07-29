package com.teamtea.eclipticseasons.patch.modules.particlerain;

import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.minecraftforge.common.ForgeConfigSpec;

@ESPlugin(mods = "particlerain")
public class PR implements IESModPlugin {

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
}
