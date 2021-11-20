package com.jsblock.render;

import mtr.gui.IDrawing;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;

public class RenderConstantSignalLight<T extends BlockEntity> extends RenderConstantSignalBase<T> {

        final int constantColor;
        final boolean redOnTop;

        public RenderConstantSignalLight(BlockEntityRenderDispatcher dispatcher, boolean isSingleSided, int constantColor, boolean redOnTop) {
            super(dispatcher, isSingleSided);
            this.constantColor = constantColor;
            this.redOnTop = redOnTop;
        }

        @Override
        protected void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, VertexConsumer vertexConsumer, T entity, float tickDelta, Direction facing, boolean isOccupied, boolean isBackSide) {
                float y = redOnTop ? 0.4375F : 0.0625F;
                IDrawing.drawTexture(matrices, vertexConsumer, -0.125F, y, -0.19375F, 0.125F, y + 0.25F, -0.19375F, facing.getOpposite(), constantColor, MAX_LIGHT_GLOWING);
            }
        }
