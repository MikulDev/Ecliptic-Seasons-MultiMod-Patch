package com.teamtea.eclipticseasons.patch.modules.presencefootsteps;

import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.neoforged.neoforge.common.ModConfigSpec;

@ESPatch(mods = "presencefootsteps")
public class PF implements IESModPatch {

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
