package subaraki.hangman.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import subaraki.hangman.blocks.NooseBlock;
import subaraki.hangman.mod.CommonConfigData;
import subaraki.hangman.mod.HangManCommon;
import subaraki.hangman.util.EntityHangableListReader;

public class NooseEntity extends Entity {

    public NooseEntity(EntityType type, Level level) {
        super(type, level);
    }

    public NooseEntity(Level level,
                       BlockPos pos) {
        super(BuiltInRegistries.ENTITY_TYPE.get(HangManCommon.NOOSE), level);
        this.setPos(pos.getX() + 0.5, pos.getY() + 0.35, pos.getZ() + 0.5);
        this.noPhysics = true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity pEntity) {
        return new ClientboundAddEntityPacket(this, pEntity); //super.getAddEntityPacket(pEntity);
    }

    /*ridetick is only called when the entity is a rider, and not a vehicle. we use the general tick update method instead.*/
    @Override
    public void tick() {
        super.tick();
        //passenger (player) gets set immediatly on spawn.
        //so when this is empty, the player has unmounted.
        if (!level().isClientSide()) {
            if (this.getPassengers().isEmpty()) {
                //set log block to unoccupied so we can spawn a new entity and sit back down
                BlockPos pos = BlockPos.containing(this.position());
                if (this.level().getBlockState(pos).getBlock() instanceof NooseBlock) {
                    level().setBlock(pos, level().getBlockState(pos).setValue(NooseBlock.OCCUPIED, false), 3);
                }
                this.kill(); //remove this entity

            }
            if (!(this.level().getBlockState(getOnPos()).getBlock() instanceof NooseBlock))
                kill();
        }

    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        whenRemoved();
    }

    @Override
    public void kill() {
        super.kill();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    /**
     * Called in {@link Entity#remove(RemovalReason)} to reset block's vacancy.
     * Do not call in Forge's onEntityRemovedFromWorld because it will cause a Concurrent Modification exception
     * when saving chunks. (on dimension leave or world exit)
     */
    public void whenRemoved() {
        var posZ = this.getZ();
        var pos = BlockPos.containing(this.position());
        if (this.level().getBlockState(pos).getBlock() instanceof NooseBlock) {
            level().setBlock(pos, level().getBlockState(pos).setValue(NooseBlock.OCCUPIED, false), 3);
        }
    }

    //used in Forge's shouldRiderSit method
    //used in Fabric to be used in mixin ref
    public boolean shouldHangedEntitySit() {
        return !this.level().getBlockState(getOnPos().below(2)).isAir();
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
            for (Entity e : getPassengers()) {
                if (e instanceof LivingEntity living) {
                    if (this.level().getBlockState(getOnPos()).getBlock() instanceof NooseBlock) {
                        BlockState state = level().getBlockState(getOnPos());
                        Direction dir = state.getValue(NooseBlock.FACING);
                        living.setYBodyRot(dir.toYRot());
                        living.setYHeadRot(dir.toYRot());
                        living.setYRot(dir.toYRot());
                    }
                    living.setXRot(45);
                }

                if (!e.hurtMarked &&
                        ((CommonConfigData.canHurtPlayer && e instanceof Player ||
                                CommonConfigData.canHurtEntity && EntityHangableListReader.has(e.getType()) && EntityHangableListReader.get(e.getType()).takesDamage()))) {
                    e.hurt(HangManCommon.HANGING, e instanceof Player ? CommonConfigData.playerDMG : CommonConfigData.entityDMG);
                }
            }
        }
    }

    //this method is needed or you get spasm galore for players
    @Override
    public void onPassengerTurned(Entity entity) {
        if (entity instanceof LivingEntity living) {
            if (this.level().getBlockState(getOnPos()).getBlock() instanceof NooseBlock) {
                BlockState state = level().getBlockState(getOnPos());
                living.setYBodyRot(state.getValue(NooseBlock.FACING).toYRot());
                living.setYHeadRot(living.yBodyRot);
                living.setXRot(45);
            }
        }
    }

    @Override
    public boolean dismountsUnderwater() {
        return false;
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pPassenger) {
        var pos = BlockPos.containing(this.position());
        var isAirUnder = this.level().getBlockState(pos.below()).isAir();
        return isAirUnder ? position().add(0, -1, 0) : position();
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity pEntity, EntityDimensions pDimensions, float pPartialTick) {
        var y = -1.42;
        if (!this.getPassengers().isEmpty()) {
            Entity e = this.getPassengers().getFirst();
            if (!(e instanceof Player) && EntityHangableListReader.has(e.getType()))
                y = -e.getEyeHeight() + EntityHangableListReader.get(e.getType()).offset();
        }
        return new Vec3(0, y, 0);
    }
}
