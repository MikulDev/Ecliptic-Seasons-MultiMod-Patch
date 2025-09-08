package com.teamtea.eclipticseasons.patch.modules.diagonalblocks;

import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;

@ESPatch(mods = "diagonalblocks")
public class DB_FWW implements IESModPatch {

    @Override
    public void common(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    @Override
    public void register(IEventBus gameBus, IEventBus modEventBus) {
        gameBus.register(DiagonalBlocksHandler.INSTANCE);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;
        public static ForgeConfigSpec.BooleanValue fence;
        public static ForgeConfigSpec.BooleanValue wall;
        // public static ForgeConfigSpec.BooleanValue windows;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("Diagonal Blocks").push("diagonalblocks");
            enable = builder.define("Enable", true);
            fence = builder.define("fence", true);
            wall = builder.define("wall", true);
            // windows = builder.define("windows", true);
            builder.pop();
        }
    }
}
