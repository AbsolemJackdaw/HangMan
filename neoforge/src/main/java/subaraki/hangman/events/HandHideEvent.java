package subaraki.hangman.events;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import subaraki.hangman.entity.NooseEntity;
import subaraki.hangman.mod.HangManCommon;

@EventBusSubscriber(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HandHideEvent {

    @SubscribeEvent
    public static void renderHandEvent(RenderHandEvent event) {
        if ((Minecraft.getInstance().getCameraEntity() instanceof NooseEntity)) {
            event.setCanceled(true);
        }
    }
}
