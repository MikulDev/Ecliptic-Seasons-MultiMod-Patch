package com.teamtea.eclipticseasons.patch.modules.presencefootsteps;

import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.neoforged.neoforge.common.ModConfigSpec;

@ESPlugin(mods = "presencefootsteps")
public class PF implements IESModPlugin {

    @Override
    public void client(ModConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ModConfigSpec.BooleanValue enable;

        public static void load(ModConfigSpec.Builder builder) {
            builder.comment("Presence Footsteps").push("presencefootsteps");
            enable = builder
                    .define("Enable", true);
            builder.pop();
        }
    }
}
