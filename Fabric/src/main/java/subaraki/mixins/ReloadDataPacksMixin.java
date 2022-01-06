package subaraki.mixins;

import net.minecraft.commands.Commands;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ServerResources;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.hangman.util.EntityHangableListReader;

@Mixin(ServerResources.class)
public class ReloadDataPacksMixin {

    @Shadow
    @Final
    private ReloadableResourceManager resources;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void registerReloadListener(RegistryAccess registryAccess, Commands.CommandSelection commandSelection, int i, CallbackInfo ci) {
        resources.registerReloadListener(new EntityHangableListReader());
    }

}