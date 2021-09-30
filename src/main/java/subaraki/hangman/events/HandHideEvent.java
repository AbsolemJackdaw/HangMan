package subaraki.hangman.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.mod.HangMan;

@Mod.EventBusSubscriber(modid = HangMan.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class HandHideEvent {

    @SubscribeEvent
    public static void renderHandEvent(RenderHandEvent event) {
        if ((Minecraft.getInstance().cameraEntity instanceof CameraPlayerOnNoose)) {
            event.setCanceled(true);
        }
    }
}
