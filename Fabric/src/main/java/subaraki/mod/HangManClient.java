package subaraki.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import subaraki.hangman.entity.EmptyEntityRenderer;
import subaraki.hangman.mod.HangManCommon;
import subaraki.registry.HangManBlock;
import subaraki.registry.HangManEntity;

public class HangManClient extends HangManCommon implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(HangManBlock.NOOSE, RenderType.cutout());
        EntityRendererRegistry.register(HangManEntity.NOOSE, EmptyEntityRenderer::new);
        EntityRendererRegistry.register(HangManEntity.CAMERA, EmptyEntityRenderer::new);
    }
}
