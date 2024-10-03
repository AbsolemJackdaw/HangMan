package subaraki.hangman.mod;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import subaraki.hangman.registry.HangManBlocks;
import subaraki.hangman.registry.HangManEntity;
import subaraki.hangman.registry.HangManItems;

@net.neoforged.fml.common.Mod(HangManCommon.MODID)
public class HangMan extends HangManCommon {

    public HangMan() {
        HangManItems.ITEMS.register(JavaModLoadingContext.get().getModEventBus());
        HangManBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HangManEntity.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigData.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigData.CLIENT_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modConfig);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addToCreativeTab);
    }

    public void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(HangManItems.NOOSE);
        }
    }

    public void modConfig(ModConfigEvent event) {
        ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigData.CLIENT_SPEC)
            ConfigData.refreshClient();
        else if (config.getSpec() == ConfigData.SERVER_SPEC)
            ConfigData.refreshServer();
    }
}