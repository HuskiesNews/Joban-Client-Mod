package com.jsblock.mixin;

import mtr.mappings.UtilitiesClient;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/* This mixin inject MTR Mod's Open Ticket Screen Function/Method, and brings up our own Ticket Screen instead (based on MTR Mod's) */
@Mixin(mtr.packet.PacketTrainDataGuiClient.class)
public class TicketMachineMixin {
	/* Inject this to the head of openTicketMachineScreenS2C, that way our code will be run first before the MTR Mod does */
	@Inject(method = "openTicketMachineScreenS2C", at = @At(value = "HEAD"), cancellable = true)
	private static void mixin(Minecraft minecraftClient, FriendlyByteBuf packet, CallbackInfo ci) {
		final int balance = packet.readInt();
		minecraftClient.execute(() -> {
			if (!(minecraftClient.screen instanceof com.jsblock.gui.TicketMachineScreen)) {
				UtilitiesClient.setScreen(minecraftClient, new com.jsblock.gui.TicketMachineScreen(balance));
			}
		});
		/* Because our code is injected before the MTR Mod's, we can cancel running further code to prevent MTR Mod from bringing up the default screen */
		ci.cancel();
	}
}
