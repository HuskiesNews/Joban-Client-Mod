package com.jsblock.render;

import com.jsblock.blocks.KCRNameSign;
import com.jsblock.gui.IDrawing;
import minecraftmappings.BlockEntityRendererMapper;
import mtr.block.IBlock;
import mtr.config.Config;
import mtr.data.*;
import mtr.gui.ClientData;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RenderKCRStationName<T extends BlockEntity> extends BlockEntityRendererMapper<T> implements IGui {

    public RenderKCRStationName(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        final WorldAccess world = entity.getWorld();
        final BlockPos pos = entity.getPos();
        if (world == null) {
            return;
        }

        Station station = RailwayData.getStation(ClientData.STATIONS, pos);
        final Direction facing = IBlock.getStatePropertySafe(world, pos, HorizontalFacingBlock.FACING);
        final double offset = IBlock.getStatePropertySafe(world, pos, KCRNameSign.EXIT_ON_LEFT) ? 0.5 : 0;

        matrices.push();
        if(facing == Direction.SOUTH) {
            matrices.translate(0.69 - offset, 0.43, 0.43);
        }

        if(facing == Direction.NORTH) {
            matrices.translate(0.31 + offset, 0.43, 0.57);
        }

        if(facing == Direction.EAST) {
            matrices.translate(0.43, 0.43, 0.31 + offset);
        }

        if(facing == Direction.WEST) {
            matrices.translate(0.57, 0.43, 0.69 - offset);
        }

        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180));
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(facing.asRotation()));
        matrices.scale(0.021F, 0.021F, 0.021F);

        final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        final String stationName = station == null ? new TranslatableText("gui.mtr.untitled").getString() : station.name;
        final VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
        IDrawing.drawStringWithFont(matrices, textRenderer, immediate, stationName, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 0, 0, 60, 32, 1, 0xEEEEEE, false, MAX_LIGHT_GLOWING, "kcr_chin", "kcr_eng");
        immediate.draw();
        matrices.pop();
    }
}
