package subaraki.hangman.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.ForgeNooseEntity;
import subaraki.hangman.mod.HangManCommon;

@Mod(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.MOD)
public class HangManEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(NeoForgeRegistries.ENTITY, HangManCommon.MODID);

    public static final RegistryObject<EntityType<?>> HANG_DUMMY = ENTITY_TYPES.register(HangManCommon.noose,
            () -> EntityType.Builder.of(ForgeNooseEntity::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSummon().sized(0.001F, 0.001F).build(HangManCommon.noose));

    public static final RegistryObject<EntityType<CameraPlayerOnNoose>> CAMERA = ENTITY_TYPES.register(HangManCommon.camera,
            () -> EntityType.Builder.<CameraPlayerOnNoose>of(CameraPlayerOnNoose::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSave().noSummon().sized(0.001F, 0.001F).build(HangManCommon.camera));
}
