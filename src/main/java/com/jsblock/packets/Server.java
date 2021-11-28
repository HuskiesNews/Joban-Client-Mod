package com.jsblock.packets;

import net.minecraft.inventory.Inventories;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class Server {
    public static void receiveTicketsC2S(MinecraftServer minecraftServer, ServerPlayerEntity player, PacketByteBuf packet) {
        final int type = packet.readInt();
        final int emeralds = packet.readInt();

        minecraftServer.execute(() -> {
            final World world = player.world;
            // TODO: Merge code from server tickets mod
            Inventories.remove(player.inventory, itemStack -> itemStack.getItem() == Items.EMERALD, emeralds, false);
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1, 1);
        });
    }
}
