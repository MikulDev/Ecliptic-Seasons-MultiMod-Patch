package com.teamtea.eclipticseasons.patch.modules.simpleclouds;

import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.minecraftforge.common.ForgeConfigSpec;


@ESPatch(mods = "simpleclouds")
public class SC implements IESModPatch {

    @Override
    public void client(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("Simple Clouds","This is just a simple processing routine and is not fully compatible.").push("simpleclouds");
            enable = builder.define("Enable", true);
            builder.pop();
        }
    }
}
