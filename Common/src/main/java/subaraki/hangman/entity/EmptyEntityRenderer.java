package subaraki.hangman.entity;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;


public class EmptyEntityRenderer extends EntityRenderer<Entity> {

    private static final ResourceLocation empty = new ResourceLocation("");

    public EmptyEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(Entity entity, Frustum frustum, double posX, double posY, double posZ) {
        return true;
    }

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return empty;
    }
}
