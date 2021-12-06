package com.jsblock.blocks;

import com.jsblock.Joestu;
import minecraftmappings.BlockEntityMapper;
import minecraftmappings.BlockEntityProviderMapper;
import minecraftmappings.TickableMapper;
import minecraftmappings.Utilities;
import mtr.block.IBlock;
import mtr.data.Platform;
import mtr.data.RailwayData;
import mtr.data.Route;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.*;


public class ButterflyLight extends HorizontalFacingBlock implements BlockEntityProviderMapper {

    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public ButterflyLight(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing()).with(LIT, false);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.with(LIT, false));
    }

    @Override
    public BlockEntityType<? extends BlockEntity> getType() {
        return Joestu.BUTTERFLY_LIGHT_TILE_ENTITY;
    }

    @Override
    public <T extends BlockEntity> void tick(World world, BlockPos pos, T blockEntity) {
        TileEntityButterFlyLight.tick(world, pos, blockEntity);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(2, 0, 0, 14, 5.8, 10, facing);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntityButterFlyLight(pos, state);
    }

    public static class TileEntityButterFlyLight extends BlockEntityMapper implements TickableMapper {

        public TileEntityButterFlyLight(BlockPos pos, BlockState state) {
            super(Joestu.BUTTERFLY_LIGHT_TILE_ENTITY, pos, state);
        }

        @Override
        public void tick() {
            tick(world, pos, this);
        }

        public static <T extends BlockEntity> void tick(World world, BlockPos pos, T blockEntity) {
            if(world != null && !world.isClient()) {
                final BlockState state = world.getBlockState(pos);
                final RailwayData railwayData = RailwayData.getInstance(world);
                if (railwayData == null) {
                    return;
                }

                final Platform platform = RailwayData.getClosePlatform(railwayData.platforms, pos, 5, 3, 3);
                if (platform == null) {
                    return;
                }

                final Set<Route.ScheduleEntry> schedules = railwayData.getSchedulesAtPlatform(platform.id);
                if (schedules == null) {
                    return;
                }

                final List<Route.ScheduleEntry> scheduleList = new ArrayList<>(schedules);
                if (!scheduleList.isEmpty()) {
                    Collections.sort(scheduleList);

                    /* If the train has not yet arrived (Should be negative when train arrived) */
                    if(scheduleList.get(0).arrivalMillis - System.currentTimeMillis() > 0) {
                        if(!Utilities.isScheduled(world, pos, state.getBlock())) {
                            Utilities.scheduleBlockTick(world, new BlockPos(pos), state.getBlock(), 16);
                        }
                        return;
                    }

                    int remainingSecond = (int) (scheduleList.get(0).arrivalMillis - System.currentTimeMillis()) / 1000;
                    int seconds = (platform.getDwellTime() / 2) - Math.abs(remainingSecond);
                    /* If the departure time is still more than 10 seconds, return and don't blink the light */
                    if(seconds > 10) return;
                    /* This gets the time of the day, expressed in ticks */
                    /* The following setBlockState code will be ran every 16 ticks or 0.8 second */
                    /* Does not work when doDaylightCycle is false */
                    if(world.getTimeOfDay() % 16 == 0) {
                        /* This cycles through the block state of the "lit" property, which has true and false (as it's a boolean), creating a blinking effect */
                        world.setBlockState(pos, state.cycle(LIT));
                    }
                }
            }
        }
    }
}