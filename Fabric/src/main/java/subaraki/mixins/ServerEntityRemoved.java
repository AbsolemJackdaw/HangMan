package subaraki.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.NooseEntity;

@Mixin(targets = "net.minecraft.server.level.ServerLevel$EntityCallbacks")
public class ServerEntityRemoved {

    @Inject(method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V", at = @At("TAIL"))
    public void removestuff(Entity entity, CallbackInfo ci) {
        if (entity instanceof NooseEntity noose) {
            noose.whenRemovedFromWorld();
        }
    }

}
