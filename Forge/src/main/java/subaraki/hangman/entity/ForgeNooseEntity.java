package subaraki.hangman.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class ForgeNooseEntity extends NooseEntity {

    public ForgeNooseEntity(EntityType<?> type, Level level) {
        super(type, level);
    }
    
    @Override
    public void remove(RemovalReason p_146834_) {
    	super.remove(p_146834_);
    	super.whenRemovedFromWorld();
    }

    @Override
    public boolean shouldRiderSit() {
        return shouldHangedEntitySit();
    }
}
