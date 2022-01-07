package subaraki.hangman.mod;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.util.DamageSourceHang;

public abstract class HangManCommon {
    public static final String MODID = "hangman";
    public static final Logger LOG = LogManager.getLogger();
    public static final DamageSource HANGING = new DamageSourceHang("hanging");

    public static EntityType<?> NOOSE; //abstracter to allow for Forge to use a modified version
    public static EntityType<CameraPlayerOnNoose> CAMERA;


}