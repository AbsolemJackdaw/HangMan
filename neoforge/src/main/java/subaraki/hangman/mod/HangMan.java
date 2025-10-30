package subaraki.hangman.mod;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import subaraki.hangman.registry.HangManBlocks;
import subaraki.hangman.registry.HangManEntity;
import subaraki.hangman.registry.HangManItems;

@Mod(HangManCommon.MODID)
public class HangMan extends HangManCommon {

    public HangMan(IEventBus eventBus, ModContainer container) {
        HangManItems.ITEMS.register(eventBus);
        HangManBlocks.BLOCKS.register(eventBus);
        HangManEntity.ENTITY_TYPES.register(eventBus);

        container.registerConfig(ModConfig.Type.SERVER, ConfigData.SERVER_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, ConfigData.CLIENT_SPEC);
        eventBus.addListener(this::modConfig);
     }

    public void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(HangManItems.NOOSE.get());
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