package subaraki.hangman.registry;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.NeoForgeRegistriesSetup;
import subaraki.hangman.blocks.NooseBlock;
import subaraki.hangman.mod.HangManCommon;

public class HangManBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, HangManCommon.MODID);

    public static final RegistryObject<Block> NOOSE = BLOCKS.register("noose", NooseBlock::new);
}
