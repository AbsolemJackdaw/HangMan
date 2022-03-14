package subaraki.hangman.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.ForgeNooseEntity;
import subaraki.hangman.mod.HangManCommon;

@Mod.EventBusSubscriber(modid = HangManCommon.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HangManEntity {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, HangManCommon.MODID);

    public static final RegistryObject<EntityType<?>> HANG_DUMMY = ENTITIES.register("hang_dummy",
            () -> EntityType.Builder.of(ForgeNooseEntity::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSummon().sized(0.001F, 0.001F).build("hang_dummy"));

    public static final RegistryObject<EntityType<CameraPlayerOnNoose>> CAMERA = ENTITIES.register("camera",
            () -> EntityType.Builder.<CameraPlayerOnNoose>of(CameraPlayerOnNoose::new, MobCategory.MISC)
                    .setTrackingRange(256).setUpdateInterval(20).noSave().noSummon().sized(0.001F, 0.001F).build("camera"));

    @SubscribeEvent
    public static void register(RegistryEvent<EntityType<?>> event) {
        HangManCommon.NOOSE = HANG_DUMMY.get();
        HangManCommon.CAMERA = CAMERA.get();
    }

}
