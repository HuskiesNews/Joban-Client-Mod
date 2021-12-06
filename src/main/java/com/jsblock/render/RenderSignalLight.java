package com.jsblock.render;

import mtr.gui.IDrawing;
import mtr.render.RenderSignalBase;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;

public class RenderSignalLight<T extends BlockEntity> extends RenderSignalBase<T> {

        final int proceedColor;
        final boolean inverted;
        final boolean redOnTop;

        public RenderSignalLight(BlockEntityRenderDispatcher dispatcher, boolean isSingleSided, boolean inverted, boolean redOnTop, int proceedColor) {
            super(dispatcher, isSingleSided);
            this.proceedColor = proceedColor;
            this.redOnTop = redOnTop;
            this.inverted = inverted;
        }

        @Override
        protected void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, VertexConsumer vertexConsumer, T entity, float tickDelta, Direction facing, boolean isOccupied, boolean isBackSide) {
            float y = isOccupied == redOnTop ? 0.4375F : 0.0625F;
            if(inverted) {
                IDrawing.drawTexture(matrices, vertexConsumer, -0.125F, y, -0.19375F, 0.125F, y + 0.25F, -0.19375F, facing.getOpposite(), isOccupied ? proceedColor : 0xFFFF0000, MAX_LIGHT_GLOWING);
            } else {
                IDrawing.drawTexture(matrices, vertexConsumer, -0.125F, y, -0.19375F, 0.125F, y + 0.25F, -0.19375F, facing.getOpposite(), isOccupied ? 0xFFFF0000 : proceedColor, MAX_LIGHT_GLOWING);
            }
        }
}
