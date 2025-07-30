package com.teamtea.eclipticseasons.patch.config;

import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import com.teamtea.eclipticseasons.patch.modules.PatchCore;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class PatchClientConfig {

    public static final ModConfigSpec CLIENT_CONFIG = new ModConfigSpec.Builder().configure(PatchClientConfig::new).getRight();

    protected PatchClientConfig(ModConfigSpec.Builder builder) {
        for (IESModPlugin modPlugin : PatchCore.MOD_PLUGINS) {
            modPlugin.client(builder);
        }
    }

    public static void UpdateConfig(ModConfigEvent modConfigEvent) {
        if (!(modConfigEvent instanceof ModConfigEvent.Unloading)
                && modConfigEvent.getConfig().getSpec() == CLIENT_CONFIG) {
        }
    }
}
