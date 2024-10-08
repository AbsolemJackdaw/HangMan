package subaraki.hangman.events;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import subaraki.hangman.entity.NooseEntity;
import subaraki.hangman.mod.HangManCommon;

@EventBusSubscriber(modid = HangManCommon.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class DisableGuiOnHang {

    @SubscribeEvent
    public static void gameoverlayEvent(RenderGuiEvent.Pre event) {
        if (Minecraft.getInstance().player.getVehicle() instanceof NooseEntity) {
            event.setCanceled(true);
        }
    }
}
