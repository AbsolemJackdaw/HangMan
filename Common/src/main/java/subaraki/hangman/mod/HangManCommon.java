package subaraki.hangman.mod;

import net.minecraft.resources.ResourceLocation;
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

    public static final String noose = "hang_dummy";
    public static final String camera = "camera";

    public static final ResourceLocation NOOSE = new ResourceLocation(MODID, noose);
    public static final ResourceLocation CAMERA = new ResourceLocation(MODID, camera);


}