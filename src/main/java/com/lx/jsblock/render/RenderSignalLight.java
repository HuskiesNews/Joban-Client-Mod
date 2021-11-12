package com.lx.jsblock.render;

import mtr.gui.IDrawing;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;

public class RenderSignalLight<T extends BlockEntity> extends mtr.render.RenderSignalBase<T> {

        final int proceedColor;
        final int constantColor;
        final boolean inverted;
        final int colorRed = 0xFFFF0000;
        final boolean redOnTop;

        public RenderSignalLight(BlockEntityRenderDispatcher dispatcher, boolean isSingleSided, int constantColor, boolean inverted, boolean redOnTop, int proceedColor) {
            super(dispatcher, isSingleSided);
            this.proceedColor = proceedColor;
            this.constantColor = constantColor;
            this.redOnTop = redOnTop;
            this.inverted = inverted;
        }

        @Override
        protected void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, VertexConsumer vertexConsumer, T entity, float tickDelta, Direction facing, boolean isOccupied, boolean isBackSide) {
                if(inverted) {
                    float y = isOccupied == redOnTop ? 0.4375F : 0.0625F;
                    IDrawing.drawTexture(matrices, vertexConsumer, -0.125F, y, -0.19375F, 0.125F, y + 0.25F, -0.19375F, facing.getOpposite(), isOccupied ? proceedColor : colorRed, MAX_LIGHT_GLOWING);
                } else {
                    /* Repurposed to if the corresponding light is on top in this block */
                    float y = redOnTop ? 0.4375F : 0.0625F;
                    IDrawing.drawTexture(matrices, vertexConsumer, -0.125F, y, -0.19375F, 0.125F, y + 0.25F, -0.19375F, facing.getOpposite(), constantColor, MAX_LIGHT_GLOWING);
                }
            }
        }
