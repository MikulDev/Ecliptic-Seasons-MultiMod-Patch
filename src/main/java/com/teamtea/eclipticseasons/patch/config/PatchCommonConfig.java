package com.teamtea.eclipticseasons.patch.config;

import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import com.teamtea.eclipticseasons.patch.modules.PatchCore;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class PatchCommonConfig {
    public static final ForgeConfigSpec COMMON_CONFIG = new ForgeConfigSpec.Builder().configure(PatchCommonConfig::new).getRight();

    protected PatchCommonConfig(ForgeConfigSpec.Builder builder) {
        for (IESModPlugin modPlugin : PatchCore.MOD_PLUGINS) {
            modPlugin.common(builder);
        }
    }

    public static void UpdateConfig(ModConfigEvent modConfigEvent) {
        if (!(modConfigEvent instanceof ModConfigEvent.Unloading)
                && modConfigEvent.getConfig().getSpec() == COMMON_CONFIG) {
        }
    }

}

