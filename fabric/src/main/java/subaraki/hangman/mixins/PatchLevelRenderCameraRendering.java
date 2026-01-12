package subaraki.hangman.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import subaraki.hangman.entity.CameraPlayerOnNoose;
import subaraki.hangman.entity.NooseEntity;

@Mixin(EntityRenderDispatcher.class)
public class PatchLevelRenderCameraRendering {

    @Inject(method = "shouldRender",
            at = @At("HEAD"))
    private <E extends Entity> void applyPatch(E entity, Frustum frustum, double camX, double camY, double camZ, CallbackInfoReturnable<Boolean> cir) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;

        if (player != null && player.getVehicle() instanceof NooseEntity && minecraft.getCameraEntity() instanceof CameraPlayerOnNoose)
            cir.setReturnValue(true);
    }
}
