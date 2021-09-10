package subaraki.hangman.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import subaraki.hangman.blocks.NooseBlock;
import subaraki.hangman.mod.HangMan;
import subaraki.hangman.registry.HangManEntity;

public class HangEntityDummy extends Entity {

    private final BlockPos logPos;

    public HangEntityDummy(EntityType type, Level level) {
        super(type, level);
        logPos = BlockPos.ZERO;
    }

    public HangEntityDummy(Level level, BlockPos pos) {
        super(HangManEntity.HANG_DUMMY.get(), level);
        this.logPos = pos;
        this.setPos(pos.getX() + 0.5, pos.getY() + 0.35, pos.getZ() + 0.5);
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

    /*ridetick is only called when the entity is a rider, and not a vehicle. we use the general tick update method instead.*/
    @Override
    public void tick() {
        super.tick();
        //passenger (player) gets set immediatly on spawn.
        //so when this is empty, the player has unmounted.
        if (this.getPassengers().isEmpty()) {
            //set log block to unoccupied so we can spawn a new entity and sit back down
            if (this.level.getBlockState(logPos).getBlock() instanceof NooseBlock) {
                level.setBlock(logPos, level.getBlockState(logPos).setValue(NooseBlock.OCCUPIED, false), 3);
            }
            this.kill(); //remove this entity

        }
    }

    @Override
    public void onRemovedFromWorld() {
        if (this.level.getBlockState(logPos).getBlock() instanceof NooseBlock) {
            level.setBlock(logPos, level.getBlockState(logPos).setValue(NooseBlock.OCCUPIED, false), 3);
        }
        super.onRemovedFromWorld();
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public double getPassengersRidingOffset() {
        return -1.37D;
    }

    @Override
    protected void removePassenger(Entity entity) {
        super.removePassenger(entity);
        if (entity instanceof ServerPlayer player)
            player.connection.send(new ClientboundSetCameraPacket(player));
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return getPassengers().size() == 0;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!getPassengers().isEmpty()) {
            for (Entity e : getPassengers()) {
                if (e instanceof Player player) {
                    player.setYRot(player.yBodyRot);
                    player.setXRot(50);
                }

                if (random.nextInt(10) == 0)
                    e.hurt(HangMan.HANGING, 5);
                if (random.nextInt(20) == 0)
                    level.playLocalSound(getX(), getY(), getZ(), SoundEvents.DROWNED_AMBIENT_WATER, SoundSource.PLAYERS, 1.0f, 1.0f, false);
            }
        }
    }


    @Override
    public void onPassengerTurned(Entity entity) {
        if (entity instanceof Player player) {
            player.hurt(DamageSource.CRAMMING, 2);
            if (this.level.getBlockState(getOnPos()).getBlock() instanceof NooseBlock) {
                BlockState state = level.getBlockState(getOnPos());
                player.setYBodyRot(state.getValue(NooseBlock.FACING).toYRot());
            }
        }
    }
}
