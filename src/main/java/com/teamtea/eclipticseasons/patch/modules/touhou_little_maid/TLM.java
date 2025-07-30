package com.teamtea.eclipticseasons.patch.modules.touhou_little_maid;

import com.teamtea.eclipticseasons.api.constant.solar.SolarTerm;
import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@ESPlugin(mods = "touhou_little_maid")
public class TLM implements IESModPlugin {

    @Override
    public void common(ModConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    @Override
    public void register(IEventBus gameBus, IEventBus modEventBus) {
        gameBus.register(LittleMaid.INSTANCE);
    }

    public static class Config {

        public static ModConfigSpec.BooleanValue enable;

        public static void load(ModConfigSpec.Builder builder) {
            builder.comment("touhou_little_maid").push("touhou_little_maid");
            enable = builder.define("Enable", true);
            builder.pop();
        }
    }
}
