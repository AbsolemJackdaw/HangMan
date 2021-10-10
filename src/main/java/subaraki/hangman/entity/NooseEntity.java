package subaraki.hangman.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fmllegacy.network.NetworkHooks;
import subaraki.hangman.blocks.NooseBlock;
import subaraki.hangman.mod.ConfigData;
import subaraki.hangman.mod.HangMan;
import subaraki.hangman.registry.HangManEntity;
import subaraki.hangman.util.EntityHangableListReader;

public class NooseEntity extends Entity {

    public NooseEntity(EntityType type, Level level) {
        super(type, level);
    }

    public NooseEntity(Level level, BlockPos pos) {
        super(HangManEntity.HANG_DUMMY.get(), level);
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
        if (!level.isClientSide()) {
            if (this.getPassengers().isEmpty()) {
                //set log block to unoccupied so we can spawn a new entity and sit back down
                BlockPos pos = new BlockPos(this.getX(), this.getY(), this.getZ());
                if (this.level.getBlockState(pos).getBlock() instanceof NooseBlock) {
                    level.setBlock(pos, level.getBlockState(pos).setValue(NooseBlock.OCCUPIED, false), 3);
                }
                this.kill(); //remove this entity

            }
            if (!(this.level.getBlockState(getOnPos()).getBlock() instanceof NooseBlock))
                kill();
        }

    }

    @Override
    public void remove(RemovalReason p_146834_) {
        super.remove(p_146834_);
    }

    @Override
    public void kill() {
        super.kill();
    }

    @Override
    public void onRemovedFromWorld() {
        BlockPos pos = new BlockPos(this.getX(), this.getY(), this.getZ());
        if (this.level.getBlockState(pos).getBlock() instanceof NooseBlock) {
            level.setBlock(pos, level.getBlockState(pos).setValue(NooseBlock.OCCUPIED, false), 3);
        }
        super.onRemovedFromWorld();
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public double getPassengersRidingOffset() {
        if (!this.getPassengers().isEmpty()) {
            Entity e = this.getPassengers().get(0);
            if (!(e instanceof Player) && EntityHangableListReader.has(e.getType()))
                return -e.getEyeHeight() + EntityHangableListReader.get(e.getType()).offset();
        }

        return -1.42D;
    }

    @Override
    protected void removePassenger(Entity entity) {
        super.removePassenger(entity);
        if (entity instanceof ServerPlayer player)
            player.connection.send(new ClientboundSetCameraPacket(player));
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        return true;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!getPassengers().isEmpty()) {
            boolean isUndead = false;
            for (Entity e : getPassengers()) {
                if (e instanceof LivingEntity living) {
                    if (this.level.getBlockState(getOnPos()).getBlock() instanceof NooseBlock) {
                        BlockState state = level.getBlockState(getOnPos());
                        Direction dir = state.getValue(NooseBlock.FACING);
                        living.setYBodyRot(dir.toYRot());
                        living.setYHeadRot(dir.toYRot());
                        living.setYRot(dir.toYRot());
                    }
                    living.setXRot(45);
                    isUndead = living.getMobType() == MobType.UNDEAD;

                }
                if (random.nextInt(10) == 0 && (ConfigData.canHurtPlayer && e instanceof Player || !isUndead && ConfigData.canHurtEntity && !(e instanceof Player))) {
                    e.hurt(HangMan.HANGING, e instanceof Player ? ConfigData.playerDMG : ConfigData.entityDMG);
                }
            }
        }
    }

    //this method is needed or you get spasm galore for players
    @Override
    public void onPassengerTurned(Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (this.level.getBlockState(getOnPos()).getBlock() instanceof NooseBlock) {
                BlockState state = level.getBlockState(getOnPos());
                living.setYBodyRot(state.getValue(NooseBlock.FACING).toYRot());
                living.setYHeadRot(living.yBodyRot);
                living.setXRot(45);
            }
        }
    }
}
