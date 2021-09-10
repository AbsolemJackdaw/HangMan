package subaraki.hangman.registry;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import subaraki.hangman.blocks.NooseBlock;
import subaraki.hangman.mod.HangMan;

public class HangManBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HangMan.MODID);

    public static final RegistryObject<Block> NOOSE = BLOCKS.register("noose", NooseBlock::new);
}
