package subaraki.hangman.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.ForgeNooseEntity;
import subaraki.hangman.mod.HangManCommon;

public class HangManEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, HangManCommon.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<ForgeNooseEntity>> HANG_DUMMY = ENTITY_TYPES.register(HangManCommon.NOOSE_ENTITY_STR,
            () -> EntityType.Builder.of(ForgeNooseEntity::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSummon().sized(0.001F, 0.001F).build(HangManCommon.ENTITY_NOOSE_KEY));

    public static final DeferredHolder<EntityType<?>, EntityType<CameraPlayerOnNoose>> CAMERA = ENTITY_TYPES.register(HangManCommon.CAMERA_ENTITY_STR,
            () -> EntityType.Builder.<CameraPlayerOnNoose>of(CameraPlayerOnNoose::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSave().noSummon().sized(0.001F, 0.001F).build(HangManCommon.ENTITY_CAMERA_KEY));
}
