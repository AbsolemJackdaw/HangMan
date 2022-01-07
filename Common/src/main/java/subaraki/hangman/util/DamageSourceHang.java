package subaraki.hangman.util;

import net.minecraft.world.damagesource.DamageSource;

public class DamageSourceHang extends DamageSource {

    public DamageSourceHang(String name) {
        super(name);
        bypassArmor();
    }
}
