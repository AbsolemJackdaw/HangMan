package subaraki.hangman.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.NooseEntity;

@Mixin(LivingEntityRenderer.class)
public class EntitySitOrHangOnNoose {

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isPassenger()Z", ordinal = 1, shift = At.Shift.AFTER))
    private void insert(LivingEntity livingEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if (livingEntity.getVehicle() instanceof NooseEntity noose)
            ((LivingEntityRenderer) (Object) this).getModel().riding = noose.shouldHangedEntitySit();
    }

    //Legacy code : this code does the same, but does so without using redirect or modify constant which both do not allow stacking.
    //more info at : https://github.com/LlamaLad7/MixinExtras/wiki/ModifyExpressionValue
//    @ModifyExpressionValue(
//            method = "render",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isPassenger()Z")
//    )
//    private boolean editSitModel(boolean original, LivingEntity livingEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
//        return livingEntity.getVehicle() instanceof NooseEntity noose ? noose.shouldHangedEntitySit() : original;
//    }

}
