package com.lx.jsblock.blocks;

import com.lx.jsblock.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SignalLightInverted1 extends mtr.block.BlockSignalLightBase {

    public SignalLightInverted1(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TileEntitySignalLightInverted();
    }

    public static class TileEntitySignalLightInverted extends BlockEntity {

        public TileEntitySignalLightInverted() {
            super(Blocks.SIGNAL_LIGHT_INVERTED_ENTITY_1);
        }
    }
}