package com.teamtea.eclipticseasons.patch.api;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;

public interface IESModPatch {

    default void client(ForgeConfigSpec.Builder consumer){};
    default void common(ForgeConfigSpec.Builder consumer){};

    default void register(IEventBus gameBus, IEventBus modEventBus){};

    default void init() {
    }
}
