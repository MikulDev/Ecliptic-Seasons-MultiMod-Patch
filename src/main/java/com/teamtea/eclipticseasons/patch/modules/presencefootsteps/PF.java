package com.teamtea.eclipticseasons.patch.modules.presencefootsteps;

import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.minecraftforge.common.ForgeConfigSpec;

@ESPlugin(mods = "presencefootsteps")
public class PF implements IESModPlugin {

    @Override
    public void client(ForgeConfigSpec.Builder consumer) {
        PFConfig.load(consumer);
    }
}
