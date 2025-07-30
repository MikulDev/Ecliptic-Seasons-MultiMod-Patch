package com.teamtea.eclipticseasons.patch.data.lang;

import com.teamtea.eclipticseasons.data.general.lang.LangHelper;
import com.teamtea.eclipticseasons.patch.EclipticSeasonsPatch;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class Lang_EN extends LangHelper {
    public Lang_EN(PackOutput gen, ExistingFileHelper helper) {
        super(gen, helper, EclipticSeasonsPatch.MODID, "en_us");
    }


    @Override
    protected void addTranslations() {
        addTouhouLittleMaid();
        addConfigLang();
    }


    private void addTouhouLittleMaid() {
        add("task.eclipticseasons.clean_snow", "Clean Snow");
        add("task.eclipticseasons.clean_snow.desc", "Applied to snow-covered blocks from Ecliptic Season");
        add("task.eclipticseasons.clean_snow.condition.has_broom", "Has Broom");
    }

    private void addConfigLang() {

    }
}
