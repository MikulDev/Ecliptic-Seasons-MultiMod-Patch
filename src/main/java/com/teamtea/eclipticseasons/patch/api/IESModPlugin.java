package com.teamtea.eclipticseasons.patch.api;

import net.minecraftforge.common.ForgeConfigSpec;

public interface IESModPlugin {

    default void client(ForgeConfigSpec.Builder consumer){};
    default void common(ForgeConfigSpec.Builder consumer){};


}
