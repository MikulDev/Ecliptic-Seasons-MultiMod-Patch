package com.teamtea.eclipticseasons.patch.modules.hauntedharvest;

import com.teamtea.eclipticseasons.api.constant.solar.SolarTerm;
import com.teamtea.eclipticseasons.patch.api.ESPatch;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import com.teamtea.eclipticseasons.patch.config.PatchCommonConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@ESPatch(mods = "hauntedharvest")
public class HH implements IESModPatch {

    @Override
    public void common(ModConfigSpec.Builder consumer) {
        Config.load(consumer);
    }

    public static class Config {

        public static ModConfigSpec.BooleanValue enable;
        public static ModConfigSpec.ConfigValue<List<? extends SolarTerm>> hauntedharvest_halloween_time;
        public static ModConfigSpec.ConfigValue<List<? extends SolarTerm>> hauntedharvest_mobs_wear_pumpkins_time;

        public static void load(ModConfigSpec.Builder builder) {
            builder.comment("Haunted Harvest").push("hauntedharvest");
            enable = builder.define("Enable", true);
            hauntedharvest_halloween_time = builder.comment("Solar Terms in which Haunted Harvest villager AI behaviors will be active.")
                    .defineListAllowEmpty("Halloween Time",
                            () -> List.of(
                                    SolarTerm.COLD_DEW,
                                    SolarTerm.FIRST_FROST),
                            () -> SolarTerm.COLD_DEW,
                            PatchCommonConfig::validSolarTerm);
            hauntedharvest_mobs_wear_pumpkins_time = builder.comment("Adds custom times in which mobs can wear pumpkins. Leave empty to ignore.")
                    .defineListAllowEmpty(" Mobs Wear Pumpkins Time",
                            () -> List.of(SolarTerm.FIRST_FROST),
                            () -> SolarTerm.FIRST_FROST,
                            PatchCommonConfig::validSolarTerm);
            builder.pop();
        }
    }
}
