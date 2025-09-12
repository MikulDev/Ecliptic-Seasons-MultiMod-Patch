package com.teamtea.eclipticseasons.patch.modules.domum_ornamentum;

import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;

@ESPatch(mods = "domum_ornamentum",esVersion = "0.12.0-pre15")
public class MM_DO implements IESModPatch {

    @Override
    public void common(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    @Override
    public void register(IEventBus gameBus, IEventBus modEventBus) {
        gameBus.register(MM_OrnamentumHandler.INSTANCE);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;
        // public static ModConfigSpec.BooleanValue windows;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("Domum Ornamentum").push("domum_ornamentum");
            enable = builder
                    .comment("Here, automatic snow-covered model compatibility has been added for certain blocks from Domum Ornamentum. At the moment, this is only a compromise solution.")
                    .define("Enable", true);
            // fence = builder.define("fence", true);
            // wall = builder.define("wall", true);
            // windows = builder.define("windows", true);
            builder.pop();
        }
    }
}
