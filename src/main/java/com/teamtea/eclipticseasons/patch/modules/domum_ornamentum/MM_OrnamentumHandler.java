package com.teamtea.eclipticseasons.patch.modules.domum_ornamentum;

import com.teamtea.eclipticseasons.api.data.season.SnowDefinition;
import com.teamtea.eclipticseasons.common.core.map.MapChecker;
import com.teamtea.eclipticseasons.common.core.snow.SnowChecker;
import com.teamtea.eclipticseasons.patch.EclipticSeasonsPatch;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Supplier;

public class MM_OrnamentumHandler {
    public static final MM_OrnamentumHandler INSTANCE = new MM_OrnamentumHandler();

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTagsUpdatedEvent(TagsUpdatedEvent tagsUpdatedEvent) {
        if (!MM_DO.Config.enable.get()) return;
        try {
            long start = System.nanoTime();

            Class<?> clazz = Class.forName("com.ldtteam.domumornamentum.block.ModBlocks");
            DeferredRegister<Block> types = (DeferredRegister<Block>)clazz.getField("BLOCKS").get(null);

            SnowDefinition.Info info = SnowDefinition.Info.builder().snowPassable(true).flag(MapChecker.FLAG_CUSTOM).build();
            SnowDefinition.Info infoSolid = SnowDefinition.Info.builder().flag(MapChecker.FLAG_CUSTOM).build();
            SnowDefinition.Info infoSolidAO = SnowDefinition.Info.builder().flag(MapChecker.FLAG_CUSTOM_AO).build();


            for (Supplier<Block> supplier : types.getEntries()) {
                Holder.Reference<Block> holder = supplier.get().builtInRegistryHolder();
                Block block = holder.get();
                ResourceLocation location = holder.key().location();
                // if (!location.getNamespace().equals("yuushya")) continue;
                String string = location.getPath();

                // if (string.contains("post")) {
                //     SnowChecker.SNOW_DEFINITION_MAP.putIfAbsent(block, SnowDefinition.builder()
                //             .blocks(HolderSet.direct(holder))
                //             .info(info)
                //             .build()
                //     );
                // } else
                    if (string.contains("shingle")
                // ||string.contains("pillar")
                ) {
                    SnowChecker.SNOW_DEFINITION_MAP.putIfAbsent(block, SnowDefinition.builder()
                            .blocks(HolderSet.direct(holder))
                            .info(infoSolidAO)
                            .build()
                    );
                }


            }
            long end = System.nanoTime();
            long elapsedMs = (end - start) / 1_000_000;
            EclipticSeasonsPatch.logger("[Domum Ornamentum x SnowDefinition] Registry scan took " + elapsedMs + " ms");
        } catch (NullPointerException | ClassCastException | NoSuchFieldException | ClassNotFoundException |
                 IllegalAccessException e) {
            EclipticSeasonsPatch.logger(e);
        }
    }


}
