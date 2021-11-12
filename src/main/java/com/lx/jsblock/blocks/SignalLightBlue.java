package com.lx.jsblock.blocks;

import com.lx.jsblock.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class SignalLightBlue extends mtr.block.BlockSignalLightBase {

    public SignalLightBlue(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TileEntitySignalLightBlue();
    }

    public static class TileEntitySignalLightBlue extends BlockEntity {

        public TileEntitySignalLightBlue() {
            super(Blocks.SIGNAL_LIGHT_BLUE_ENTITY_1);
        }
    }
}