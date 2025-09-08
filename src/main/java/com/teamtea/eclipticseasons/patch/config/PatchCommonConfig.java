package com.teamtea.eclipticseasons.patch.config;

import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import com.teamtea.eclipticseasons.patch.modules.PatchCore;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class PatchCommonConfig {
    public static final ModConfigSpec COMMON_CONFIG = new ModConfigSpec.Builder().configure(PatchCommonConfig::new).getRight();

    protected PatchCommonConfig(ModConfigSpec.Builder builder) {
        for (IESModPatch modPlugin : PatchCore.MOD_PLUGINS) {
            modPlugin.common(builder);
        }
    }

    public static void UpdateConfig(ModConfigEvent modConfigEvent) {
        if (!(modConfigEvent instanceof ModConfigEvent.Unloading)
                && modConfigEvent.getConfig().getSpec() == COMMON_CONFIG) {
        }
    }

    public static boolean validSeason(Object o) {
        if (o instanceof String s) {
            try {
                com.teamtea.eclipticseasons.api.constant.solar.Season.valueOf(s);
                return true;
            } catch (IllegalArgumentException ignored) {
            }
        }
        return false;
    }

}

