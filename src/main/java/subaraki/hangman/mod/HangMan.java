package subaraki.hangman.mod;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import subaraki.hangman.registry.HangManBlocks;
import subaraki.hangman.registry.HangManEntity;
import subaraki.hangman.registry.HangManItems;

@Mod(HangMan.MODID)
public class HangMan {
    public static final String MODID = "hangman";
    public static final Logger LOG = LogManager.getLogger();
    public static final DamageSource HANGING = (new DamageSource("hanging")).bypassArmor();

    public HangMan() {
        HangManBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HangManItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        HangManEntity.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

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