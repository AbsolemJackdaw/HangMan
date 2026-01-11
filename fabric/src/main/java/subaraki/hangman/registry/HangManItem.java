package subaraki.hangman.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import subaraki.hangman.mod.HangManCommon;

public class HangManItem {

    public static void register() {
        Registry.register(BuiltInRegistries.ITEM, HangManCommon.ITEM_KEY, new BlockItem(HangManBlock.NOOSE, new Item.Properties().setId(HangManCommon.ITEM_KEY)));
    }
}
