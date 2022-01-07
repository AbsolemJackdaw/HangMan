package subaraki.hangman.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import subaraki.hangman.entity.NooseEntity;
import subaraki.hangman.mod.HangManCommon;

@Mod.EventBusSubscriber(modid = HangManCommon.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DisableGuiOnHang {

    @SubscribeEvent
    public static void gameoverlayEvent(RenderGameOverlayEvent.Pre event) {
        if (event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {
            if (Minecraft.getInstance().player.getVehicle() instanceof NooseEntity noose) {
                event.setCanceled(true);
            }
        }
    }
}
