package subaraki.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.NooseEntity;

@Mixin(ItemInHandRenderer.class)
public class DisableFirstPersonArm {

    @Inject(method = "renderHandsWithItems", at = @At("HEAD"),cancellable = true)
    public void cancelRender(float f, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, LocalPlayer localPlayer, int i, CallbackInfo ci) {
        if (Minecraft.getInstance().player.getVehicle() instanceof NooseEntity noose) {
            ci.cancel();
        }
    }
}
