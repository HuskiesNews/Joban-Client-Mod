package com.jsblock.render;

import com.jsblock.blocks.LightBlock;
import com.jsblock.config.ClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import mtr.block.IBlock;
import mtr.client.IDrawing;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.BlockEntityRendererMapper;
import mtr.mappings.Utilities;
import mtr.render.MoreRenderLayers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

import static mtr.data.IGui.MAX_LIGHT_GLOWING;

public class RenderLightBlockParticle<T extends BlockEntityMapper> extends BlockEntityRendererMapper<T> {

	public RenderLightBlockParticle(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public final void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
		final Level world = entity.getLevel();
		if (world == null || ClientConfig.getRenderDisabled()) {
			return;
		}

		final Player player = Minecraft.getInstance().player;
		if(player == null || !Utilities.isHolding(player, item -> Block.byItem(item) instanceof LightBlock)) {
			return;
		}

		final BlockState state = world.getBlockState(entity.getBlockPos());
		if (!(state.getBlock() instanceof LightBlock)) {
			return;
		}

		final Direction facing = IBlock.getStatePropertySafe(state, HorizontalDirectionalBlock.FACING);
		matrices.pushPose();
		matrices.translate(0F, 0.5F, 0.25F);
		matrices.mulPose(Vector3f.YP.rotationDegrees(-player.yHeadRot));
		matrices.translate(0F, -0.5F, -0.25F);

		matrices.translate(0.25F, 0F, 0.25F);
		matrices.mulPose(Vector3f.XP.rotationDegrees(player.xRotO));
		matrices.translate(-0.25F, 0F, -0.25F);

		final VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new ResourceLocation("jsblock:textures/item/light_block.png"), true));
		IDrawing.drawTexture(matrices, vertexConsumer, 0, 0, 1.0F, 1.0F, facing, MAX_LIGHT_GLOWING);
		matrices.popPose();
	}
}
