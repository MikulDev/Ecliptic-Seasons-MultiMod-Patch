package com.teamtea.eclipticseasons.patch.api;

import net.neoforged.neoforge.common.ModConfigSpec;

public interface IESModPlugin {

    default void client(ModConfigSpec.Builder consumer){};
    default void common(ModConfigSpec.Builder consumer){};


}
