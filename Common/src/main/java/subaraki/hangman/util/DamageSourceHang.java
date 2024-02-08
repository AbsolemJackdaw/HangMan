package subaraki.hangman.util;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

public class DamageSourceHang extends DamageSource {

    public DamageSourceHang(String name) {
        super(Holder.direct(new DamageType(name, DamageScaling.NEVER,100f)));
    }
}
