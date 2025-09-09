package com.teamtea.eclipticseasons.patch.modules.enhancedcelestials;

import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.neoforged.neoforge.common.ModConfigSpec;

@ESPatch(mods = "enhancedcelestials")
public class EC implements IESModPatch {

    @Override
    public void common(ModConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ModConfigSpec.BooleanValue enable;

        public static void load(ModConfigSpec.Builder builder) {
            builder.comment("Enhanced Celestials").push("enhancedcelestials");
            enable = builder
                    .comment("Here, automatic snow-covered model compatibility has been added for certain blocks from Fetzi's Asian Decoration. At the moment, this is only a compromise solution.")
                    .define("Enable", true);
            builder.pop();
        }
    }
}
