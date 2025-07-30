package com.teamtea.eclipticseasons.patch.api;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.ModConfigSpec;

public interface IESModPlugin {

    default void client(ModConfigSpec.Builder consumer){};
    default void common(ModConfigSpec.Builder consumer){};

    default void register(IEventBus gameBus, IEventBus modEventBus){};


}
