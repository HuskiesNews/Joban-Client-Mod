package com.jsblock.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/* This mixin opens up our own Ticket Screen (copied from MTR Mod), and cancel the code that open MTR's Ticket Screen */
@Mixin(mtr.packet.PacketTrainDataGuiClient.class)
public class TicketMachineMixin {
    @Inject(method = "openTicketMachineScreenS2C", at = @At(value = "HEAD"), cancellable = true)
    private static void mixin(MinecraftClient minecraftClient, PacketByteBuf packet, CallbackInfo ci) {
        final int balance = packet.readInt();
        minecraftClient.execute(() -> {
            if (!(minecraftClient.currentScreen instanceof com.jsblock.gui.TicketMachineScreen)) {
                minecraftClient.openScreen(new com.jsblock.gui.TicketMachineScreen(balance));
            }
        });
        ci.cancel();
    }
}
