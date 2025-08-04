package com.teamtea.eclipticseasons.patch.modules.incontrol;

import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.neoforged.neoforge.common.ModConfigSpec;

@ESPatch(mods = "incontrol")
public class IC implements IESModPatch {

    @Override
    public void common(ModConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ModConfigSpec.BooleanValue enable;

        public static void load(ModConfigSpec.Builder builder) {
            builder.comment("InControl").push("incontrol");
            enable = builder.define("Enable", true);
            builder.pop();
        }
    }
}
