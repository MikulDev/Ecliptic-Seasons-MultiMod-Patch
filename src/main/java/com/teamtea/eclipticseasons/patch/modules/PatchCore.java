package com.teamtea.eclipticseasons.patch.modules;

import com.teamtea.eclipticseasons.patch.EclipticSeasonsPatch;
import com.teamtea.eclipticseasons.patch.api.ESPlugin;
import com.teamtea.eclipticseasons.patch.api.IESModPlugin;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModInfo;
import net.neoforged.neoforgespi.language.ModFileScanData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PatchCore {
    public static final List<IESModPlugin> MOD_PLUGINS = new ArrayList<>();

    public static void run() {
        MOD_PLUGINS.clear();
        List<String> classNames = ModList.get().getAllScanData().stream().flatMap(($) -> $.getAnnotations().stream()).filter(($) -> {
            if (!$.annotationType().getClassName().equals(ESPlugin.class.getName())) {
                return false;
            } else {
                List<String> required = (ArrayList<String>) $.annotationData().getOrDefault("mods", new ArrayList<>());
                return new HashSet<>(ModList.get().getMods().stream().map(IModInfo::getModId).toList()).containsAll(required);
            }
        }).map(ModFileScanData.AnnotationData::memberName).toList();

        for (String className : classNames) {
            EclipticSeasonsPatch.logger("Start loading plugin at " + className);
            try {
                Class<?> clazz = Class.forName(className);
                if (IESModPlugin.class.isAssignableFrom(clazz)) {
                    IESModPlugin plugin = (IESModPlugin) clazz.getDeclaredConstructor().newInstance();
                    MOD_PLUGINS.add(plugin);
                }
            } catch (Throwable var7) {
                EclipticSeasonsPatch.logger("Error loading plugin at " + className, var7);
            }
        }
    }

    public static void register(IEventBus gameBus, IEventBus modEventBus) {
        for (IESModPlugin modPlugin : MOD_PLUGINS) {
            modPlugin.register(gameBus,modEventBus);
        }
    }
}
