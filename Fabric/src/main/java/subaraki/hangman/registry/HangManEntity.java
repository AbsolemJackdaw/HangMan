package subaraki.hangman.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.NooseEntity;
import subaraki.hangman.mod.HangManCommon;

public class HangManEntity {
    public static final EntityType<NooseEntity> NOOSE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(HangManCommon.MODID, HangManCommon.noose),
            FabricEntityTypeBuilder.<NooseEntity>create(MobCategory.MISC, NooseEntity::new).dimensions(EntityDimensions.fixed(0.001F, 0.001F)).trackedUpdateRate(20).trackRangeBlocks(256).disableSummon().build());

    public static final EntityType<CameraPlayerOnNoose> CAMERA = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(HangManCommon.MODID, HangManCommon.camera),
            FabricEntityTypeBuilder.<CameraPlayerOnNoose>create(MobCategory.MISC, CameraPlayerOnNoose::new).dimensions(EntityDimensions.fixed(0.001F, 0.001F)).trackedUpdateRate(20).trackRangeBlocks(256).disableSummon().disableSaving().build());

    public static void register() {
        //classloader to init static final fields
    }
}
