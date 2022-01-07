package com.jsblock.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mtr.client.IDrawing;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.Direction;

public class RenderConstantSignalLight<T extends BlockEntityMapper> extends RenderConstantSignalBase<T> {

	final int constantColor;
	final boolean redOnTop;

	public RenderConstantSignalLight(BlockEntityRenderDispatcher dispatcher, boolean isSingleSided, int constantColor, boolean redOnTop) {
		super(dispatcher, isSingleSided);
		this.constantColor = constantColor;
		this.redOnTop = redOnTop;
	}

	@Override
	protected void render(PoseStack matrices, MultiBufferSource vertexConsumers, VertexConsumer vertexConsumer, T entity, float tickDelta, Direction facing, boolean isOccupied, boolean isBackSide) {
		float y = redOnTop ? 0.4375F : 0.0625F;
		IDrawing.drawTexture(matrices, vertexConsumer, -0.125F, y, -0.19375F, 0.125F, y + 0.25F, -0.19375F, facing.getOpposite(), constantColor, MAX_LIGHT_GLOWING);
	}
}
