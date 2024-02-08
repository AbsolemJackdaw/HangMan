package subaraki.hangman.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import subaraki.hangman.mod.HangManCommon;

public class HangManItem {

    public static void register() {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(HangManCommon.MODID, "noose"), new BlockItem(HangManBlock.NOOSE, (new FabricItemSettings())));
    }
}
