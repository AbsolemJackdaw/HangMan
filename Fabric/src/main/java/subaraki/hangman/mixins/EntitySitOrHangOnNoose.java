package subaraki.hangman.mixins;

import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import subaraki.hangman.entity.NooseEntity;

@Mixin(LivingEntityRenderer.class)
public abstract class EntitySitOrHangOnNoose {

    @Redirect(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isPassenger()Z"))
    public boolean renderEdit(LivingEntity instance) {
        boolean flag = true;
        if (instance.getVehicle() != null && instance.getVehicle() instanceof NooseEntity noose)
            flag = noose.shouldHangedEntitySit();
        return instance.isPassenger() && (instance.getVehicle() != null && flag);
    }
}
