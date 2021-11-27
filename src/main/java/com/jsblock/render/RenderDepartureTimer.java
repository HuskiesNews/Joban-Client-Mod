package com.jsblock.render;

import mtr.block.IBlock;
import mtr.data.IGui;
import mtr.data.Platform;
import mtr.data.RailwayData;
import mtr.data.Route;
import mtr.gui.ClientData;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RenderDepartureTimer<T extends BlockEntity> extends BlockEntityRenderer<T> implements IGui {

    public RenderDepartureTimer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        final WorldAccess world = entity.getWorld();
        final BlockPos pos = entity.getPos();
        if (world == null) {
            return;
        }

        final Platform platform = RailwayData.getClosePlatform(ClientData.PLATFORMS, pos, 5, 3, 3);
        if (platform == null) {
            return;
        }

        final Set<Route.ScheduleEntry> schedules = ClientData.SCHEDULES_FOR_PLATFORM.get(platform.id);
        if (schedules == null) {
            return;
        }

        String timeRemaining = "";

        final List<Route.ScheduleEntry> scheduleList = new ArrayList<>(schedules);
        if (!scheduleList.isEmpty()) {
            Collections.sort(scheduleList);
            if(scheduleList.get(0).arrivalMillis - System.currentTimeMillis() > 0) return;

            int remainingSecond = (int) (scheduleList.get(0).arrivalMillis - System.currentTimeMillis()) / 1000;
            int seconds = Math.abs((platform.getDwellTime() / 2) - Math.abs(remainingSecond));
            int minutes = seconds / 60;

            timeRemaining = String.format("%d:%02d", minutes, seconds);
        }

        final Direction facing = IBlock.getStatePropertySafe(world, pos, HorizontalFacingBlock.FACING);
        matrices.push();
        if(facing == Direction.SOUTH) {
            matrices.translate(0.73, 0.52, 0.24);
        }

        if(facing == Direction.NORTH) {
            matrices.translate(0.28, 0.52, 0.76);
        }

        if(facing == Direction.EAST) {
            matrices.translate(0.24, 0.52, 0.28);
        }

        if(facing == Direction.WEST) {
            matrices.translate(0.76, 0.52, 0.73);
        }

        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(facing.asRotation()));
        matrices.scale(0.021F, 0.021F, 0.021F);
        final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        textRenderer.draw(matrices, timeRemaining, 0, 0, 0xFF4444);
        matrices.pop();
    }
}
