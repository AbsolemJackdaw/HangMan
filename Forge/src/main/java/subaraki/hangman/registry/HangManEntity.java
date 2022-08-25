package subaraki.hangman.registry;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.ForgeNooseEntity;
import subaraki.hangman.mod.HangManCommon;

@Mod.EventBusSubscriber(modid = HangManCommon.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HangManEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HangManCommon.MODID);

    public static final RegistryObject<EntityType<?>> HANG_DUMMY = ENTITY_TYPES.register(HangManCommon.noose,
            () -> EntityType.Builder.of(ForgeNooseEntity::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSummon().sized(0.001F, 0.001F).build(HangManCommon.noose));

    public static final RegistryObject<EntityType<CameraPlayerOnNoose>> CAMERA = ENTITY_TYPES.register(HangManCommon.camera,
            () -> EntityType.Builder.<CameraPlayerOnNoose>of(CameraPlayerOnNoose::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSave().noSummon().sized(0.001F, 0.001F).build(HangManCommon.camera));

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registry.ENTITY_TYPE_REGISTRY)) {
        }
    }

}
