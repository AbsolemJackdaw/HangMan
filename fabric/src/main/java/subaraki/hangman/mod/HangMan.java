package subaraki.hangman.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import subaraki.hangman.registry.HangManBlock;
import subaraki.hangman.registry.HangManEntity;
import subaraki.hangman.registry.HangManItem;
import subaraki.hangman.util.EntityHangableListReader;

public class HangMan extends HangManCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        HangManBlock.register();
        HangManItem.register();
        HangManEntity.register();
        //TODO find alternative to fabric.impl for resourcemanager
        //fabric.impl has a tendency to crash Quilt
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new EntityHangableListReaderFabricImpl());
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(content -> {
            content.addAfter(() -> Items.SPECTRAL_ARROW, HangManBlock.NOOSE::asItem); // bad
        });
    }

    public static class EntityHangableListReaderFabricImpl extends EntityHangableListReader implements SimpleSynchronousResourceReloadListener {

        //no need to add more code here, this'll do the job and call the parent's class
        @Override
        public ResourceLocation getFabricId() {
            return ResourceLocation.fromNamespaceAndPath(HangManCommon.MODID, "resource_reloader");
        }

        @Override
        public void onResourceManagerReload(ResourceManager resourceManager) {
        }
    }
}
