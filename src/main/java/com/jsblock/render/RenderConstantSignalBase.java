package com.jsblock.render;

import minecraftmappings.BlockEntityRendererMapper;
import mtr.block.BlockSignalLightBase;
import mtr.block.BlockSignalSemaphoreBase;
import mtr.block.IBlock;
import mtr.data.IGui;
import mtr.render.MoreRenderLayers;
import mtr.render.RenderTrains;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.WorldAccess;

public abstract class RenderConstantSignalBase<T extends BlockEntity> extends BlockEntityRendererMapper<T> implements IBlock, IGui {

    protected final boolean isSingleSided;

    public RenderConstantSignalBase(BlockEntityRenderDispatcher dispatcher, boolean isSingleSided) {
        super(dispatcher);
        this.isSingleSided = isSingleSided;
    }

    @Override
    public final void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        final WorldAccess world = entity.getWorld();
        if (world == null) {
            return;
        }

        final BlockPos pos = entity.getPos();
        final BlockState state = world.getBlockState(pos);
        if (!(state.getBlock() instanceof BlockSignalLightBase || state.getBlock() instanceof BlockSignalSemaphoreBase)) {
            return;
        }
        final Direction facing = IBlock.getStatePropertySafe(state, HorizontalFacingBlock.FACING);
        if (RenderTrains.shouldNotRender(pos, RenderTrains.maxTrainRenderDistance, null)) {
            return;
        }
        matrices.push();
        matrices.translate(0.5, 0, 0.5);

        for (int i = 0; i < 2; i++) {
            final Direction newFacing = (i == 1 ? facing.getOpposite() : facing);
            matrices.push();
            matrices.multiply(Vec3f.NEGATIVE_Y.getDegreesQuaternion(newFacing.asRotation()));
            final VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new Identifier("mtr:textures/block/white.png"), false));
            render(matrices, vertexConsumers, vertexConsumer, entity, tickDelta, newFacing, false, i == 1);
            matrices.pop();

            if (isSingleSided) {
                break;
            }
        }
        matrices.pop();
    }

    protected abstract void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, VertexConsumer vertexConsumer, T entity, float tickDelta, Direction facing, boolean isOccupied, boolean isBackSide);
}
