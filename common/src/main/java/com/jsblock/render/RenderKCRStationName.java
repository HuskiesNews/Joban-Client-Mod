package com.jsblock.render;

import com.jsblock.blocks.KCRNameSign;
import com.jsblock.gui.IDrawing;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Vector3f;
import mtr.block.IBlock;
import mtr.data.IGui;
import mtr.data.RailwayData;
import mtr.data.Station;
import mtr.gui.ClientData;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.BlockEntityRendererMapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

public class RenderKCRStationName<T extends BlockEntityMapper> extends BlockEntityRendererMapper<T> implements IGui {

	public RenderKCRStationName(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
		final Level world = entity.getLevel();
		final BlockPos pos = entity.getBlockPos();
		if (world == null) {
			return;
		}

		Station station = RailwayData.getStation(ClientData.STATIONS, pos);
		final Direction facing = IBlock.getStatePropertySafe(world, pos, HorizontalDirectionalBlock.FACING);
		final double offset = IBlock.getStatePropertySafe(world, pos, KCRNameSign.EXIT_ON_LEFT) ? 0.5 : 0;

		matrices.pushPose();
		if (facing == Direction.SOUTH) {
			matrices.translate(0.69 - offset, 0.43, 0.43);
		}

		if (facing == Direction.NORTH) {
			matrices.translate(0.31 + offset, 0.43, 0.57);
		}

		if (facing == Direction.EAST) {
			matrices.translate(0.43, 0.43, 0.31 + offset);
		}

		if (facing == Direction.WEST) {
			matrices.translate(0.57, 0.43, 0.69 - offset);
		}

		matrices.mulPose(Vector3f.ZP.rotationDegrees(180));
		matrices.mulPose(Vector3f.YP.rotationDegrees(facing.toYRot()));
		matrices.scale(0.021F, 0.021F, 0.021F);

		final Font textRenderer = Minecraft.getInstance().font;
		final String stationName = station == null ? new TranslatableComponent("gui.mtr.untitled").getString() : station.name;
		final MultiBufferSource.BufferSource immediate = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());
		IDrawing.drawStringWithFont(matrices, textRenderer, immediate, stationName, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 0, 0, 60, 32, 1, 0xEEEEEE, false, MAX_LIGHT_GLOWING, "kcr_chin", "kcr_eng");
		immediate.endBatch();
		matrices.popPose();
	}
}
