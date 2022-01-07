package subaraki.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import subaraki.hangman.mod.HangManCommon;

public class HangManItem {

    public static void register() {
        Registry.register(Registry.ITEM, new ResourceLocation(HangManCommon.MODID, "noose"), new BlockItem(HangManBlock.NOOSE, (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));

    }
}
