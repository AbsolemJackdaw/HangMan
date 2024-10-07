package subaraki.hangman.events;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import subaraki.hangman.entity.NooseEntity;
import subaraki.hangman.mod.HangManCommon;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class DontHitYourselfEvent {

    @SubscribeEvent
    public static void clickEvent(InputEvent.InteractionKeyMappingTriggered event) {
        if (event.isAttack() && Minecraft.getInstance().cameraEntity instanceof NooseEntity)
            event.setCanceled(true);
    }
}
