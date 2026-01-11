package subaraki.hangman.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.entity.NooseEntity;

@Mixin(HumanoidMobRenderer.class)
public class EntitySitOrHangOnNoose {

    @Inject(method = "extractHumanoidRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FLnet/minecraft/client/renderer/item/ItemModelResolver;)V",
            at = @At(value = "TAIL"))
    private static void insert(LivingEntity entity, HumanoidRenderState reusedState, float partialTick, ItemModelResolver itemModelResolver, CallbackInfo ci) {
        if (entity.isPassenger() && entity.getVehicle() instanceof NooseEntity noose) {
            reusedState.isPassenger = noose.shouldHangedEntitySit();
        }
    }

//    legacy 2.0
//    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isPassenger()Z", ordinal = 1, shift = At.Shift.AFTER))
//    private void insert(LivingEntity livingEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
//        if (livingEntity.getVehicle() instanceof NooseEntity noose)
//            ((LivingEntityRenderer) (Object) this).getModel().riding = noose.shouldHangedEntitySit();
//    }

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
