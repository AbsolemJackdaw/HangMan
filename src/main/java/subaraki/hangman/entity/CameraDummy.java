package subaraki.hangman.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import subaraki.hangman.registry.HangManEntity;

public class CameraDummy extends Entity {

    private final BlockPos logPos;

    public CameraDummy(EntityType type, Level level) {
        super(type, level);
        logPos = BlockPos.ZERO;
    }

    public CameraDummy(Level level, BlockPos pos) {
        super(HangManEntity.CAMERA.get(), level);
        this.logPos = pos;
        this.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
