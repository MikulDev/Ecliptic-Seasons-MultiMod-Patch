package com.teamtea.eclipticseasons.patch.mixin.modules.incontrol;


import com.teamtea.eclipticseasons.api.EclipticSeasonsApi;
import com.teamtea.eclipticseasons.api.constant.solar.Season;
import com.teamtea.eclipticseasons.patch.modules.incontrol.IC;
import mcjty.incontrol.rules.support.GenericRuleEvaluator;
import mcjty.incontrol.tools.rules.IEventQuery;
import mcjty.incontrol.tools.varia.Tools;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.BiFunction;

@Mixin({GenericRuleEvaluator.class})
public abstract class MixinGenericRuleEvaluator {

    @Shadow(remap = false)
    @Final
    private List<BiFunction<Object, IEventQuery, Boolean>> checks;


    @Inject(at = {@At(value = "HEAD")},
            method = {"addSpringCheck"},
            remap = false, cancellable = true)
    private void eclipticseasons$addSpringCheck(Boolean s, CallbackInfo ci) {
        if (IC.Config.enable.get()) {
            this.checks.add((event, query) -> Season.SPRING == EclipticSeasonsApi.getInstance().getSolarTerm(Tools.getServerWorld(query.getWorld(event))).getSeason());
            ci.cancel();
        }
    }

    @Inject(at = {@At(value = "HEAD")},
            method = {"addSummerCheck"},
            remap = false, cancellable = true)
    private void eclipticseasons$addSummerCheck(Boolean s, CallbackInfo ci) {
        if (IC.Config.enable.get()) {
            this.checks.add((event, query) -> Season.SUMMER == EclipticSeasonsApi.getInstance().getSolarTerm(Tools.getServerWorld(query.getWorld(event))).getSeason());
            ci.cancel();
        }
    }

    @Inject(at = {@At(value = "HEAD")},
            method = {"addAutumnCheck"},
            remap = false, cancellable = true)
    private void eclipticseasons$addAutumnCheck(Boolean s, CallbackInfo ci) {
        if (IC.Config.enable.get()) {
            this.checks.add((event, query) -> Season.AUTUMN == EclipticSeasonsApi.getInstance().getSolarTerm(Tools.getServerWorld(query.getWorld(event))).getSeason());
            ci.cancel();
        }
    }

    @Inject(at = {@At(value = "HEAD")},
            method = {"addWinterCheck"},
            remap = false, cancellable = true)
    private void eclipticseasons$addWinterCheck(Boolean s, CallbackInfo ci) {
        if (IC.Config.enable.get()) {
            this.checks.add((event, query) -> Season.WINTER == EclipticSeasonsApi.getInstance().getSolarTerm(Tools.getServerWorld(query.getWorld(event))).getSeason());
            ci.cancel();
        }
    }

}
