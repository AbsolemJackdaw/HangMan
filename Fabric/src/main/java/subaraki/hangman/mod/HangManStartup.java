package subaraki.hangman.mod;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class HangManStartup extends HangManCommon implements PreLaunchEntrypoint {

    //legacy code : more info at : https://github.com/LlamaLad7/MixinExtras/wiki/ModifyExpressionValue
    @Override
    public void onPreLaunch() {
        //MixinExtrasBootstrap.init();
    }

}
