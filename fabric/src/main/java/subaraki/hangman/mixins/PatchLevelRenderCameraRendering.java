package subaraki.hangman.mixins;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public abstract class PatchLevelRenderCameraRendering {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Definition(id = "LocalPlayer", type = LocalPlayer.class)
    @Expression("? instanceof LocalPlayer")
    @WrapOperation(method = "extractVisibleEntities", at = @At("MIXINEXTRAS:EXPRESSION"))
    public boolean help(Object object, Operation<Boolean> original, @Local Camera camera, @Local Entity entity) {
        var flag = original.call(object) && (object != minecraft.player || minecraft.player.isSpectator());
        return flag;
    }
}
