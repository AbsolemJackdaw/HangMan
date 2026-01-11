package subaraki.hangman.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import subaraki.hangman.entity.EmptyEntityRenderer;
import subaraki.hangman.registry.HangManBlock;
import subaraki.hangman.registry.HangManEntity;

public class HangManClient extends HangManCommon implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(HangManBlock.NOOSE, ChunkSectionLayer.CUTOUT);
        EntityRendererRegistry.register(HangManEntity.NOOSE, EmptyEntityRenderer::new);
        EntityRendererRegistry.register(HangManEntity.CAMERA, EmptyEntityRenderer::new);
    }
}
