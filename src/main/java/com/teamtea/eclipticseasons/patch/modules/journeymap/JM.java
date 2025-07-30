package com.teamtea.eclipticseasons.patch.modules.journeymap;

import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.minecraftforge.common.ForgeConfigSpec;

@ESPlugin(mods = "journeymap")
public class JM implements IESModPlugin {

    @Override
    public void client(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("Journey Map").push("journeymap");
            enable = builder
                    .define("Enable", true);
            builder.pop();
        }
    }
}
