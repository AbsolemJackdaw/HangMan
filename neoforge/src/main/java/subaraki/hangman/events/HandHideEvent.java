package subaraki.hangman.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.NooseEntity;
import subaraki.hangman.mod.HangManCommon;

@Mod.EventBusSubscriber(modid = HangManCommon.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HandHideEvent {

    @SubscribeEvent
    public static void renderHandEvent(RenderHandEvent event) {
        if ((Minecraft.getInstance().getCameraEntity() instanceof NooseEntity)) {
            event.setCanceled(true);
        }
    }
}
