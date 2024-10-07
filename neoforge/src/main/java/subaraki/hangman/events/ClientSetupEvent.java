package subaraki.hangman.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import subaraki.hangman.entity.EmptyEntityRenderer;
import subaraki.hangman.mod.HangManCommon;
import subaraki.hangman.registry.HangManEntity;

@EventBusSubscriber(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvent {

    @SubscribeEvent
    public static void reg(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HangManEntity.HANG_DUMMY.get(), EmptyEntityRenderer::new);
        event.registerEntityRenderer(HangManEntity.CAMERA.get(), EmptyEntityRenderer::new);
    }
}
