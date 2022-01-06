package subaraki.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.NooseEntity;

@Mixin(Gui.class)
public class DisabelOverlayOnHanging {

    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    public void haltRender(PoseStack poseStack, float f, CallbackInfo ci) {
        if (Minecraft.getInstance().player.getVehicle() instanceof NooseEntity noose) {
            ci.cancel();
        }
    }
}
