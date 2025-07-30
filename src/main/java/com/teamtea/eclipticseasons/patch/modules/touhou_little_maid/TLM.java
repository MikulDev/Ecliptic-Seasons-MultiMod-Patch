package com.teamtea.eclipticseasons.patch.modules.touhou_little_maid;

import com.teamtea.eclipticseasons.patch.api.ESPatches;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;

@ESPatches(mods = "touhou_little_maid")
public class TLM implements IESModPatch {

    @Override
    public void common(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    @Override
    public void register(IEventBus gameBus, IEventBus modEventBus) {
        gameBus.register(LittleMaid.INSTANCE);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("touhou_little_maid").push("touhou_little_maid");
            enable = builder.define("Enable", true);
            builder.pop();
        }
    }
}
