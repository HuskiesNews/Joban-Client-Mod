package com.jsblock;

import mtr.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public interface ItemGroups {
	/* Tab for creative inventory */
	CreativeModeTab MAIN = Registry.getItemGroup(new ResourceLocation(Joban.MOD_ID, "core"), () -> new ItemStack(Blocks.HELPLINE_3));
	CreativeModeTab PIDS = Registry.getItemGroup(new ResourceLocation(Joban.MOD_ID, "pids"), () -> new ItemStack(Blocks.PIDS_RV_TCL));
}
