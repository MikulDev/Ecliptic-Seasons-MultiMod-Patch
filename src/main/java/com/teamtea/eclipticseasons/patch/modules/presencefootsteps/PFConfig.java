package com.teamtea.eclipticseasons.patch.modules.presencefootsteps;

import net.minecraftforge.common.ForgeConfigSpec;

public class PFConfig {

    public static ForgeConfigSpec.BooleanValue enable;

    public static void load(ForgeConfigSpec.Builder builder) {
        builder.push("presencefootsteps").comment("Presence Footsteps");
        enable = builder
                // .comment("Info used for development.")
                .define("Enable", true);
        builder.pop();
    }
}