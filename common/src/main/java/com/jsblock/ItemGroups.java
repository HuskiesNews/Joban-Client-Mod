package com.jsblock;

import mtr.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public interface ItemGroups {
	CreativeModeTab MAIN = Registry.getItemGroup(new ResourceLocation(Joestu.MOD_ID, "core"), () -> new ItemStack(Blocks.HELPLINE_3));
	CreativeModeTab PIDS = Registry.getItemGroup(new ResourceLocation(Joestu.MOD_ID, "core"), () -> new ItemStack(Blocks.PIDS_RV));
}
