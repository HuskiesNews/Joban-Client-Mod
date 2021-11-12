package com.lx.jsblock;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface ItemGroups {
        ItemGroup MAIN = FabricItemGroupBuilder.build(new Identifier(Joestu.MOD_ID, "core"), () -> new ItemStack(Blocks.HELPLINE_3));
}