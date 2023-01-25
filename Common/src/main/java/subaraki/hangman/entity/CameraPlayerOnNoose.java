package subaraki.hangman.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import subaraki.hangman.mod.HangManCommon;

public class CameraPlayerOnNoose extends Entity {

    public CameraPlayerOnNoose(EntityType type, Level level) {
        super(type, level);
    }

    public CameraPlayerOnNoose(Level level, BlockPos pos) {
        super(BuiltInRegistries.ENTITY_TYPE.get(HangManCommon.NOOSE), level);
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
    public boolean isInvulnerable() {
        return true;
    }

    public boolean isAttackable() {
        return false;
    }


}
