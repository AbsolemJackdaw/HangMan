package subaraki.hangman.mod;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import subaraki.hangman.registry.HangManBlocks;
import subaraki.hangman.registry.HangManEntity;
import subaraki.hangman.registry.HangManItems;

@Mod(HangManCommon.MODID)
public class HangMan extends HangManCommon {

    public HangMan() {
        HangManItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HangManBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HangManEntity.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigData.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigData.CLIENT_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modConfig);


    }

    public void modConfig(ModConfigEvent event) {
        ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigData.CLIENT_SPEC)
            ConfigData.refreshClient();
        else if (config.getSpec() == ConfigData.SERVER_SPEC)
            ConfigData.refreshServer();
    }
}