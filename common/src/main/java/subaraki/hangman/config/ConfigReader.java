package subaraki.hangman.config;

import subaraki.hangman.mod.CommonConfigData;
import subaraki.hangman.mod.HangManCommon;

public class ConfigReader extends CommonConfigData {
    //initialize configuration file here. do not remove
    private static final SimpleConfig config = load();

    // Custom config provider, returns the default config content
    // if the custom provider is not specified SimpleConfig will create an empty file instead
    private static String provider(String filename) {
        StringBuilder builder = new StringBuilder();
        builder.append("#if players can be hurt on the noose, return true\n");
        builder.append("canHurtPlayer=false\n");
        builder.append("#if entities can be hurt on the noose, return true. ('false' omits the data pack rule, 'true' enforces it\n");
        builder.append("canHurtEntity=false\n");
        builder.append("#\n");
        builder.append("#amount of damage players take when on the noose\n");
        builder.append("playerDamage=5\n");
        builder.append("#amount of damage entities take when on the noose\n");
        builder.append("entityDamage=10\n");

        builder.append("\n\n\n\n#This config can be reloaded in game after a change with F3+T");

        return builder.toString();
    }

    public static void reloadServer() {
        load();
    }

    private static SimpleConfig load() {
        SimpleConfig config = SimpleConfig.of(HangManCommon.MODID).provider(ConfigReader::provider).request();
        canHurtPlayer = config.getOrDefault("canHurtPlayer", false);
        entityDMG = config.getOrDefault("entityDamage", 0);
        playerDMG = config.getOrDefault("playerDamage", 4);
        canHurtEntity = config.getOrDefault("canHurtEntity", true);
        return config;
    }

}
