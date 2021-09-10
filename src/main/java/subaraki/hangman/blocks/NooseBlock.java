package subaraki.hangman.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import subaraki.hangman.entity.CameraDummy;
import subaraki.hangman.entity.HangEntityDummy;

public class NooseBlock extends Block {
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 6.0D, 12.0D, 16.0D, 10.0D);
    protected static final VoxelShape SHAPE_SIDE = Block.box(6.0D, 0.0D, 4.0D, 10.0D, 16.0D, 12.0D);
    protected static final VoxelShape COLLISION = Block.box(5.0D, -1.0D, 7.0D, 11.0D, 6.0D, 9.0D);
    protected static final VoxelShape COLLISION_SIDE = Block.box(7.0D, -1.0D, 5.0D, 9.0D, 6.0D, 11.0D);

    public NooseBlock() {
        super(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION).noOcclusion().strength(1.0f).sound(SoundType.WOOL).isValidSpawn(NooseBlock::never).isRedstoneConductor(NooseBlock::never).isSuffocating(NooseBlock::never).isViewBlocking(NooseBlock::never));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OCCUPIED, false));
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    public static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH ? COLLISION : COLLISION_SIDE;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH ? SHAPE : SHAPE_SIDE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH ? SHAPE : SHAPE_SIDE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateDef) {
        stateDef.add(FACING, OCCUPIED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        return this.defaultBlockState().setValue(FACING, placeContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !level.isEmptyBlock(pos.above());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (player instanceof ServerPlayer serverPlayer && !state.getValue(OCCUPIED) && hand == InteractionHand.MAIN_HAND) {

            HangEntityDummy nooseEntity = new HangEntityDummy(level, pos);
            player.startRiding(nooseEntity);
            level.setBlock(pos, state.setValue(OCCUPIED, true), 3);
            level.addFreshEntity(nooseEntity);

            BlockPos cameraPos =
                    switch (state.getValue(FACING)) {
                        case EAST -> pos.east(3).below(1);
                        case WEST -> pos.west(3).below(1);
                        case NORTH -> pos.north(3).below(1);
                        case SOUTH -> pos.south(3).below(1);
                        default -> pos.below(1).north(3);
                    };
            CameraDummy camera = new CameraDummy(level, pos);
            camera.setPos(cameraPos.getX() + 0.5, cameraPos.getY(), cameraPos.getZ() + 0.5);
            camera.setXRot(-20f);
            float yRotation = switch (state.getValue(FACING)) {
                case EAST -> 90;
                case WEST -> -90;
                case SOUTH -> 180;
                default -> 0;
            };
            camera.setYRot(yRotation);
            level.addFreshEntity(camera);
            serverPlayer.connection.send(new ClientboundSetCameraPacket(camera));
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide()) {
            if (entity instanceof LivingEntity living && !(entity instanceof Player)) {
                if (state.getBlock() instanceof NooseBlock && !state.getValue(OCCUPIED)) {
                    HangEntityDummy noose = new HangEntityDummy(level, pos);
                    if (living instanceof Mob mob)
                        mob.setPersistenceRequired();
                    living.startRiding(noose, false);
                    noose.positionRider(living);
                    level.addFreshEntity(noose);
                    level.setBlock(pos, state.setValue(OCCUPIED, true), 3);
                }
            }
        }
    }
}
