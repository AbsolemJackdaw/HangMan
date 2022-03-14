package subaraki.hangman.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import subaraki.hangman.util.EntityHangableListReader;
import subaraki.hangman.registry.HangManBlock;
import subaraki.hangman.registry.HangManEntity;
import subaraki.hangman.registry.HangManItem;

public class HangMan extends HangManCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        NOOSE = HangManEntity.NOOSE;
        CAMERA = HangManEntity.CAMERA;
        HangManBlock.register();
        HangManItem.register();
        ResourceManagerHelperImpl.get(PackType.SERVER_DATA).registerReloadListener(new EntityHangableListReaderFabricImpl());
    }

    public static class EntityHangableListReaderFabricImpl extends EntityHangableListReader implements SimpleSynchronousResourceReloadListener {

        @Override
        public ResourceLocation getFabricId() {
            return new ResourceLocation(HangManCommon.MODID, "idkyet");
        }

        @Override
        public void onResourceManagerReload(ResourceManager resourceManager) {

        }
    }
}
