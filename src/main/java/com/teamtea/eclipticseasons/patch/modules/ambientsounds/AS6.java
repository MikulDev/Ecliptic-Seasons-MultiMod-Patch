package com.teamtea.eclipticseasons.patch.modules.ambientsounds;

import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.minecraftforge.common.ForgeConfigSpec;

@ESPlugin(mods = "ambientsounds")
public class AS6 implements IESModPlugin {

    @Override
    public void client(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("AmbientSounds").push("ambientsounds");
            enable = builder
                    .define("Enable", true);
            builder.pop();
        }
    }
}
