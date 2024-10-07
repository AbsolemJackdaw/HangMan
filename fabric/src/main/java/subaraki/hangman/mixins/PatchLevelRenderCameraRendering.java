package subaraki.hangman.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LevelRenderer.class)
public class PatchLevelRenderCameraRendering {

    @Dynamic //have mcdev stop complaining about modifyconstant that it doesn't know about
    @ModifyConstant(method = "renderLevel", constant = @Constant(classValue = LocalPlayer.class))
    private boolean applyPatch(Object entity, Class<LocalPlayer> constant) {
        return (entity instanceof LocalPlayer) && !(entity == Minecraft.getInstance().player && !Minecraft.getInstance().player.isSpectator());
    }
}
