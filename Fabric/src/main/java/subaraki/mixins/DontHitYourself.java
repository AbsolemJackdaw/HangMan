package subaraki.mixins;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.NooseEntity;

@Mixin(Minecraft.class)
public class DontHitYourself {

    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    public void dont(CallbackInfo ci) {
        if (Minecraft.getInstance().player.getVehicle() instanceof NooseEntity noose) {
            ci.cancel();
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    public void please(CallbackInfo ci) {
        if (Minecraft.getInstance().player.getVehicle() instanceof NooseEntity noose) {
            ci.cancel();
        }
    }
}
