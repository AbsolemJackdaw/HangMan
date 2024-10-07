package subaraki.hangman.mod;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigData extends CommonConfigData {

    public static final ServerConfig SERVER;
    public static final ModConfigSpec SERVER_SPEC;
    public static final ClientConfig CLIENT;
    public static final ModConfigSpec CLIENT_SPEC;

    static {
        final Pair<ServerConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    static {
        final Pair<ClientConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static void refreshClient() {

    }

    public static void refreshServer() {

        playerDMG = SERVER.playerHurtDamage.get();
        entityDMG = SERVER.entityHurtDamage.get();
        canHurtPlayer = SERVER.canHurtPlayer.get();
        canHurtEntity = SERVER.canHurtEntity.get();
    }

    public static class ServerConfig {

        public final ModConfigSpec.IntValue playerHurtDamage;
        public final ModConfigSpec.IntValue entityHurtDamage;
        public final ModConfigSpec.BooleanValue canHurtEntity;
        public final ModConfigSpec.BooleanValue canHurtPlayer;

        ServerConfig(ModConfigSpec.Builder builder) {

            builder.push("general");
            playerHurtDamage = builder.comment("how much dmg the player receives when on a noose").defineInRange("playerHurtDamage", 0, 0, 20);
            entityHurtDamage = builder.comment("how much dmg entities receive when on a noose").defineInRange("entityHurtDamage", 5, 1, 20);
            canHurtPlayer = builder.comment("Are players damaged ?").define("canHurtPlayer", false);
            canHurtEntity = builder.comment("Are entities damaged ? (omits the data pack rule 'takesDamage'").define("canHurtEntity", false);
            builder.pop();
        }
    }

    public static class ClientConfig {

        ClientConfig(ModConfigSpec.Builder builder) {

        }
    }
}