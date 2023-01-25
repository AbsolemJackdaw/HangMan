package subaraki.hangman.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.NooseEntity;
import subaraki.hangman.mod.HangManCommon;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = HangManCommon.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DontHitYourselfEvent {

    @SubscribeEvent
    public static void clickEvent(final InputEvent.InteractionKeyMappingTriggered event) {
        if (event.isAttack() && Minecraft.getInstance().cameraEntity instanceof NooseEntity)
            event.setCanceled(true);
    }

}
