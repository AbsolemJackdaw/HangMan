package subaraki.hangman.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.NooseEntity;

@Environment(EnvType.CLIENT)
@Mixin(targets = "net.minecraft.client.multiplayer.ClientLevel$EntityCallbacks")
public class OnClientEntityRemoved {

    @Inject(method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V", at = @At("TAIL"))
    public void removestuff(Entity entity, CallbackInfo ci) {
        if (entity instanceof NooseEntity noose) {
            noose.whenRemovedFromWorld();
        }
    }

}
