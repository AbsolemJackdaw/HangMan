package subaraki.hangman.mod;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class HangManCommon {
    public static final String MODID = "hangman";
    public static final Logger LOG = LogManager.getLogger();
//    public static final DamageType DMG = new DamageType("hanging", DamageScaling.NEVER, 100f);
//    public static final DamageSource HANGING = new DamageSourceHang();

    public static final String NOOSE_ENTITY_STR = "hang_dummy";
    public static final String CAMERA_ENTITY_STR = "camera";
    public static final String LISTENER_STR = "reload_listener";
    private static final String NOOSE_STR = "noose";

    public static final ResourceKey<Block> BLOCK_KEY = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(HangManCommon.MODID, NOOSE_STR));
    public static final ResourceKey<Item> ITEM_KEY = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HangManCommon.MODID, NOOSE_STR));
    public static final ResourceKey<EntityType<?>> ENTITY_NOOSE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HangManCommon.MODID, NOOSE_ENTITY_STR));
    public static final ResourceKey<EntityType<?>> ENTITY_CAMERA_KEY = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(HangManCommon.MODID, CAMERA_ENTITY_STR));
    public static final ResourceKey<DamageType> HANG_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(MODID, NOOSE_STR));

    public static final Identifier NOOSE = Identifier.fromNamespaceAndPath(MODID, NOOSE_ENTITY_STR);
    public static final Identifier CAMERA = Identifier.fromNamespaceAndPath(MODID, CAMERA_ENTITY_STR);

    public static DamageSource hangDmg(Level level) {
        var dmg = level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(HangManCommon.HANG_DAMAGE);
        return new DamageSource(dmg);
    }

}