package subaraki.hangman.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import subaraki.hangman.blocks.NooseBlock;
import subaraki.hangman.mod.HangManCommon;

public class HangManBlock {
    public static final NooseBlock NOOSE = new NooseBlock();

    public static void register() {
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(HangManCommon.MODID, "noose"), NOOSE);
    }
}
