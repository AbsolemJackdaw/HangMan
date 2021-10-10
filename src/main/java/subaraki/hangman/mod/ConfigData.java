package subaraki.hangman.mod;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigData {

    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static boolean canHurtPlayer = false;
    public static boolean canHurtEntity = true;
    public static int playerDMG = 0;
    public static int entityDMG = 5;

    static {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static void refreshClient() {

    }

    public static void refreshServer() {

        playerDMG = SERVER.playerHurtDamage.get();
        entityDMG = SERVER.entityHurtDamage.get();
        canHurtPlayer = SERVER.canHurtPlayer.get();
        canHurtEntity = SERVER.canHurtEntities.get();

    }

    public static class ServerConfig {

        public final ForgeConfigSpec.IntValue playerHurtDamage;
        public final ForgeConfigSpec.IntValue entityHurtDamage;

        public final ForgeConfigSpec.BooleanValue canHurtPlayer;
        public final ForgeConfigSpec.BooleanValue canHurtEntities;

        ServerConfig(ForgeConfigSpec.Builder builder) {

            builder.push("general");
            playerHurtDamage = builder.comment("how much dmg the player receives when on a noose").defineInRange("playerHurtDamage", 0, 0, 20);
            entityHurtDamage = builder.comment("how much dmg the player receives when on a noose").defineInRange("entityHurtDamage", 5, 1, 20);
            canHurtPlayer = builder.comment("Are players damaged ?").define("canHurtPlayer", false);
            canHurtEntities = builder.comment("Are entities damaged ?").define("canHurtPlayer", true);

            builder.pop();
        }
    }

    public static class ClientConfig {

        ClientConfig(ForgeConfigSpec.Builder builder) {

        }
    }
}