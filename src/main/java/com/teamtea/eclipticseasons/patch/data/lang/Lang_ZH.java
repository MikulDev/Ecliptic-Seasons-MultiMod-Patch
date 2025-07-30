package com.teamtea.eclipticseasons.patch.data.lang;


import com.teamtea.eclipticseasons.data.general.lang.LangHelper;
import com.teamtea.eclipticseasons.patch.EclipticSeasonsPatch;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class Lang_ZH extends LangHelper {
    public Lang_ZH(PackOutput gen, ExistingFileHelper helper) {
        super(gen, helper, EclipticSeasonsPatch.MODID, "zh_cn");
    }


    @Override
    protected void addTranslations() {
        addTouhouLittleMaid();
        addConfigLang();
    }

    private void addTouhouLittleMaid() {
        add("task.eclipticseasons.clean_snow", "扫雪");
        add("task.eclipticseasons.clean_snow.desc", "适用于节气的覆雪方块");
        add("task.eclipticseasons.clean_snow.condition.has_broom", "持有扫帚");
    }

    private void addConfigLang() {


    }


}
