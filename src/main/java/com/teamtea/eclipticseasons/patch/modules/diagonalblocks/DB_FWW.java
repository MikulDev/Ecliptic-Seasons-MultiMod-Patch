package com.teamtea.eclipticseasons.patch.modules.diagonalblocks;

import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import com.teamtea.eclipticseasons.patch.modules.cold_sweat.Cold_Sweat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@ESPatch(mods = "diagonalblocks")
public class DB_FWW implements IESModPatch {

    @Override
    public void common(ModConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    @Override
    public void register(IEventBus gameBus, IEventBus modEventBus) {
        gameBus.register(DiagonalBlocksHanlder.INSTANCE);
    }

    public static class Config {

        public static ModConfigSpec.BooleanValue enable;
        public static ModConfigSpec.BooleanValue fence;
        public static ModConfigSpec.BooleanValue wall;
        // public static ModConfigSpec.BooleanValue windows;

        public static void load(ModConfigSpec.Builder builder) {
            builder.comment("Diagonal Blocks").push("diagonalblocks");
            enable = builder.define("Enable", true);
            fence = builder.define("fence", true);
            wall = builder.define("wall", true);
            // windows = builder.define("windows", true);
            builder.pop();
        }
    }
}
