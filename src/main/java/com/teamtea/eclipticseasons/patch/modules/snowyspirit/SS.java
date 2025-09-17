package com.teamtea.eclipticseasons.patch.modules.snowyspirit;

import com.teamtea.eclipticseasons.api.constant.solar.SolarTerm;
import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import com.teamtea.eclipticseasons.patch.config.PatchCommonConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

@ESPatch(mods = "snowyspirit")
public class SS implements IESModPatch {

    @Override
    public void common(ForgeConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ForgeConfigSpec.BooleanValue enable;
        public static ForgeConfigSpec.ConfigValue<List<? extends SolarTerm>> snowyspirit_winters;
        public static ForgeConfigSpec.BooleanValue specialTime;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.comment("Snowy Spirit").push("snowyspirit");
            enable = builder.define("Enable", true);
            specialTime = builder.comment("Enable special time with SnowySpirit.")
                    .define("SpecialTime", true);
            snowyspirit_winters = builder.comment("Solar Terms in which SnowySpirit villager AI behaviors will be active.")
                    .defineListAllowEmpty("WinterTime",
                            () -> List.of(SolarTerm.BEGINNING_OF_WINTER,
                                    SolarTerm.LIGHT_SNOW,
                                    SolarTerm.HEAVY_SNOW,
                                    SolarTerm.WINTER_SOLSTICE,
                                    SolarTerm.LESSER_COLD,
                                    SolarTerm.GREATER_COLD),
                            PatchCommonConfig::validSolarTerm);
            builder.pop();
        }
    }
}
