package subaraki.mod;

import net.fabricmc.api.ModInitializer;
import subaraki.hangman.mod.HangManCommon;
import subaraki.registry.HangManBlock;
import subaraki.registry.HangManEntity;
import subaraki.registry.HangManItem;

public class HangMan extends HangManCommon implements ModInitializer {

    @Override
    public void onInitialize() {
        NOOSE = HangManEntity.NOOSE;
        CAMERA = HangManEntity.CAMERA;
        HangManBlock.register();
        HangManItem.register();
    }

}
