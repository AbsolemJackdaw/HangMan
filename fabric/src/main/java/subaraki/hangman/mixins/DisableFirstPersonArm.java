package subaraki.hangman.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.CameraPlayerOnNoose;

@Mixin(ItemInHandRenderer.class)
public class DisableFirstPersonArm {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "renderHandsWithItems", at = @At("HEAD"), cancellable = true)
    public void cancelRender(float partialTick, PoseStack poseStack, SubmitNodeCollector nodeCollector, LocalPlayer player, int packedLight, CallbackInfo ci) {
        if (Minecraft.getInstance().getCameraEntity() instanceof CameraPlayerOnNoose noose) {
            ci.cancel();
        }
    }
}
