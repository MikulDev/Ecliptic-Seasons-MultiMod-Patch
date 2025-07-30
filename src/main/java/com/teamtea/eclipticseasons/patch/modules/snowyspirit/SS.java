package com.teamtea.eclipticseasons.patch.modules.snowyspirit;

import com.teamtea.eclipticseasons.api.constant.solar.SolarTerm;
import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@ESPlugin(mods = "snowyspirit")
public class SS implements IESModPlugin {

    @Override
    public void common(ModConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ModConfigSpec.BooleanValue enable;
        public static ModConfigSpec.ConfigValue<List<? extends SolarTerm>> snowyspirit_winters;
        public static ModConfigSpec.BooleanValue specialTime;

        public static void load(ModConfigSpec.Builder builder) {
            builder.comment("Snowy Spirit").push("snowyspirit");
            enable = builder.define("Enable", true);
            specialTime = builder.comment("Enable special time with SnowySpirit.")
                    .define("SpecialTime", true);
            snowyspirit_winters = builder.comment("Solar Terms in which Snowy Spirit villager AI behaviors will be active.")
                    .defineListAllowEmpty("WinterTime",
                            () -> List.of(SolarTerm.BEGINNING_OF_WINTER,
                                    SolarTerm.LIGHT_SNOW,
                                    SolarTerm.HEAVY_SNOW,
                                    SolarTerm.WINTER_SOLSTICE,
                                    SolarTerm.LESSER_COLD,
                                    SolarTerm.GREATER_COLD), () -> SolarTerm.WINTER_SOLSTICE,
                            o -> o instanceof SolarTerm);
            builder.pop();
        }
    }
}
