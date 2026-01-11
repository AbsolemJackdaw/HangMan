package subaraki.hangman.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import subaraki.hangman.blocks.NooseBlock;
import subaraki.hangman.mod.HangManCommon;

public class HangManBlock {
    public static final NooseBlock NOOSE = new NooseBlock();

    public static void register() {
        Registry.registerForHolder(BuiltInRegistries.BLOCK, HangManCommon.BLOCK_KEY, NOOSE);
    }
}
