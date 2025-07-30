package com.teamtea.eclipticseasons.patch.modules.touhou_little_maid;

import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.List;

@ESPlugin(mods = "touhou_little_maid")
public class TLM implements IESModPlugin {

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
