package com.teamtea.eclipticseasons.patch.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class PatchCommonConfig {
    public static final ForgeConfigSpec COMMON_CONFIG = new ForgeConfigSpec.Builder().configure(PatchCommonConfig::new).getRight();

    protected PatchCommonConfig(ForgeConfigSpec.Builder builder) {

    }

    public static void UpdateConfig(ModConfigEvent modConfigEvent) {
        if (!(modConfigEvent instanceof ModConfigEvent.Unloading)
                && modConfigEvent.getConfig().getSpec() == COMMON_CONFIG) {

        }
    }

}

