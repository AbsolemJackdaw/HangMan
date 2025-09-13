package subaraki.hangman.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CameraPlayerOnNoose extends Entity {

    public CameraPlayerOnNoose(EntityType type, Level level) {
        super(type, level);
    }

    public CameraPlayerOnNoose(EntityType type, Level level, BlockPos pos) {
        super(type, level);
        this.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    public boolean isAttackable() {
        return false;
    }


    @Override
    public <T> T getOrDefault(DataComponentType<? extends T> component, T defaultValue) {
        return super.getOrDefault(component, defaultValue);
    }

    @Override
    public @Nullable <T> TypedDataComponent<T> getTyped(DataComponentType<T> component) {
        return super.getTyped(component);
    }

    @Override
    public Component getFeedbackDisplayName() {
        return super.getFeedbackDisplayName();
    }
}
