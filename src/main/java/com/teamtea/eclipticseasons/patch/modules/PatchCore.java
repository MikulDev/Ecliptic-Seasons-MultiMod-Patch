package com.teamtea.eclipticseasons.patch.modules;

import com.teamtea.eclipticseasons.patch.EclipticSeasonsPatch;
import com.teamtea.eclipticseasons.patch.api.ESPatches;
import com.teamtea.eclipticseasons.patch.api.IESModPatch;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.forgespi.language.ModFileScanData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PatchCore {
    public static final List<IESModPatch> MOD_PLUGINS = new ArrayList<>();

    public static void run() {
        MOD_PLUGINS.clear();
        List<String> classNames = ModList.get().getAllScanData().stream().flatMap(($) -> $.getAnnotations().stream()).filter(($) -> {
            if (!$.annotationType().getClassName().equals(ESPatches.class.getName())) {
                return false;
            } else {
                List<String> required = (ArrayList<String>) $.annotationData().getOrDefault("mods", new ArrayList<>());
                return new HashSet<>(ModList.get().getMods().stream().map(IModInfo::getModId).toList()).containsAll(required);
            }
        }).map(ModFileScanData.AnnotationData::memberName).toList();

        for (String className : classNames) {
            EclipticSeasonsPatch.logger("Find patch from " + className);
            try {
                Class<?> clazz = Class.forName(className);
                if (IESModPatch.class.isAssignableFrom(clazz)) {
                    IESModPatch plugin = (IESModPatch) clazz.getDeclaredConstructor().newInstance();
                    MOD_PLUGINS.add(plugin);
                }
            } catch (Throwable var7) {
                EclipticSeasonsPatch.logger("Failed to load patch from " + className, var7);
            }
        }
    }

    public static void register(IEventBus gameBus, IEventBus modEventBus) {
        for (IESModPatch modPlugin : MOD_PLUGINS) {
            modPlugin.register(gameBus,modEventBus);
        }
    }
}
