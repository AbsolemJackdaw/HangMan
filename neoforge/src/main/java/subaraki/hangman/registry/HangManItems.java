package subaraki.hangman.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import subaraki.hangman.mod.HangManCommon;

import java.util.function.Supplier;

public class HangManItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(HangManCommon.MODID);
    public static final Supplier<BlockItem> NOOSE = ITEMS.register("noose", () -> new BlockItem(HangManBlocks.NOOSE.get(), new Item.Properties()));
}
