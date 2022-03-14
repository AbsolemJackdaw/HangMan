package subaraki.hangman.events;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import subaraki.hangman.entity.EmptyEntityRenderer;
import subaraki.hangman.mod.HangManCommon;
import subaraki.hangman.registry.HangManBlocks;
import subaraki.hangman.registry.HangManEntity;

@Mod.EventBusSubscriber(modid = HangManCommon.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEvent {

    @SubscribeEvent
    public static void clientsetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(HangManBlocks.NOOSE.get(), RenderType.cutout());

    }

    @SubscribeEvent
    public static void reg(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HangManEntity.HANG_DUMMY.get(), EmptyEntityRenderer::new);
        event.registerEntityRenderer(HangManEntity.CAMERA.get(), EmptyEntityRenderer::new);

    }
}
