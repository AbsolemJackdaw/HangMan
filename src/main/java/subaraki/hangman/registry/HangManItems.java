package subaraki.hangman.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import subaraki.hangman.mod.HangMan;

public class HangManItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HangMan.MODID);

    public static final RegistryObject<Item> NOOSE = ITEMS.register("noose",
            () -> new BlockItem(HangManBlocks.NOOSE.get(), (new Item.Properties()).tab(CreativeModeTab.TAB_TOOLS)));
}
